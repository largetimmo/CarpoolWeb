package core;

/**
 * Created by admin on 2017/8/15.
 */
public class UserReg extends UserInfo{
    private String email;
    private String nickname;
    private String cell;
    public UserReg (String username, String password, String email,String nickname,String cell) {
        this.username = username;
        this.password = util.Md5encrypt.getMd5(password);
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
