package ru.ifmo.lang.Sadovnikov.t08;

import java.io.IOException;

/**
 * Created by alexkane on 3/18/15.
 */
public interface RussianRoulette {

    void play(Gun gun) throws IOException;

    interface Gun {
        boolean fire() throws IOException;
    }

}
