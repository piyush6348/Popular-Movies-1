package com.example.dell.movieapiproject1a;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;

/**
 * Created by dell on 2/2/2016.
 */
public class CustomListAdapter extends ArrayAdapter{

    private final Context con;
    private final String[]  a;

    public CustomListAdapter(Context con,String[] a) {
       // super(con, R.layout.myimg);
        super(con,R.layout.kk,a);
        this.a = a;
        this.con = con;



    }
    public View getView(int position,View view,ViewGroup parent) {
       // LayoutInflater inflater=con.getLayoutInflater();
      /*  if(view==null) {
           // LayoutInflater inflater = (LayoutInflater) con.getSystemService(con.LAYOUT_INFLATER_SERVICE);
           // View rowView = inflater.inflate(R.layout.myimg, null, true);
            LayoutInflater inflater=con.getLayoutInflater();
             view = inflater.inflate(R.layout.myimg, null, true);
        }*/
       /* LayoutInflater inflater=con.getLayoutInflater();
        View rowview = inflater.inflate(R.layout.kk, null, true);
        ImageView imgv = (ImageView) rowview.findViewById(R.id.imageView2);
        // Picasso.with(con).load(a[position]).into(imageView);
       // Picasso.with(con).load("http://image.tmdb.org/t/p/w185//mSvpKOWbyFtLro9BjfEGqUw5dXE.jpg").into(imgv);
       // Picasso.with(con).load(a[position]).into(imgv);
       // URL url=new URL(a[position]);
        URI uri=URI.create(a[position]);
        String ans=uri.toASCIIString();
        Picasso.with(con).load(a[position]).into(imgv);*/
        Log.v("IMAGES",a[2]);
        View v;
        LayoutInflater m=(LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view==null)
            v=m.inflate(R.layout.kk,parent,false);
        else
            v=view;
        ImageView img=(ImageView)v.findViewById(R.id.imageView2);
        Picasso.with(con).load(a[position]).into(img);
        return v;

    }
    }

