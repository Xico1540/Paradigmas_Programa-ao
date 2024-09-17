/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CBL;

import Exceptions.EditionAlreadyInListException;
import Exceptions.InvalidIndexException;
import Exceptions.InvalidProjectNameException;
import Project.EditionImpl;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import ma02_resources.participants.Student;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Status;
import ma02_resources.project.Submission;
import ma02_resources.project.Task;
/* 
* Nome: Lucas Teixeira Fernandes
* Número: 8220297
* Turma: 3
* 
* Nome: Francisco Jose Pinto costa
* Número: 8220805
* Turma: 1
 */
public class CblImpl implements CblManagement {

    private Edition[] editions;
    private int counter;
    private final int DEFAULT_SIZE = 5;

    public CblImpl() {
        this.editions = new Edition[DEFAULT_SIZE];
        this.counter = 0;
    }

    @Override
    public Edition returnEdition(int index) {
        if (index >= 0 && index < this.counter) {
            return this.editions[index];
        }
        return null;
    }

    @Override
    public void addEdition(Edition edition) throws EditionAlreadyInListException {

        for (int i = 0; i < this.counter; i++) {
            if (this.editions[i].equals(edition)) {
                throw new EditionAlreadyInListException("This edition already exists\n");
            }
            if (edition.getStatus() == Status.ACTIVE) {
                edition.setStatus(Status.INACTIVE);
            }
            if (this.editions.length == counter) {
                Edition[] temp = new Edition[this.editions.length];
                for (int k = 0; k < this.editions.length; k++) {
                    temp[k] = this.editions[k];
                }
                this.editions = new Edition[this.editions.length * 2];
                for (int j = 0; j < this.editions.length; j++) {
                    this.editions[j] = temp[j];
                }
                this.editions[this.counter++] = edition;
            }

        }
        this.editions[counter++] = edition;
        if (this.counter == 1) {
            this.editions[counter - 1].setStatus(Status.ACTIVE);
        }
    }

    @Override
    public void removeEdition(int index) throws InvalidIndexException {
        if (editions[index] == null) {
            throw new InvalidIndexException(" Index null.\n");
        } else {
            int i;
            for (i = index; i < this.editions.length - 1; i++) {
                this.editions[i] = this.editions[i + 1];
            }
            this.editions[i] = null;
            this.counter--;
        }
    }

    @Override
    public void setActiveEdition(int index) throws InvalidIndexException {
        if (this.editions[index] == null) {
            throw new InvalidIndexException("The given edition index in null.\n");
        }
        for (int i = 0; i < this.counter; i++) {
            if (i == index) {
                this.editions[i].setStatus(Status.ACTIVE);
            } else if (this.editions[i].getStatus() == Status.ACTIVE) {
                this.editions[i].setStatus(Status.INACTIVE);
            }
        }
    }

    @Override
    public void addProjectToEdition(int editionIndex, String name, String description, String[] tags) throws ParseException, IOException, InvalidIndexException {
        Edition currentEdition = this.editions[editionIndex];
        if (currentEdition == null) {
            throw new InvalidIndexException("The given edition index is null.\n");
        }
        try {
            currentEdition.addProject(name, description, tags);
        } catch (ParseException e) {
            throw new ParseException("An error has occurred while parsing the json template.", 0);
        } catch (IOException e) {
            throw new IOException("An error has occurred while trying to read the json template.");
        }
    }

    @Override
    public Edition[] getEditionsWithMissingSubmissions() {
        int counter = 0;
        Edition[] missingSubmissions;
        for (int i = 0; i < this.counter; i++) {
            Project[] tempProject = this.editions[i].getProjects();
            int projectsMissingCounter = 0;
            for (int j = 0; j < this.editions[i].getNumberOfProjects(); j++) {
                if (!tempProject[j].isCompleted()) {
                    projectsMissingCounter++;
                }
            }
            if (projectsMissingCounter > 0) {
                counter++;
            }
        }
        missingSubmissions = new Edition[counter];
        counter = 0;
        for (int i = 0; i < this.counter; i++) {
            Project[] tempProject = this.editions[i].getProjects();
            int projectsWithMissingSubmissions = 0;
            for (int j = 0; j < this.editions[i].getNumberOfProjects(); j++) {
                if (!tempProject[j].isCompleted()) {
                    projectsWithMissingSubmissions++;
                }
            }
            if (projectsWithMissingSubmissions > 0) {
                missingSubmissions[counter++] = this.editions[i];
            }
        }
        return missingSubmissions;
    }

