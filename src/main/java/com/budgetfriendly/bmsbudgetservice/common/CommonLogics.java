package com.budgetfriendly.bmsbudgetservice.common;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

@Component
public class CommonLogics {

   public int getMonth(Date expenseDate){
       int expenseMonth = 0;
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       String date = format.format(expenseDate);
       LocalDate currentDate = LocalDate.parse(date);
       Month currentMonth = currentDate.getMonth();
       String month = String.valueOf(currentMonth);

       switch (month){
           case "JANUARY":
               expenseMonth = 1;
               break;

           case "FEBRUARY":
               expenseMonth = 2;
               break;

           case "MARCH":
               expenseMonth = 3;
               break;

           case "APRIL":
               expenseMonth = 4;
               break;

           case "MAY":
               expenseMonth = 5;
               break;

           case "JUNE":
               expenseMonth = 6;
               break;

           case "JULY":
               expenseMonth = 7;
               break;

           case "AUGUST":
               expenseMonth = 8;
               break;

           case "SEPTEMBER":
               expenseMonth = 9;
               break;

           case "OCTOBER":
               expenseMonth = 10;
               break;

           case "NOVEMBER":
               expenseMonth = 11;
               break;

           case "DECEMBER":
               expenseMonth = 12;
               break;
       }
       return expenseMonth;
   }

   public int getYear(Date expenseDate){
       int expenseYear = 0;
       SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
       String date = format.format(expenseDate);
       LocalDate currentDate = LocalDate.parse(date);
       expenseYear = currentDate.getYear();
       return expenseYear;
   }
}
