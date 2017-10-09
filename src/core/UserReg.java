package core;

/**
 * Created by admin on 2017/8/15.
 */
public class UserReg extends UserInfo{
    private String email;
    private String nickname;

    public UserReg (String username, String password, String email,String nickname) {
        this.username = username;
        this.password = util.Md5encrypt.getMd5(password);
        this.email = email;
        this.nickname = nickname;
    }


    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
