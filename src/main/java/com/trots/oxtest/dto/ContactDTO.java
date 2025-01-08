package com.trots.oxtest.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
