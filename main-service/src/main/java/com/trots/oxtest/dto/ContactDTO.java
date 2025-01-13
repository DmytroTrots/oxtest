package com.trots.oxtest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ContactDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Firstname cannot be blank")
    @Size(min = 1, message = "Firstname length is too short")
    @Size(max = 50, message = "Firstname length is too long")
    private String firstName;

    @NotBlank(message = "Lastname cannot be blank")
    @Size(min = 1, message = "Lastname length is too short")
    @Size(max = 50, message = "Lastname length is too long")
    private String lastName;

    @NotBlank(message = "Phone number cannot be blank")
    @Size(min = 5, message = "Phone number length is too short")
    @Size(max = 50, message = "Phone number length is too long")
    private String phone;

    @NotNull
    private Long clientId;

    @JsonProperty(access = Access.READ_ONLY)
    private List<TaskDTO> tasks = new ArrayList<>();

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

    @JsonIgnore
    private String parentUsername;

}
