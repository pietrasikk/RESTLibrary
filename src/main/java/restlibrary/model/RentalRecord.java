package restlibrary.model;

import restlibrary.model.enums.RentalRecordStatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "RENTAL_RECORD")
public class RentalRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Book book;
    private User user;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private RentalRecordStatusEnum rentalRecordStatus;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RENTAL_RECORD_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "BOOK_ID", nullable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "RENTAL_RECORD_RENTAL_DATE")
    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    @Column(name = "RENTAL_RECORD_RETURNED_DATE")
    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    @Column(name = "RENTAL_RECORD_STATUS")
    @Enumerated(EnumType.STRING)
    public RentalRecordStatusEnum getRentalRecordStatus() {
        return rentalRecordStatus;
    }

    public void setRentalRecordStatus(RentalRecordStatusEnum rentalRecordStatus) {
        this.rentalRecordStatus = rentalRecordStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentalRecord that = (RentalRecord) o;

        if (!id.equals(that.id)) return false;
        if (!book.equals(that.book)) return false;
        if (!user.equals(that.user)) return false;
        if (!rentalDate.equals(that.rentalDate)) return false;
        if (!returnDate.equals(that.returnDate)) return false;
        return rentalRecordStatus == that.rentalRecordStatus;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + book.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + rentalDate.hashCode();
        result = 31 * result + returnDate.hashCode();
        result = 31 * result + rentalRecordStatus.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RentalRecord{" +
                "id=" + id +
                ", book=" + book +
                ", user=" + user +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", rentalRecordStatus=" + rentalRecordStatus +
                '}';
    }
}
