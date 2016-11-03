package com.nikita.nepalidateconverter;

import android.util.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by nikita on 10/20/16.
 */

public class DateConverter{

    DateMapper dateMapper;
    // calculation of equivalent english date...

    Date convertedEnglishDate;

    DateConverter() {
        dateMapper = new DateMapper();


    }



    private long getDaysBetweenNepaliDates(int nepYear, int nepMonth, int nepDay) {
        long totalNepDaysCount = 0;
        // count total days in-terms of year
        for (int i = dateMapper.startingNepYear; i < nepYear; i++) {
            for (int j = 0; j < 12; j++) {

                totalNepDaysCount += dateMapper.nepaliYearsMap.get(i)[j];
            }
        }

        // count total days in-terms of month
        for (int j = dateMapper.startingNepMonth; j < nepMonth; j++) {
            totalNepDaysCount += dateMapper.nepaliYearsMap.get(nepYear)[j-1];
        }

        // count total days in-terms of date
        totalNepDaysCount += nepDay - dateMapper.startingNepDay;
        return totalNepDaysCount;

    }

    public Date getConvertedEnglishDate(int nepYear, int nepMonth, int nepDay){

        int engYear = dateMapper.startingEngYear;
        int engMonth = dateMapper.startingEngMonth;
        int engDay = dateMapper.startingEngDay;
        int dayOfWeek = dateMapper.dayOfWeek;

        int endDayOfMonth = 0;
        long totalNepDaysCount = getDaysBetweenNepaliDates(nepYear, nepMonth, nepDay);

        while (totalNepDaysCount != 0) {
            Calendar startingEnglishYearCalender = Calendar.getInstance();
            startingEnglishYearCalender.set(engYear, engMonth, engDay);
            endDayOfMonth = startingEnglishYearCalender.getActualMaximum(Calendar.DAY_OF_MONTH);
            engDay++;
            dayOfWeek++;
            if (engDay > endDayOfMonth) {
                engMonth++;
                engDay = 1;
                if (engMonth > 11) {
                    engYear++;
                    engMonth = 0;
                }
            }
            if (dayOfWeek > 7) {
                dayOfWeek = 1;
            }
            totalNepDaysCount--;
        }

        Calendar requiredDate = Calendar.getInstance();
        requiredDate.set(engYear, engMonth, engDay);

        convertedEnglishDate = requiredDate.getTime();
        return convertedEnglishDate;
    }



}
