/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

import Project.ContactImpl;
import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Student;

/**
 *
 * @author RP
 */
public class StudentImpl extends ParticipantImpl implements Student {

    private int number;

    public StudentImpl(int number, String name, String email, ContactImpl contact, InstituitionImpl intituition) {
        super(name, email, contact, intituition);
        this.number = number;
    }

   

    @Override
    public int getNumber() {
        return this.number;
    }

}
