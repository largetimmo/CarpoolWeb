package pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by admin on 2017/8/15.
 */
@Entity
@Table(name = "USER_REG")
public class UserReg extends UserInfo{
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

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCell() {
        return cell;
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
