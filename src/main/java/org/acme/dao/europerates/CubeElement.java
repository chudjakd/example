package org.acme.dao.europerates;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Cube")
public class CubeElement {


    private String currency;

    private String rate;
    @XmlAttribute
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String value) {
        this.currency = value;
    }
    @XmlAttribute
    public String getRate() {
        return rate;
    }

    public void setRate(String value) {
        this.rate = value;
    }
}
