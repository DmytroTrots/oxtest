package com.trots.oxtest.service.impl;

import com.trots.oxtest.dto.ClientDTO;
import com.trots.oxtest.dto.ContactDTO;
import com.trots.oxtest.mapper.ClientMapper;
import com.trots.oxtest.mapper.ContactMapper;
import com.trots.oxtest.model.entity.ContactEntity;
import com.trots.oxtest.service.ClientService;
import com.trots.oxtest.service.ContactService;
import com.trots.oxtest.util.KeycloakHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserClientContactManager {

    private final ClientService clientService;
    private final ContactService contactService;
    private final ClientMapper clientMapper;
    private final ContactMapper contactMapper;
    private final KeycloakHelper keycloakHelper;
    private final KeycloakUserService keycloakUserService;

    @Transactional
    public ClientDTO saveClientWithUser(ClientDTO clientDTO) {
        clientDTO.setParentUsername(keycloakHelper.getUsername());
        keycloakUserService.createUser(clientDTO.getUsername(), clientDTO.getEmail(), clientDTO.getPassword(), "ROLE_CLIENT");
        return clientService.save(clientDTO);
    }

    @Transactional
    public ContactDTO saveContactWithUser(ContactDTO contactDTO) {
        ClientDTO existingClientDto = clientService.findById(contactDTO.getClientId());
        contactDTO.setParentUsername(existingClientDto.getUsername());
        keycloakUserService.createUser(contactDTO.getUsername(), contactDTO.getEmail(), contactDTO.getPassword(), "ROLE_CONTACT");
        ContactEntity contactToSave = contactMapper.toEntity(contactDTO);
        contactToSave.setClient(clientMapper.toEntity(existingClientDto));
        return contactService.save(contactToSave);
    }

}
