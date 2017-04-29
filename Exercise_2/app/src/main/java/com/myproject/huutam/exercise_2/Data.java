package com.myproject.huutam.exercise_2;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by huutam on 29/04/2017.
 */

public class Data implements Serializable{
    ArrayList<tinh> DSTinh;
    String tinhSelect;
    Data(){
        DSTinh = new ArrayList<>();
        tinhSelect="";
    }
}
