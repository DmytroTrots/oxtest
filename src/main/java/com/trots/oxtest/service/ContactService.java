package com.trots.oxtest.service;

import com.trots.oxtest.dto.ContactDTO;
import java.util.List;

public interface ContactService {


    ContactDTO save(ContactDTO contactDto);

    void deleteById(Long id);

    ContactDTO findById(Long id);

    List<ContactDTO> findAll();

    ContactDTO updateById(Long id, ContactDTO contactDTO);
}
