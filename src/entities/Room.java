package entities;

import entities.enums.RoomStatus;

public abstract class Room {
    protected double dailyPrice;
    protected RoomStatus status;
    protected Room(Double dailyPrice, RoomStatus status){
        this.dailyPrice = dailyPrice;
        this.status = status;
    }
    protected Room(){
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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Status: " + this.status);
        sb.append("Price: " + this.dailyPrice);
        return "";
    }
}
