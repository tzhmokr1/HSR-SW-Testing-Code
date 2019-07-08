package ch.hsr.testing.unittest.address.dependencyexample;

public class AddressRetrieverException extends Exception {
    public AddressRetrieverException(String message, Exception e) {
        super(message);
    }
}
