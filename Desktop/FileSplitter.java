/**
 * Created by alexkane on 2/21/15.
 */
public interface FileSplitter {
    void splitFile(SplitConfig config);
    interface SplitConfig {
        String getSourceFilePath();
        String getOddLinesFilePath();
        String getEvenLinesFilePath();
    }
}
