package com.kirillkurko.cityinformation.bot.services.implementations;

import com.kirillkurko.cityinformation.bot.services.interfaces.DataService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DataServiceImplementation implements DataService  {

    private static final String baseUrl = "http://localhost:8080/cityInformation/cities/";

    @Override
    public String getCityInformation(String name) {
        String message;
        try {
            name = URLEncoder.encode(name, StandardCharsets.UTF_8);
            URL url = new URL(baseUrl + name);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            StringBuilder inline = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }
            scanner.close();
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(inline.toString());
            message = (String) object.get("message");
        }
        catch (Exception exception) {
            message = "В этом городе я пока не был";
        }
        return message;
    }
}
