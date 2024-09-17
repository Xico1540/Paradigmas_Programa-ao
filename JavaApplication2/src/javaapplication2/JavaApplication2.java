/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication2;

import CBL.CblImpl;
import Exceptions.EditionAlreadyInListException;
import Exceptions.InvalidIndexException;
import Exceptions.InvalidProjectNameException;
import Project.ContactImpl;
import Project.EditionImpl;
import Project.ProjectImpl;
import Project.TaskImpl;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import ma02_resources.participants.InstituitionType;
import ma02_resources.project.Status;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.TaskAlreadyInProject;

/**
 *
 * @author RP
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException, EditionAlreadyInListException, InvalidIndexException, InvalidProjectNameException, IllegalNumberOfTasks, TaskAlreadyInProject {
        FacilitadorImpl x = new FacilitadorImpl("area", "Joao", "sss@gmail",
                new ContactImpl("porto", "marco", "ae", "asd", "pt", "911"),
                new InstituitionImpl("nome", "ssss@.com", InstituitionType.NGO,
                        new ContactImpl("hhhh", "ddd", "sdsss", "www", "yyy", "uiuuu"), "sssss", "ription"));

        System.out.println(x.getEmail() + x.getName() + x.getAreaOfExpertise());

        PartnerImpl f = new PartnerImpl("wwww", "eeee", "jndkjs", "aweqwea",
                new ContactImpl("ccco", "ccc", "ae", "awed", "asd", "awes"),
                new InstituitionImpl("www", "ssss", InstituitionType.NGO,
                        new ContactImpl("hhhh", "aaadd", "sdsss", "aww", "yyy", "uiuuu"), "sssss", "ription"));

        StudentImpl t = new StudentImpl(12, "ww", "ss",
                new ContactImpl("pweo", "maweweo", "ae", "asd", "aszzd", "ads"),
                new InstituitionImpl("www", "sszs", InstituitionType.NGO,
                        new ContactImpl("hhhh", "ddzzzzd", "sdsss", "www", "yyzzzzy", "uizzzuuu"), "sssss", "ription"));
        
        TaskImpl task1=new TaskImpl(LocalDate.now(), LocalDate.now().plusDays(5), "eeee", "tttt");
        EditionImpl edicao = new EditionImpl("name", "project_template.json", LocalDate.now(), LocalDate.now().plusDays(10), Status.ACTIVE);
        
        EditionImpl edicao2 = new EditionImpl("name2", "project_template.json", LocalDate.now(), LocalDate.now().plusDays(10), Status.ACTIVE);
        
        String xico = "lll";
        
        String[] arrays = new String[]{xico};
         ProjectImpl project1 = new ProjectImpl("LucasLindo","obvio",arrays);
         
        edicao.addProject("henrique", "é", arrays);
        
        edicao2.addProject("Lucas", "lindo", arrays);
        CblImpl c = new CblImpl();

     
        project1.addTask(task1);
        
        c.addEdition(edicao);
        c.addEdition(edicao2);
       
        System.out.println(c.getNumberOfEditions());
        System.out.println(c.listEditions());
        System.out.println(c.listProjectsByEdition(0));
        System.out.println(c.listCompletedProjectsByEdition(0));
    }

}
