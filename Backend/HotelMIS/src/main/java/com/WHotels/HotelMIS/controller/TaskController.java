package com.WHotels.HotelMIS.controller;

import com.WHotels.HotelMIS.model.Task;
import com.WHotels.HotelMIS.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/task")
public class TaskController {

    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTask(){
        List<Task> tasks = taskService.getTasks();
        return tasks;
    }
    @PatchMapping("/{taskId}/status")
    public ResponseEntity<Task> changeTaskStatus(@PathVariable long taskId, @RequestParam String newStatus) {
        Task updatedTask = taskService.changeTaskStatus(taskId, newStatus);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        } else {
            return ResponseEntity.notFound().build(); // Task not found
        }
    }

    @PostMapping("/add")
    public String addTask(@RequestBody Task task){
        return taskService.addTask(task);
    }

    @GetMapping("/staff/{staffId}")
    public List<Task> getTasksByStaffId(@PathVariable long staffId) {
        return taskService.getTasksByStaffId(staffId);
    }

    @PatchMapping("/{taskId}/markProgress")
    public Task markTaskAsInProgress(@PathVariable int taskId) {
        System.out.println(taskId);
        return taskService.updateTaskStatusToInProgress(taskId);
    }

    @PatchMapping("/{taskId}/markCompleted")
    public Task markTaskAsCompleted(@PathVariable int taskId) {
        System.out.println(taskId);
        return taskService.updateTaskStatusToCompleted(taskId);
    }






}
