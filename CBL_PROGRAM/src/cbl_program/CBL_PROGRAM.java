/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cbl_program;

import CBL.CblImpl;
import Exceptions.InvalidIndexException;
import java.io.IOException;
import java.util.Scanner;

/* 
* Nome: Lucas Teixeira Fernandes
* Número: 8220297
* Turma: 3
* 
* Nome: Francisco Jose Pinto costa
* Número: 8220805
* Turma: 1
 */
public class CBL_PROGRAM {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InvalidIndexException {
        CblImpl cbl = new CblImpl();
        Menu m = new Menu(cbl);
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("------ Menu -------\n");
            System.out.println("[1] --- add edition ---\n");
            System.out.println("[2] --- remove edition ---\n");
            System.out.println("[3] --- set active edition ---\n");
            System.out.println("[4] --- add project --- \n");
            System.out.println("[5] --- add task to project ---\n");
            System.out.println("[6] --- add submission to task ---\n");
            System.out.println("[7] --- lists ---\n");
            System.out.println("[0] --- quit ---\n");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    m.createEdition();
                    break;
                case 2:
                    m.removeEdition();
                    break;
                case 3:
                    m.setactiveEdition();
                    break;
                case 4:
                    m.addProjecttoEdition();
                    break;
                case 5:
                    m.addTaskToProject();
                    break;
                case 6:
                    m.addSubmissionToTask();
                    break;
                case 7:
                    m.lists();
            }

        } while (option != 0);
        scanner.close();
    }
}
