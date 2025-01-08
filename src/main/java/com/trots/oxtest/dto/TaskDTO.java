package com.trots.oxtest.dto;

import com.trots.oxtest.model.TaskStatus;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;
    private String description;
    private TaskStatus status;
    private Date deadlineTime;

}
