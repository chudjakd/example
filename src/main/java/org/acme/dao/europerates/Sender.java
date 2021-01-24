package org.acme.dao.europerates;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Sender")
public class Sender {

    private String name;

    public String getName() {
        return name;
    }
    @XmlElement(name = "name",namespace = "http://www.gesmes.org/xml/2002-08-01")
    public void setName(String name) {
        this.name = name;
    }
}
