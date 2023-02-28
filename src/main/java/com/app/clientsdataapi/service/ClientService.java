package com.app.clientsdataapi.service;

import com.app.clientsdataapi.dto.ClientDTO;
import com.app.clientsdataapi.entity.Client;
import org.springframework.data.domain.Page;


public interface ClientService {
    void createClient(ClientDTO client);
     Page<Client> getClients(Integer page);

    Client findById(String id);

    void updateClient(String id, ClientDTO clientDTO);

    void deleteClient(Client client);
}
