package IteratorDesignPattern;


import java.util.ArrayList;
import java.util.List;

// 1. BOOK CLASS
class Book {

    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}

// 2. BOOK ITERATOR INTERFACE
interface BookIterator {

    boolean hasNext();

    Book next();
}

// 3. BOOK COLLECTION
class BookCollection {

    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public BookIterator createIterator() {
        return new BookCollectionIterator(books);
    }
}

// 4. CONCRETE BOOK ITERATOR
class BookCollectionIterator implements BookIterator {

    private List<Book> books;
    private int position = 0;

    public BookCollectionIterator(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean hasNext() {
        return position < books.size();
    }

    @Override
    public Book next() {

        if (!hasNext()) {
            return null;
        }

        Book book = books.get(position);
        position++;

        return book;
    }
}

// 5. MAIN CLASS
public class Main {

    public static void main(String[] args) {

        // Create book collection
        BookCollection library = new BookCollection();

        // Add books
        library.addBook(
            new Book("Java Programming", "James Gosling")
        );

        library.addBook(
            new Book("Spring Boot", "Craig Walls")
        );

        library.addBook(
            new Book("Clean Code", "Robert C. Martin")
        );

        library.addBook(
            new Book("Python Basics", "Guido van Rossum")
        );

        // Create iterator
        BookIterator iterator = library.createIterator();

        // Display books one by one
        System.out.println("Library Books:");

        while (iterator.hasNext()) {

            Book book = iterator.next();

            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());

            System.out.println("--------------------");
        }
    }
}
