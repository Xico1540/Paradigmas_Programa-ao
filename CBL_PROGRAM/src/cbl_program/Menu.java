/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cbl_program;

import CBL.CblImpl;
import Exceptions.EditionAlreadyInListException;
import Exceptions.InvalidIndexException;
import Project.ContactImpl;
import Project.EditionImpl;
import Project.SubmissionImpl;
import Project.TaskImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaapplication2.InstituitionImpl;
import javaapplication2.StudentImpl;
import ma02_resources.participants.InstituitionType;
import ma02_resources.participants.Student;
import ma02_resources.project.Status;
import ma02_resources.project.Submission;
import ma02_resources.project.Task;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.TaskAlreadyInProject;

/* 
* Nome: Lucas Teixeira Fernandes
* Número: 8220297
* Turma: 3
* 
* Nome: Francisco Jose Pinto costa
* Número: 8220805
* Turma: 1
 */
public class Menu {

    private CblImpl cbl;
    private BufferedReader reader;

    public Menu(CblImpl cbl) {
        this.cbl = cbl;
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Lista elementos do programa com base nas opções selecionadas pelo
     * utilizador.
     *
     * Exibe um menu com diferentes opções de listagem e solicita ao utilizador
     * que escolha uma opção. Com base na opção selecionada, chama a função
     * correspondente para listar os elementos desejados.
     *
     * @throws IOException se ocorrer um erro de entrada/saída durante a leitura
     * dos dados fornecidos pelo utilizador.
     * @throws InvalidIndexException se o índice fornecido pelo utilizador for
     * inválido.
     */
    public void lists() throws IOException, InvalidIndexException {
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("------ Lists -------\n");
            System.out.println("[1] --- list editions ---\n");
            System.out.println("[2] --- list projects by edition ---\n");
            System.out.println("[3] --- list complete projects by edition ---\n");
            System.out.println("[4] --- list tasks by project ---\n");
            System.out.println("[5] --- list submissions by task --- \n");
            System.out.println("[0] --- back --- \n");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    this.listeditions();
                    break;
                case 2:
                    this.listProjectsByEdition();
                    break;
                case 3:
                    this.listCompletedProjectsByEdition();
                    break;
                case 4:
                    this.listTasksOfProject();
                    break;
                case 5:
                    this.listSubmissionsOfTask();
                    break;

            }

        } while (option != 0);
    }

    /**
     * Chama a funcao listar edições.
     */
    public void listeditions() {
        System.out.println(cbl.listEditions());
    }

    /**
     * Lista os projetos completos por uma dada edição
     *
     * @throws IOException
     * @throws InvalidIndexException
     */
    public void listCompletedProjectsByEdition() throws IOException, InvalidIndexException {
        System.out.println("edition index:\t");
        String inputstringindex = reader.readLine();
        int index = Integer.parseInt(inputstringindex);

        System.out.println(cbl.listCompletedProjectsByEdition(index));
    }

    /**
     * Lista os projetos por uma dada edição
     *
     * @throws IOException
     * @throws InvalidIndexException
     */
    public void listProjectsByEdition() throws IOException, InvalidIndexException {
        System.out.println("edition index:\t");
        String inputstringindex = reader.readLine();
        int index = Integer.parseInt(inputstringindex);

        System.out.println(cbl.listProjectsByEdition(index));
    }

    /**
     * Adiciona uma nova edição com base nas informações fornecidas pelo
     * utilizador. Solicita ao utilizador o nome, data de início, data de
     * término e modelo de projeto da nova edição. A edição é adicionada à lista
     * de edições.
     *
     * @throws DateTimeException se o formato de data fornecido pelo utilizador
     * for inválido
     * @throws IOException se ocorrer um erro ao ler os dados fornecidos pelo
     * utilizador
     * @throws EditionAlreadyInListException se a edição já estiver na lista de
     * edições
     */
    public void createEdition() {
        try {
            System.out.print("\nname:\t");
            String name = reader.readLine();
            System.out.print("\nstart date(dd/MM/yyyy format)\t");
            String startDateString = reader.readLine();
            System.out.print("\nend date(dd/MM/yyyy format)\t");
            String endDateString = reader.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate startDate = LocalDate.parse(startDateString, formatter);
            LocalDate endDate = LocalDate.parse(endDateString, formatter);

            System.out.print("\nproject template:\t");
            String projectTemplate = reader.readLine();
            cbl.addEdition(new EditionImpl(name, projectTemplate, startDate, endDate, Status.INACTIVE));
        } catch (DateTimeException e) {
            System.out.println("Invalid date format.");
        } catch (IOException e) {
            System.out.println("invalid text in some atributes");
        } catch (EditionAlreadyInListException e) {
            System.out.println("the edition is already in the edition list.");
        }
    }

    /**
     * Remove uma edição com base no índice fornecido pelo utilizador. Exibe uma
     * lista das edições disponíveis e solicita ao utilizador o índice da edição
     * a ser removida. A edição é removida da lista de edições.
     *
     * @throws InvalidIndexException se o índice fornecido pelo utilizador for
     * inválido
     * @throws IOException se ocorrer um erro ao ler o índice fornecido pelo
     * utilizador
     */
    public void removeEdition() {

        try {
            listeditions();
            System.out.println("edition index:\t");
            String inputstringindex = reader.readLine();
            int index = Integer.parseInt(inputstringindex);
            cbl.removeEdition(index);
        } catch (InvalidIndexException e) {
            System.out.println("invalid index");
        } catch (IOException e) {
            System.out.println("invalid index");
        }
    }

    /**
     * Define uma edição como ativa com base no índice fornecido pelo
     * utilizador. Exibe uma lista das edições disponíveis e solicita ao
     * utilizador o índice da edição a ser definida como ativa. A edição é
     * marcada como ativa na lista de edições.
     *
     * @throws IOException se ocorrer um erro ao ler o índice fornecido pelo
     * utilizador
     * @throws InvalidIndexException se o índice fornecido pelo utilizador for
     * inválido
     */
    public void setactiveEdition() {
        try {
            listeditions();
            System.out.println("edition index:\t");
            String inputstringindex = reader.readLine();
            int index = Integer.parseInt(inputstringindex);
            cbl.setActiveEdition(index);
        } catch (IOException e) {
            System.out.println("An error has ocurred!\n");
        } catch (InvalidIndexException e) {
            System.out.println("Invalid index error!\n");
        }
    }

    /**
     * Adiciona um novo projeto a uma edição com base nas informações fornecidas
     * pelo utilizador. Solicita ao utilizador o índice da edição, o nome,
     * descrição e lista de tags do projeto. O projeto é adicionado à edição
     * correspondente.
     *
     * @throws InvalidIndexException se o índice fornecido pelo utilizador for
     * inválido
     * @throws ParseException se ocorrer um erro ao analisar os dados fornecidos
     * pelo utilizador
     * @throws IOException se ocorrer um erro ao ler os dados fornecidos pelo
     * utilizador
     */
    public void addProjecttoEdition() {
        try {
            String name, description, taglist, stringindex;
            String[] tagarray;
            int index;
            System.out.println("edition index:");
            stringindex = reader.readLine();
            index = Integer.parseInt(stringindex);
            System.out.println("name:\n");
            name = reader.readLine();
            System.out.println("description:\n");
            description = reader.readLine();
            System.out.println("taglist by spaces:\n");
            taglist = reader.readLine();
            tagarray = taglist.split(" ");
            cbl.addProjectToEdition(index, name, description, tagarray);

        } catch (InvalidIndexException e) {
            System.out.println("invalid index");
        } catch (ParseException e) {
            System.out.println("error while parsing");
        } catch (IOException e) {
            System.out.println("error ");
        }
    }

    /**
     * Adiciona uma tarefa a um projeto.
     *
     * Solicita ao utilizador informações sobre a edição, nome do projeto,
     * título, descrição, data de início e data de término da tarefa. Com base
     * nessas informações, cria uma instância de TaskImpl e adiciona ao projeto
     * correspondente.
     *
     * @throws IOException se ocorrer um erro de entrada/saída durante a leitura
     * dos dados fornecidos pelo utilizador.
     * @throws IllegalNumberOfTasks se o número máximo de tarefas para o projeto
     * já tiver sido atingido.
     * @throws TaskAlreadyInProject se a tarefa já estiver presente no projeto.
     */
    public void addTaskToProject() {
        try {
            String title, description, stringindex, projectName, startDateString, endDateString;
            LocalDate startDate, endDate;
            int index;

            System.out.println("edition index:");
            stringindex = reader.readLine();
            index = Integer.parseInt(stringindex);
            System.out.println("project name:");
            projectName = reader.readLine();
            System.out.println("title:\n");
            title = reader.readLine();

            System.out.println("description:\n");
            description = reader.readLine();
            System.out.print("\nstart date(dd/MM/yyyy format)\t");
            startDateString = reader.readLine();
            System.out.print("\nend date(dd/MM/yyyy format)\t");
            endDateString = reader.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            startDate = LocalDate.parse(startDateString, formatter);
            endDate = LocalDate.parse(endDateString, formatter);
            cbl.returnEdition(index).getProject(projectName).addTask(new TaskImpl(startDate, endDate, title, description));
        } catch (IOException e) {
            System.out.println("Error. ");
        } catch (IllegalNumberOfTasks e) {
            System.out.println("The max number of tasks was achieved.");
        } catch (TaskAlreadyInProject e) {
            System.out.println("The given task is already already.");
        }
    }

    /**
     * Adiciona uma submissão a uma tarefa em um projeto.
     *
     * Solicita ao utilizador informações sobre a edição, nome do projeto,
     * título da tarefa e texto da submissão. Com base nessas informações, cria
     * uma instância de SubmissionImpl e adiciona à tarefa correspondente.
     *
     * @throws IOException se ocorrer um erro de entrada/saída durante a leitura
     * dos dados fornecidos pelo utilizador.
     */
    public void addSubmissionToTask() {
        try {
            String text;
            ContactImpl contactoGeral = new ContactImpl("rua geral", "Felgueiras", "PortoState", "123", "Portugal", "999 999 999");
            InstituitionImpl estg = new InstituitionImpl("ESTG", "ESTG@gmail.com", InstituitionType.UNIVERSITY, contactoGeral, "estg.com", "estg é a MELHOR");
            StudentImpl student1 = new StudentImpl(1, "Francisco", "francisco@gmail.com", contactoGeral, estg);

            int index;
            String projectName, taskTitle, stringindex;
            System.out.println("edition index:");
            stringindex = reader.readLine();
            index = Integer.parseInt(stringindex);
            System.out.println("project name:");
            projectName = reader.readLine();
            System.out.println("task title:\n");
            taskTitle = reader.readLine();
            System.out.println("submission text:\n");
            text = reader.readLine();

            cbl.returnEdition(index).getProject(projectName).getTask(taskTitle).addSubmission(new SubmissionImpl(student1, text));

        } catch (IOException e) {
            System.out.println("Error. ");
        }
    }

    /**
     * Lista as tarefas de um projeto.
     *
     * Solicita ao utilizador informações sobre a edição e nome do projeto. Com
     * base nessas informações, obtém a lista de tarefas do projeto
     * correspondente e as exibe na saída padrão.
     *
     * @throws IOException se ocorrer um erro de entrada/saída durante a leitura
     * dos dados fornecidos pelo utilizador.
     */
    public void listTasksOfProject() throws IOException {
        String title, stringindex, projectName;
        int index;
        System.out.println("edition index:");
        stringindex = reader.readLine();
        index = Integer.parseInt(stringindex);
        System.out.println("project name:");
        projectName = reader.readLine();
        Task[] tasks1 = cbl.returnEdition(index).getProject(projectName).getTasks();
        int counter = cbl.returnEdition(index).getProject(projectName).getNumberOfTasks();
        System.out.println("tasks from " + projectName + ":");
        for (int i = 0; i < counter; i++) {
            System.out.println("\ttitle: " + tasks1[i].getTitle());
            System.out.println("\tdescription: " + tasks1[i].getDescription());
            System.out.println("\tStarts/started at: " + tasks1[i].getStart());
            System.out.println("\tEnds/ended at: " + tasks1[i].getEnd());
        }
    }

    /**
     * Lista as submissões de uma tarefa em um projeto.
     *
     * Solicita ao utilizador informações sobre a edição, nome do projeto e
     * título da tarefa. Com base nessas informações, obtém a lista de
     * submissões da tarefa correspondente e as exibe na saída padrão.
     *
     * @throws IOException se ocorrer um erro de entrada/saída durante a leitura
     * dos dados fornecidos pelo utilizador.
     */
    public void listSubmissionsOfTask() throws IOException {
        String title, stringindex, projectName, taskTitle;
        int index;
        System.out.println("edition index:");
        stringindex = reader.readLine();
        index = Integer.parseInt(stringindex);
        System.out.println("project name:");
        projectName = reader.readLine();
        System.out.println("task title:\n");
        taskTitle = reader.readLine();

        Submission[] submissions = cbl.returnEdition(index).getProject(projectName).getTask(taskTitle).getSubmissions();
        int counter = cbl.returnEdition(index).getProject(projectName).getTask(taskTitle).getNumberOfSubmissions();
        System.out.println("submissions from " + taskTitle + " of project " + projectName + ":");
        for (int i = 0; i < counter; i++) {
            System.out.println("\tstudent: " + submissions[i].getStudent().getName());
            System.out.println("\ttext: " + submissions[i].getText());
        }
    }
}
