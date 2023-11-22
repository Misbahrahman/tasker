package com.example.tasker.transformer;

import com.example.tasker.Enum.Priority;
import lombok.Data;

import java.util.Date;
@Data
public class TaskRequest {

    String title;

    String description;

    boolean completed;

    Priority priority;

    Date dueDate;

    int assignedToId;

}
