package ru.ifmo.lang.Sadovnikov.t07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by alexkane on 4/20/15.
 */
public class WhiteboardClient {
    public static void main(String[] args) throws IOException {
        if (args[0].equals("get")) {
            URL url = new URL("http://localhost:8080/get");
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String content = URLDecoder.decode(reader.readLine(), "Unicode");
            reader.close();
            System.out.println(content);
        } else {
            URL url = new URL("http://localhost:8080/post?message=" + URLEncoder.encode(args[1], "UTF-8"));
            url.openConnection().getInputStream();
        }
    }
}
