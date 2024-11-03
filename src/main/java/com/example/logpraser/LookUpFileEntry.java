package com.example.logpraser;

// Class to represent object created from the Lookup Table
public class LookUpFileEntry {
    private int dstPort;
    private String Protocol;
    private String tag;

    public LookUpFileEntry(int dstPort, String protocol, String tag) {
        this.dstPort = dstPort;
        this.Protocol = protocol;
        this.tag = tag;
    }

    public int getDstPort() {
        return dstPort;
    }

    public String getProtocol() {
        return Protocol;
    }

    public String getTag() {
        return tag;
    }
}
