package ch.hsr.testing.unittest.mocking;

import ch.hsr.testing.unittest.testbuilderpattern.Address;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AddressRetrieverTest {

    private static final double DONT_CARE = 1.0;
    private static final String validAnswer = "{\"osm_type\":\"way\",\"osm_id\":\"22423623\",\"licence\":\"Data © OpenStreetMap contributors, ODbL 1.0. https:\\/\\/osm.org\\/copyright\",\"boundingbox\":[\"47.4997479\",\"47.5007981\",\"8.723437\",\"8.724456\"],\"address\":{\"country\":\"Schweiz\\/Suisse\\/Svizzera\\/Svizra\",\"country_code\":\"ch\",\"road\":\"Bahnhofplatz\",\"city\":\"Winterthur\",\"neighbourhood\":\"Neuwiesen\",\"county\":\"Bezirk Winterthur\",\"postcode\":\"8400\",\"suburb\":\"Altstadt\",\"house_number\":\"7;9\",\"state\":\"Zürich\"},\"lon\":\"8.72393173342697\",\"display_name\":\"7;9, Bahnhofplatz, Neuwiesen, Altstadt, Winterthur, Bezirk Winterthur, Zürich, 8400, Schweiz\\/Suisse\\/Svizzera\\/Svizra\",\"place_id\":\"75486453\",\"lat\":\"47.500274\"}";
    private static final String invalidJson = "{no valid JSON}}}";
    private static final String unexpectedAnswer = "{\"foo\":\"bar\"}";

    private AddressRetriever addressRetriever;


    @Test
    public void returnsAddressOnValidJSON() throws AddressRetrieverException, IOException {
        HttpService mock = mock(HttpService.class);
        when(mock.get(any())).thenReturn(validAnswer);

        addressRetriever = new AddressRetriever("FZWYaXm5WUA7TNcUzAflX1wlqKWMcrep", mock);
        Address address = addressRetriever.retrieve(47.4999158, 8.7235591);

        Assertions.assertEquals("Winterthur", address.getCity());
    }

    @Test
    public void returnsExceptionOnInvalidJSON() throws AddressRetrieverException, IOException {
        HttpService mock = mock(HttpService.class);
        when(mock.get(any())).thenReturn(invalidJson);
        addressRetriever = new AddressRetriever("FZWYaXm5WUA7TNcUzAflX1wlqKWMcrep", mock);
        Throwable t = null;

        try {
            addressRetriever.retrieve(DONT_CARE, DONT_CARE);
        } catch (AddressRetrieverException e) {
            t = e;
        }

        org.assertj.core.api.Assertions.assertThat(t).isNotNull();
        org.assertj.core.api.Assertions.assertThat(t.getLocalizedMessage()).contains("Could not parse");
    }


    @Test
    public void returnsExceptionOnUnexpectedAnswer() throws AddressRetrieverException, IOException {
        HttpService mock = mock(HttpService.class);
        when(mock.get(any())).thenReturn(unexpectedAnswer);
        addressRetriever = new AddressRetriever("FZWYaXm5WUA7TNcUzAflX1wlqKWMcrep", mock);
        Throwable t = null;

        try {
            addressRetriever.retrieve(DONT_CARE, DONT_CARE);
        } catch (AddressRetrieverException e) {
            t = e;
        }

        org.assertj.core.api.Assertions.assertThat(t).isNotNull();
        org.assertj.core.api.Assertions.assertThat(t.getLocalizedMessage()).contains("Could not parse");
    }


    @Test
    public void returnsExceptionOnHttpException() throws AddressRetrieverException, IOException {
        HttpService mock = mock(HttpService.class);
        when(mock.get(any())).thenThrow(new IOException("just for fun"));
        addressRetriever = new AddressRetriever("FZWYaXm5WUA7TNcUzAflX1wlqKWMcrep", mock);
        Throwable t = null;

        try {
            addressRetriever.retrieve(DONT_CARE, DONT_CARE);
        } catch (AddressRetrieverException e) {
            t = e;
        }

        org.assertj.core.api.Assertions.assertThat(t).isNotNull();
        org.assertj.core.api.Assertions.assertThat(t.getLocalizedMessage()).contains("Exception from HttpService");
    }


}