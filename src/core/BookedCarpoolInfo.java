package core;

/**
 * Created by admin on 2017/8/31.
 */
public class BookedCarpoolInfo {
    private CarpoolInfo carpoolInfo;
    private String reference_number;
    private String seats;

    public CarpoolInfo getCarpoolInfo() {
        return carpoolInfo;
    }

    public String getReference_number() {
        return reference_number;
    }

    public String getSeats() {
        return seats;
    }

    public BookedCarpoolInfo(CarpoolInfo carpoolInfo, String reference_number, String seats) {
        this.carpoolInfo = carpoolInfo;
        this.reference_number = reference_number;
        this.seats = seats;
    }
}
