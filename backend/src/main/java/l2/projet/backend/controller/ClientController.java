package l2.projet.backend.controller;

import l2.projet.backend.Exception.ClientNotFound;
import l2.projet.backend.Exception.InternalServerError;
import l2.projet.backend.model.Client;
import l2.projet.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:3000/")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @PostMapping("/clients")
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        try{
            Client newClient = new Client(client.getNom(), client.getSolde());
            clientRepository.save(newClient);
            return new ResponseEntity<>(newClient, HttpStatus.CREATED);
        }catch (Exception ex){
            throw new InternalServerError(ex.getMessage());
        }
    }

    // Update user
    @PutMapping("clients/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client client) {

        Optional<Client> clientList = clientRepository.findById(id);
        if (clientList.isPresent()) {
            Client _client = clientList.get();
            _client.setNom(client.getNom());
            _client.setSolde(client.getSolde());
            return new ResponseEntity<>(clientRepository.save(_client), HttpStatus.OK);
        } else {
            throw new ClientNotFound("Invalid Client Id");
        }
    }

    // Get all Users
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getAllClient() {

        try {
            List<Client> clientList = new ArrayList<Client>();
            clientRepository.findAll(Sort.by(Sort.Order.by("numCompte"))).forEach(clientList::add);
            return new ResponseEntity<>(clientList, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

    // Get user by ID
    @GetMapping("clients/{id}")
    public ResponseEntity<Client> getUserByID(@PathVariable("id") Long id) {

        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            return new ResponseEntity<>(optionalClient.get(), HttpStatus.OK);
        } else {
            throw new ClientNotFound("Invalid User Id");
        }

    }

    // Delete user
    @DeleteMapping("clients/{id}")
    public ResponseEntity<Client> deleteUser(@PathVariable("id") Long id) {

        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            clientRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new ClientNotFound("Invalid User Id");
        }
    }

    //search by name
    @GetMapping("/clients/{nom}")
    public ResponseEntity<List<Client>> getClientByNom(@PathVariable("nom") String nom) {
        try {
            List<Client> clientList = new ArrayList<>();
            clientRepository.findByNom(nom).forEach(clientList::add);
            return new ResponseEntity<>(clientList, HttpStatus.OK);
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
}
