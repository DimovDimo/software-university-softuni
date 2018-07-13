package p04_BookComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Book implements Comparable<Book> {
    private String title;
    private int year;
    private List<String> authors;

    public Book(String title, int year, String... authors) {
        this.setTitle(title);
        this.setYear(year);
        this.setAuthors(authors);
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    private void setYear(int year) {
        this.year = year;
    }

    public List<String> getAuthors() {
        return authors;
    }

    private void setAuthors(String... authors) {
        if (authors.length == 0){
            this.authors = new ArrayList<>();
        } else {
            this.authors = Arrays.stream(authors).collect(Collectors.toList());
        }
    }

    @Override
    public int compareTo(Book book) {
        if (this.title.equals(book.title)){
            if (this.year == book.year){
                return 0;
            } else if (this.year > book.year){
                return 1;
            } else {
                return -1;
            }

        } else if (this.title.compareTo(book.title) > 0){
            return 1;
        } else {
            return -1;
        }
    }
}
