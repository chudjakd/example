package org.acme.dao.skuskaxml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
public class Root {

    private String question;
    private String shoot;


    public String getQuestion() {
        return question;
    }
    @XmlElement(name = "question")
    public void setQuestion(String question) {
        this.question = question;
    }

    public String getShoot() {
        return shoot;
    }
    @XmlElement(name = "shoot")
    public void setShoot(String shoot) {
        this.shoot = shoot;
    }







}
