package restlibrary.model;


import java.io.Serializable;
import java.util.List;

public class RentedBook implements Serializable {

    private Long userId;
    private List<Long> booksId;

    public RentedBook() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getBooksId() {
        return booksId;
    }

    public void setBooksId(List<Long> booksId) {
        this.booksId = booksId;
    }

    @Override
    public String toString() {
        return "RentedBook{" +
                "userId=" + userId +
                ", booksId=" + booksId +
                '}';
    }
}
