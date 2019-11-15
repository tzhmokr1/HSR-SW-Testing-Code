/***
 * Excerpted from "Pragmatic Unit Testing in Java with JUnit",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/utj2 for more book information.
 ***/
package ch.hsr.testing.unittest.address.dependencyexample;

import ch.hsr.testing.unittest.address.api.Address;
import ch.hsr.testing.unittest.address.dependencyexample.api.AuthenticationInformation;
import ch.hsr.testing.unittest.address.dependencyexample.api.HttpService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressRetrieverTest {
    private HttpService httpServiceMock;
    private AddressRetriever retriever;

    private String correctAddressJSON = "{\"address\":{"
            + "\"house_number\":\"324\","
            + "\"road\":\"North Tejon Street\","
            + "\"city\":\"Colorado Springs\","
            + "\"state\":\"Colorado\","
            + "\"postcode\":\"80903\","
            + "\"country_code\":\"us\"}"
            + "}";

    private String missingHouseNumberJSON = "{\"address\":{"
            + "\"road\":\"North Tejon Street\","
            + "\"city\":\"Colorado Springs\","
            + "\"state\":\"Colorado\","
            + "\"postcode\":\"80903\","
            + "\"country_code\":\"us\"}"
            + "}";


    private String emptyAddressJSON = "{\"address\":{}";

    @BeforeEach
    public void createRetriever() throws IOException {
        httpServiceMock = mock(HttpService.class);
        retriever = new AddressRetriever(httpServiceMock);

    }

    @Test
    public void answersAppropriateAddressForValidCoordinates()
            throws AddressRetrieverException, IOException {
        when(httpServiceMock.get(contains("lat=38.000000&lon=-104.000000"), any()))
                .thenReturn(correctAddressJSON);

        Address address = retriever.retrieve(38.0, -104.0, mock(AuthenticationInformation.class));

        Address expectedAddress = new Address("324", "North Tejon Street", "Colorado Springs", "Colorado", "80903");
        assertThat(address, is(equalTo(expectedAddress)));
    }

    @Test
    public void canHandleEmptyFieldsMissingHouseNumber()
            throws AddressRetrieverException, IOException {
        when(httpServiceMock.get(contains("lat=39.000000&lon=-104.000000"), any()))
                .thenReturn(missingHouseNumberJSON);

        Address address = retriever.retrieve(39.0, -104.0, mock(AuthenticationInformation.class));

        Address expectedAddress = new Address(null, "North Tejon Street", "Colorado Springs", "Colorado", "80903");
        assertThat(address, is(equalTo(expectedAddress)));
    }

    @Test
    public void canHandleExceptionFromHttpService()
            throws AddressRetrieverException, IOException {
        when(httpServiceMock.get(contains("lat=40.000000&lon=-104.000000"), any()))
                .thenThrow(new IOException());


        Assertions.assertThrows(AddressRetrieverException.class,
                () -> retriever.retrieve(40.0, -104.0, mock(AuthenticationInformation.class)));
    }

}
