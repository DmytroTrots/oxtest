package com.trots.oxtest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.trots.oxtest.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Description cannot be blank")
    @Size(min = 1, message = "Description length is too short")
    @Size(max = 300, message = "Description length is too long")
    private String description;

    private TaskStatus status;

    @NotNull
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date deadlineTime;

    private Long contactId;

    @JsonProperty(access = Access.READ_ONLY)
    private String contactFirstName;

    @JsonProperty(access = Access.READ_ONLY)
    private String contactLastName;

}
