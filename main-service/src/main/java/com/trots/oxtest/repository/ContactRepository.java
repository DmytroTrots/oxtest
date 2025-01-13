package com.trots.oxtest.repository;

import com.trots.oxtest.model.entity.ContactEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {

    List<ContactEntity> findAllByClientId(Long id);

    Optional<ContactEntity> findByUserId(Long userId);
}
