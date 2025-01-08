package com.trots.oxtest.controller;

import com.trots.oxtest.dto.ClientDTO;
import com.trots.oxtest.service.ClientService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v_0/client")
@RequiredArgsConstructor
@CacheConfig(cacheNames = "client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    @Cacheable(key = "'allClients'")
    public List<ClientDTO> findAll() {
       return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ClientDTO findById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @CacheEvict(key = "'allClients'")
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }

    @PostMapping
    @CacheEvict(key = "'allClients'")
    public ClientDTO save(@RequestBody ClientDTO clientDTO) {
        return clientService.save(clientDTO);
    }

    @PutMapping("/{id}")
    @CacheEvict(key = "'allClients'")
    public ClientDTO updateById(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        return clientService.updateById(id, clientDTO);
    }

}
