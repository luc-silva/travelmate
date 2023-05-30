package application;

import model.dao.ClientDAO;
import model.dao.DaoFactory;
import model.dao.RoomDAO;
import model.entities.Client;
import model.entities.Room;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RoomDAO roomDAO= DaoFactory.createRoomDao();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Selecione uma sala:");
        int escolha = scanner.nextInt();

        Room room = roomDAO.findRoomByNumber(escolha);
        if(room != null){
            System.out.print(room);
        } else {
            System.out.print("Nenhuma Sala Encontrada");
        }
    }
}