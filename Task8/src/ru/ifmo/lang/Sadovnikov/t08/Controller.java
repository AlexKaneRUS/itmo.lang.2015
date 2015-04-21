package ru.ifmo.lang.Sadovnikov.t08;

import java.io.IOException;

/**
 * Created by alexkane on 4/21/15.
 */
public class Controller implements ControllerInterface {
    private final FileBasedRussianRoulette model;

    public Controller(final FileBasedRussianRoulette model) {
        this.model = model;
        View userInterface = new View(model, this);
        userInterface.createHomeScreen();
    }

    public void playNormalGun(final int numberOfBullets, final String sourceDirectory) throws IOException {
        model.playNormalGun(numberOfBullets, sourceDirectory);
    }

    public void playBonusGun(final String sourceDirectory) throws IOException {
        model.playBonusGun(sourceDirectory);
    }
}
