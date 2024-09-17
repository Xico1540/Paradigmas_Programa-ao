/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project;

import java.time.LocalDateTime;
import ma02_resources.participants.Student;
import ma02_resources.project.Submission;

/**
 *
 * @author RP
 */
public class SubmissionImpl implements Submission {

    private LocalDateTime date;
    private Student student;
    private String text;

    public SubmissionImpl(Student student, String text) {
        this.date = LocalDateTime.now();
        this.student = student;
        this.text = text;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public Student getStudent() {
        return student;
    }

    @Override
    public String getText() {
        return text;
    }

    /**
     * @param submission The object to be compared.
     * @return -1 if the task end date is before, 0 if it ends at the same time,
     * 1 if it ends after
     */
    @Override
    public int compareTo(Submission submission) {
        if (submission.getDate().isBefore(this.date)) {
            return -1;
        } else if (submission.getDate().isAfter(this.date)) {
            return 1;
        } else {
            return 0;
        }
    }



}
