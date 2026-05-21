package org.antibot.Response;

import com.maxmind.geoip2.model.AsnResponse;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;

public class LookupAllResult {
    private AsnResponse asnResponse;
    private CityResponse cityResponse;
    private CountryResponse countryResponse;
    private AsClassificationResult asClassificationResult;

    public LookupAllResult(){}

    public void setAsnResponse(AsnResponse asnResponse) {
        this.asnResponse = asnResponse;
    }

    public void setCityResponse(CityResponse cityResponse) {
        this.cityResponse = cityResponse;
    }

    public void setCountryResponse(CountryResponse countryResponse) {
        this.countryResponse = countryResponse;
    }

    public void setObjAsClassfication(AsClassificationResult asClassificationResult) {
        this.asClassificationResult = asClassificationResult;
    }

    public AsnResponse getAsnResponse() {
        return asnResponse;
    }

    public CityResponse getCityResponse() {
        return cityResponse;
    }

    public CountryResponse getCountryResponse() {
        return countryResponse;
    }

    public AsClassificationResult getObjAsClassfication() {
        return asClassificationResult;
    }
}
