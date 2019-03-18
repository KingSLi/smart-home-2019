package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHomeReader implements HomeReader {
    private String filename;

    public JsonHomeReader(String filename) {
        this.filename = filename;
    }

    @Override
    public SmartHome readSmartHome() {
        Gson gson = new Gson();
        SmartHome smartHome = new SmartHome();
        try {
            String json = new String(Files.readAllBytes(Paths.get(filename)));
            smartHome = gson.fromJson(json, SmartHome.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return smartHome;
    }
}
