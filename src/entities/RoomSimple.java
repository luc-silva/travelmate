package entities;

import entities.enums.RoomStatus;

public abstract class RoomSimple extends Room{
    public RoomSimple(RoomStatus status){
        super(status);
        this.dailyPrice = 79.50;
        this.beds.add(new Bed("Single"));
    }
    @Override
    public void getBeds(){
        for(Bed bed : this.beds){
            System.out.print(bed.toString());
        }
    }

}
