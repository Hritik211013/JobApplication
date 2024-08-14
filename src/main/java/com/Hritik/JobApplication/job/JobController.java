package com.Hritik.JobApplication.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class JobController {
    private JobService jobService;

    /**
     * This method is required for loosely coupling of JobService class.
     */
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    /**
     * This method returns list of all jobs
     * @return
     */
    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return new ResponseEntity<>(jobService.findAll(),HttpStatus.OK);
    }

    /**
     * This method is used to create a particular job.
     * @param job
     * @return
     */
    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        return new ResponseEntity<>("Job added successfully",HttpStatus.CREATED);
    }

    /**
     * This method returns job with given id.
     * @param id
     * @return
     */
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable long id){
        Job job = jobService.getJobById(id);
        if(job != null){
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * This method is used to delete a job with particular id.
     * @param id, id of job to be deleted.
     * @return
     */
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted){
            return new ResponseEntity<>("Job Deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("No Job exist with this job Id",HttpStatus.NOT_FOUND);
    }

    /**
     * This method is used to update the job details.
     * @param id, this id is used to find the job to be updated.
     * @param updatedJob, this is the updated detail of the job.
     * @return HttpStatus ok if the job is updated successfully, HttpStatus NOT_FOUND if the job with "id" is not present.
     */
    @PutMapping("/jobs/{id}")
    public ResponseEntity<String> updateJob(@PathVariable long id, @RequestBody Job updatedJob){
        boolean updated = jobService.updateJob(id, updatedJob);
        if(updated){
            return new ResponseEntity<>("Job details updated successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("No Job exist with this job id",HttpStatus.NOT_FOUND);
    }
}
