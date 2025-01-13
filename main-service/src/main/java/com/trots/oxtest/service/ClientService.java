package com.trots.oxtest.service;

import com.trots.oxtest.dto.ClientDTO;
import java.util.List;

public interface ClientService {

    ClientDTO save(ClientDTO clientDto);

    void deleteById(Long id);

    ClientDTO findById(Long id);

    ClientDTO findByUserId(Long userId);

    List<ClientDTO> findAll();

    ClientDTO update(ClientDTO clientDTO);
}
