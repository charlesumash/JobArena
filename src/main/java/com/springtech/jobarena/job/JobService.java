package com.springtech.jobarena.job;

import java.util.List;

public interface JobService {
    public List<Job> findAll();

    public void createJob(Job job);

    public Job getJobById(Long jobId);

    public Boolean deleteJobById(Long jobId);

    public Boolean updateJobById(Long jobId, Job updatedJob);
}
