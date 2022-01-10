package com.budgetfriendly.bmsbudgetservice.common;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

public class Test {

    public static void main(String args[]){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String date = "2021-12-22";
        LocalDate currentDate = LocalDate.parse(date);
        Month month1 = currentDate.getMonth();
        System.out.println(month1);
    }
}
