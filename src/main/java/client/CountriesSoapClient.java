package client;

import client.websamples.ArrayOftCountryCodeAndName;
import client.websamples.CountryInfoService;
import client.websamples.CountryInfoServiceSoapType;

import javax.xml.ws.BindingProvider;
import java.net.MalformedURLException;
import java.net.URL;

public class CountriesSoapClient {

    public static void main(String[] args) {
        CountriesSoapClient countriesSoapClient = new CountriesSoapClient();
        countriesSoapClient.callCountries();
    }

    public void callCountries() {
        CountryInfoServiceSoapType service = _getService();

        ArrayOftCountryCodeAndName arrayOftCountryCodeAndName = service.listOfCountryNamesByName();

        arrayOftCountryCodeAndName.getTCountryCodeAndName().stream().forEach(tCountryCodeAndName -> System.out.println(tCountryCodeAndName.getSName()));
    }

    private CountryInfoServiceSoapType _getService() {

        URL wsdlLocation = null;

        String wsdl = "http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso?WSDL";
        try {
            wsdlLocation = new URL(wsdl);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        CountryInfoService infoService = new CountryInfoService(wsdlLocation);
        CountryInfoServiceSoapType countryInfoServiceSoap = infoService.getCountryInfoServiceSoap();

        ((BindingProvider)countryInfoServiceSoap).
                getRequestContext(
                ).put(
                        BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                        wsdl
                );

        return countryInfoServiceSoap;
    }
}
