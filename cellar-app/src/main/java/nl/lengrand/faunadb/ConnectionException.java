package nl.lengrand.faunadb;

public class ConnectionException extends Exception {
    public ConnectionException(String errorMessage) {
        super(errorMessage);
    }
}
