package pojo;


import util.VerifyInput;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by admin on 2017/8/25.
 */
public class DateTime implements Comparable<DateTime>{
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public DateTime(String FormatedDateString){
        String[] yyyymmdd_hhmm = FormatedDateString.split(" ");
        String[] yyyymmdd = yyyymmdd_hhmm[0].split("-");
        String yyyy = yyyymmdd[0];
        String month = yyyymmdd[1];
        String dd = yyyymmdd[2];
        String[] hhmm = yyyymmdd_hhmm[1].split(":");
        String hh = hhmm[0];
        String minute = hhmm[1];
        this.year = Integer.parseInt(yyyy);
        this.month = Integer.parseInt(month);
        this.day = Integer.parseInt(dd);
        this.hour = Integer.parseInt(hh);
        this.minute = Integer.parseInt(minute);
    }
    @Override
    public String toString(){
        //yyyy-mm-dd hh:mm
        String output = year+"-"+month+"-"+day+" "+hour+":"+minute;
        return output;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getYear() {

        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public int compareTo(DateTime o) {
        try {
            Date thisdate = VerifyInput.sdtf.parse(toString());
            Date odate = VerifyInput.sdtf.parse(o.toString());
            if(thisdate.before(odate)){
                return -1;
            }else if(thisdate.after(odate)){
                return 1;
            }
            else {
                return 0;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
