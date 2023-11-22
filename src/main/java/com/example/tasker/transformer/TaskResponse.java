package com.example.tasker.transformer;

import com.example.tasker.Enum.Priority;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class TaskResponse {

    String title;

    String description;

    boolean completed;

    Priority priority;

    Date dueDate;

    String assignedToName;

}
