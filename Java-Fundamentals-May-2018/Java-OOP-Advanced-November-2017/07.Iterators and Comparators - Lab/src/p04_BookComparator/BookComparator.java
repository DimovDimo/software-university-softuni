package p04_BookComparator;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {
    public BookComparator() {
    }

    @Override
    public int compare(Book firstBook, Book secondBook) {
        return firstBook.compareTo(secondBook);
    }
}
