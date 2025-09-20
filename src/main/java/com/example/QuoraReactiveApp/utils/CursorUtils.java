package com.example.QuoraReactiveApp.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CursorUtils {

    public static boolean IsValidCursorTimeStamp(String cursorTimeStamp){
        if(cursorTimeStamp==null || cursorTimeStamp.isEmpty()){
            return false;
        }
        try {
            LocalDateTime.parse(cursorTimeStamp);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}