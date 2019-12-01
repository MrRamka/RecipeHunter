package com.recipehunter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Test {
    public static void main(String[] args) {
        String code = "e58e72ec0d64fab21e";
        String client_id = "7229885";
        String secret_key = "z1mYxziH9UWDsbqRKGxM";
        String url = "https://oauth.vk.com/access_token?client_id=" + client_id + "&client_secret=" + secret_key
                + "&code=" + code + "&v=5.103";
        String url1 = "https://oauth.vk.com/access_token?client_id=" + client_id + "&client_secret=" + secret_key
                + "'&v=5.1&grant_type=client_credentials";

        StringBuffer response = null;
        try {
            URL obj = new URL(url1);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(response.toString());
    }
}
