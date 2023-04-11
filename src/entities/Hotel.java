package entities;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name;
    private Double maintenanceFee;
    private List<Room> rooms = new ArrayList<>();

    public Hotel(String name, Double maintenanceFee) {
        this.name = name;
        this.maintenanceFee = maintenanceFee;
    }
    public Hotel(String name){
        this.name = name;
        this.maintenanceFee = 34.0;
    }

    public String getName() {
        return name;
    }

    public Double getMaintenanceFee() {
        return maintenanceFee;
    }

    public void setMaintenanceFee(Double maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void listAllRooms(){
        for(int c = 0; c < rooms.size(); c++){
            System.out.printf("Room %d", c);
            rooms.get(c).toString();
        }
    }

    public void listAvailableRooms(){
        int total = 0;
        for(Room room : rooms){
            if(room.getStatus().equals(1)){
                total ++;
                System.out.print(room.toString());
            }
        }
        if(total == 0 || total == 1){
            System.out.printf("There is %d room available.");
        } else {
            System.out.printf("There are %d rooms available.");
        }
    }
}
