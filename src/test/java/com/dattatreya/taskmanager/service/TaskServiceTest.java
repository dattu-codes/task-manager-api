package com.dattatreya.taskmanager.service;

import com.dattatreya.taskmanager.model.Task;
import com.dattatreya.taskmanager.model.User;
import com.dattatreya.taskmanager.repository.TaskRepository;
import com.dattatreya.taskmanager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TaskService taskService;

    private User user;
    private Task task;

    @BeforeEach
    void setUp() {
        user = new User("testuser", "password");
        user.setId(1L);
        task = new Task("Test Task", "Test Description", false, LocalDate.now(), Task.Priority.HIGH);
        task.setId(100L);
        task.setUser(user);
    }

    @Test
    void testGetAllTasksForUser() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(taskRepository.findByUser(user)).thenReturn(Arrays.asList(task));

        List<Task> tasks = taskService.getAllTasksForUser("testuser");

        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getTitle());
        verify(userRepository, times(1)).findByUsername("testuser");
        verify(taskRepository, times(1)).findByUser(user);
    }

    @Test
    void testGetTaskByIdAndUser_Success() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(taskRepository.findById(100L)).thenReturn(Optional.of(task));

        Optional<Task> foundTask = taskService.getTaskByIdAndUser(100L, "testuser");

        assertTrue(foundTask.isPresent());
        assertEquals("Test Task", foundTask.get().getTitle());
    }

    @Test
    void testGetTaskByIdAndUser_Unauthorized() {
        User anotherUser = new User("another", "pass");
        anotherUser.setId(2L);
        task.setUser(anotherUser);

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(taskRepository.findById(100L)).thenReturn(Optional.of(task));

        Optional<Task> foundTask = taskService.getTaskByIdAndUser(100L, "testuser");

        assertFalse(foundTask.isPresent());
    }

    @Test
    void testCreateTask() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        Task created = taskService.createTask(task, "testuser");

        assertNotNull(created);
        assertEquals("testuser", created.getUser().getUsername());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testUpdateTask_Success() {
        Task updatedDetails = new Task("Updated Title", "Updated Desc", true, LocalDate.now(), Task.Priority.LOW);

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(taskRepository.findById(100L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Task result = taskService.updateTask(100L, updatedDetails, "testuser");

        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Desc", result.getDescription());
        assertTrue(result.isCompleted());
        assertEquals(Task.Priority.LOW, result.getPriority());
    }

    @Test
    void testDeleteTask_Success() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));
        when(taskRepository.findById(100L)).thenReturn(Optional.of(task));

        assertDoesNotThrow(() -> taskService.deleteTask(100L, "testuser"));
        verify(taskRepository, times(1)).deleteById(100L);
    }
}
