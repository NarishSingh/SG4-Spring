package com.sg.booktracker;

import com.sg.booktracker.controller.BookController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Kyle David Rudy
 */
public class App {

    public static void main(String[] args) {
        //old way
        /*
        UserIO io = new UserIOConsoleImpl();
        BookView view = new BookView(io);

        BookDao dao = new BookDaoMemoryImpl();
        BookService service = new BookService(dao);

        BookController controller = new BookController(service, view);
        */
        
        //Spring FW way
        ApplicationContext actx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml"); //app context interface
        
        BookController controller = actx.getBean("controller", BookController.class); //this brings the data from beans in xml to memory
        
        controller.run();
    }
}
