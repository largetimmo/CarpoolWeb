package core;

import com.sun.istack.internal.Nullable;

/**
 * Created by admin on 2017/8/15.
 */
public class UserReg extends UserInfo{
    private String email;
    private String nickname;
    private String cell;
    public UserReg (String username, String password, String email, String nickname, String cell) {
        this.username = username;
        this.password = (password != null)?util.Md5encrypt.getMd5(password):password;
        this.email = email;
        this.nickname = nickname;
        this.cell = cell;
    }
    public UserReg (String username, String email, String nickname, String cell) {
        this.username = username;
        this.email = email;
        this.nickname = nickname;
        this.cell = cell;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCell() {
        return cell;
    }
}
