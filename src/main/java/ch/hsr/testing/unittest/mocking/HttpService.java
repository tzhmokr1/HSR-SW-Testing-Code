package ch.hsr.testing.unittest.mocking;

import java.io.IOException;

public interface HttpService {
    String get(String url) throws IOException;
}
