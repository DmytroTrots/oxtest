package com.trots.oxtest.controller;

import com.trots.oxtest.dto.ClientDTO;
import com.trots.oxtest.service.ClientService;
import com.trots.oxtest.service.impl.UserClientContactManager;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CacheConfig(cacheNames = "client")
public class ClientController {

    private static final String BASE_MAPPING = "/api/v_0/client";

    private final ClientService clientService;
    private final UserClientContactManager userClientContactManager;

    @GetMapping(BASE_MAPPING)
    @Cacheable(key = "'allClients'")
    public List<ClientDTO> findAll() {
       return clientService.findAll();
    }

    @GetMapping(BASE_MAPPING + "/{id}")
    public ClientDTO findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @GetMapping(BASE_MAPPING + "/user/{id}")
    public ClientDTO findByUserId(@PathVariable Long id) {
        return clientService.findByUserId(id);
    }

    @DeleteMapping(BASE_MAPPING + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(key = "'allClients'")
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }

    @PostMapping("/public" + BASE_MAPPING)
    @CacheEvict(key = "'allClients'")
    public ClientDTO save(@Valid @RequestBody ClientDTO clientDTO) {
        return userClientContactManager.saveClientWithUser(clientDTO);
    }

    @PutMapping(BASE_MAPPING)
    @CacheEvict(key = "'allClients'")
    public ClientDTO update(@Valid @RequestBody ClientDTO clientDTO) {
        return clientService.update(clientDTO);
    }

}
