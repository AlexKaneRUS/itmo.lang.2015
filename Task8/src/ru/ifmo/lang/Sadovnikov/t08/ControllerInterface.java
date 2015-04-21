package ru.ifmo.lang.Sadovnikov.t08;

import java.io.IOException;

/**
 * Created by alexkane on 4/21/15.
 */
public interface ControllerInterface {
    public void playNormalGun(final int NumberOfBullets, final String sourceDirectory) throws IOException;

    public void playBonusGun(final String sourceDirectory) throws IOException;
}
