package com.trots.oxtest.controller;

import com.trots.oxtest.dto.ContactDTO;
import com.trots.oxtest.service.ContactService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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
@RequestMapping("/api/v_0/contact")
@RequiredArgsConstructor
@CacheConfig(cacheNames = "contact")
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    @Caching(
            put = {@CachePut(key = "'allContacts'")},
            cacheable = {@Cacheable(key = "'allContacts'")}
    )
    public List<ContactDTO> findAll() {
       return contactService.findAll();
    }

    @GetMapping("/{id}")
    public ContactDTO findById(@PathVariable Long id) {
        return contactService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @CacheEvict(key = "'allContacts'")
    public void deleteById(@PathVariable Long id) {
        contactService.deleteById(id);
    }

    @PostMapping
    @CacheEvict(key = "'allContacts'")
    public ContactDTO save(@RequestBody ContactDTO contactDTO) {
        return contactService.save(contactDTO);
    }

    @PutMapping("/{id}")
    @CacheEvict(key = "'allContacts'")
    public ContactDTO updateById(@PathVariable Long id, @RequestBody ContactDTO contactDTO) {
        return contactService.updateById(id, contactDTO);
    }

}
