package com.myproject.huutam.exercise_2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner list ;
    ArrayList<String> arrayListSpinner;
    ImageButton see ;
    String provinceSelected="";
    ArrayList<tinh> arrayList;
    Data data_;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (Spinner)findViewById(R.id.spn_list);
        see=(ImageButton)findViewById(R.id.img_bt_see);


        arrayList= new ArrayList<>();
        arrayListSpinner=new ArrayList<>();
        arrayList.add(new tinh("AG","AnGiang"));
        arrayList.add(new tinh("BD","BinhDuong"));
        arrayList.add(new tinh("BL","BacLieu"));
        arrayList.add(new tinh("BP","BinhPhuoc"));
        arrayList.add(new tinh("BTH","BinhThuan"));
        arrayList.add(new tinh("CM","CaMau"));
        arrayList.add(new tinh("CT","CanTho"));
        arrayList.add(new tinh("HCM","TPHoChiMinh"));
        for(int i=0;i<arrayList.size();i++){
            arrayListSpinner.add(arrayList.get(i).real_var);
        }

        CurrencyAdapter adapter= new CurrencyAdapter(this,R.layout.my_lauout_spinner_item,arrayListSpinner);
        list.setAdapter(adapter);
        //provinceSelected=arrayListSpinner.get(0);
        list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                provinceSelected = arrayListSpinner.get(i);
                //Toast.makeText(MainActivity.this,provinceSelected,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data_ = new Data();
                data_.DSTinh=arrayList;
                data_.tinhSelect=provinceSelected;

                Intent convertScreen = new Intent(MainActivity.this,SosoScreen.class);
                convertScreen.putExtra("sosoTinh",data_);
                startActivity(convertScreen);
            }
        });
    }

}
