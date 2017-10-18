package core;

/**
 * Created by admin on 2017/8/15.
 */
public class VehicleOwnerInfo {
    /*
    uid = 用户id，唯一
    userlevel = 用户等级，完成任务经验增加
     */
    private int uid;
    private int userlevel;
    private String nickname;
    private String vehicle;

    public VehicleOwnerInfo(int uid, int userlevel, String nickname, String vehicle) {
        this.uid = uid;
        this.userlevel = userlevel;
        this.nickname = nickname;
        this.vehicle = vehicle;
    }

    public VehicleOwnerInfo(int uid, String vehicle) {
        this.uid = uid;
        this.vehicle = vehicle;
    }

    public int getUid() {
        return uid;
    }

    public int getUserlevel() {
        return userlevel;
    }

    public String getNickname() {
        return nickname;
    }

    public String getVehicle() {
        return vehicle;
    }
}
