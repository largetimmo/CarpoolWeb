package pojo;

/**
 * Created by admin on 2017/8/15.
 */
public class CarpoolInfo {
    /*
        user = 用户信息
        id =  那一趟车的id
        price = 每人的价格
        capacity = 剩余座位
        from = 出发地
        to = 目的地
     */
    private VehicleOwnerInfo user;
    private int id;
    private int price;
    private int capacity;
    private DateTime dateTime;
    private int remainseat;
    private String from;
    private String to;

    public String getDeparture() {
        return from;
    }

    public String getDestination() {
        return to;
    }
    public CarpoolInfo(){

    }
    public CarpoolInfo(VehicleOwnerInfo user, int id, int price, int capacity, DateTime dateTime, int remainseat, String from, String to) {
        this.user = user;
        this.id = id;
        this.price = price;
        this.capacity = capacity;
        this.dateTime = dateTime;
        this.remainseat = remainseat;
        this.from = from;
        this.to = to;
    }

    public CarpoolInfo(int price, int capacity, DateTime dateTime, int remainseat, String from, String to) {
        this.price = price;
        this.capacity = capacity;
        this.dateTime = dateTime;
        this.remainseat = remainseat;
        this.from = from;
        this.to = to;
    }

    public CarpoolInfo(VehicleOwnerInfo user, int id, int price, int capacity, DateTime dateTime, int remainseat) {
        this.user = user;
        this.id = id;
        this.price = price;
        this.capacity = capacity;
        this.dateTime = dateTime;
        this.remainseat = remainseat;


    }

    public VehicleOwnerInfo getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getCapacity() {
        return capacity;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public int getRemainseat() {
        return remainseat;
    }

    public void setUser(VehicleOwnerInfo user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setRemainseat(int remainseat) {
        this.remainseat = remainseat;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
