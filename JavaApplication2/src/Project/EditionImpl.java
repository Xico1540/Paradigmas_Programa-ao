/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Project;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Status;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author RP
 */
public class EditionImpl implements Edition {

    private String name;
    private String projecttemplate;
    private LocalDate start;
    private LocalDate end;
    private Status status;
    private Project[] projects;
    private int counter = 0;
    private final int DEFAULT_SIZE = 3;

    public EditionImpl(String name, String projecttemplate, LocalDate start, LocalDate end, Status status) {
        this.name = name;
        this.projecttemplate = projecttemplate;
        this.start = start;
        this.end = end;
        this.status = status;
        this.projects = new Project[DEFAULT_SIZE];
    }

    public int getCounter() {
        return this.counter;
    }

   

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public LocalDate getStart() {
        return this.start;
    }

    @Override
    public String getProjectTemplate() {
        return this.projecttemplate;
    }

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void addProject(String string, String string1, String[] strings) throws IOException, ParseException {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(this.projecttemplate));
            JSONObject jsonObj = (JSONObject) obj;

            //Read parameters
            int number_of_facilitators = Integer.parseInt(jsonObj.get("number_of_facilitators").toString());
            int number_of_students = Integer.parseInt(jsonObj.get("number_of_students").toString());
            int number_of_partners = Integer.parseInt(jsonObj.get("number_of_partners").toString());

            //Read array
            JSONArray tasks = (JSONArray) jsonObj.get("tasks");
            TaskImpl[] tasksArray = new TaskImpl[tasks.size()];

            int i = 0;
            for (Object taskObj : tasks) {
                try {
                    JSONObject task = (JSONObject) taskObj;

                    String title = task.get("title").toString();
                    String description = task.get("description").toString();
                    int startAt = Integer.parseInt(task.get("start_at").toString());
                    int duration = Integer.parseInt(task.get("duration").toString());

                    LocalDate start = this.start.plusDays(startAt);
                    LocalDate end = start.plusDays(duration);

                    tasksArray[i++] = new TaskImpl(start, end, title, description);
                } catch (NullPointerException e) {
                    break;
                }
            }

            if (this.getStatus().equals(Status.ACTIVE) || this.getStatus().equals(Status.INACTIVE)) {
                Project project = new ProjectImpl(string, string1, strings);
             
                try {
                    this.projects[counter] = project;
                    counter++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    Project[] newArray = new Project[this.projects.length + 10];
                    System.arraycopy(this.projects, 0, newArray, 0, this.projects.length);
                }
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } catch (org.json.simple.parser.ParseException e) {
            throw new ParseException(e.getMessage(), e.getPosition());
        }

    }

    private int findProjectpos(String s) {
        for (int i = 0; i < this.counter; i++) {
            if (projects[i].getName().equals(s)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void removeProject(String string) {
        int pos = this.findProjectpos(string);
        if (pos != -1) {
            for (int i = pos; i < this.counter - 1; i++) {
                this.projects[i] = this.projects[i + 1];
            }
        }
    }

    @Override
    public Project getProject(String string) {
        int pos = this.findProjectpos(string);
        if (pos != -1) {
            return this.projects[pos];
        } else {
            return null;
        }

    }

    @Override
    public Project[] getProjects() {
        return this.projects;
    }

    @Override
    public Project[] getProjectsByTag(String string) {
        Project temp[] = new ProjectImpl[this.counter];
        int pos = 0;

        for (Project project : this.projects) {
            for (String tag : project.getTags()) {
                if (tag.equals(string)) {
                    temp[pos] = project;
                    pos++;
                    break;
                }
            }
        }
        return temp;
    }

    @Override
    public Project[] getProjectsOf(String string) {
        Project[] temp = new ProjectImpl[this.counter];
        int pos = 0;

        for (Project project : this.projects) {
            if (project.getParticipant(string) != null) {
                temp[pos] = project;
                pos++;

            }

        }
        return temp;
    }

    @Override
    public int getNumberOfProjects() {
        return this.counter;
    }

    @Override
    public LocalDate getEnd() {
        return this.end;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Edition)) {
            return false;
        }
        Edition temporaryEdition = (Edition) obj;

        if (this == temporaryEdition) {
            return true;
        }

        return (temporaryEdition.getName().equals(this.getName())
                && temporaryEdition.getProjectTemplate().equals(this.getProjectTemplate()));
    }

}
