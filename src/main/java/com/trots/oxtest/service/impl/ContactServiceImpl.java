package com.trots.oxtest.service.impl;

import com.trots.oxtest.dto.ContactDTO;
import com.trots.oxtest.mapper.ContactMapper;
import com.trots.oxtest.model.entity.ContactEntity;
import com.trots.oxtest.repository.ContactRepository;
import com.trots.oxtest.service.ContactService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public ContactDTO save(ContactDTO contactDto) {
        return contactMapper.toDto(contactRepository.save(contactMapper.toEntity(contactDto)));
    }

    @Override
    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    @Override
    public ContactDTO findById(Long id) {
        ContactEntity contact = contactRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Contact not found", id));
        return contactMapper.toDto(contact);
    }

    @Override
    public List<ContactDTO> findAll() {
        return contactMapper.toDtos(contactRepository.findAll());
    }

}
