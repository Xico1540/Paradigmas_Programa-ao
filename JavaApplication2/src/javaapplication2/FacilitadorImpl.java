/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

import Project.ContactImpl;
import ma02_resources.participants.Contact;
import ma02_resources.participants.Facilitator;

/**
 *
 * @author RP
 */
public class FacilitadorImpl extends ParticipantImpl implements Facilitator {

    private String areaOfExpertise;

    public FacilitadorImpl(String areaOfExpertise, String name, String email, ContactImpl contact, InstituitionImpl intituition) {
        super(name, email, contact, intituition);
        this.areaOfExpertise = areaOfExpertise;
    }

  

   

    @Override
    public String getAreaOfExpertise() {
        return this.areaOfExpertise;
    }

    @Override
    public void setAreaOfExpertise(String string) {
        this.areaOfExpertise = string;
    }

}
