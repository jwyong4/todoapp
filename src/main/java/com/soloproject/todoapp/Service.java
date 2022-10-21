package com.soloproject.todoapp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.NoSuchElementException;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    private final TasksRepository tasksRepository;

    public Service(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Tasks createTasks(Tasks tasks) {
        Tasks savedTasks = tasksRepository.save(tasks);

        return savedTasks;
    }

    public Tasks updateTasks(Tasks tasks) {
        Tasks findTasks = findVerifiedTasks(tasks.getId());

        Optional.ofNullable(tasks.getTitle()).ifPresent(title -> findTasks.setTitle(title));
        Optional.ofNullable(tasks.getOrder()).ifPresent(order -> findTasks.setOrder(order));
        Optional.ofNullable(tasks.isCompleted()).ifPresent(completed -> findTasks.setCompleted(completed));

        return tasksRepository.save(findTasks);
    }

    public Tasks findTasks(int id) {
        return findVerifiedTasks(id);
    }

    public Page<Tasks> findTaskses(int page, int size) {
        return tasksRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    public Tasks findVerifiedTasks(int id) {
        Optional<Tasks> optionalTasks = tasksRepository.findById(id);
        Tasks findTasks = optionalTasks.orElseThrow(() -> new NoSuchElementException("Tasks Not Found"));

        return findTasks;
    }

    public void deleteTasks(int id) {
        Tasks findTasks = findVerifiedTasks(id);

        tasksRepository.delete(findTasks);
    }
}