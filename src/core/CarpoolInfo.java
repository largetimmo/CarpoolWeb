package core;

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
    private String remainseat;
    private String from;
    private String to;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public CarpoolInfo(VehicleOwnerInfo user, int price, int capacity, DateTime dateTime, String remainseat, String from, String to) {

        this.user = user;
        this.price = price;
        this.capacity = capacity;
        this.dateTime = dateTime;
        this.remainseat = remainseat;
        this.from = from;
        this.to = to;
    }

    public CarpoolInfo(VehicleOwnerInfo user, int id, int price, int capacity, DateTime dateTime, String remainseat) {
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

    public String getRemainseat() {
        return remainseat;
    }

}
