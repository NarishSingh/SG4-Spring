/*
annotation driven Spring f/w
this way doesn't require setting up a resources folder with the xml file inside of the src folder
1. xml setup
2. wire it up -> @Component on top of classes
3. @Autowired to your class ctor/var -> spring handles this internally once autowired
4. fix app class
-set up to read thru all comp's
-have app ctx scan proj -> makes all comp's usable
-getBean on the controller ctor, will camelcase controller on its own just follow the convention
 */
package com.sg.booktracker;

import com.sg.booktracker.controller.BookController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Kyle David Rudy
 */
public class App {

    public static void main(String[] args) {
//        UserIO io = new UserIOConsoleImpl();
//        BookView view = new BookView(io);
//
//        BookDao dao = new BookDaoMemoryImpl();
//        BookService service = new BookService(dao);
//
//        BookController controller = new BookController(service, view);

        AnnotationConfigApplicationContext actx = new AnnotationConfigApplicationContext();
        actx.scan("com.sg.booktracker"); //will now scan proj folder
        actx.refresh(); //pull in comps and make usable from actx
        
        BookController controller = actx.getBean("bookController", BookController.class); //camelcases the controller class internally

        controller.run();
    }
}
