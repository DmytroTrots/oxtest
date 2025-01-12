package com.trots.oxtest.service.impl;

import com.trots.oxtest.dto.ClientDTO;
import com.trots.oxtest.dto.ContactDTO;
import com.trots.oxtest.dto.UserDTO;
import com.trots.oxtest.mapper.ClientMapper;
import com.trots.oxtest.mapper.ContactMapper;
import com.trots.oxtest.mapper.UserMapper;
import com.trots.oxtest.model.entity.ContactEntity;
import com.trots.oxtest.service.ClientService;
import com.trots.oxtest.service.ContactService;
import com.trots.oxtest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserClientContactManager {

    private final ClientService clientService;
    private final ContactService contactService;
    private final UserService userService;
    private final ClientMapper clientMapper;
    private final UserMapper userMapper;
    private final ContactMapper contactMapper;

    @Transactional
    public ClientDTO saveClientWithUser(ClientDTO clientDTO) {
        UserDTO savedUser = userService.save(clientDTO.getUser());
        clientDTO.setUser(savedUser);
        return clientService.save(clientDTO);
    }

    @Transactional
    public ContactDTO saveContactWithUser(ContactDTO contactDTO) {
        ClientDTO existingClientDto = clientService.findById(contactDTO.getClientId());
        UserDTO savedUser = userService.save(contactDTO.getUser());
        contactDTO.setUser(savedUser);

        ContactEntity contactToSave = contactMapper.toEntity(contactDTO);
        contactToSave.setClient(clientMapper.toEntity(existingClientDto));
        return contactService.save(contactToSave);
    }

}
