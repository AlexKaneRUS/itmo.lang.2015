package ru.ifmo.lang.Sadovnikov.t06;

import java.io.IOException;
import java.util.List;

/**
 * Created by alexkane on 3/21/15.
 */
public interface Grep {

    List<String> findLines(String regex) throws IOException;

    List<String> findParts(String regex) throws IOException;

    List<String> findInvertMatch(String regex) throws IOException;

}
