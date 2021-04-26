package com.example.khalid_ali_180145;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity4 extends AppCompatActivity {
    // we"ll make HTTP request to this URL to retrieve weather conditions
    String weatherWebserviceURL = "http://api.openweathermap.org/data/2.5/weather?q=athens&appid=c401b5674d945658606ba333ae8e9de0&units=metric";
    ImageView weatherBackground;
    // Textview to show temperature and description
    TextView temperature, description;

    // JSON object that contains weather information
    JSONObject jsonObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
            temperature = (TextView) findViewById(R.id.temperature);
            description = (TextView) findViewById(R.id.description);
            weatherBackground = (ImageView) findViewById(R.id.weatherbackground);
            weather(weatherWebserviceURL);
        }
        public void chooseBackground(JSONArray jArray) {

            for (int i=0; i < jArray.length(); i++)
            {
                try {
                    JSONObject oneObject = jArray.getJSONObject(i);
                    // Pulling items from the array
                    Log.d("khalid", "jArray: "+ oneObject.toString());

                    String weatherConditions = oneObject.getString("main");
                    Log.d("khalid", "weather condition: "+weatherConditions);
                    if(weatherConditions.equals("Clear")) {
                        Log.d("khalid", "Looking for clear sky image");

                        String picURL = "https://cdn.pixabay.com/photo/2013/08/22/07/43/sky-174648_1280.jpg";
                        Glide.with(weatherBackground).load(picURL).into(weatherBackground);
                    }
                    if(weatherConditions.equals("Rain")) {
                        Log.d("khalid", "Looking for rain image");

                        String picURL = "https://cdn.pixabay.com/photo/2014/09/21/14/39/surface-455124_1280.jpg";
                        Glide.with(weatherBackground).load(picURL).into(weatherBackground);
                    }
                    if(weatherConditions.equals("Dust")) {
                        Log.d("khalid", "Looking for dust image");

                        String picURL = "https://cdn.pixabay.com/photo/2016/02/08/02/03/bb-8-1185956_1280.jpg";
                        Glide.with(weatherBackground).load(picURL).into(weatherBackground);
                    }
                    if(weatherConditions.equals("Cloud")) {
                        Log.d("khalid", "Looking for cloud sky image");

                        String picURL = "https://cdn.pixabay.com/photo/2018/06/26/18/42/storm-clouds-3499982_1280.jpg";
                        Glide.with(weatherBackground).load(picURL).into(weatherBackground);
                    }

                    String oneObjectsItem = oneObject.getString("STRINGNAMEinTHEarray");
                    String oneObjectsItem2 = oneObject.getString("anotherSTRINGNAMEINtheARRAY");
                } catch (JSONException e) {
                    Log.d("khalid", "Error JSONarray: "+e.toString());

                }
            }

        }
        public void weather(String url){
            JsonObjectRequest jsonObj = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d("khalid", response.toString());
                    try {
                        JSONObject JsonMain= response.getJSONObject("main");
                        Log.d("Khalid", "subObject: "+JsonMain.toString());
                        double temp = JsonMain.getDouble("temp");
                        Log.d("khalid", "temp: "+String.valueOf(temp));
                        temperature.setText(String.valueOf(temp) + "C");
                        JSONArray weatherArray = response.getJSONArray("weather");
                        chooseBackground(weatherArray);
                        Log.d("khalid", "bulk-Array: " +weatherArray.toString());

                    }

                    catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //log errors are here
                    Log.d("Khalid", "Error in url");
                }
            });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(jsonObj);


        }
    }

