/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project;

import javaapplication2.FacilitadorImpl;
import javaapplication2.ParticipantImpl;
import javaapplication2.PartnerImpl;
import javaapplication2.StudentImpl;
import ma02_resources.participants.Participant;
import ma02_resources.participants.Partner;
import ma02_resources.project.Project;
import ma02_resources.project.Task;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import ma02_resources.project.exceptions.TaskAlreadyInProject;

/**
 *
 * @author RP
 */
public class ProjectImpl implements Project {

    private String name;
    private String description;
    private int numberOfParticipants;
    private int numberOfTasks;
    private final int MAX_TASKS;
    private final int MAX_PARTNERS;
    private final int MAX_PARTICIPANTS;
    private final int MAX_FACILITATORS;
    private final int MAX_STUDENTS;
    private final int TAGS_SIZE = 5;
    private int tagsCounter = 0;
    private String[] tags;
    private Task[] tasks;
    private ParticipantImpl[] participants;
    

    public ProjectImpl(String name, String description, int maxTasks, int maxPartners, int maxFacilitator, int maxStudents) {
        this.name = name;
        this.description = description;
        this.numberOfParticipants = 0;
        this.numberOfTasks = 0;
        this.MAX_PARTNERS = maxPartners;
        this.MAX_FACILITATORS = maxFacilitator;
        this.MAX_STUDENTS = maxStudents;
        this.MAX_TASKS = maxTasks;
        this.MAX_PARTICIPANTS = this.MAX_FACILITATORS + this.MAX_PARTNERS + this.MAX_STUDENTS;
        this.participants = new ParticipantImpl[this.MAX_PARTICIPANTS];
        this.tags = new String[this.TAGS_SIZE];
        this.tasks = new Task[this.MAX_TASKS];

    }
 public ProjectImpl(String name, String description, String[] tags) {
        this.name = name;
        this.description = description;
        this.numberOfParticipants = numberOfParticipants;
        this.numberOfTasks = 0;
        this.MAX_PARTNERS = 4;
        this.MAX_FACILITATORS = 4;
        this.MAX_STUDENTS = 4;
        this.MAX_TASKS = 4;
        this.MAX_PARTICIPANTS = this.MAX_FACILITATORS + this.MAX_PARTNERS + this.MAX_STUDENTS;
        this.participants = new ParticipantImpl[this.MAX_PARTICIPANTS];
        this.tags = new String[this.TAGS_SIZE];
        this.tasks = new Task[this.MAX_TASKS];

    }
    
    
    
    
    public void addTag(String tag) throws ParticipantAlreadyInProject {
        if (tag == null) {
            throw new IllegalArgumentException("The given argument is null.");
        }
        for (String tags : this.tags) {
            if (tags.equals(tag)) {
                throw new ParticipantAlreadyInProject("The tag is already in the project");
            }
        }
        this.tags[this.tagsCounter++] = tag;
    }

    /**
     *
     * @return
     */
    @Override

    public String getName() {
        return this.name;
    }

    /**
     *
     * @return
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     *
     * @return
     */
    @Override
    public int getNumberOfParticipants() {
        return this.numberOfParticipants;
    }

    /**
     *
     * @return
     */
    @Override
    public int getNumberOfStudents() {
        int numberOfStudents = 0;
        for (int i = 0; i < this.numberOfParticipants; i++) {
            if (this.participants[i] instanceof StudentImpl) {
                numberOfStudents++;
            }
        }
        return numberOfStudents;
    }

    /**
     *
     * @return
     */
    @Override
    public int getNumberOfPartners() {
        int numberOfPartners = 0;
        for (int i = 0; i < this.numberOfParticipants; i++) {
            if (this.participants[i] instanceof PartnerImpl) {
                numberOfPartners++;
            }
        }
        return numberOfPartners;
    }

    /**
     *
     * @return
     */
    @Override
    public int getNumberOfFacilitators() {
        int numberOfFacilitators = 0;
        for (int i = 0; i < this.numberOfParticipants; i++) {
            if (this.participants[i] instanceof FacilitadorImpl) {
                numberOfFacilitators++;
            }
        }
        return numberOfFacilitators;
    }

    /**
     *
     * @return
     */
    @Override
    public int getNumberOfTasks() {
        return this.numberOfTasks;
    }

    /**
     *
     * @return
     */
    @Override
    public int getMaximumNumberOfTasks() {
        return this.MAX_TASKS;
    }

