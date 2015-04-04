package ru.ifmo.lang.Sadovnikov.t06;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by alexkane on 3/21/15.
 */
class SimpleGrepUtility implements Grep {
    private String[] lines;

    public SimpleGrepUtility(final InputStream sourceFileInputStream) throws IOException {
        BufferedInputStream fileReader = new BufferedInputStream(sourceFileInputStream);
        int character;
        String theText = "";
        while ((character = fileReader.read()) != -1) {
            theText += (char) character;
        }
        this.lines = theText.split("\\n");
        fileReader.close();
    }

    public List<String> findLines(String regex) throws IOException {
        List<String> result = new ArrayList<String>();
        Pattern finalRegex = Pattern.compile(regex);
        for (int i = 0; i < lines.length; i++) {
            Matcher theMatcher = finalRegex.matcher(lines[i]);
            if (theMatcher.find()) {
                result.add(lines[i]);
            }
        }
        return result;
    }

    @Override
    public List<String> findParts(String regex) throws IOException {
        List<String> result = new ArrayList<String>();
        Pattern finalRegex = Pattern.compile(regex);
        for (int i = 0; i < lines.length; i++) {
            Matcher theMatcher = finalRegex.matcher(lines[i]);
            if (theMatcher.find()) {
                result.add(theMatcher.group());
            }
        }
        return result;
    }

    @Override
    public List<String> findInvertMatch(String regex) throws IOException {
        List<String> result = new ArrayList<String>();
        Pattern finalRegex = Pattern.compile(".*" + regex + ".*");
        for (int i = 0; i < lines.length; i++) {
            Matcher theMatcher = finalRegex.matcher(lines[i]);
            if (!theMatcher.find()) {
                result.add(lines[i]);
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 3){
            String regex = args[1];
            InputStream sourceFile = new FileInputStream(args[2]);
            if (args[0].equals("-o")){
                System.out.println(new SimpleGrepUtility(sourceFile).findParts(regex));
            }else {
                System.out.println(new SimpleGrepUtility(sourceFile).findInvertMatch(regex));
            }
        }else {
            String regex = args[0];
            InputStream sourceFile = new FileInputStream(args[1]);
            System.out.println(new SimpleGrepUtility(sourceFile).findLines(regex));
        }
    }
}
