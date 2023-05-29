package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    private static Connection connection;

    //inicia conexão
    public static Connection connectDatabase(){
        if(connection == null){
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                connection = DriverManager.getConnection(url, props);
            } catch (SQLException e){
                System.out.print(e.getMessage());
            }
        }
        return connection;
    }

    //fecha conexão
    public static void closeConnection(){
        if(connection != null){
            try{
                connection.close();
            } catch (SQLException e){
                System.out.print(e.getMessage());
            }
        }
    }

    //lê arquivo de propriedades e cria um objeto com os valores contidos
    private static Properties loadProperties(){
        Properties props = new Properties();
        try (FileInputStream FIS = new FileInputStream("database.properties")){
            props.load(FIS);
        } catch(IOException e){
            System.out.print(e.getMessage());
        }
        return props;
    }
}
