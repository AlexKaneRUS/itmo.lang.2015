import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.*;

public class OddEvenFileSplitter implements FileSplitter {
    public void splitFile(final SplitConfig config) {
        final String fileName1 = config.getSourceFilePath();
        final String fileName2 = config.getOddLinesFilePath();
        final String fileName3 = config.getEvenLinesFilePath();
        String line;
        try {
            final FileReader fileReader = new FileReader(fileName1);
            final LineNumberReader bufferedReader = new LineNumberReader(fileReader);
            final FileWriter fileWriter1 = new FileWriter(fileName2);
            final BufferedWriter bufferedWriter1 = new BufferedWriter(fileWriter1);
            final FileWriter fileWriter2 = new FileWriter(fileName3);
            final BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
            while ((line = bufferedReader.readLine()) != null) {
                if (bufferedReader.getLineNumber() % 2 == 1) {
                    bufferedWriter1.write(line);
                    bufferedWriter1.newLine();
                } else {
                    bufferedWriter2.write(line);
                    bufferedWriter2.newLine();
                }
            }
            bufferedReader.close();
            bufferedWriter1.close();
            bufferedWriter2.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file " + fileName1);
        } catch (IOException ex) {
            System.out.println("Error reading file " + fileName1);
        }
    }

    static class SplitCfg implements SplitConfig {
        public String file1;
        public String file2;
        public String file3;

        public SplitCfg(final String file1, final String file2, final String file3) {
            this.file1 = file1;
            this.file2 = file2;
            this.file3 = file3;
        }

        public String getSourceFilePath() {
            return file1;
        }

        public String getOddLinesFilePath() {
            return file2;
        }

        public String getEvenLinesFilePath() {
            return file3;
        }
    }

    public static void main(String[] args) {
        final String file1 = args[0];
        final String file2 = args[1];
        final String file3 = args[2];

        final FileSplitter new1 = new OddEvenFileSplitter();
        final SplitConfig cfg1 = new SplitCfg(file1, file2, file3);
        new1.splitFile(cfg1);
    }
}
