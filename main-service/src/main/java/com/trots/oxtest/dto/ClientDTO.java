package com.trots.oxtest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Company name cannot be blank")
    @Size(min = 1, message = "Company name length is too short")
    @Size(max = 200, message = "Company name length is too long")
    private String companyName;

    @NotBlank(message = "Industry cannot be blank")
    @Size(min = 1, message = "Industry length is too short")
    @Size(max = 200, message = "Industry length is too long")
    private String industry;

    @NotBlank(message = "Address cannot be blank")
    @Size(min = 1, message = "Address length is too short")
    @Size(max = 400, message = "Address length is too long")
    private String address;

    @Email
    @NotBlank
    @JsonProperty(access = Access.WRITE_ONLY)
    private String email;

    @NotBlank
    @JsonProperty(access = Access.WRITE_ONLY)
    private String username;

    @NotBlank
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;

    @JsonProperty(access = Access.READ_ONLY)
    private List<ContactDTO> contacts = new ArrayList<>();

    @JsonIgnore
    private String parentUsername;

}
