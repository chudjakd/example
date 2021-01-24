package org.acme.dao.europerates;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement(name = "Cube")
public class PurpleCube {


    private EnvelopeCube envelopeCube;

    public EnvelopeCube getEnvelopeCube() {
        return envelopeCube;
    }
    @XmlElement(name="Cube",namespace = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref")
    public void setEnvelopeCube(EnvelopeCube envelopeCube) {
        this.envelopeCube = envelopeCube;
    }
}
