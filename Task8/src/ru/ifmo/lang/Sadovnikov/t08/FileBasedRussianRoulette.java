package ru.ifmo.lang.Sadovnikov.t08;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FileBasedRussianRoulette implements RussianRoulette {
    private String victim;
    private ArrayList<Path> listOfVictims = new ArrayList<Path>();
    private ArrayList<Observer> observers = new ArrayList<Observer>();


    public void play(Gun gun) throws IOException {
        visitFiles();
        Random random = new Random();
        if (!listOfVictims.isEmpty()) {
            final Path theVictim = listOfVictims.get(random.nextInt(listOfVictims.size()));
            final boolean shot = gun.fire();
            if (shot) {
                Files.delete(theVictim);
                notifyObservers("Был удалён файл " + theVictim.toString());
            } else {
                notifyObservers("Вам крупно повезло, ведь мог быть удалён файл " + theVictim.toString());
            }
        } else {
            notifyObservers("Файлов в директории нет!");
        }
    }

    private void visitFiles() throws IOException {
        listOfVictims.clear();
        final Path pathToFiles = Paths.get(victim);
        final FileVisitor<Path> theVisitor = new SimpleFileVisitor<Path>() {
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs)
                    throws IOException {
                listOfVictims.add(path);
                return FileVisitResult.CONTINUE;
            }
        };
        Files.walkFileTree(pathToFiles, theVisitor);
    }

    public static class LoadedGun implements Gun {
        private int numberOfBullets;

        public LoadedGun(final int numberOfBullets) {
            this.numberOfBullets = numberOfBullets;
        }

        public boolean fire() throws IOException {
            final int thisNumber = numberOfBullets;
            Random random = new Random();
            int randomNumber = random.nextInt(6) + 1;
            return randomNumber <= thisNumber;
        }
    }

    public static class BonusGun implements Gun {
        public boolean fire() throws IOException {
            Calendar indian = new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
            return !(indian.get(Calendar.HOUR_OF_DAY) == 2) && (indian.get(Calendar.MINUTE) == 28) && (indian.get(Calendar.SECOND) == 0);
        }
    }

    public void playNormalGun(final int numberOfBullets, final String sourceDirectory) throws IOException {
        victim = sourceDirectory;
        LoadedGun newGun = new LoadedGun(numberOfBullets);
        this.play(newGun);
    }

    public void playBonusGun(final String sourceDirectory) throws IOException {
        victim = sourceDirectory;
        BonusGun newGun = new BonusGun();
        this.play(newGun);
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    public void notifyObservers(final String message) {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(message);
        }
    }

    public int getNumberOfObservers() {
        return observers.size();
    }
}
