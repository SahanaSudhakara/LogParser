package com.example.logpraser;

// Class to hold Individual lines from a logfile, and obtain Port and Protocol
public class LogFileEntry {
    private String[] logParts;

    public LogFileEntry(String logLine) throws UnsupportedLogVersionException {
        this.logParts = logLine.split(" ");
        int version = Integer.parseInt(logParts[0]);
        if (version != 2) {
            throw new UnsupportedLogVersionException("Unsupported log version: "+version);
        }
    }

    public int getDstPort() {
        return Integer.parseInt(logParts[6]);
    }

    public String getProtocol() {
        int protocolNumber = Integer.parseInt(logParts[7]);
        return InternetProtocolMapping.getInternetProtocolName(protocolNumber);
    }
}
