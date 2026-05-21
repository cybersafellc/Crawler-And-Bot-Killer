package org.antibot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.AsnResponse;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.model.CountryResponse;
import org.antibot.Model.Connections;
import org.antibot.Response.ObjAsClassfication;
import org.antibot.Response.LookupAllResult;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;

public class BotKiller {
    private final File dbAsn;
    private final File dbCity;
    private final File dbCountry;
    private final Connections database;

    public BotKiller() throws SQLException {
        this.dbAsn = new File("geolitedb/GeoLite2-ASN.mmdb");
        this.dbCity = new File("geolitedb/GeoLite2-City.mmdb");
        this.dbCountry = new File("geolitedb/GeoLite2-Country.mmdb");
        this.database = new Connections();
    }

    public LookupAllResult lookupAll(String net){
        LookupAllResult allResult = new LookupAllResult();
        allResult.setAsnResponse(asnLookup(net));
        allResult.setCityResponse(cityLookup(net));
        allResult.setCountryResponse(countryLookup(net));
        allResult.setObjAsClassfication(romChecker(net));
        return allResult;
    }

    public String lookupAllJson(String net) throws JsonProcessingException {
        ObjectMapper maper = new ObjectMapper();
        return maper.writeValueAsString(lookupAll(net));
    }

    public ObjAsClassfication romChecker(String net){
        AsnResponse asnResponse = getAsnResponse(net);
        return database.asClassifiaction.getByAsNumber("AS"+asnResponse.getAutonomousSystemNumber());
    }

    public String romCheckerJson(String net){
        AsnResponse asnResponse = getAsnResponse(net);
        ObjAsClassfication asClassifiaction = database.asClassifiaction.getByAsNumber("AS" + asnResponse.getAutonomousSystemNumber());
        try{
            return objAsClassificationObjToJson(asClassifiaction);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AsnResponse asnLookup(String net) {
        return getAsnResponse(net);
    }

    public String asnLookupJson(String net) throws IOException {
        return getAsnResponse(net).toJson();
    }

    public CityResponse cityLookup(String net){
        return getCityResponse(net);
    }

    public String cityLookupJson(String net) throws IOException {
        return getCityResponse(net).toJson();
    }

    public CountryResponse countryLookup(String net){
        return getCountryResponse(net);
    }

    public String countryLookupJson(String net) throws IOException {
        return getCountryResponse(net).toJson();
    }

    // internal use
    private AsnResponse getAsnResponse(String net){
        try(DatabaseReader reader = new DatabaseReader.Builder(dbAsn).build()){
            InetAddress inet = InetAddress.getByName(net);
            return reader.asn(inet);
        } catch (IOException e){
            throw new RuntimeException(e);
        } catch (GeoIp2Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CityResponse getCityResponse(String net){
        try(DatabaseReader reader = new DatabaseReader.Builder(dbCity).build()){
            InetAddress inet = InetAddress.getByName(net);
            return reader.city(inet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (GeoIp2Exception e){
            throw new RuntimeException(e);
        }
    }

    private CountryResponse getCountryResponse(String net){
        try(DatabaseReader reader = new DatabaseReader.Builder(dbCountry).build()){
            InetAddress inet = InetAddress.getByName(net);
            return reader.country(inet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (GeoIp2Exception e){
            throw new RuntimeException(e);
        }
    }

    private String objAsClassificationObjToJson(ObjAsClassfication object) throws JsonProcessingException{
        ObjectMapper maper = new ObjectMapper();
        return maper.writeValueAsString(object);
    }
}
