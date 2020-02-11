package com.example.android.quakereport;

import android.nfc.Tag;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.example.android.quakereport.EarthquakeActivity.LOG_TAG;

public class QueryUtils {


    private static URL createUrl(String stringUrl){
        URL url = null;
        try{
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String MakeHTTPRequest (URL url) throws IOException{
        String jsonResponse = "";
        if(url==null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode() == 200){
                inputStream =urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }
            else{
                Log.e(LOG_TAG, "Error Response Code " + urlConnection.getResponseCode() );
            }
                
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the Earthquake JSON results", e);
        }
        finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream!=null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if(inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line!=null){
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    private QueryUtils(){

    }

    public static List<Earth> extractFeaturesFromJson(String earthquakeJson){

        if(TextUtils.isEmpty(earthquakeJson)){
            return null;
        }
        List<Earth> earth = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(earthquakeJson);
            JSONArray earthquakearray = jsonObject.getJSONArray("features");
            for(int i =0; i<earthquakearray.length(); i++){
                JSONObject currentObject = earthquakearray.getJSONObject(i);
                JSONObject properties = currentObject.getJSONObject("properties");
                double magnitude = properties.getDouble("mag");
                String place = properties.getString("place");
                long time = properties.getLong("time");
                String url = properties.getString("url");

                Earth earth1 = new Earth(magnitude, place, time, url);
                earth.add(earth1);
            }

        }
        catch (JSONException e){
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return earth;


    }
    public static List<Earth> fetchEarthquakeData (String requestUrl) {
        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = MakeHTTPRequest(url);

        } catch (Exception e) {
            Log.e(LOG_TAG,"Problem making the HTTP request",e);

        }
        List<Earth> earths = extractFeaturesFromJson(jsonResponse);
        return earths;
    }


}
