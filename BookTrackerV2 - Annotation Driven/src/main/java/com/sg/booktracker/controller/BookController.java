package com.sg.booktracker.controller;

import com.sg.booktracker.dto.Book;
import com.sg.booktracker.ui.BookView;
import com.sg.booktracker.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookController {
    
//    @Autowired //can do this, but do over ctor
    private BookService service;
    private BookView view;

    @Autowired //preferable to have it over ctor if you have fields and a non-d.ctor
    public BookController(BookService service, BookView view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() {
        view.displayBookTrackerBanner();
        
        while(true) {
            int choice = view.displayMenuAndGetChoice();
            
            switch(choice) {
                case 1: //view all
                    List<Book> books = service.getAllBooks();
                    view.displayAllBooks(books);
                    break;
                case 2: //view one
                    String title = view.getBookTitle();
                    Book book = service.getBookByTitle(title);
                    if(book != null) {
                        view.displayBookDetails(book);
                    } else {
                        view.displayError("Book does not exist");
                    }
                    break;
                case 3: //add
                    Book newBook = view.getNewBook();
                    service.addBook(newBook);
                    view.displayAddSuccess();
                    break;
                case 4: //update
                    String updateTitle = view.getBookTitleToUpdate();
                    Book updateBook = service.getBookByTitle(updateTitle);
                    if(updateBook != null) {
                        updateBook = view.getUpdateBook(updateBook);
                        service.updateBook(updateBook);
                        view.displayUpdateSuccess();
                    } else {
                        view.displayError("Book doesn't exist");
                    }
                    break;
                case 5: //delete
                    String deleteTitle = view.getBookTitleToDelete();
                    Book deleteBook = service.getBookByTitle(deleteTitle);
                    if(deleteBook != null) {
                        service.deleteBookByTitle(deleteTitle);
                        view.displayDeleteSuccess();
                    } else {
                        view.displayError("Book doesn't exist");
                    }
                    break;
                case 6: //exit
                    view.displayExit();
                    System.exit(0);
                    break;
                default: //unknown
                    view.displayError("Unknown Option");
                    break;
            }
        }
    }
    
}
