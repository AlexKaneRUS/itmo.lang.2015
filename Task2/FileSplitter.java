/**
 * Created by alexkane on 2/27/15.
 */
public interface FileSplitter {
    void splitFile(SplitConfig config);

    public interface SplitConfig {
        String getSourceFilePath();

        String getOddLinesFilePath();

        String getEvenLinesFilePath();
    }
}