    @Override
    public Project[] getProjectsMissingSubmissions(int index) throws InvalidIndexException {
        if (this.editions[index] == null) {
            throw new InvalidIndexException("The given index is null.\n");
        }
        Project[] projectArray = this.editions[index].getProjects();
        int counter = 0;
        Project[] projectsWithMissingSubmissions;

        for (int i = 0; i < this.editions[index].getNumberOfProjects(); i++) {
            if (!projectArray[i].isCompleted()) {
                counter++;
            }
        }
        if (counter == 0) {
            return null;
        }
        projectsWithMissingSubmissions = new Project[counter];
        counter = 0;

        for (int i = 0; i < this.editions[index].getNumberOfProjects(); i++) {
            if (!projectArray[i].isCompleted()) {
                projectsWithMissingSubmissions[counter++] = projectArray[i];
            }
        }
        return projectsWithMissingSubmissions;
    }

    @Override
    public int getNumberOfProjects(int index) throws InvalidIndexException {
        if (this.editions[index] == null) {
            throw new InvalidIndexException("The given index is null.\n");
        }
        return (this.editions[index].getNumberOfProjects());

    }

    @Override
    public int getNumberOfEditions() {
        return this.counter;
    }

    @Override
    public String getProjectProgressText(int index, String projectName) throws InvalidIndexException {
        String s = "\t\tProject progress\n";
        if (this.editions[index] == null) {
            throw new InvalidIndexException("This index is null.\n");
        }

        Project project = this.editions[index].getProject(projectName);
        if (project == null) {
            throw new InvalidIndexException("This name is invalid.\n");
        }
        s += "\t\tCurrent number of participants:\t" + project.getNumberOfParticipants() + "/" + project.getMaximumNumberOfParticipants();
        s += "\n\t\tCurrent number of facilitators:\t" + project.getNumberOfFacilitators() + "/" + project.getMaximumNumberOfFacilitators();
        s += "\n\t\tCurrent number of partners:\t" + project.getNumberOfPartners() + "/" + project.getMaximumNumberOfPartners();
        s += "\n\t\tCurrent number of students:\t" + project.getNumberOfStudents() + "/" + project.getMaximumNumberOfStudents();
        s += "\n\t\tCurrent number of tasks:\t" + project.getNumberOfTasks() + "/" + project.getMaximumNumberOfTasks();
        s += "\n\t\tCurrent project state: \t" + project.isCompleted();
        return s;
    }

    @Override
    public String getEditionProgressText(int index) throws InvalidIndexException {
        String s = "\t\tEdition progress\n";
        if (this.editions[index] == null) {
            throw new InvalidIndexException("The given index is null.\n");
        }
        Project[] projects = this.editions[index].getProjects();
        s += "\t\tEdition name:\t" + this.editions[index].getName();
        s += "\n\t\tStarts at/ Started at:\t" + this.editions[index].getStart();
        s += "\n\t\tEnds at/ Ended at:\t" + this.editions[index].getEnd();
        s += "\n\t\tCurrent edition status:\t" + this.editions[index].getStatus();
        if (this.editions[index].getNumberOfProjects() == 0) {
            s += "\t\tNo projects available.\n";
        } else {
            s += "\n\t\tNumber of projects:\t" + this.editions[index].getNumberOfProjects();
            s += "\n\t\tRatio of completed projects:\t" + (double) (this.getNumberOfCompletedProjects(index) / this.editions[index].getNumberOfProjects()) * 100 + "%\n";
        }

        return s;
    }

    /**
     *
     * Retorna o número de projetos concluídos na edição com o índice fornecido.
     *
     *
     * @param index o índice da edição
     * @return o número de projetos concluídos
     */
    private int getNumberOfCompletedProjects(int index) {
        Project[] projects = this.editions[index].getProjects();
        int pojectsCounter = 0;

        for (int i = 0; i < projects.length; i++) {
            if (projects[i] != null) {
                if (projects[i].isCompleted()) {
                    pojectsCounter++;
                }
            }
        }
        return pojectsCounter;
    }

