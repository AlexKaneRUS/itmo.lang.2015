/**
 * Created by alexkane on 2/27/15.
 */
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

public class OddEvenFileSplitter implements FileSplitter {
    public OddEvenFileSplitter() {
    }

    public void splitFile(SplitConfig config) {
        String sourceFileName = config.getSourceFilePath();
        String oddLinesFileName = config.getOddLinesFilePath();
        String evenLinesFileName = config.getEvenLinesFilePath();

        try {
            FileReader sourceFileReader = new FileReader(sourceFileName);
            LineNumberReader sourceFileBufferedReader = new LineNumberReader(sourceFileReader);
            FileWriter oddLinesFileWriter = new FileWriter(oddLinesFileName);
            BufferedWriter oddLinesFileBufferedWriter = new BufferedWriter(oddLinesFileWriter );
            FileWriter evenLinesFileWriter = new FileWriter(evenLinesFileName);
            BufferedWriter evenLinesFileBufferedWriter = new BufferedWriter(evenLinesFileWriter);

            String line;
            while ((line = sourceFileBufferedReader.readLine()) != null) {
                if (sourceFileBufferedReader.getLineNumber() % 2 == 1) {
                    oddLinesFileBufferedWriter.write(line);
                    oddLinesFileBufferedWriter.newLine();
                } else {
                    evenLinesFileBufferedWriter .write(line);
                    evenLinesFileBufferedWriter .newLine();
                }
            }

            sourceFileBufferedReader.close();
            oddLinesFileBufferedWriter.close();
            evenLinesFileBufferedWriter .close();
        } catch (FileNotFoundException var12) {
            System.out.println("Unable to open file " + sourceFileName);
        } catch (IOException var13) {
            System.out.println("Error reading file " + sourceFileName);
        }

    }

    static class SplitCfg implements SplitConfig {
        public String sourceFile;
        public String oddLinesFile;
        public String evenLinesFile;

        public SplitCfg(String sourceFile, String oddLinesFile, String evenLinesFile) {
            this.sourceFile = sourceFile;
            this.oddLinesFile = oddLinesFile;
            this.evenLinesFile = evenLinesFile;
        }

        public String getSourceFilePath() {
            return this.sourceFile;
        }

        public String getOddLinesFilePath() {
            return this.oddLinesFile;
        }

        public String getEvenLinesFilePath() {
            return this.evenLinesFile;
        }
    }

    public static void main(String[] args) {
        String sourceFile = args[0];
        String oddLinesFile = args[1];
        String evenLinesFile = args[2];
        OddEvenFileSplitter new1 = new OddEvenFileSplitter();
        SplitCfg cfg1 = new SplitCfg(sourceFile, oddLinesFile, evenLinesFile);
        new1.splitFile(cfg1);
    }
}


