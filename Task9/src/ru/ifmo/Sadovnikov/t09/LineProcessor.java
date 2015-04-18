package ru.ifmo.Sadovnikov.t09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by vmath on 18.04.15.
 */
public class LineProcessor {
    private final String outputFile;
    private List<String> lines;

    public LineProcessor(final String sourceFile, final String outputFile) throws IOException {
        this.outputFile = outputFile;
        this.lines = Files.readAllLines(Paths.get(sourceFile));
    }

    public void sort(){
        lines = lines.stream().sorted().collect(Collectors.toList());
    }

    public void skip(int linesForSkip){
        lines = lines.stream().skip(linesForSkip).collect(Collectors.toList());
    }

    public void limit(int linesForLimit){
        lines = lines.stream().limit(linesForLimit).collect(Collectors.toList());
    }

    public void shuffle(){
        Collections.shuffle(lines);
    }

    public void distinct(){
        lines = lines.stream().distinct().collect(Collectors.toList());
    }

    public void filter(String regex){
        Pattern thisRegEx = Pattern.compile(regex);
        lines = lines.stream().filter(i -> thisRegEx.matcher(i).matches()).collect(Collectors.toList());
    }

    public void writeLines() throws IOException {
        Files.write(Paths.get(outputFile), lines);
    }

    public static void main(String[] args) throws IOException {
        String sourceFile = args[0];
        String outputFile = args[1];
        LineProcessor lineProcessor = new LineProcessor(sourceFile, outputFile);

        for (int i = 2; i < args.length; i++){
            if (args[i].equals("sort")){
                lineProcessor.sort();
            }
            if (args[i].equals("skip")){
                lineProcessor.skip(Integer.parseInt(args[i + 1]));
            }
            if (args[i].equals("limit")){
                lineProcessor.limit(Integer.parseInt(args[i + 1]));
            }
            if (args[i].equals("shuffle")){
                lineProcessor.shuffle();
            }
            if (args[i].equals("distinct")){
                lineProcessor.distinct();
            }
            if (args[i].equals("filter")){
                lineProcessor.filter(args[i++]);
            }
        }

        lineProcessor.writeLines();
    }
}
