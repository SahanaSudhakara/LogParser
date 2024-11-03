# LogParse

This Java application parses flow log data and assigns tags to each row based on a lookup table. It processes flow logs, maps each entry to a specified tag (from a lookup table CSV), and provides statistics on matches and unmatched records, saving the results to a specified output file.

## Features

- **Tagging Flow Logs:** Maps each flow log entry to a tag based on destination port and protocol using a lookup table.
- **Case-Insensitive Matching:** Ensures protocol matching is case-insensitive for flexibility.
- **Statistics:**
  - Count of matches for each tag.
  - Count of matches for each port/protocol combination.
  - Count of untagged flow logs.
- **Supports Version 2 Logs Only:** Throws an exception for unsupported log versions.

## Requirements

- Java 8 or higher
- Maven or Gradle (optional for dependency management)

## Setup and Running the Program

1. **Clone the Repository:**
    ```bash
    git clone git@github.com:SahanaSudhakara/LogParser.git
    cd LogParser
    ```

2. **Compile the Program:**
    ```bash
    javac -d ./target/classes src/main/java/com/example/logparser/*.java
    ```

3. **Run the Program:**
    ```bash
    java -cp ./target/classes com.example.logparser.LogAnalyser
    ```
    
## Usage

The program requires two file paths via user provied scanner input:

- **lookupFilePath:** Path to a CSV file with destination port, protocol, and tag mappings (e.g., `dstport,protocol,tag`).
- **flowLogFilePath:** Path to a file containing flow log entries in the following format:
    ```
    version account-id eni-id srcaddr dstaddr srcport dstport protocol packets bytes start end action log-status
    ```
### Example Command
```bash
java -cp ./target/classes com.example.logparser.LogAnalyser
```
lookup.csv

![Look Up](https://github.com/SahanaSudhakara/LogParser/blob/main/OutputImages/Sample_lookUp.png)

flowlogs  sample
![Flow log](https://github.com/SahanaSudhakara/LogParser/blob/main/OutputImages/Sample_logs.png)

## Output

The program will generate statistics and save them to the output file, that is stored in a file called `output.txt` in the root directory of this project:

- **Tag Counts:** Displays counts for each tag based on matches from the lookup table.
- **Port-Protocol Counts:** Displays counts of each unique port/protocol combination.
- **Untagged Count:** Count of entries with no matching tag.

## Error Handling

- **Unsupported Log Version:** If the log version is not 2, an `UnsupportedLogVersionException` is thrown.
- **File Not Found:** Throws an `IOException` if the lookup table, flow log file, or output file cannot be accessed or created.

## Assumptions
- **Output File:** The program by default creates an `output.txt` file with the results and stores it in the root directory of the `LogParser` project.
- **Log Version:** The program only handles logs of version 2 as described in this Amazon [document](https://docs.aws.amazon.com/vpc/latest/userguide/flow-log-records.html). The program skips any records that are not of version 2, and prints an unsupportedlogversion exception message.
- **Log Entry Fields:** The program only considers fields: dstport, protocol and version from the logs while creating a Log Entry object. The rest of the fields are ignored, on the basis of the requirement of this exercise.
- **Port-Protocol Count:** The port protocol count generated is based on the combination of dstport and protocol, and does not take the srcport into consideration.
- **Sample Example:** The sample logs provided in the example above is a smaller subset generated from the logs provided in the problem statement.
- **Protocol Mapping:** The protocol mapping for this exercise is based on the standards as described [here](https://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml). Additional documentation is added to the InternetProtocolMapping class.

