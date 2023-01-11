package ExamTasks.Task8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "cfg")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PolynomCFG {
    @XmlElement
    private float a;
    @XmlElement
    private float b;
    @XmlElement
    private float c;

    public float getA(){
        return a;
    }
    public float getB(){
        return b;
    }
    public float getC(){
        return c;
    }
}
