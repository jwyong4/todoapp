package com.soloproject.todoapp;
import org.mapstruct.Mapper;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface TasksMapper {
    Tasks tasksPostToTasks(Dto.Post requestBody);
    Tasks tasksPatchToTasks(Dto.Patch requestBody);
    Dto.response tasksToTasksResponse(Tasks tasks);
    List<Dto.response> tasksToTasksResponses(List<Tasks> taskss);
}
