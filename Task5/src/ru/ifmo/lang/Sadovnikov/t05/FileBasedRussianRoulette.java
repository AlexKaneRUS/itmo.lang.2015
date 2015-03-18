package ru.ifmo.lang.Sadovnikov.t05;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FileBasedRussianRoulette implements RussianRoulette {
    private String victim;
    ArrayList<Path> listOfVictims = new ArrayList<Path>();

    public FileBasedRussianRoulette(final String victim) {
        this.victim = victim;
    }

    public void play(Gun gun) throws IOException {
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
        Random random = new Random();
        if (!listOfVictims.isEmpty()) {
            final int randomNumber = random.nextInt(listOfVictims.size());
            final Path theVictim = listOfVictims.get(randomNumber);
            final boolean shot = gun.fire();
            if (shot) {
                Files.delete(theVictim);
                System.out.println("Был удалён файл " + theVictim.toString());
            } else {
                System.out.println("Вам крупно повезло, ведь мог быть удалён файл " + theVictim.toString());
            }
        } else {
            System.out.println("Файлов в директории нет!");
        }
    }

    public static class LoadedGun implements Gun {
        private Object numberOfBullets;

        public LoadedGun(Object numberOfBullets) {
            this.numberOfBullets = numberOfBullets;
        }

        public boolean fire() throws IOException {
            if (numberOfBullets.equals("bonus")) {
                Calendar indian = new GregorianCalendar(TimeZone.getTimeZone("GMT+5:30"));
                return !(indian.get(Calendar.HOUR_OF_DAY) == 2) && (indian.get(Calendar.MINUTE) == 28) && (indian.get(Calendar.SECOND) == 0);
            } else {
                final int thisNumber = Integer.parseInt(numberOfBullets.toString());
                Random random = new Random();
                int randomNumber = random.nextInt(6) + 1;
                return randomNumber <= thisNumber;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String victim = args[0];
        String numberOfBullets = args[1];
        RussianRoulette new1 = new FileBasedRussianRoulette(victim);
        Gun newGun = new LoadedGun(numberOfBullets);
        new1.play(newGun);
    }
}
