package com.trots.oxtest.service.impl;

import com.trots.oxtest.dto.ClientDTO;
import com.trots.oxtest.exception.ResourceNotFoundException;
import com.trots.oxtest.mapper.ClientMapper;
import com.trots.oxtest.model.entity.ClientEntity;
import com.trots.oxtest.repository.ClientRepository;
import com.trots.oxtest.service.ClientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
              new ResourceNotFoundException(ClientEntity.class, id));
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDTO findByUserId(Long userId) {
        ClientEntity client = clientRepository.findByUserId(userId).orElseThrow(() ->
              new ResourceNotFoundException(ClientEntity.class, userId));
        return clientMapper.toDto(client);
    }

    @Override
    public List<ClientDTO> findAll() {
        return clientMapper.toDtos(clientRepository.findAll());
    }

    @Override
    @Transactional
    public ClientDTO update(ClientDTO clientDTO) {
        ClientEntity existingClient = clientRepository.findById(clientDTO.getId()).orElseThrow(() ->
              new ResourceNotFoundException(ClientEntity.class, clientDTO.getId()));

        existingClient.setAddress(clientDTO.getAddress());
        existingClient.setIndustry(clientDTO.getIndustry());
        existingClient.setCompanyName(clientDTO.getCompanyName());
        existingClient.getUser().setEmail(clientDTO.getUser().getEmail());
        return clientMapper.toDto(existingClient);
    }

}
