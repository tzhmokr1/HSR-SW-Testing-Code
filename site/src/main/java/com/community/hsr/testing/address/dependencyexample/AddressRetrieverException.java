package com.community.hsr.testing.address.dependencyexample;

import java.io.IOException;

public class AddressRetrieverException extends Exception {
    public AddressRetrieverException(String message, Exception e) {
        super(message);
    }
}
