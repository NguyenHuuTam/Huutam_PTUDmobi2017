package com.myproject.huutam.bonus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class See extends AppCompatActivity {
    ImageButton seeAction;
    ImageButton returnAction;
    Spinner currency;
    ArrayList<CurrencyObject> currencyListSee = new ArrayList<>();
    ArrayList<String> arr = new ArrayList<>();
    TextView result;
    String currencySelected = "mfknfkadns";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see);

        seeAction = (ImageButton)findViewById(R.id.bnt_see_See);
        returnAction = (ImageButton)findViewById(R.id.bnt_return_from_see);
        currency = (Spinner)findViewById(R.id.currencySelect_see);
        result=(TextView)findViewById(R.id.tv_result);
        currencyListSee =(ArrayList<CurrencyObject>)getIntent().getSerializableExtra("currencyList_See");
        for(int i = 0;i<currencyListSee.size();i++){
            arr.add(currencyListSee.get(i).CurrencyCode);
        }

        CurrencyAdapter adapter= new CurrencyAdapter(this,R.layout.my_lauout_spinner_item,arr);

        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        //adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        currency.setAdapter(adapter);

        currency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                currencySelected = arr.get(i);
                //Toast.makeText(getApplicationContext(), "The option is:" + currencySelected , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //thiết lập sự kiện chọn phần tử cho Spinner
        //currency.setOnItemSelectedListener(new MyProcessEvent());

        returnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainScreen = new Intent(See.this,MainActivity.class);
                finish();
                //startActivity(mainScreen);
            }
        });

        seeAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<currencyListSee.size();i++){
                    if(currencyListSee.get(i).CurrencyCode == currencySelected){
                        result.setText(String.valueOf(currencyListSee.get(i).Transfer)+" "+"VND");
                    }
                }

                //Toast.makeText(See.this, currency.getSelectedItem().g, Toast.LENGTH_LONG).show();

            }
        });
    }
}
