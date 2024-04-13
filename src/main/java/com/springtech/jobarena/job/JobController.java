package com.springtech.jobarena.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/all")
    public List<Job> findAll(){
        return jobService.findAll();
    }

    @PostMapping("/add")
    public String createJob(@RequestBody Job job){
        jobService.createJob(job);
        return "Job successfully created";
    }
}
