package restlibrary.model;

import restlibrary.model.enums.GenreTypeEnum;
import restlibrary.model.enums.BookTypeEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BOOK")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String author_1;
    private String author_2;
    private String author_3;
    private String author_4;
    private String author_5;
    private BookTypeEnum bookType;
    private String language;
    private int pages;
    private int releaseYear;
    private String publishingHouse;
    private String isbn;
    private int copies;
    private GenreTypeEnum genreType;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BOOK_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TITLE", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "AUTHOR_1", nullable = false)
    public String getAuthor_1() {
        return author_1;
    }

    public void setAuthor_1(String author_1) {
        this.author_1 = author_1;
    }

    @Column(name = "AUTHOR_2")
    public String getAuthor_2() {
        return author_2;
    }

    public void setAuthor_2(String author_2) {
        this.author_2 = author_2;
    }

    @Column(name = "AUTHOR_3")
    public String getAuthor_3() {
        return author_3;
    }

    public void setAuthor_3(String author_3) {
        this.author_3 = author_3;
    }

    @Column(name = "AUTHOR_4")
    public String getAuthor_4() {
        return author_4;
    }

    public void setAuthor_4(String author_4) {
        this.author_4 = author_4;
    }

    @Column(name = "AUTHOR_5")
    public String getAuthor_5() {
        return author_5;
    }

    public void setAuthor_5(String author_5) {
        this.author_5 = author_5;
    }

    @Column(name = "BOOK_TYPE", nullable = false)
    public BookTypeEnum getBookType() {
        return bookType;
    }

    public void setBookType(BookTypeEnum bookType) {
        this.bookType = bookType;
    }

    @Column(name = "LANGUAGE", nullable = false)
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Column(name = "PAGES", nullable = false)
    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Column(name = "RELEASE_YEAR", nullable = false)
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Column(name = "PUBLISHING_HOUSE", nullable = false)
    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    @Column(name = "ISBN", nullable = false)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Column(name = "COPIES", nullable = false)
    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Column(name = "GENRE_TYPE", nullable = false)
    public GenreTypeEnum getGenreType() {
        return genreType;
    }

    public void setGenreType(GenreTypeEnum genreType) {
        this.genreType = genreType;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author_1='" + author_1 + '\'' +
                ", author_2='" + author_2 + '\'' +
                ", author_3='" + author_3 + '\'' +
                ", author_4='" + author_4 + '\'' +
                ", author_5='" + author_5 + '\'' +
                ", bookType=" + bookType +
                ", language='" + language + '\'' +
                ", pages=" + pages +
                ", releaseYear=" + releaseYear +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", isbn='" + isbn + '\'' +
                ", copies=" + copies +
                ", genreType=" + genreType +
                '}';
    }
}
