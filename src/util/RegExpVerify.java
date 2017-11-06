package util;

import java.lang.reflect.Parameter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenjunhao on 2017/11/6.
 */
public class RegExpVerify {
    private static final String REG_EXP_EMAIL = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    private static final String REG_EXP_USERNAME_PASSWORD = "^[A-Za-z0-9]+$";
    private static Pattern pattern = null;
    private static Matcher matcher = null;
    //Method to verify username or password
    //if success then return true
    //cannot not pass the verification then return false
    public static boolean verifyUNPS(String str){
        pattern = Pattern.compile(REG_EXP_USERNAME_PASSWORD);
        matcher = pattern.matcher(str);
        if (matcher.find()){
            return true;
        }
        return false;
    }
    public static boolean verifyEmail(String str){
        pattern = Pattern.compile(REG_EXP_EMAIL);
        matcher = pattern.matcher(str);
        if (matcher.find()){
            return  true;
        }
        return false;
    }
}
