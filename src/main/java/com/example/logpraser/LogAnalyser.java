package com.example.logpraser;
import java.io.*;


public class LogAnalyser {
    public static void main(String[] args) {
        String lookupFilePath= "";
        String flowLogFilePath = "";
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            
            // Read Input files from Command Line
            System.out.print("Enter the lookup file path: ");
            lookupFilePath= reader.readLine();

            System.out.print("Enter the flow log file path: ");
             flowLogFilePath = reader.readLine();
        }
            catch (IOException e) {
                System.err.println("An error occurred while reading input. Please try again.");
                e.printStackTrace();
            } catch (Exception e) {
                System.err.println("An unexpected error occurred.");
                e.printStackTrace();
            }

            /* Code Written to hardcode the filepath in the Resources directory instead of passing in as filepaths as variables.
            If you want to use these files, please comment lines 9-24.
           String lookupFilePath = getResourceFilePath("lookuptable.txt");
            String flowLogFilePath = getResourceFilePath("logs.txt");
            */

        try {
            // Default: Write output to output.txt in the root folder.
            String outputFileName = "output.txt";
            File outPutFile = new File(outputFileName);
            if (outPutFile.exists()) {
                System.out.println("File already exists: " + outPutFile.getAbsolutePath());
            } else {
                outPutFile.createNewFile();
                System.out.println("Output file created: " + outPutFile.getAbsolutePath());

            }

            LookUpTable lookUpTable = new LookUpTable(lookupFilePath);
            LogFileProcessor logFileProcessor = new LogFileProcessor(flowLogFilePath, lookUpTable);
            logFileProcessor.processLogs();

            ResultFileWriter.flushResultToFile(outPutFile.getAbsolutePath(),
                    logFileProcessor.getTagCount(),
                    logFileProcessor.getPortProtocolCount(),
                    logFileProcessor.getUntaggedCount());

            System.out.println("Processing complete. Results saved to " + outPutFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error: Unable to create output file. Please check file permissions or disk space.");
            e.printStackTrace();
        }
        }
    }
