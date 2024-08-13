package com.Hritik.JobApplication.job.impl;

import com.Hritik.JobApplication.job.Job;
import com.Hritik.JobApplication.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    List<Job> jobs = new ArrayList<>();
    private long nextId = 1L;
    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(long id) {
        for(Job job : jobs){
            if(job.getId() == id){
                return job;
            }
        }
        return null;
    }

    @Override
    public boolean deleteJobById(long id) {
        for(Job job : jobs){
            if(job.getId()==id){
                jobs.remove(job);
                return true;
            }
        }
        return false;
    }

}
