package co.com.soft.tecnology.googleservices.qbo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Michael Vega
 */
public class NetClient {


  private String uri;
  private int timeout;
  private String method;
  private String accept;
  private Object entity;
  private int readTimeout;

  private Map<String, String> parameters;
  private Map<String, String> headerParameters;
  private Map<String, String> replaceParameters;
  private Map<String, String> requestProperties;

  private void init() {

    this.parameters = new HashMap<>();
    this.headerParameters = new HashMap<>();
    this.replaceParameters = new HashMap<>();
    this.requestProperties = new HashMap<>();

    if (this.method != null) {
      this.method = this.method.toUpperCase();
    }

    if (this.uri != null) {
      this.uri = this.uri.toLowerCase();
    }

  }

  public NetClient(String uri, String method) {

    this.uri = uri;
    this.method = method != null ? method.toUpperCase() : "GET";
    this.accept = "application/json";

    this.init();

  }

  public NetClient(String uri) {

    this.method = "GET";
    this.accept = "application/json";
    this.uri = uri;

    this.init();
  }

  public String consumeService() throws IOException {

    long transactionID = System.currentTimeMillis();

    StringBuilder builderUrl = new StringBuilder();
    builderUrl.append(this.uri);

    StringBuilder builderParameters = new StringBuilder();

    this.addParameters(builderUrl, builderParameters);

    if ((this.method != null) && this.method.equals("GET") && (this.parameters != null)
        && !this.parameters.isEmpty()) {
      builderUrl.append(builderParameters.toString());
    }

    URL url = new URL(builderUrl.toString());
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setRequestMethod(this.method);
    conn.setDoOutput(true);

    if (this.timeout > 0) {
      conn.setConnectTimeout(this.timeout);
    }

    if (this.readTimeout > 0) {
      conn.setReadTimeout(this.readTimeout);
    }

    // Headers
    this.addHeaders(transactionID, conn);

    // Send post request
    this.sendPostRequest(transactionID, builderParameters, conn);

    System.out.println("builderUrl: " + builderUrl);

    // Send json object
    if (this.entity != null) {

      conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
      conn.setRequestMethod(this.method);
      conn.setDoInput(true);
      conn.setDoOutput(true);

      ObjectMapper mapper = new ObjectMapper();

      String json = mapper.writeValueAsString(this.entity);

      conn.setFixedLengthStreamingMode(json.getBytes().length);
      try (OutputStream os = conn.getOutputStream()) {
        os.write(json.getBytes(java.nio.charset.StandardCharsets.UTF_8));
      }
    }

    // Validate reponse
    if ((conn.getResponseCode() != 200) && (conn.getResponseCode() != 201)) {
      throw new RuntimeException(
          "[NetClientException] " + conn.getResponseCode() + " " + conn.getResponseMessage());
    }

    InputStreamReader in = new InputStreamReader(conn.getInputStream());
    BufferedReader br = new BufferedReader(in);
    String output;
    StringBuilder sb = new StringBuilder();

    // Read response
    while ((output = br.readLine()) != null) {

      for (Map.Entry<String, String> entry : this.replaceParameters.entrySet()) {
        if ((output != null) && output.contains(entry.getKey())) {
          output = output.replace(entry.getKey(), entry.getValue());
        }
      }

      sb.append(output);
    }

    in.close();

    conn.disconnect();
    return sb.toString();

  }

  private void sendPostRequest(long transactionID, StringBuilder builderParameters,
      HttpURLConnection conn) throws IOException {
    if ((this.method != null) && this.method.equals("POST") && (this.parameters != null)
        && !this.parameters.isEmpty()) {
      try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
        wr.writeBytes(builderParameters.toString());
        wr.flush();
      }
    }
  }

  private void addHeaders(long transactionID, HttpURLConnection conn) {
    if ((this.headerParameters != null) && !this.headerParameters.isEmpty()) {
      for (Map.Entry<String, String> entry : this.headerParameters.entrySet()) {
        conn.setRequestProperty(entry.getKey(), entry.getValue());
      }
    }
  }

  private void addParameters(StringBuilder builderUrl, StringBuilder builderParameters)
      throws UnsupportedEncodingException {
    if ((this.parameters != null) && !this.parameters.isEmpty()) {

      builderUrl.append("?");

      int size = this.parameters.size();
      int counter = 0;

      for (Map.Entry<String, String> entry : this.parameters.entrySet()) {

        builderParameters.append(entry.getKey());
        builderParameters.append("=");
        builderParameters.append(URLEncoder.encode(entry.getValue(),
            java.nio.charset.StandardCharsets.UTF_8.displayName()));

        if (counter < (size - 1)) {
          builderParameters.append("&");
        }

        counter++;
      }

    }
  }

  public void addParameters(String key, String value) {
    this.parameters.put(key, value);
  }

  public void addHeaderParameters(String key, String value) {
    this.headerParameters.put(key, value);
  }

  public void addReplaceParameters(String key, String value) {
    this.replaceParameters.put(key, value);
  }

  public void addRequestProperties(String key, String value) {
    this.requestProperties.put(key, value);
  }

  public String getUri() {
    return this.uri;
  }

  public String getMethod() {
    return this.method;
  }

  public String getAccept() {
    return this.accept;
  }

  public Map<String, String> getParameters() {
    return this.parameters;
  }

  public Map<String, String> getHeaderParameters() {
    return this.headerParameters;
  }

  public Map<String, String> getReplaceParameters() {
    return this.replaceParameters;
  }

  public Map<String, String> getRequestProperties() {
    return this.requestProperties;
  }

  public void setEntity(Object entity) {
    this.entity = entity;
  }

  public int getTimeout() {
    return this.timeout;
  }

  public void setTimeout(int timeout) {
    this.timeout = timeout;
  }

  public int getReadTimeout() {
    return this.readTimeout;
  }

  public void setReadTimeout(int readTimeout) {
    this.readTimeout = readTimeout;
  }

  @Override
  public String toString() {
    return "NetClient{" + "uri=" + this.uri + ", method=" + this.method + ", accept=" + this.accept
        + ", entity=" + this.entity + ", timeout=" + this.timeout + ", readTimeout="
        + this.readTimeout + ", parameters=" + this.parameters + ", headerParameters="
        + this.headerParameters + ", replaceParameters=" + this.replaceParameters
        + ", requestProperties=" + this.requestProperties + '}';
  }

}
