package com.Hritik.JobApplication.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job getJobById(long id);
    boolean deleteJobById(long id);
}
