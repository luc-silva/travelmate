package application;

import model.dao.ClientDAO;
import model.dao.DaoFactory;
import model.dao.RoomDAO;
import model.entities.Client;
import model.entities.Room;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RoomDAO roomDAO = DaoFactory.createRoomDao();
        ClientDAO clientDAO = DaoFactory.createClientDao();

        showHeader();
        System.out.println("Bem vindo(a)! Qual operação deseja executar?");

        int option = 0;
        while (option != 10){
            showOptions();
            option = scanner.nextInt();
            switch (option){
                case 1:
                    List<Room> rooms = roomDAO.listRooms();
                    for(Room room : rooms){
                        System.out.println(room);
                    }
                    break;
                case 2:
                    List<Room> availableRooms = roomDAO.listAvailableRooms();
                    for(Room room : availableRooms){
                        System.out.println(room);
                    }
                    break;
                case 3:
                    Room room = new Room();

                    System.out.println("Insira a capacidade máxima da nova sala: ");
                    int salaCapacidade = scanner.nextInt();
                    System.out.println("Insira o número da nova sala: ");
                    int salaNumero = scanner.nextInt();

                    room.setMax_capability(salaCapacidade);
                    room.setDoor_number(salaNumero);

                    roomDAO.addRoom(room);
                    break;
                case 4:
                    System.out.println("Deseja designar um cliente para a sala [0] ou editar seus detalhes [1]? : ");
                    Integer roomOption = scanner.nextInt();

                    switch (roomOption){
                        case 0:
                            System.out.println("Insira o ID do cliente: ");
                            Integer id = scanner.nextInt();
                            Client client = clientDAO.findClientById(id);

                            System.out.println("Insira o ID da sala: ");
                            Integer roomId = scanner.nextInt();
                            Room roomToAssign = roomDAO.findRoomById(roomId);

                            if(client != null && roomToAssign != null){
                                roomToAssign.setResident(client);
                                roomDAO.updateRoomById(roomToAssign.getId(), roomToAssign);
                                System.out.println("Feito.");
                            } else{
                                System.out.println("Cliente ou sala não encontrados.");
                            }
                            break;

                        case 1:
                            System.out.println("Insira o ID da sala: ");
                            Integer roomToUpdateId = scanner.nextInt();

                            System.out.println("Insira o novo número da sala: ");
                            Integer newNumber = scanner.nextInt();

                            System.out.println("Insira a nova capacidade de pessoas da sala: ");
                            Integer capacity = scanner.nextInt();

                            Room roomToUpdate = roomDAO.findRoomById(roomToUpdateId);
                            if(roomToUpdate != null) {
                                roomToUpdate.setDoor_number(newNumber);
                                roomToUpdate.setMax_capability(capacity);
                                roomDAO.updateRoomById(roomToUpdate.getId(), roomToUpdate);
                            } else {
                                System.out.println("Sala não encontrada.");
                            }
                            break;
                        default:
                            System.out.println("Opção inválida... Voltando ao menu.");
                            break;
                    }
                    break;
                case 5:
                    List<Client> clientList = clientDAO.listClients();
                    if(clientList != null){
                        for(Client client : clientList){
                            System.out.println(client);
                        }
                    } else {
                        System.out.println("Sem clientes disponíveis.");
                    }

                    break;
                case 6:
                    Client client = new Client();
                    System.out.println("Insira o nome do cliente: ");
                    String clientName = scanner.next();
                    System.out.println("Insira a idade do cliente:  ");
                    int clientAge = scanner.nextInt();

                    client.setAge(clientAge);
                    client.setName(clientName);

                    clientDAO.addClient(client);
                    break;
                case 10:
                    System.out.print("Até logo!");
                    break;
                default:
                    System.out.print("Insira uma opção válida.");
                    break;
            }
        }


    }

    private static void showHeader(){
        System.out.println("================ TRAVELMATE ================");
        System.out.println("           Sua agência de viagens!          ");
        System.out.println("============================================");
    }
    private static void showOptions(){
        System.out.println("[1] - Listar todas as salas.");
        System.out.println("[2] - Listar todas as salas disponíveis.");
        System.out.println("[3] - Adicionar uma nova sala.");
        System.out.println("[4] - Atualizar sala.");
        System.out.println("[5] - Listar todos os clientes.");
        System.out.println("[6] - Adicionar um novo cliente.");
        System.out.println("[10] - Sair.");
    }
}