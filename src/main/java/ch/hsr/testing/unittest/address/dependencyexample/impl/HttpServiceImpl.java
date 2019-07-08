/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
***/
package ch.hsr.testing.unittest.address.dependencyexample.impl;


import ch.hsr.testing.unittest.address.dependencyexample.api.AuthenticationInformation;
import ch.hsr.testing.unittest.address.dependencyexample.api.HttpService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpServiceImpl implements HttpService {

   public String get(String url, AuthenticationInformation authenticationInformation) throws IOException {
      CloseableHttpClient client = HttpClients.createDefault();
      HttpGet request = new HttpGet(url);
      CloseableHttpResponse response = client.execute(request);
      try {
         HttpEntity entity = response.getEntity();
         return EntityUtils.toString(entity);
      } finally {
         response.close();
      }
   }
}
