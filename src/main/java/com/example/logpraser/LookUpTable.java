package com.example.logpraser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LookUpTable {
    private Map<String, String> lookupMap = new HashMap<>();

    public LookUpTable(String lookupFilePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(lookupFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int dstPort = Integer.parseInt(parts[0].trim());
                String protocol = parts[1].trim().toLowerCase();
                String tag = parts[2].trim().toLowerCase();  // Normalize tag to lowercase

                String key = dstPort + "," + protocol;
                lookupMap.put(key, tag);
            }
        }
    }

    public String findTag(int dstPort, String protocol) {
        String key = dstPort + "," + protocol.toLowerCase();
        return lookupMap.get(key);
    }
}
