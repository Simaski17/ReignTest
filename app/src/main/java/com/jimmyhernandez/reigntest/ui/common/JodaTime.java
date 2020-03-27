package com.jimmyhernandez.reigntest.ui.common;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by jimmyhernandez on 31-01-18.
 */

public class JodaTime {

    public String resultTime;
    public ArrayList<String> months = new ArrayList<String>(Arrays.asList("Ene", "Feb", "Mar", "Abr", "May",
            "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"));

    long secMilli = 1000;
    long miMilli = secMilli * 60;
    long hoursMilli = miMilli * 60;
    long daysMilli = hoursMilli * 24;

    public String transformDate(String photoTimestamp) {

        DateTime utc = new DateTime(System.currentTimeMillis(), DateTimeZone.getDefault());
        DateTime utc2 = new DateTime(photoTimestamp, DateTimeZone.getDefault());
        long current = utc.getMillis();
        long current2 = utc2.getMillis();

        long difference = current - current2;

        if (difference < 0) {
            difference = difference * -1;
        }

        long daysPassed = difference / daysMilli;
        difference = difference % daysMilli;

        long hoursElapsed = difference / hoursMilli;
        difference = difference % hoursMilli;

        long minutesElapsed = difference / miMilli;
        difference = difference % miMilli;


        if (daysPassed > 7) {
            if (utc.getYear() == utc2.getYear()) {
                resultTime = utc2.getDayOfMonth() + " " + months.get(utc2.getMonthOfYear() - 1);
            } else {
                resultTime = utc2.getDayOfMonth() + " " + months.get(utc2.getMonthOfYear() - 1) + " " + utc2.getYear();
            }
        } else if (daysPassed > 0 && daysPassed <= 7) {
            if (daysPassed > 1) {
                resultTime = "Hace " + daysPassed + " Días";
            } else {
                resultTime = "Hace " + daysPassed + " Día";
            }
        } else if (hoursElapsed > 0 && hoursElapsed <= 24) {
            if (hoursElapsed > 1) {
                resultTime = "Hace " + hoursElapsed + " Horas";
            } else {
                resultTime = "Hace " + hoursElapsed + " Hora";
            }
        } else if (minutesElapsed > 0 && minutesElapsed < 60) {
            if (minutesElapsed > 1) {
                resultTime = "Hace " + minutesElapsed + " Minutos";
            } else {
                resultTime = "Hace " + minutesElapsed + " Minuto";
            }
        } else {
            resultTime = "Menos de un minuto";
        }

        return resultTime;

    }

}
