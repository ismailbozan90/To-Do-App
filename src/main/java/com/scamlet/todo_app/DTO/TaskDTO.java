package com.scamlet.todo_app.DTO;


import lombok.Data;

import java.util.Date;

@Data
public class TaskDTO {
    private String title;
    private String description;
    private Date date;
    private boolean status;
}
