package com.yahoo.apps.instagram;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpRequest;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends Activity {
    public static final String CLIENT_ID = "ed6d5ad1698a4ef6b9f275e1b7c223cb";
    public static final String URL = "https://api.instagram.com/v1/media/popular?client_id=";
    private ArrayList<ImageData> photos;
    private ImageAdapter photosAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        photos = new ArrayList<ImageData>();
        photosAdapter = new ImageAdapter(this, photos);
        ListView lvPhotos = (ListView)findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(photosAdapter);
        queryImages();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void queryImages(){
        String url = URL + CLIENT_ID;
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(url, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray jsonArray = null;
                try{
                    jsonArray = response.getJSONArray("data");
                    for (int i = 0 ; i < jsonArray.length(); i++){
                        JSONObject photoJson = jsonArray.getJSONObject(i);
                        ImageData imageData = new ImageData();
                        imageData.username = photoJson.getJSONObject("user").getString("username");
                        imageData.caption = photoJson.getJSONObject("caption").getString("text");
                        imageData.imageUrl = photoJson.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        imageData.imageHeight = photoJson.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
                        imageData.likesCount = photoJson.getJSONObject("likes").getInt("count");
                        imageData.userProfileImageUrl = photoJson.getJSONObject("user").getString("profile_picture");
                        imageData.id = photoJson.getString("id");
                        photos.add(imageData);
                    }
                }catch (JSONException e){
                    Log.d("Error", e.getMessage());
                }
                photosAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("Error", responseString);
            }
        });
    }
}























