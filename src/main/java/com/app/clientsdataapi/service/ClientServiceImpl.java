package com.app.clientsdataapi.service;

import com.app.clientsdataapi.dto.ClientDTO;
import com.app.clientsdataapi.entity.Client;
import com.app.clientsdataapi.entity.Gender;
import com.app.clientsdataapi.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createClient(ClientDTO clientDTO) {
        Gender gender;
        if (clientDTO.getGender().equals(Gender.MALE.name())) {
            gender = Gender.MALE;
        } else {
            gender = Gender.FEMALE;
        }

        Client client = new Client(
                clientDTO.getFirstname(),
                clientDTO.getLastname(),
                clientDTO.getMiddlename(),
                clientDTO.getYearOfBirth(),
                gender,
                clientDTO.getOccupation()
        );

        repository.save(client);
    }

    @Override
    public Page<Client> getClients(Integer pageNumber) {
        if (pageNumber==null) {
            pageNumber = 0;
        }
        int pageSize = 5;
        PageRequest pageReq = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(pageReq);
    }

    @Override
    public Client findById(String id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public void updateClient(String id, ClientDTO clientDTO) {
        Client client = findById(id);
        if (clientDTO.getFirstname()!= null) {
            client.setFirstname(clientDTO.getFirstname());
        }
        if (clientDTO.getLastname()!= null) {
            client.setLastname(clientDTO.getLastname());
        }
        if (clientDTO.getMiddlename()!= null) {
            client.setMiddlename(clientDTO.getMiddlename());
        }
        if (clientDTO.getGender()!= null) {
            client.setGender(Gender.MALE.name().equals(clientDTO.getGender()) ? Gender.MALE : Gender.FEMALE );
        }
        if (clientDTO.getYearOfBirth()!= null) {
            client.setYearOfBirth(clientDTO.getYearOfBirth());
        }
        if (clientDTO.getOccupation()!= null) {
            client.setOccupation(clientDTO.getOccupation());
        }
        repository.save(client);
    }

    @Override
    public void deleteClient(Client client) {
        repository.delete(client);
    }
}
