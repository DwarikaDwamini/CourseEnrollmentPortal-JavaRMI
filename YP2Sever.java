
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
//server 
public class YP2Sever {
            int indx=0;
            int i=0, j=0;
            String courseNum = "";
     
    public void startServer() throws RemoteException, NotBoundException{
        try {
            // create registry on port 1099
            ArrayList objArr = new ArrayList();
            Registry registry = LocateRegistry.createRegistry(1099); 
            System.out.println("server started\n");
            String section, timeID, roomNo,capacity,id,prof;
            ArrayList<String> offList;
            ArrayList objref;
            // This list contains each element in offered list
            //YP2CourseDetails YP2offered YP2userDetails
            try (Scanner scan = new Scanner(new File("YP2offered.txt"))) {
                // This list contains each element in offered list
                //YP2CourseDetails YP2offered YP2userDetails
                offList = new ArrayList<>();
                objref = new ArrayList();
                while (scan.hasNext())
                {
                    offList.add(scan.next());
                }
            }
            
           
            // create ArrayList with the course number, course title, course description
            FileInputStream fi = new FileInputStream("YP2CourseDetails.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(fi));
            for(i=0;i<2;i++)
            {
                String data =   in.readLine();
                String[] words = data.split("\t"); 
                for(String each : words)
                {
                    System.out.println(each);
                }
               
               if(offList.contains(words[0]))  // 1 cpsm sec time room capa prof 
                {
                        int index = offList.indexOf(words[0]);
                        
                        id=offList.get(index);
                        String obj = words[0];
                        section = offList.get(index+2);
                        timeID =offList.get(index+3);
                        roomNo =offList.get(index+4);
                        capacity =offList.get(index+5);
                        prof = offList.get(index+6);
                        //rebinding objects
                      
                      YP2Interface objint  = new YP2Service(words[0],words[1],words[2],section,timeID,roomNo,capacity,prof);
                        
                        registry.rebind(obj, objint);
                        objArr.add(objint);
                       objref.add(obj);
                        
                }
                
            }
            
            
            while(true) {
                
                for(int k = 0; k<objArr.size();k++){
                    String obj = "CSCI"+(k+1);
                    YP2Interface impl = (YP2Interface) registry.lookup(obj);  /// looking the registry for objects
                     String courseList = impl.getcapacity();
                    
           
            String capacityobj1 = impl.getcapacity();
            String idobj1=impl.getId();
            String proofobj1 = impl.getprof();
            String roomnoobj1 = impl.getroomNo();
            String timeidobj1 = impl.gettimeID();
            String sectionobj1 = impl.getsection();
            String word2obj1 = impl.getword2();
            String word1obj1 = impl.getword1();
            String word0obj1 = impl.getword0();
            
            int capaci=Integer.parseInt(capacityobj1);
                    
                    if(capaci>=5){
                        System.out.println("Time to unbind");
                        registry.unbind(obj);
                        System.out.println("Unbind successful");
                        capacityobj1="1";
                        sectionobj1 = "sec02";
             	
                        YP2Service objint  = new YP2Service(word0obj1,word1obj1,word2obj1,sectionobj1,timeidobj1,roomnoobj1,capacityobj1,proofobj1);
                        registry.rebind(obj, objint);
                         YP2Interface impl1 = (YP2Interface) registry.lookup(obj); 
                        System.out.println("sectiton" +impl1.getsection() + "capacity"+impl1.getcapacity());
                        // System.out.println(objint);
                      
                    }
                }
                
            }
            
                    
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
  
     
    public static void main(String[] args) throws FileNotFoundException, RemoteException, NotBoundException {
       YP2Sever serv = new YP2Sever();
    
        serv.startServer();
    
    }
}