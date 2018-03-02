package pojo;



/**
 * Created by admin on 2017/8/15.
 */

public class UserReg{
    private int uid;
    private String username;
    private String password;
    private String email;
    private String nickname;
    private String cell;
    public UserReg (String username, String password, String email, String nickname, String cell) {
        this.username = username;
        this.password = password;
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
    public UserReg(){

    }

    public String getUsername() {
        return username;
    }
    public int getUid() {
        return uid;
    }
    public String getPassword() {
        return password;
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

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    @Override
    public String toString() {
        return "UserReg{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", cell='" + cell + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
