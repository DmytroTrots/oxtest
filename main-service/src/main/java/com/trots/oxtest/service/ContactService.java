package com.trots.oxtest.service;

import com.trots.oxtest.dto.ContactDTO;
import com.trots.oxtest.model.entity.ContactEntity;
import java.util.List;

public interface ContactService {


    ContactDTO save(ContactEntity contactDto);

    void deleteById(Long id);

    ContactDTO findById(Long id);

    List<ContactDTO> findAll();

    List<ContactDTO> findAllByClientId(Long clientId);

    ContactDTO findByUserId(Long userId);

    ContactDTO update(ContactDTO contactDTO);
}
