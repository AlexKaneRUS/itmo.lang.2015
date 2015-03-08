package ru.ifmo.lang.SadovnikovAlexander.t04;

import java.io.IOException;

/**
 * Created by alexkane on 3/7/15.
 */
public interface FileSizeCalculator {
    long getSize(final String pathToDir, final String fileTemplate) throws IOException;
}
