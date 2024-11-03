package com.example.logpraser;
import java.util.Map;

import java.util.HashMap;

public class InternetProtocolMapping {
    private static final Map<Integer, String> protocolMap = new HashMap<>();

    // Currently only handles the below protocol numbers as per: https://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml
    // This can be extended to include more protocols from the spec commented above
    static {
        protocolMap.put(1, "ICMP");
        protocolMap.put(6, "TCP");
        protocolMap.put(17, "UDP");
        protocolMap.put(41, "IPv6");
        protocolMap.put(47, "GRE");
        protocolMap.put(50, "ESP");
        protocolMap.put(51, "AH");
        protocolMap.put(58, "ICMPv6");
        protocolMap.put(89, "OSPF");
        protocolMap.put(132, "SCTP");
    }

    public static String getInternetProtocolName(int protocolNumber) {
        return protocolMap.getOrDefault(protocolNumber, "unknown");
    }

}
