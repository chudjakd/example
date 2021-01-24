package org.acme.dao.europerates;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "Envelope",namespace = "http://www.gesmes.org/xml/2002-08-01")
public class Envelope {
//    @XmlSchemaType(name = "gesmes:subject")

    //private Subject subject;
//    @XmlSchemaType(name = "gesmes:sender")

    private String subject;


    private Sender sender;
//    @XmlSchemaType(name = "Cube")

    private PurpleCube cube;
//    private String xmlnsGesmes;
//    private String xmlns;
//    private String prefix;

    public String getSubject() {
        return subject;
    }
    @XmlElement(name = "subject",namespace = "http://www.gesmes.org/xml/2002-08-01")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Sender getSender() {
        return sender;
    }

    @XmlElement(name = "Sender", namespace = "http://www.gesmes.org/xml/2002-08-01")
    public void setSender(Sender value) {
        this.sender = value;
    }

    public PurpleCube getCube() {
        return cube;
    }

    @XmlElement(name = "Cube", namespace = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref")
    public void setCube(PurpleCube value) {
        this.cube = value;
    }

//    public String getXmlnsGesmes() {
//        return xmlnsGesmes;
//    }
//
//    public void setXmlnsGesmes(String value) {
//        this.xmlnsGesmes = value;
//    }
//
//    public String getXmlns() {
//        return xmlns;
//    }
//
//    public void setXmlns(String value) {
//        this.xmlns = value;
//    }
//
//    public String getPrefix() {
//        return prefix;
//    }
//
//    public void setPrefix(String value) {
//        this.prefix = value;
//    }

}
