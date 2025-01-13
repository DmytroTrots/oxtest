package com.trots.oxtest.controller;

import com.trots.oxtest.dto.ContactDTO;
import com.trots.oxtest.service.ContactService;
import com.trots.oxtest.service.impl.UserClientContactManager;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final UserClientContactManager userClientContactManager;

    @GetMapping
    @Cacheable(key = "'allContacts'")
    public List<ContactDTO> findAll() {
       return contactService.findAll();
    }

    @GetMapping("/client/{id}")
    public List<ContactDTO> findAllByClientId(@PathVariable Long id) {
        return contactService.findAllByClientId(id);
    }

    @GetMapping("/user/{id}")
    public ContactDTO findByUserId(@PathVariable Long id) {
        return contactService.findByUserId(id);
    }

    @GetMapping("/{id}")
    public ContactDTO findById(@PathVariable Long id) {
        return contactService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(key = "'allContacts'")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        contactService.deleteById(id);
    }

    @PostMapping
    @CacheEvict(key = "'allContacts'")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ContactDTO save(@Valid @RequestBody ContactDTO contactDTO) {
        return userClientContactManager.saveContactWithUser(contactDTO);
    }

    @PutMapping
    @CacheEvict(key = "'allContacts'")
    public ContactDTO updateById(@Valid @RequestBody ContactDTO contactDTO) {
        return contactService.update(contactDTO);
    }

}
