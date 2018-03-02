package util;

import dao.SupportCityDAO;

import java.lang.reflect.Parameter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenjunhao on 2017/11/6.
 */
public class VerifyInput {
    private static final String REG_EXP_EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    private static final String REG_EXP_USERNAME_PASSWORD = "^[A-Za-z0-9]+$";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final SimpleDateFormat sdtf =  new SimpleDateFormat(DATETIME_FORMAT);
    public static final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    private static Pattern pattern = null;
    private static Matcher matcher = null;
    //Method to verify username or password
    //if success then return true
    //cannot not pass the verification then return false
    public static boolean verifyUNPS(String str){
        pattern = Pattern.compile(REG_EXP_USERNAME_PASSWORD);
        matcher = pattern.matcher(str);
        return matcher.find();
    }
    public static boolean verifyEmail(String str){
        pattern = Pattern.compile(REG_EXP_EMAIL);
        matcher = pattern.matcher(str);
        return matcher.find();
    }
    //verifyDate only validate the format
    public static boolean verifyDate(String datestr){
        boolean flag = false;
        try {
            String[] date_arr = datestr.split("-");
            int[] date_int_arr = new int[3];
            for (int i = 0; i<date_arr.length;i++){
                date_int_arr[i] = Integer.parseInt(date_arr[i]);
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setLenient(false);
            calendar.set(date_int_arr[0],date_int_arr[1]-1,date_int_arr[2]);
            calendar.getTime();
            flag = true;


        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    //verifyDateTime only validate the format
    public static boolean verifyDateTime(String datetime){
        //yyyy-MM-dd hh:mm
        boolean flag = false;
        try {
            String[] datetime_arr = datetime.split(" ");
            if(verifyDate(datetime_arr[0])){
                int[] time_int_arr = new int[2];
                String[] time_str_arr = datetime_arr[1].split(":");
                for(int i =0; i<time_str_arr.length;i++){
                    time_int_arr[i] = Integer.parseInt(time_str_arr[i]);
                }
                if(time_int_arr[0]<24 && time_int_arr[0]>=0 && time_int_arr[1]>=0 && time_int_arr[1]<=59){
                    flag = true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return flag;
    }
    public static boolean verifyCity(String city){
        ArrayList<String> supportCity = SupportCityDAO.getInstance().getSupportCity();
        return supportCity.contains(city);
    }
    public static boolean dateAfterToday(String date_str){
        boolean flag = false;
        try {
            if(verifyDate(date_str)){
                Date date_today = sdf.parse(sdf.format(Calendar.getInstance().getTime()));
                Date date_input = sdf.parse(date_str);
                if (date_input.compareTo(date_today)>0){
                    flag = true;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return flag;
    }



}
