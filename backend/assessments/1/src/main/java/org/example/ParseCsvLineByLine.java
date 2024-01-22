package org.example;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * For parsing csv file line by line
 */
public class ParseCsvLineByLine {
    public static void main(String[] args) throws Exception {
        URL fileUrl = ParseCsvLineByLine.class.getClassLoader().getResources("data.csv").nextElement();

        CSVParser csvParser = new CSVParserBuilder()
                .withSeparator(',')
                .withIgnoreQuotations(true)
                .build();

        CSVReader csvReader = new CSVReaderBuilder(new FileReader(fileUrl.getFile()))
                    .withSkipLines(1)
                    .withCSVParser(csvParser)
                    .build();


        String[] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            if (nextLine != null) {
               String name = nextLine[0];
               String team = nextLine[1];
               String role = nextLine[2];
               int matchPlayed = Integer.parseInt(nextLine[3]);
               int runs = Integer.parseInt(nextLine[4]);
               float average = Float.parseFloat(nextLine[5]);
               int strikeRate = Integer.parseInt(nextLine[6]);
               int wicketsTaken =Integer.parseInt(nextLine[7]);
            }
            else{
                Logging.getmsg().warn("Next Line is Null ! Nothing to read");
            }
        }

        Player newPlayer = new Player(name,team,role,matchPlayed,runs,average,strikeRate,wicketsTaken);

    }
}