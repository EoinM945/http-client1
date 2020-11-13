package org.example;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonReader;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class App
{

    private static final String POSTS_API_URL = "https://eoinm94.zendesk.com/api/v2/tickets.json";

    private static  HttpURLConnection connection;



    public static void main( String[] args ) throws IOException, InterruptedException {




        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        try{

            URL url = new URL("https://eoinm94.zendesk.com/api/v2/tickets.json");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization",  basicAuth("eoinmcdonald.94@gmail.com", "Smiles.10"));
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if(status> 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) !=null){
                        responseContent.append(line);
                }
                reader.close();
            }
            else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) !=null){
                    responseContent.append(line);
                }
                reader.close();
            }

            System.out.println(responseContent.toString());
           parse(responseContent.toString());





        } catch(MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }



/*

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("Authorization",  basicAuth("eoinmcdonald.94@gmail.com", "Smiles.10"))
                .uri(URI.create(POSTS_API_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());

        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        List<Ticket> ticket = mapper.readValue(response.body(), new TypeReference<List<Ticket>> (){});


        ticket.forEach(System.out::println);


*/


    }



    public static String parse(String responseBody) throws FileNotFoundException {

        JSONObject tickets = new JSONObject(new JSONTokener(new FileReader("ticket")));

        for(int i =0; i< tickets.length(); i++){
            JSONObject ticket = tickets.getJSONObject(i);
            int id = ticket.getInt("id");
            String subject = ticket.getString("subject");
            String createdAt = ticket.getString("created_at");
            String updatedAt = ticket.getString("updated_at");
            String rawSubject = ticket.getString("raw_subject");
            System.out.println(id + " " + subject + " " + createdAt + " " + updatedAt + " " + rawSubject);


        }
        return null;




    }






    private static String basicAuth(String email, String password) {
        return "Basic " + Base64.getEncoder().encodeToString((email + ":" + password).getBytes());
    }
}