    /**
     *
     * Retorna o índice da edição ativa.
     *
     *
     * @return o índice da edição ativa, ou -1 se nenhuma edição estiver ativa
     */
    private int findActiveEdition() {
        for (int i = 0; i < this.counter; i++) {
            if (this.editions[i] != null) {
                if (this.editions[i].getStatus().equals(Status.ACTIVE)) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void addSubmissionToProject(String projectName, String submissionTitle, Submission submission, String email) throws InvalidIndexException, InvalidProjectNameException {
        int index = findActiveEdition();
        if (index == -1) {
            throw new InvalidIndexException("No active edition was found.");
        }
        Edition activeEdition = this.editions[index];

        Project project = activeEdition.getProject(projectName);
        if (project == null) {
            throw new InvalidProjectNameException("The given project name was not found.");
        }
        if (project.getParticipant(email) != null) {
            if (project.getTask(submissionTitle) != null) {
                project.getTask(submissionTitle).addSubmission(submission);
            } else {
                throw new InvalidIndexException("The given task title is invalid.");
            }
        } else {
            throw new InvalidIndexException("The given student email is not present.");
        }
    }

    @Override
    public String listEditions() {
        String s = "";
        for (int i = 0; i < this.counter; i++) {
            s += "\t" + (i + 1) + ":";
            s += "\n\t\tEdition name:\t" + this.editions[i].getName();
            s += "\n\t\tStarts at/ Started at:\t" + this.editions[i].getStart();
            s += "\n\t\tEnds at/ Ended at:\t" + this.editions[i].getEnd();
            s += "\n\t\tCurrent edition status:\t" + this.editions[i].getStatus();
            if (this.editions[i].getNumberOfProjects() == 0) {
                s += "\t\tNo projects available.\n";
            } else {
                s += "\n\t\tNumber of projects:\t" + this.editions[i].getNumberOfProjects();
                s += "\n\t\tRatio of completed projects:\t" + (double) (this.getNumberOfCompletedProjects(i) / this.editions[i].getNumberOfProjects()) * 100 + "%\n";
            }
        }
        return s;
    }

    @Override
    public String listProjectsByEdition(int index) throws InvalidIndexException {
        if (index < 0 || index > this.counter - 1) {
            throw new InvalidIndexException("No edition was found.");
        }
        String s = "";
        Project[] tempProjects = this.editions[index].getProjects();
        for (int i = 0; i < this.editions[index].getNumberOfProjects(); i++) {
            s += "\t\tCurrent number of participants:\t" + tempProjects[i].getNumberOfParticipants() + "/" + tempProjects[i].getMaximumNumberOfParticipants();
            s += "\n\t\tCurrent number of facilitators:\t" + tempProjects[i].getNumberOfFacilitators() + "/" + tempProjects[i].getMaximumNumberOfFacilitators();
            s += "\n\t\tCurrent number of partners:\t" + tempProjects[i].getNumberOfPartners() + "/" + tempProjects[i].getMaximumNumberOfPartners();
            s += "\n\t\tCurrent number of students:\t" + tempProjects[i].getNumberOfStudents() + "/" + tempProjects[i].getMaximumNumberOfStudents();
            s += "\n\t\tCurrent number of tasks:\t" + tempProjects[i].getNumberOfTasks() + "/" + tempProjects[i].getMaximumNumberOfTasks();
            s += "\n\t\tCurrent project state: \t" + tempProjects[i].isCompleted();
        }
        return s;
    }

    @Override
    public String listCompletedProjectsByEdition(int index) throws InvalidIndexException {
        if (index < 0 || index > this.counter - 1) {
            throw new InvalidIndexException("No edition was found.");
        }
        String s = "";
        Project[] tempProjects = this.editions[index].getProjects();
        int completeCounter = 0;
        for (int i = 0; i < this.editions[index].getNumberOfProjects(); i++) {
            if (tempProjects[i].isCompleted()) {

                completeCounter++;
                s += "\t\tCurrent number of participants:\t" + tempProjects[i].getNumberOfParticipants() + "/" + tempProjects[i].getMaximumNumberOfParticipants();
                s += "\n\t\tCurrent number of facilitators:\t" + tempProjects[i].getNumberOfFacilitators() + "/" + tempProjects[i].getMaximumNumberOfFacilitators();
                s += "\n\t\tCurrent number of partners:\t" + tempProjects[i].getNumberOfPartners() + "/" + tempProjects[i].getMaximumNumberOfPartners();
                s += "\n\t\tCurrent number of students:\t" + tempProjects[i].getNumberOfStudents() + "/" + tempProjects[i].getMaximumNumberOfStudents();
                s += "\n\t\tCurrent number of tasks:\t" + tempProjects[i].getNumberOfTasks() + "/" + tempProjects[i].getMaximumNumberOfTasks();
            }
        }
        if (completeCounter == 0) {
            s += "There are no projects completed in this edition";
        }
        return s;
    }
}
