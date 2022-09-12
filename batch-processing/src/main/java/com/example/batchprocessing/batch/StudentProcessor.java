package com.example.batchprocessing.batch;

import com.example.batchprocessing.models.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class StudentProcessor implements ItemProcessor<Student, Student> {

    private static final Logger log = LoggerFactory.getLogger(StudentProcessor.class);

    public Date calculateDOB(int age) {
        LocalDate current_date = LocalDate.now();
        int year = current_date.getYear() - age;
        LocalDate date1 = LocalDate.of(year,1,1);
        Date dob = Date.valueOf(date1);
        return dob;
    }

    @Override
    public Student process(final Student student) throws Exception {
        String firstName = student.getFirstName().toUpperCase();
        String lastName = student.getLastName().toUpperCase();
        double gpa = student.getGpa();
        int age = student.getAge();
        Date dob = calculateDOB(age);
        student.setDob(dob);
        SimpleDateFormat sdf = new SimpleDateFormat(
                "MM-dd-yyyy");
        System.out.println(String.format("Converted from [%d] to [%s]",age,sdf.format(dob)));

        final Student transformedPerson = new Student(firstName, lastName, gpa, dob);

        log.info("Converting (" + student + ") into (" + transformedPerson + ")");

        return student;
    }
}
