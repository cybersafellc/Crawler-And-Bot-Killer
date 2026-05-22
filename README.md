# How to use

## example: Lookup all ip/net

```java
import org.antibot.BotKiller;
import org.antibot.Interface.ConfigSetup;
import org.antibot.Response.LookupAllResult;

BotKiller botKiller = new BotKiller(new ConfigSetup() {
    private final String mysqlUsername = System.getenv("USERMYSQL");
    private final String mysqlPassword = Sysetm.getenv("PASSMYSQL");
    private final String mysqlHost = System.getenv("HOSTMYSQL");

    private final String asnPath = "geolitedb/GeoLite2-ASN.mmdb";
    private final String cityPath = "geolitedb/GeoLite2-City.mmdb";
    private final String countryPath = "geolitedb/GeoLite2-Country.mmdb";

    @Override
    public String getMysqlUsername() {
        return mysqlUsername;
    }

    @Override
    public String getMysqlPassword() {
        return mysqlPassword;
    }

    @Override
    public String getMysqlHost() {
        return mysqlHost;
    }

    @Override
    public String getAsnPath() {
        return asnPath;
    }

    @Override
    public String getCityPath() {
        return cityPath;
    }

    @Override
    public String getCountryPath() {
        return countryPath;
    }
});

LookupAllResult result = botKiller.lookupAll("8.8.8.8");
```