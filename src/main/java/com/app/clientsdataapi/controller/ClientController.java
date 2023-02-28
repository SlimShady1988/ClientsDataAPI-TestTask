package com.app.clientsdataapi.controller;

import com.app.clientsdataapi.dto.ClientDTO;
import com.app.clientsdataapi.dto.MessageResponse;
import com.app.clientsdataapi.entity.Client;
import com.app.clientsdataapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createClient(@RequestBody ClientDTO clientDTO) {
        clientService.createClient(clientDTO);
        return ResponseEntity.ok().body(
                new MessageResponse(String.format("Client '%s' was successfully created", clientDTO.getFirstname()))
        );
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Client>> clientsList(@RequestParam(value = "page", required = false) Integer page) {
        Page<Client> clients = clientService.getClients(page);
        return ResponseEntity.ok().body(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> profile(@PathVariable("id") String id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PutMapping(value = "/{id}/edit")
    public ResponseEntity<?> edit(@PathVariable("id") String id, @RequestBody ClientDTO clientDTO) {
        clientService.updateClient(id, clientDTO);
        return ResponseEntity.ok().body(
                new MessageResponse(String.format("Client '%s' was successfully updated", clientDTO.getFirstname()))
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable( name = "id") String id) {
        var client = clientService.findById(id);
        clientService.deleteClient(client);
        return ResponseEntity.ok().body(new MessageResponse("Client  was successfully deleted"));
    }

}
