package entities;

import entities.enums.RoomStatus;

import java.util.ArrayList;
import java.util.List;

public abstract class Room {
    protected double dailyPrice;
    protected List<Bed> beds = new ArrayList<>();
    protected RoomStatus status;
    protected Room(RoomStatus status){
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

    public abstract void getBeds();

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Status: " + this.status);
        sb.append("Price: " + this.dailyPrice);
        return sb.toString();
    }
}
