package com.myproject.huutam.bonus;

import java.io.Serializable;

/**
 * Created by huutam on 04/04/2017.
 */

public class CurrencyObject implements Serializable {
    String CurrencyCode;
    String CurrencyName;
    Float Buy;
    Float Transfer;
    Float Sell;
    CurrencyObject(){
        CurrencyCode="";
        CurrencyName="";
        Buy=0f;
        Transfer=0f;
        Sell=0f;
    }
}
