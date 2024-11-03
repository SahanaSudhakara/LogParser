package com.example.logpraser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class ResultFileWriter {

    public static void flushResultToFile(String filePath, Map<String, Integer> tagCount, Map<String, Integer> protocolCount, int untaggedCount) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Tag Counts:");
            for (Map.Entry<String, Integer> entry : tagCount.entrySet()) {
                writer.printf("%s: %d\n", entry.getKey(), entry.getValue());
            }

            writer.println("\nPort-Protocol Counts:");
            for (Map.Entry<String, Integer> entry : protocolCount.entrySet()) {
                writer.printf("%s: %d%n", entry.getKey(), entry.getValue());
            }
            writer.println("\nUntagged Count:");
            writer.printf("untagged: %d%n", untaggedCount);
        }
    }
}
