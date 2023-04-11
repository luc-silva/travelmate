package entities;

import entities.enums.RoomStatus;

public class Room {
    private double dailyPrice;
    private RoomStatus status;
    public Room(Double dailyPrice, RoomStatus status){
        this.dailyPrice = dailyPrice;
        this.status = status;
    }
    public Room(){
        this.dailyPrice = 456.0;
        this.status = RoomStatus.EMPTY;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public double getDailyPrice() {
        return dailyPrice;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public RoomStatus getStatus() {
        return status;
    }
}