    /**
     *
     * @return
     */
    @Override
    public long getMaximumNumberOfParticipants() {
        return this.MAX_PARTICIPANTS;
    }

    /**
     *
     * @return
     */
    @Override
    public int getMaximumNumberOfStudents() {
        return this.MAX_STUDENTS;
    }

    /**
     *
     * @return
     */
    @Override
    public int getMaximumNumberOfPartners() {
        return this.MAX_PARTNERS;
    }

    /**
     *
     * @return
     */
    @Override
    public int getMaximumNumberOfFacilitators() {
        return this.MAX_FACILITATORS;
    }

    /**
     *
     * @param p
     * @throws IllegalNumberOfParticipantType
     * @throws ParticipantAlreadyInProject
     */
    @Override
    public void addParticipant(Participant p) throws IllegalNumberOfParticipantType, ParticipantAlreadyInProject {
        if (p instanceof Partner && this.getNumberOfPartners() == this.MAX_PARTNERS) {
            throw new IllegalNumberOfParticipantType("Maximum number of partners exceeded ");
        } else if (p instanceof StudentImpl && this.getNumberOfStudents() == this.MAX_STUDENTS) {
            throw new IllegalNumberOfParticipantType("Maximum number of students exceeded ");
        } else if (p instanceof FacilitadorImpl && this.getMaximumNumberOfFacilitators() == this.MAX_FACILITATORS) {
            throw new IllegalNumberOfParticipantType("Maximum number of facilitadors exceeded ");
        }
        for (int i = 0; i < this.numberOfParticipants;i++) {
            if (this.participants[i].equals(p)) {
                throw new ParticipantAlreadyInProject("This participant is already in the project");
            }
        }
        this.participants[this.numberOfParticipants++] = (ParticipantImpl) p;
    }

    /**
     *
     * @param string
     * @return
     */
    @Override
    public Participant removeParticipant(String string) {
        ParticipantImpl removedParticipant = null;
        for (int i = 0; i < this.numberOfParticipants; i++) {
            if (this.participants[i].getEmail().equals(string)) {
                removedParticipant = this.participants[i];
                for (int j = i; j < this.numberOfParticipants - 1; j++) {
                    this.participants[j] = this.participants[j + 1];
                }

                break;
            }
        }
        return removedParticipant;
    }

    /**
     *
     * @param string
     * @return
     */
    @Override
    public Participant getParticipant(String string) {
        for (int i = 0; i < this.numberOfParticipants; i++) {
            if (this.participants[i].getEmail() == string) {
                return this.participants[i];
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public String[] getTags() {
        return this.tags;
    }

    /**
     *
     * @param string
     * @return
     */
    @Override
    public boolean hasTag(String string) {
        for (int i = 0; i < this.tagsCounter; i++) {
            if (this.tags[i].equals(string)) {
                return true;
            }

        }
        return false;
    }

    /**
     *
     * @param task
     * @throws IllegalNumberOfTasks
     * @throws TaskAlreadyInProject
     */
   @Override
    public void addTask(Task task) throws IllegalNumberOfTasks, TaskAlreadyInProject {
        if (this.numberOfTasks == this.MAX_TASKS) {
            throw new IllegalNumberOfTasks("The max number of tasks was achieved.");
        }
        if (getTask(task.getTitle()) != null) {
            throw new TaskAlreadyInProject("The given task is already already ");
        }

        this.tasks[this.numberOfTasks++] = task;
    }

    /**
     *
     * @param string
     * @return
     */
    @Override
    public Task getTask(String string) {
        for (int i = 0; i < this.numberOfTasks; i++) {
            if (this.tasks[i].getTitle().equals(string)) {
                return this.tasks[i];
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public Task[] getTasks() {
        return this.tasks;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isCompleted() {
        if(this.numberOfTasks != this.MAX_TASKS){
            return false;
        }
       for(int i = 0 ; i <this.numberOfTasks ; i ++){
           if(this.tasks[i].getNumberOfSubmissions() == 0){
               return false;
           }
       }
       return true;
    }
    
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(!(obj instanceof ProjectImpl)){
            return false;
        }
        ProjectImpl temp = (ProjectImpl) obj;
        
        if(temp == null){
            return false;
        }
        return (temp.getName().equals(this.getName())&&
                temp.getDescription().equals(this.getDescription()));
    }

  
    

}
