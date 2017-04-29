package com.myproject.huutam.exercise_2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class SosoScreen extends AppCompatActivity {

    String tinh="";
    TextView province_name;
    Spinner timeShow;
    Data dataSoso = new Data();
    String tinhVT="khhin";
    ArrayList<tinh> listTinhSoso =  new ArrayList<>();
    ArrayList<String> time = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soso_screen);
        province_name=(TextView)findViewById(R.id.tv_tinh);
        timeShow=(Spinner)findViewById(R.id.spn_time);

        dataSoso =(Data) getIntent().getSerializableExtra("sosoTinh");
        listTinhSoso = dataSoso.DSTinh;
        tinh = dataSoso.tinhSelect;
        province_name.setText(tinh);
        for (int i = 0;i<listTinhSoso.size();i++){
            if(listTinhSoso.get(i).real_var==tinh){
                tinhVT = listTinhSoso.get(i).vt_var;
                break;
            }
        }

        //Toast.makeText(SosoScreen.this,tinhVT,Toast.LENGTH_LONG).show();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new ReadJson().execute("http://thanhhungqb.tk:8080/kqxsmn");
            }
        });

        CurrencyAdapter adapter= new CurrencyAdapter(this,R.layout.my_lauout_spinner_item,time);
        timeShow.setAdapter(adapter);
        //provinceSelected=arrayListSpinner.get(0);
        timeShow.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    class ReadJson extends AsyncTask<String , Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            String contend = getContend_From_URL(strings[0]);
            return contend;
        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            //super.onPostExecute(s);
            try {
                JSONObject root = new JSONObject(s);
                JSONObject item = new JSONObject(root.getString(tinhVT));
                for (int i=0;i<item.names().length();i++){
                    time.add(item.names().get(i).toString());

                }


                Toast.makeText(SosoScreen.this,root.getString(tinhVT),Toast.LENGTH_LONG).show();
                //Toast.makeText(SosoScreen.this,tinhVT,Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(SosoScreen.this,"No",Toast.LENGTH_LONG).show();
            }

        }
    }

    private static String getContend_From_URL(String theUrl)
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
            e.printStackTrace();
        }
        return content.toString();
    }
}
