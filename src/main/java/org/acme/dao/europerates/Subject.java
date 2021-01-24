package org.acme.dao.europerates;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "subject")
public class Subject {

    private String prefix;
    private String text;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String value) {
        this.prefix = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String value) {
        this.text = value;
    }
}
