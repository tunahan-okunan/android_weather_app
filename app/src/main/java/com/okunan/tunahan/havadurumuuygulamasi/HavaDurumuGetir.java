package com.okunan.tunahan.havadurumuuygulamasi;

import android.os.AsyncTask;
import android.widget.TextView;

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

/**
 * Created by TUNAHAN on 28.11.2017.
 */

public class HavaDurumuGetir extends AsyncTask<Void, Void, Void> {

    String data = "";
    TextView mTextView;
    String havaDurumu;
    String tanim;
    Double temp;
    String sehir;

    public HavaDurumuGetir(TextView textView,String sehir) {
        this.mTextView = textView;
        this.sehir=sehir;
    }

    @Override
    protected Void doInBackground(Void... params) {
        URL url = null;
        int a = 0;
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+sehir+",tr&appid=faebbba83a14a0b4fceabd72ca4b4b76");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("weather");

            JSONObject jsonObject_weather = jsonArray.getJSONObject(0);
            havaDurumu = jsonObject_weather.getString("main");
            tanim = jsonObject_weather.getString("description");

            JSONObject jsonObject_main = jsonObject.getJSONObject("main");
            temp = jsonObject_main.getDouble("temp");//main'in içinden sıcaklığı aldık
            sehir = jsonObject.getString("name");//en sondaki kısımdan city ismini aldık
            temp = (Double) (temp - 273);//Kelvin olduğu için Celcius'a çevirdik
        } catch (MalformedURLException e) {
            a = 1;
            e.printStackTrace();
        } catch (IOException e) {
            a = 2;
            e.printStackTrace();
        } catch (JSONException e) {
            a = 3;
            e.printStackTrace();
        }

        int b = a;
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        mTextView.setText(havaDurumu + "\n" + tanim + "\n" + String.format("%.1f", temp) + "\n" + sehir);


        //   MainActivity.mFetchedData.setText(data);

    }
}
