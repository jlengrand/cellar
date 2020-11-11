package nl.lengrand.cellar.faunadb;

public class ConnectionException extends Exception {
    public ConnectionException(String errorMessage) {
        super(errorMessage);
    }
}
