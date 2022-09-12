package com.example.batchprocessing.batch;

import com.example.batchprocessing.models.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");
            LocalDate current_date = LocalDate.now();
            jdbcTemplate.query("SELECT firstname, lastname, gpa, dob FROM student",
                    (rs, row) -> new Student(
                            rs.getString(1),
                            rs.getString(2),
                            rs.getDouble(3),
                            rs.getDate(4))
            ).forEach(person -> log.info("Found <" + person + "> in the database."));
        }
    }
}
