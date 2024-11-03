package com.example.logpraser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogFileProcessor {
    private LookUpTable lookUpTable;
    private Map<String, Integer> tagCount;
    private Map<String, Integer> portProtocolCount;
    private String flowLogFilePath;
    private int untaggedCount;

    public LogFileProcessor(String flowLogFilePath, LookUpTable lookUpTable) throws IOException {
        this.lookUpTable = lookUpTable;
        this.tagCount = new HashMap<>();
        this.portProtocolCount = new HashMap<>();
        this.untaggedCount = 0;
        this.flowLogFilePath = flowLogFilePath;
    }

    public void processLogs() {
        try (BufferedReader reader = new BufferedReader(new FileReader(flowLogFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    LogFileEntry logFileEntry = new LogFileEntry(line);
                    String tag = lookUpTable.findTag(logFileEntry.getDstPort(), logFileEntry.getProtocol());

                    if (tag != null) {
                        tag = tag.toLowerCase();
                        tagCount.put(tag, tagCount.getOrDefault(tag, 0) + 1);
                    } else {
                        untaggedCount++;
                    }

                    String portProtocolKey = logFileEntry.getDstPort() + "," + logFileEntry.getProtocol().toLowerCase();
                    portProtocolCount.put(portProtocolKey, portProtocolCount.getOrDefault(portProtocolKey, 0) + 1);
                } catch (UnsupportedLogVersionException e) {
                    System.err.println("Skipping log entry due to unsupported version: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getTagCount() {
        return tagCount;
    }

    public Map<String, Integer> getPortProtocolCount() {
        return portProtocolCount;
    }

    public  int getUntaggedCount() {
        return untaggedCount;
    }
}
