package com.myproject.huutam.bonus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Convert extends AppCompatActivity {

    ArrayList<CurrencyObject> currencyListConvert = new ArrayList<>();
    ArrayList<String> arr = new ArrayList<>();
    ArrayList<String> arr2 = new ArrayList<>();
    Spinner convert1;
    Spinner convert2;
    ImageButton returnMenu;
    ImageButton convertAction;
    String selectConvert1="";
    String selectConvert2="";
    EditText inputNumberEdit;
    TextView resultConvert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        convert1 = (Spinner)findViewById(R.id.convert1);
        convert2 = (Spinner)findViewById(R.id.convert2);
        returnMenu = (ImageButton)findViewById(R.id.bnt_return_convert);
        convertAction = (ImageButton)findViewById(R.id.bntConvertAction);
        inputNumberEdit = (EditText)findViewById(R.id.currencyInput);
        resultConvert = (TextView)findViewById(R.id.tvresultConvert);

        currencyListConvert =(ArrayList<CurrencyObject>)getIntent().getSerializableExtra("currencyList_Convert");

        for(int i = 0;i<currencyListConvert.size();i++){
            arr.add(currencyListConvert.get(i).CurrencyCode);
            arr2.add(currencyListConvert.get(i).CurrencyCode);
        }

        CurrencyAdapter adapter= new CurrencyAdapter(this,R.layout.my_lauout_spinner_item,arr);
        CurrencyAdapter adapter2= new CurrencyAdapter(this,R.layout.my_lauout_spinner_item,arr2);

        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        //adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        convert1.setAdapter(adapter);
        convert2.setAdapter(adapter2);

        convert1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectConvert1 = arr.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        convert2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectConvert2 = arr2.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        returnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainScreen = new Intent(Convert.this,MainActivity.class);
                finish();
            }
        });

        convertAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float number1=1;
                float number2 =1;
                for(int i=0;i<currencyListConvert.size();i++){
                    if(currencyListConvert.get(i).CurrencyCode == selectConvert1){
                        number1 = currencyListConvert.get(i).Transfer;
                    }

                    if(currencyListConvert.get(i).CurrencyCode == selectConvert2){
                        number2 = currencyListConvert.get(i).Transfer;
                    }
                }
                float inputNumber = Float.valueOf(inputNumberEdit.getText().toString());
                float result = inputNumber*number2/number1;
                resultConvert.setText(String.valueOf(result));

            }
        });
    }
}
