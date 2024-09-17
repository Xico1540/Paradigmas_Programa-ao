/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

import Project.ContactImpl;
import ma02_resources.participants.Contact;
import ma02_resources.participants.Partner;

/**
 *
 * @author RP
 */
public class PartnerImpl extends ParticipantImpl implements Partner {

    private String vat;
    private String website;

    public PartnerImpl(String vat, String website, String name, String email, ContactImpl contact, InstituitionImpl intituition) {
        super(name, email, contact, intituition);
        this.vat = vat;
        this.website = website;
    }

  

    @Override
    public String getVat() {
        return this.vat;
    }

    @Override
    public String getWebsite() {
        return this.website;
    }

}
