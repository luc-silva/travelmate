package model.dao;

import db.Database;
import model.dao.implementations.ClientDAOJDBC;
import model.dao.implementations.RoomDAOJDBC;

import java.sql.Connection;

public class DaoFactory {
    public static ClientDAO createClientDao(){
        return new ClientDAOJDBC( Database.connectDatabase());
    }
    public static RoomDAO createRoomDao(){
        return new RoomDAOJDBC(Database.connectDatabase());
    }
}
