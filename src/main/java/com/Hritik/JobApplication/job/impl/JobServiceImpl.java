package com.Hritik.JobApplication.job.impl;

import com.Hritik.JobApplication.job.Job;
import com.Hritik.JobApplication.job.JobRepository;
import com.Hritik.JobApplication.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    private Long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * This method is used to find all jobs.
     * @return List of all jobs.
     */
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    /**
     * This method is used to create a job.
     * @param job
     */
    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    /**
     * This method is used to fetch a job with given id.
     * @param id
     * @return the job with given id.
     */
    @Override
    public Job getJobById(long id) {
        return jobRepository.findById(id).orElse(null);
    }

    /**
     * This method deletes a job with given id.
     * @param id
     * @return true if the job is deleted successfully, false otherwise.
     */
    @Override
    public boolean deleteJobById(long id) {
        if(jobRepository.existsById(id)){
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * This method is used to update the details of a particular job.
     * @param id
     * @param updatedJob
     * @return
     */
    @Override
    public boolean updateJob(long id, Job updatedJob) {
        Job job = jobRepository.findById(id).orElse(null);
        if(job != null){
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }

}
