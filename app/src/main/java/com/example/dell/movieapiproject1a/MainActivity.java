package com.example.dell.movieapiproject1a;


import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

  String ans = null;
  GridView gv;
    String url_to_be_passed="http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=fb35a680090503d985daf7217ecec0a2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gv=(GridView)findViewById(R.id.gridView);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getBaseContext(), DetailsActivity.class);
                Bundle extras=new Bundle();
                extras.putInt("Position", position);
                extras.putString("j", ans);
                i.putExtras(extras);
                startActivity(i);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_sort_by_popularity:
                url_to_be_passed="http://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=fb35a680090503d985daf7217ecec0a2";
                new Connection_and_Setimages().execute(url_to_be_passed);
                break;
            case R.id.menu_sort_by_user_rating:
                url_to_be_passed="http://api.themoviedb.org/3/discover/movie?sort_by=vote_average.desc&api_key=fb35a680090503d985daf7217ecec0a2";
                new Connection_and_Setimages().execute(url_to_be_passed);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class Connection_and_Setimages extends AsyncTask<String, Void, String[]> {

        @Override
        protected String[] doInBackground(String... params) {

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.

            try {
                URL url=new URL(params[0]);
                urlConnection=(HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream is=urlConnection.getInputStream();
                StringBuffer buff=new StringBuffer();

                if(is==null)
                    ans=null;

                reader=new BufferedReader(new InputStreamReader(is));

                String l;
                while ((l=reader.readLine())!=null){
                    buff.append(l+"\n");
                }

                if(buff.length()==0)
                    ans=null;

                ans=buff.toString();
            }catch (Exception e){
                e.printStackTrace();
                ans=null;
            }finally {
                if(urlConnection!=null)
                    urlConnection.disconnect();
                if(reader!=null)
                    try {

                        reader.close();
                    }catch (Exception e){
                        e.printStackTrace();

                    }
            }
           // Log.("JSON-STRING",ans);
            Log.v("JSON-STRING",ans);
            //Reading poster path..
            try {
                return getarray(ans);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }




        @Override
        protected void onPostExecute(String[] x) {

            CustomListAdapter ad=new CustomListAdapter(MainActivity.this,x);

            gv.setAdapter(ad);


        }


    }
    private String[] getarray(String ans2) throws JSONException {
        String ur="http://image.tmdb.org/t/p/w185/";
        JSONObject obj=new JSONObject(ans2);
        JSONArray arr=obj.optJSONArray("results");
        String[] posterpath2=new String[arr.length()];
        for(int i=0;i<arr.length();i++)
        {
            JSONObject last=arr.optJSONObject(i);
            posterpath2[i]=ur+last.getString("poster_path");
            Log.v("POSTER_PATH"+i,posterpath2[i]);
        }

        return posterpath2;
    }



}
