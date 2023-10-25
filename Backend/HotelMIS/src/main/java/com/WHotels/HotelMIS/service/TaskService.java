package com.WHotels.HotelMIS.service;

import com.WHotels.HotelMIS.model.Task;
import com.WHotels.HotelMIS.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task changeTaskStatus(long taskId, String newStatus) {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task != null) {
            task.setStatus(newStatus);
            return taskRepository.save(task);
        }
        return null; // Task not found
    }


    public List<Task> getTasksByStaffId(long staffId) {
        return taskRepository.findByStaffIdAndStatusNot(staffId, "Completed");
    }

    public String addTask(Task task) {
        taskRepository.save(task);
        return "task added";
    }

    public Task updateTaskStatusToInProgress(int taskId) {
        Optional<Task> optionalTask = taskRepository.findById((long) taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus("In Progress");
            return taskRepository.save(task);
        } else {
            throw new EntityNotFoundException("Task not found with ID: " + taskId);
        }
    }

    public Task updateTaskStatusToCompleted(int taskId) {
        Optional<Task> optionalTask = taskRepository.findById((long) taskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setStatus("Completed");
            return taskRepository.save(task);
        } else {
            throw new EntityNotFoundException("Task not found with ID: " + taskId);
        }
    }
}
