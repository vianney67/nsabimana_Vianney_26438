package com.poggy.question5_task_api.controller.task;

import com.poggy.question5_task_api.model.task.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new Task(1L, "Submit assignment", "Submit Spring Boot assignment", false, "HIGH", "2026-02-20"));
        tasks.add(new Task(2L, "Buy groceries", "Milk, bread, eggs", false, "MEDIUM", "2026-02-18"));
        tasks.add(new Task(3L, "Gym", "Leg day workout", true, "LOW", "2026-02-16"));
        tasks.add(new Task(4L, "Pay bills", "Electricity and internet", false, "HIGH", "2026-02-19"));
        tasks.add(new Task(5L, "Read book", "Read 30 pages of Clean Code", false, "MEDIUM", "2026-02-25"));
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam("completed") boolean completed) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isCompleted() == completed) {
                result.add(task);
            }
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String priority) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority() != null && task.getPriority().equalsIgnoreCase(priority)) {
                result.add(task);
            }
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        tasks.add(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setCompleted(updatedTask.isCompleted());
                task.setPriority(updatedTask.getPriority());
                task.setDueDate(updatedTask.getDueDate());
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                task.setCompleted(true);
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                tasks.remove(task);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
