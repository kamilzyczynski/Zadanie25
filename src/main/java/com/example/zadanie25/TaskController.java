package com.example.zadanie25;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {
    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String main(Model model) {

        List<Task> tasksHigh = taskRepository.findAllByPriorityIs(Priority.HIGH);
        List<Task> tasksMedium = taskRepository.findAllByPriorityIs(Priority.MEDIUM);
        List<Task> tasksLow = taskRepository.findAllByPriorityIs(Priority.LOW);

        model.addAttribute("tasksHigh", tasksHigh);
        model.addAttribute("tasksMedium", tasksMedium);
        model.addAttribute("tasksLow", tasksLow);

        return "home";
    }

    @GetMapping("/add")
    public String add(Model model) {

        model.addAttribute("task", new Task());
        return "addTask";
    }

    @GetMapping("/archive")
    public String showDoneTasks(Model model) {
        List<Task> tasksDone = taskRepository.findAllByDoneIsTrueOrderByCompletionDate();
        model.addAttribute("tasksDone", tasksDone);

        return "archive";
    }

    @GetMapping("/task/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Optional<Task> taskById = taskRepository.findById(id);

        if (taskById.isPresent()) {
            Task task = taskById.get();
            model.addAttribute("task", task);
            return "editTask";
        }
        return "redirect:/";
    }

    @PostMapping("/task/edit")
    public String editTask(Task task) {

        taskRepository.save(task);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addTask(Task task) {

        taskRepository.save(task);
        return "redirect:/";
    }

    @PostMapping("/task/info")
    public String showtaskInfoForm(@RequestParam("taskId") Long taskId, Model model) {
        Optional<Task> taskById = taskRepository.findById(taskId);

        if (taskById.isPresent()) {
            Task task = taskById.get();
            model.addAttribute("task", task);
            return "taskInfoForm";
        }

        return "redirect:/";
    }

    @PostMapping("/task/save")
    public String saveTask(@RequestParam("taskId") Long taskId) {
        Optional<Task> taskById = taskRepository.findById(taskId);

        if (taskById.isPresent()) {
            Task task = taskById.get();
            task.setDone(true);
            task.setPriority(null);
            taskRepository.save(task);
        }
        return "redirect:/";
    }
}
