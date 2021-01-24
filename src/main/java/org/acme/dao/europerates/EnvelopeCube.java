package org.acme.dao.europerates;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name ="Cube")
public class EnvelopeCube {


    private List<CubeElement> cubes;


    private String time;


    public List<CubeElement> getCubes() {
        return cubes;
    }
    @XmlElement(name = "Cube",namespace = "http://www.ecb.int/vocabulary/2002-08-01/eurofxref")
    public void setCubes(List<CubeElement> cubes) {
        this.cubes = cubes;
    }

    public String getTime() {
        return time;
    }
    @XmlAttribute(name = "time")
    public void setTime(String time) {
        this.time = time;
    }
}
