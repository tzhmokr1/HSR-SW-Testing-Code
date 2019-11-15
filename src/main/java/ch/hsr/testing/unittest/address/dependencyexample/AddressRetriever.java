package ch.hsr.testing.unittest.address.dependencyexample;


import ch.hsr.testing.unittest.address.api.Address;
import ch.hsr.testing.unittest.address.dependencyexample.api.AuthenticationInformation;
import ch.hsr.testing.unittest.address.dependencyexample.api.HttpService;
import org.apache.http.ParseException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;

public class AddressRetriever {
    private HttpService httpService;

    public AddressRetriever(HttpService httpService) {
        this.httpService = httpService;
    }

    public Address retrieve(double latitude, double longitude, AuthenticationInformation authenticationInformation) throws AddressRetrieverException {
        String params = String.format("lat=%.6f&lon=%.6f", latitude, longitude);
        JSONObject obj;
        try {
            String response = httpService.get(
                    "http://open.mapquestapi.com/nominatim/v1/reverse?format=json&"
                            + params, authenticationInformation);

            obj = (JSONObject) new JSONParser().parse(response);
        } catch (IOException | org.json.simple.parser.ParseException e) {
            throw new AddressRetrieverException("Received Exception from HttpService", e);
        } catch (ParseException e) {
            throw new AddressRetrieverException("Could not parse JSON from HttpService", e);
        }


        JSONObject address = (JSONObject) obj.get("address");
        String country = (String) address.get("country_code");
        if (!country.equals("us"))
            throw new UnsupportedOperationException(
                    "cannot support non-US addresses at this time");

        String houseNumber = (String) address.get("house_number");
        String road = (String) address.get("road");
        String city = (String) address.get("city");
        String state = (String) address.get("state");
        String zip = (String) address.get("postcode");
        return new Address(houseNumber, road, city, state, zip);
    }
}