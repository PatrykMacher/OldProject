package com.patryk.Task1;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping()
public class TaskController {
    @Autowired
    RequestRepository requestRepository;

    @GetMapping("/requestsInProgress")
    public int result(){
        return requestRepository.inProgress();

    }
    @PostMapping("/requests")
    public String request(@RequestBody List<Request> requests) throws IOException {
         requestRepository.save(requests);
         requestRepository.create();
         return "200 OK"; //XDD
    }
    @GetMapping("/results")
    public String getResults() throws IOException {

        return requestRepository.getFile();
    }






}
