package com.sg.M4DVD;

import com.sg.M4DVD.controller.DVDLibraryController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        /*
        UserIO io = new UserIOImpl();
        DVDLibraryView v = new DVDLibraryView(io);
        DVDLibraryDAO dao = new DVDLibraryDAOImpl();
        DVDLibraryController c = new DVDLibraryController(dao, v);
         */

        AnnotationConfigApplicationContext actx = new AnnotationConfigApplicationContext();
        actx.scan("com.sg.M4DVD");
        actx.refresh();

        DVDLibraryController c = actx.getBean("dVDLibraryController", DVDLibraryController.class);

        c.run();
    }

}
