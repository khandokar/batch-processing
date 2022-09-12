package com.example.batchprocessing.controllers;

import com.example.batchprocessing.models.Student;
import com.example.batchprocessing.repository.StudentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

  @Autowired
  JobLauncher jobLauncher;

  @Autowired
  Job importUserJob;

  @Autowired
  StudentRepository repository;

  @GetMapping("/all")
  public String allAccess() {
    return "Public access.";
  }

  @GetMapping("/list")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public List<Student> list() {
    List<Student> studs =  repository.findAll();
    return studs;
  }

  @GetMapping("/batch")
  @PreAuthorize("hasRole('ADMIN')")
  public String batch() throws Exception{
    JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
            .toJobParameters();
    jobLauncher.run(importUserJob,jobParameters);

    return "Batch job has started.";
  }

}
