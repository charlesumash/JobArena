package com.springtech.jobarena.job;

import java.util.List;

public interface JobService {
    public List<Job> findAll();

    public void createJob(Job job);

    public Job getJobById(Long id);

    public Boolean deleteJobById(Long id);

    public Boolean updateJobById(Long id, Job updatedJob);
}
