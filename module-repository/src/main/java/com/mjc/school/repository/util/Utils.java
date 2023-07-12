package com.mjc.school.repository.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    public static List<String> readFile(String fileName){
        List<String> fileContents;
        ClassLoader classLoader = Utils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("File not found.");
        }

        try(InputStreamReader streamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(streamReader))
        {
            fileContents = reader.lines().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileContents;
    }

    public static LocalDateTime getRandomDate(){
        int randomInt = new Random().nextInt(30);
        return LocalDateTime.now().minusDays(randomInt).withNano(0);
    }

}
