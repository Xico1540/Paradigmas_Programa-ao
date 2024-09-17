package Project;


import Project.SubmissionImpl;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import ma02_resources.project.Submission;
import ma02_resources.project.Task;

public class TaskImpl implements Task{
    private LocalDate start;
    private LocalDate end;
    private String title;
    private String description;
    private SubmissionImpl[] submissions;
    private int contador = 0;

    public TaskImpl(LocalDate start, LocalDate end, String title, String description) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.description = description;
        this.submissions = new SubmissionImpl[1];
    }

    @Override
    public LocalDate getStart() {
        return start;
    }

    @Override
    public LocalDate getEnd() {
        return end;
    }

    private void setEnd(LocalDate end) {
        this.end = end;
    }

    
    @Override
    public int getDuration() {
        return (int) Duration.between(this.getStart(),this.getEnd()).toDays();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Submission[] getSubmissions() {
        return submissions;
    }

   

    
    @Override
    public int getNumberOfSubmissions() {
        return this.contador;
    }

    
    @Override
    public void addSubmission(Submission submission) {
       
            if (submission == null) {
                throw new IllegalArgumentException("submissao nula");
            } else if (submissions.length == this.contador) {
                SubmissionImpl[] temp = new SubmissionImpl[submissions.length ];
                for(int j = 0 ;  j < submissions.length; j++){
                    temp[j] = submissions[j];
                }
                this.submissions = new SubmissionImpl[submissions.length * 2];
                for(int k = 0 ;  k < submissions.length; k++){
                    submissions[k]= temp[k];
                }
            }
           this.submissions[this.contador++] = (SubmissionImpl) submission;
          
    }

   
    @Override
    public void extendDeadline(int i) {
        this.end.plusDays(i);
    }

    
    @Override
    public int compareTo(Task task) {
        if (task.getEnd().isBefore(this.end)) {
            return -1;
        } else if(task.getEnd().isAfter(this.end)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj){
        if(obj== null){
            return false ;
        }
        if(!(obj instanceof TaskImpl)){
            return false;
        }
        TaskImpl temp = (TaskImpl) obj;
        if( temp == null){
            return false ;
        }
        return (temp.getTitle().equals(this.getTitle())&&
                temp.getDescription().equals(this.getDescription())&&
                temp.getStart().equals(this.getStart())&&
                temp.getEnd().equals(this.getEnd()));
        
    }

    @Override
    public String toString() {
        return "TaskImpl{" + "start=" + start + ", end=" + end + ", title=" + title + ", description=" + description + ", submissions=" + submissions + ", contador=" + contador + '}';
    }
  
    
    
}