package model.dao;

import db.Database;
import model.dao.implementations.ClientDAOJDBC;

import java.sql.Connection;

public class DaoFactory {
    public static ClientDAO createClientDao(){
        return new ClientDAOJDBC( Database.connectDatabase());
    }
}
