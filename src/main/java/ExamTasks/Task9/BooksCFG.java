package ExamTasks.Task9;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "cfg")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksCFG {
    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    ArrayList<String> books = new ArrayList<>();

    @XmlElementWrapper(name = "booksToBuy")
    @XmlElement(name = "book")
    ArrayList<String> booksToBuy = new ArrayList<>();
    @XmlElement(name = "status")
    boolean status;

    public String getBook(int index){
        return books.get(index);
    }

    public ArrayList<String> getBooksToBuy(){
        return booksToBuy;
    }

    public boolean getStatus(){
        return status;
    }
}
