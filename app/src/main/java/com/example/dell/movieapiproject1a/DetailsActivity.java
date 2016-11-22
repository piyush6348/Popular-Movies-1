package com.example.dell.movieapiproject1a;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailsActivity extends AppCompatActivity {
    ImageView img;
    TextView t1,t2,t3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle p=getIntent().getExtras();
        int pos=p.getInt("Position");
        String fil=p.getString("j");
        String ur="";

        Log.e("file",fil);

        img=(ImageView)findViewById(R.id.imageView);
        t1=(TextView)findViewById(R.id.data);
        t2=(TextView)findViewById(R.id.synopsis);
        t3=(TextView)findViewById(R.id.name);

        JSONObject obj= null;
        try {
            obj = new JSONObject(fil);
            JSONArray arr=obj.optJSONArray("results");
            JSONObject last=arr.optJSONObject(pos);

            ur="http://image.tmdb.org/t/p/w185/"+last.getString("poster_path");
            t1.setText("User Rating- "+last.getString("vote_average")+"/10"+"\n\n"+"Date of Release "+last.getString("release_date"));
            Picasso.with(this).load(ur).into(img);
            t3.setText(last.getString("original_title"));
            //t2.setTextColor(Color.parseColor("#5C6BC0"));
            t2.setText("\n"+last.getString("overview"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
