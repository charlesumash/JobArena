package com.springtech.jobarena.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/all")
    public ResponseEntity<List<Job>> findAll(){
        List<Job> allJobs = jobService.findAll();
        if(allJobs.size()>0){
            return new ResponseEntity<>(allJobs, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job successfully created", HttpStatus.CREATED);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable Long jobId){
        Job job = jobService.getJobById(jobId);
        if(job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable Long jobId){
        Boolean deleted = jobService.deleteJobById(jobId);
        if(deleted){
            return new ResponseEntity<>("Job successfully deleted", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/{jobId}")
    public ResponseEntity<String> updateJob(@PathVariable Long jobId,
                                            @RequestBody Job updatedJob){
        Boolean updated = jobService.updateJobById(jobId, updatedJob);
        if (updated){
            return new ResponseEntity<>("Job successfully updated", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
