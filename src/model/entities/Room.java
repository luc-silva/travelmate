package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Room implements Serializable {
    Integer max_capability;
    Integer door_number;
    Client resident;
    Integer id;

    public Room(){}
    public Room(Integer max_capability, Integer door_number, Client resident, Integer id){
        this.max_capability = max_capability;
        this.door_number = door_number;
        this.resident = resident;
        this.id = id;
    }

    public Integer getMax_capability() {
        return max_capability;
    }

    public void setMax_capability(Integer max_capability) {
        this.max_capability = max_capability;
    }

    public Integer getDoor_number() {
        return door_number;
    }

    public void setDoor_number(Integer door_number) {
        this.door_number = door_number;
    }

    public Client getResident() {
        return resident;
    }

    public void setResident(Client resident) {
        this.resident = resident;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id.equals(room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Room{" +
                "max_capability=" + max_capability +
                ", door_number=" + door_number +
                ", resident=" + resident +
                ", id=" + id +
                '}';
    }
}
