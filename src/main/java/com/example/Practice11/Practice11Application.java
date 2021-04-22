package com.example.Practice11;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class Practice11Application implements CommandLineRunner {

    public static String[] arguments;
    private StringBuilder cache;

    public static void main(String[] args) {
        arguments = args;
        SpringApplication.run(Practice11Application.class, args);
    }

    @PostConstruct
    public void init() {
        cache = new StringBuilder();
        try (FileReader fileReader = new FileReader(Practice11Application.arguments[0])) {
            Scanner scanner = new Scanner(fileReader);
            while (scanner.hasNextLine()) {
                cache.append(scanner.nextLine());
            }
        } catch (Exception e) {
            cache.append("null");
        }
    }

    @Override
    public void run(String... args) {
        String secondFileName = args[1];
        try (FileWriter fileWriter = new FileWriter(secondFileName, false)) {
            fileWriter.append(getCache());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getCache() {
        String caching = cache.toString();
        return  caching.equals("null") ? "null" : String.valueOf(caching.hashCode());
    }

    @PreDestroy
    public void destroy() {
        File file = new File(Practice11Application.arguments[0]);
        file.delete();
    }

}
