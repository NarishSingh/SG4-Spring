/*
Service layer changes
-App should stop student creation for an existing ID
-stop student creation if any field is empty
-record to an audit log when students are created or removed from system
 */
package com.sg.classroster;

import com.sg.classroster.controller.ClassRosterController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        /*
        UserIO myIO = new UserIOImpl();
        ClassRosterView myView = new ClassRosterView(myIO);
        ClassRosterDao myDao = new ClassRosterDaoImpl();
        ClassRosterAuditDAO myAuditDao = new ClassRosterAuditDAOImpl();
        ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
        ClassRosterController controller = new ClassRosterController(myService, myView);
         */
        
        AnnotationConfigApplicationContext actx = new AnnotationConfigApplicationContext();
        actx.scan("com.sg.classroster");
        actx.refresh();

        ClassRosterController controller = actx.getBean("classRosterController", ClassRosterController.class);

        controller.run();
    }

}
