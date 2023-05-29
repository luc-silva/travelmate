package model.dao;

import model.entities.Client;

public interface ClientDAO {
    public Client findClientById(Integer id);
    public void addClient(Client client);
    public void deleteClientById(Integer id);
    public void updateClientById(Integer id, Client updatedClient);
}
