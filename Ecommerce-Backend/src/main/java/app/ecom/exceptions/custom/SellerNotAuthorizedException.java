package app.ecom.exceptions.custom;

public class SellerNotAuthorizedException extends RuntimeException {
    public SellerNotAuthorizedException(String message) {
        super(message);
    }
}
