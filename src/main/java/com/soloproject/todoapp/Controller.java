package com.soloproject.todoapp;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/")
@Validated
public class Controller {
    private final Service service;
    private final TasksMapper mapper;

    public Controller(Service service, TasksMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postTasks(@Valid @RequestBody Dto.Post requestBody) {
        Tasks tasks = mapper.tasksPostToTasks(requestBody);
        Tasks createTasks = service.createTasks(tasks);
        Dto.response response = mapper.tasksToTasksResponse(createTasks);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity patchTasks(@PathVariable("id") @Positive int id, @Valid @RequestBody Dto.Patch requestBody) {
        requestBody.setId(id);

        Tasks tasks = mapper.tasksPatchToTasks(requestBody);
        Tasks updateTasks = service.updateTasks(tasks);
        Dto.response response = mapper.tasksToTasksResponse(updateTasks);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTasks(@PathVariable("id") @Positive int id) {
        Tasks findTasks = service.findTasks(id);
        Dto.response response = mapper.tasksToTasksResponse(findTasks);

        return new ResponseEntity<>(new SingleResponseDto<>(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getTaskses(@Positive @RequestParam int page, @Positive @RequestParam int size) {
        Page<Tasks> pageTasks = service.findTaskses(page - 1, size);
        List<Tasks> tasks = pageTasks.getContent();
        List<Dto.response> responses = mapper.tasksToTasksResponses(tasks);

        return new ResponseEntity<>(new MultiResponseDto<>(tasks, pageTasks), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTasks(@PathVariable("id") @Positive int id) {
        service.deleteTasks(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}