package core;

/**
 * Created by admin on 2017/8/21.
 */
public class UserInfo {
    protected String username;
    protected String password;

    public UserInfo(){

    }
    public UserInfo(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
