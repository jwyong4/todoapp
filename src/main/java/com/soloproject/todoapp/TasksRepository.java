package com.soloproject.todoapp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TasksRepository extends JpaRepository<Tasks, Integer> {
}
