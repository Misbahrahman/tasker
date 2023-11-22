package com.example.tasker.model;

import com.example.tasker.Enum.Priority;
import com.sun.jdi.BooleanType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder


public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String title;

    String description;

    boolean completed;

    @Enumerated(EnumType.STRING)
    Priority priority;

    Date dueDate;


    @ManyToOne
    @JoinColumn
    User assignedTo;


}
