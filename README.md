# CourseEnrollmentPortal-JavaRMI

-->Run YP2Server.java
It creates a RMI Registry at port 1099(you can specify your own Registered port number 1024-49151). 
Implements the Remote Interface

-->Run YP2Service.java
This is the interface implementation which extends UnicastRemoteObject

-->Run YP2Client.java(Run it on different compputer)
Enter only valid username and password.
Then Enter 1 or 2 ( 1 for enrollment of course and 2 for dropping the course)
If the user selects 1 he will be displayed with the courses left for the enrollment.
If the user selects 2 he will be displayed with the courses he already enrolled.

If the capacity of the course exceeds 5 then the YP2Service removes the course object from the Registry and 
creates a new object for the same course
