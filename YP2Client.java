import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

 
public class YP2Client {
    public void doTest(String username, String opt1){
        String user = username; 
        try {
            // fire to localhost port 1099
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
              BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            // search for myMessage service
            System.out.println("Select any course from the list");
            int i;
           for(i=0;i<2;i++)
           {
           YP2Interface impl = (YP2Interface) myRegistry.lookup("CSCI" +(i+1));  /// looking the registry for objects
           String courseList = impl.getCourse();
           System.out.println(courseList);
           } 
           
            System.out.println("enter course id ");
            String selectedId = in.readLine();
            //selectedCourse(selectedCourse);
           for(i=0;i<2;i++){
            YP2Interface implobj1 = (YP2Interface) myRegistry.lookup("CSCI" +(i+1)); 
            String capacityobj1 = implobj1.getcapacity();
        /*    String idobj1=implobj1.getId();
            String proofobj1 = implobj1.getprof();
            String roomnoobj1 = implobj1.getroomNo();
            String timeidobj1 = implobj1.gettimeID();
            String sectionobj1 = implobj1.getsection();
            String word2obj1 = implobj1.getword2();
            String word1obj1 = implobj1.getword1();
            String word0obj1 = implobj1.getword0(); */
            
            int capacity=Integer.parseInt(capacityobj1);
         // System.out.println("capacity"+capacity);
           
           }
   
        if (selectedId.equals("CSCI1")){
            YP2Interface  msgInpl = (YP2Interface) myRegistry.lookup("CSCI1");
             msgInpl.selectedCourse(selectedId,user,opt1);
        }
        else if(selectedId.equals("CSCI2")){
            YP2Interface  msgInpl = (YP2Interface) myRegistry.lookup("CSCI2");
             msgInpl.selectedCourse(selectedId,user,opt1);
        }
        else {
            System.out.println("Invalid course Id");
        }
        
        
      
    
        } catch (IOException | NumberFormatException | NotBoundException e) {
        }       
    }
     
    public static void main(String[] args) throws IOException {
        while(true){
        YP2Client cli = new YP2Client();
        System.out.println("Enter your username");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
       String username = in.readLine();
       System.out.println("Please enter your option");
       System.out.println("1 Enroll");
       System.out.println("2 Drop");
       String opt = in.readLine();
       
       
        Scanner s = new Scanner(new File("YP2userDetails.txt"));
       
        int wordindex =0;
        
         while( s.hasNextLine()){   
            String lineFromFile = s.nextLine();
            if(lineFromFile.contains(username))
            {
               wordindex = lineFromFile.indexOf(username);
            
            
             }
         }
        if (wordindex > -1)
        {
        System.out.println("user found");
        while(s.hasNextLine())
            {
                String coursesOfuser = s.nextLine();
                if(coursesOfuser.contains(username))
                {
                    System.out.println("Userdetails found" + coursesOfuser);
                    break;
                }
            }
        
        }
        else
        {
            System.out.println("user not found");
        }
       
        cli.doTest(username,opt); 
    }
}
}
