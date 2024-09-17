/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

import Project.ContactImpl;
import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Participant;

/**
 *
 * @author RP
 */
public abstract class ParticipantImpl implements Participant {

    private String name;
    private String email;
    private ContactImpl contact;
    private InstituitionImpl intituition;

    public ParticipantImpl(String name, String email, ContactImpl contact, InstituitionImpl intituition) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.intituition = intituition;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public Contact getContact() {
        return this.contact;
    }

    @Override
    public Instituition getInstituition() {
        return this.intituition;
    }

    @Override
    public void setInstituition(Instituition instn) {
        this.intituition = (InstituitionImpl) instn;
    }

    @Override
    public void setContact(Contact cntct) {
        this.contact = (ContactImpl) cntct;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null ){
            return false;
        }
        if(!(obj instanceof ParticipantImpl )){
            return false;
        }
        ParticipantImpl temp = (ParticipantImpl) obj;
        if(temp != this){
            return false;
        }
        return (temp.getInstituition().equals(this.getInstituition())&&
                temp.getEmail().equals(this.getEmail())&&
                temp.getContact().equals(this.getContact())&&
                temp.getName().equals(this.getName()));
    }
}
