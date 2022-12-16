package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

public class GeoIpServiceTests {

    @Test
    public void IpServiceTests() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("188.43.101.130");
        System.out.println(ipLocation);
    }
}