package com.hdd.controller;

import com.hdd.model.Status;
import com.hdd.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class StatusRestController {
    @Autowired
    private StatusService statusService;
    //-------------------Retrieve All Statuses----------------------------------------------------

    @RequestMapping(value = "/statuses/", method = RequestMethod.GET)
    public ResponseEntity<List<Status>> listAllStatuses() {
        List<Status> statuses = (List<Status>) statusService.findAll();
        if (statuses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    //-------------------Retrieve Single Status--------------------------------------------------------

    @RequestMapping(value = "/statuses/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Status> getStatus(@PathVariable("id") long id) {
        System.out.println("Fetching Status with id " + id);
        Status status = statusService.findById(id);
        if (status == null) {
            System.out.println("Status with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    //-------------------Create a Customer--------------------------------------------------------

    @RequestMapping(value = "/statuses/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(@RequestBody Status status, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Status " + status.getName());
        statusService.save(status);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/statues/{id}").buildAndExpand(status.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a Status --------------------------------------------------------

    @RequestMapping(value = "/statuses/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Status> updateStatus(@PathVariable("id") long id, @RequestBody Status status) {
        System.out.println("Updating Status " + id);

        Status currentStatus = statusService.findById(id);

        if (currentStatus == null) {
            System.out.println("Status with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentStatus.setName(status.getName());
        currentStatus.setId(status.getId());

        statusService.save(currentStatus);
        return new ResponseEntity<>(currentStatus, HttpStatus.OK);
    }

    //------------------- Delete a Status --------------------------------------------------------

    @RequestMapping(value = "/statuses/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Status> deleteStatus(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Status with id " + id);

        Status status = statusService.findById(id);
        if (status == null) {
            System.out.println("Unable to delete. Status with id " + id + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        statusService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
