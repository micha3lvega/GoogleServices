package co.com.soft.tecnology.googleservices;

public class GoogleApiException extends Exception {

  private static final long serialVersionUID = 1L;
  private final String message;

  public GoogleApiException(String message) {
    this.message = message;
  }

  @Override
  public String getLocalizedMessage() {
    return this.message;
  }

  @Override
  public String getMessage() {
    return this.message;
  }


}
