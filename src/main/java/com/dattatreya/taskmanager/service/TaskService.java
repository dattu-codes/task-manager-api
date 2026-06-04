package com.dattatreya.taskmanager.service;

import com.dattatreya.taskmanager.model.Task;
import com.dattatreya.taskmanager.model.User;
import com.dattatreya.taskmanager.repository.TaskRepository;
import com.dattatreya.taskmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasksForUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return taskRepository.findByUser(user);
    }

    public Optional<Task> getTaskByIdAndUser(Long id, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent() && task.get().getUser().getId().equals(user.getId())) {
            return task;
        }
        return Optional.empty();
    }

    public Task createTask(Task task, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        task.setUser(user);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        return taskRepository.findById(id).map(task -> {
            if (!task.getUser().getId().equals(user.getId())) {
                throw new RuntimeException("Unauthorized");
            }
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setCompleted(taskDetails.isCompleted());
            task.setTargetDate(taskDetails.getTargetDate());
            task.setPriority(taskDetails.getPriority());
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
    }

    public void deleteTask(Long id, String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        taskRepository.deleteById(id);
    }
}
