package com.myproject.huutam.bonus;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageButton seeLayout;
    ImageButton convertLayout;
    ArrayList<CurrencyObject> currentList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anhxa
        seeLayout = (ImageButton)findViewById(R.id.bnt_see);
        convertLayout = (ImageButton)findViewById(R.id.bnt_convert);

        //thaotac
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadXML().execute("https://www.vietcombank.com.vn/exchangerates/ExrateXML.aspx");
            }
        });
        seeLayout.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent seeScreen = new Intent(MainActivity.this,See.class);
                seeScreen.putExtra("currencyList_See",currentList);
                startActivity(seeScreen);
            }
        });

        convertLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent convertScreen = new Intent(MainActivity.this,Convert.class);
                convertScreen.putExtra("currencyList_Convert",currentList);
                startActivity(convertScreen);
            }
        });
    }


    private static String getContentFromURL(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            //e.printStackTrace();

        }
        return content.toString();
    }

    private class ReadXML extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            return getContentFromURL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList noteList = document.getElementsByTagName("ExrateList");
            Element element = (Element) noteList.item(0);
            String title = parser.getValue(element,"DateTime");

            NodeList exrate = document.getElementsByTagName("Exrate");
            for(int i = 0; i < exrate.getLength(); i++){
                NamedNodeMap nameNodeMap = exrate.item(i).getAttributes();
                CurrencyObject currencyObject = new CurrencyObject();
                currencyObject.CurrencyCode = nameNodeMap.item(0).getNodeValue();
                currencyObject.CurrencyName = nameNodeMap.item(1).getNodeValue();
                currencyObject.Buy = Float.valueOf(nameNodeMap.item(2).getNodeValue());
                currencyObject.Transfer = Float.valueOf(nameNodeMap.item(3).getNodeValue());
                currencyObject.Sell = Float.valueOf(nameNodeMap.item(4).getNodeValue());
                currentList.add(currencyObject);
            }


        }
    }
}
