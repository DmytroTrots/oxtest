package com.trots.oxtest.service.impl;

import com.trots.oxtest.dto.ContactDTO;
import com.trots.oxtest.exception.ResourceNotFoundException;
import com.trots.oxtest.mapper.ContactMapper;
import com.trots.oxtest.model.entity.ContactEntity;
import com.trots.oxtest.repository.ContactRepository;
import com.trots.oxtest.service.ContactService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public ContactDTO save(ContactEntity contact) {
        return contactMapper.toDto(contactRepository.save(contact));
    }

    @Override
    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    @Override
    public ContactDTO findById(Long id) {
        ContactEntity contact = contactRepository.findById(id).orElseThrow(() ->
               new ResourceNotFoundException(ContactEntity.class, id));
        return contactMapper.toDto(contact);
    }

    @Override
    public List<ContactDTO> findAll() {
        return contactMapper.toDtos(contactRepository.findAll());
    }

    @Override
    public List<ContactDTO> findAllByClientId(Long clientId) {
        return contactMapper.toDtos(contactRepository.findAllByClientId(clientId));
    }

    @Override
    public ContactDTO findByUsername(String username) {
        ContactEntity contact = contactRepository.findByUsername(username).orElseThrow(() ->
                 new ResourceNotFoundException(ContactEntity.class, username));
        return contactMapper.toDto(contact);
    }

    @Override
    @Transactional
    public ContactDTO update(ContactDTO contactDTO) {
        ContactEntity existingContact = contactRepository.findById(contactDTO.getId()).orElseThrow(() ->
               new ResourceNotFoundException(ContactEntity.class, contactDTO.getId()));

        existingContact.setEmail(contactDTO.getEmail());
        existingContact.setPhone(contactDTO.getPhone());
        existingContact.setFirstName(contactDTO.getFirstName());
        existingContact.setLastName(contactDTO.getLastName());
        return contactMapper.toDto(existingContact);
    }

}
