package ru.ifmo.lang.Sadovnikov.t05;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by alexkane on 3/18/15.
 */
public interface RussianRoulette {

    void play(Gun gun) throws IOException;

    interface Gun {
        boolean fire() throws IOException;
    }

}
