package com.trots.oxtest.service.impl;

import com.trots.oxtest.dto.ClientDTO;
import com.trots.oxtest.mapper.ClientMapper;
import com.trots.oxtest.model.entity.ClientEntity;
import com.trots.oxtest.repository.ClientRepository;
import com.trots.oxtest.service.ClientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public ClientDTO save(ClientDTO clientDto) {
        return clientMapper.toDto(clientRepository.save(clientMapper.toEntity(clientDto)));
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDTO findById(Long id) {
        ClientEntity client = clientRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Task not found", id));
        return clientMapper.toDto(client);
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientMapper.toDtos(clientRepository.findAll());
    }

}
