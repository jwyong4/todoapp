package com.soloproject.todoapp;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASKS_ID")
    private int id;

    @Column(name = "TASKS_TITLE")
    private String title;

    @Column(name = "TASKS_ORDER")
    private int order;

    @Column(name = "TASKS_COMPLETE")
    private boolean completed;
}
