package pojo;

/**
 * Created by admin on 2017/8/31.
 */
public class BookedCarpoolInfo {
    private CarpoolInfo carpoolInfo;
    private String reference_number;
    private int seats;

    public CarpoolInfo getCarpoolInfo() {
        return carpoolInfo;
    }

    public String getRef() {
        return reference_number;
    }

    public int getSeats() {
        return seats;
    }

    public BookedCarpoolInfo(CarpoolInfo carpoolInfo, String reference_number, int seats) {
        this.carpoolInfo = carpoolInfo;
        this.reference_number = reference_number;
        this.seats = seats;
    }
    public BookedCarpoolInfo(){

    }

    public void setCarpoolInfo(CarpoolInfo carpoolInfo) {
        this.carpoolInfo = carpoolInfo;
    }

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
