package com.laptrinhdidong.electroniccomunications.utils;

import java.util.HashMap;

public class TimeLesson {

    public static HashMap<String, String> mapTimeLesson = new HashMap<>();
    public static TimeLesson timeLesson;

    public static TimeLesson getInstance() {
        if (timeLesson == null) {
            timeLesson = new TimeLesson();
            mapTimeLesson.put("1", "7h");
            mapTimeLesson.put("2", "7h50");
            mapTimeLesson.put("3", "8h50");
            mapTimeLesson.put("4", "9h40");
            mapTimeLesson.put("5", "10h40");
            mapTimeLesson.put("7", "12h30");
            mapTimeLesson.put("8", "13h20");
            mapTimeLesson.put("9", "14h20");
            mapTimeLesson.put("10", "15h10");
            mapTimeLesson.put("11", "16h10");
            mapTimeLesson.put("12", "17h");
        }
        return timeLesson;
    }

    public String getValue(String key) {
        return mapTimeLesson.get(key);
    }
}
