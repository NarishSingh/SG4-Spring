package com.sg.M4DVD;

import com.sg.M4DVD.controller.DVDLibraryController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        /*
        UserIO io = new UserIOImpl();
        DVDLibraryView v = new DVDLibraryView(io);
        DVDLibraryDAO dao = new DVDLibraryDAOImpl();
        DVDLibraryController c = new DVDLibraryController(dao, v);
         */

        ApplicationContext actx = new ClassPathXmlApplicationContext("applicationContext.xml");

        DVDLibraryController c = actx.getBean("controller", DVDLibraryController.class);

        c.run();
    }

}
