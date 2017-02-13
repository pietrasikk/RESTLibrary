package restlibrary.model;

import restlibrary.model.enums.UserRoleEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "USER")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String login;
    private String name;
    private String surname;
    private UserRoleEnum role;
    private List<RentalRecord> rentalRecords;

    public void addRentalRecord(RentalRecord rentalRecord) {
        rentalRecords.add(rentalRecord);
        rentalRecord.setUser(this);
    }

    public void removeRentalRecord(RentalRecord rentalRecord) {
        rentalRecords.remove(rentalRecord);
        rentalRecord.setUser(null);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "USER_LOGIN", nullable = false)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "USER_NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "USER_SURNAME", nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "USER_ROLE", nullable = false)
    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    @OneToMany(mappedBy = "user")
    public List<RentalRecord> getRentalRecords() {
        return rentalRecords;
    }

    public void setRentalRecords(List<RentalRecord> rentalRecords) {
        this.rentalRecords = rentalRecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!id.equals(user.id)) return false;
        if (!login.equals(user.login)) return false;
        if (!name.equals(user.name)) return false;
        if (!surname.equals(user.surname)) return false;
        if (role != user.role) return false;
        return rentalRecords.equals(user.rentalRecords);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + rentalRecords.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role +
                ", rentalRecords=" + rentalRecords +
                '}';
    }
}
