package com.trots.oxtest.model.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientEntity extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String companyName;
    @Column(nullable = false)
    private String industry;
    @Column(nullable = false, unique = true)
    private String address;
    @Column(nullable = false, updatable = false)
    private String parentUsername;
    @Column(nullable = false, updatable = false)
    private String username;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactEntity> contacts;

}
