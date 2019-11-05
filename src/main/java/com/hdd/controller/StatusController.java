package com.hdd.controller;

import com.hdd.model.Book;
import com.hdd.model.Status;
import com.hdd.service.BookService;
import com.hdd.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StatusController {
    @Autowired
    private StatusService statusService;
    @Autowired
    private BookService bookService;

    @GetMapping("/statuses")
    public ModelAndView listStatuses() {
        Iterable<Status> statuses = statusService.findAll();
        ModelAndView modelAndView = new ModelAndView("/status/list");
        modelAndView.addObject("statuses", statuses);
        return modelAndView;
    }

    @GetMapping("/create-status")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/status/create");
        modelAndView.addObject("status", new Status());
        return modelAndView;
    }

    @PostMapping("/create-status")
    public ModelAndView saveProvince(@ModelAttribute Status status) {
        statusService.save(status);

        ModelAndView modelAndView = new ModelAndView("/status/create");
        modelAndView.addObject("status", new Status());
        modelAndView.addObject("message", "New status created successfully");
        return modelAndView;
    }

    @GetMapping("/edit-status/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Status status = statusService.findById(id);
        if (status != null) {
            ModelAndView modelAndView = new ModelAndView("/status/edit");
            modelAndView.addObject("status", status);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-status")
    public ModelAndView updateStatus(@ModelAttribute Status status) {
        statusService.save(status);
        ModelAndView modelAndView = new ModelAndView("/status/edit");
        modelAndView.addObject("status", status);
        modelAndView.addObject("message", "Status updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-status/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Status status = statusService.findById(id);
        if (status != null) {
            ModelAndView modelAndView = new ModelAndView("/status/delete");
            modelAndView.addObject("status", status);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-status")
    public String deleteCategory(@ModelAttribute Status status) {
        statusService.remove(status.getId());
        return "redirect:statuses";
    }

    @GetMapping("/view-status/{id}")
    public ModelAndView viewCategory(@PathVariable("id") Long id) {
        Status status = statusService.findById(id);
        if (status == null) {
            return new ModelAndView("/error.404");
        }

        Iterable<Book> books = bookService.findAllByStatus(status);

        ModelAndView modelAndView = new ModelAndView("/status/view");
        modelAndView.addObject("status", status);
        modelAndView.addObject("books", books);
        return modelAndView;
    }
}
