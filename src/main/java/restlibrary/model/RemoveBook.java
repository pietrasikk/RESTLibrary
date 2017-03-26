package restlibrary.model;

import java.io.Serializable;
import java.util.List;


public class RemoveBook implements Serializable {

    private List<Long> booksId;

    public List<Long> getBooksId() {
        return booksId;
    }

    public void setBooksId(List<Long> booksId) {
        this.booksId = booksId;
    }

    @Override
    public String toString() {
        return "RemoveBook{" +
                "booksId=" + booksId +
                '}';
    }
}
