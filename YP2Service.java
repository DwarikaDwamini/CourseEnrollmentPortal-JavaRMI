        
import java.io.*;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

// This is the interface implementation which extends UnicastRemoteObject
public class YP2Service extends UnicastRemoteObject implements YP2Interface {
 
    
    String courseNum = "";
    String courseName = "";
    String courseDesc = "";
    String section="";
    String timeId ="";
    String roomNo ="";
    String capacit="";
    String professor ="";
    String idnum="";
    public YP2Service(String courseNu,String courseNam, String courseDes,String sec,String time, String room, String capac, String prof) throws RemoteException {
        
        courseNum = courseNu;
        courseName = courseNam;
        courseDesc = courseDes;
        section = sec;
        timeId = time;
        roomNo = room;
        capacit = capac;  
        professor = prof;
        
    }
    
 
    
    
    @Override
    public void incCount(String opt, String selectedCourse) throws RemoteException, AccessException {
        String option = opt;
      
        
        if(option.equals("1")){
          //  System.out.println("before Increased enroll capacity" + capacit);
        int cap = Integer.parseInt(this.capacit) + 1;
        this.capacit = String.valueOf(cap);
        System.out.println("Increased enroll capacity" + capacit);
        }
        else  
            if(option.equals("2")){
                // System.out.println(" before decreased drop capacity" + capacit);
        int cap = Integer.parseInt(this.capacit) - 1;
       this.capacit = String.valueOf(cap);
      // System.out.println("decreased drop capacity" + capacit);
        } 
            
    }
    @Override
    // this is used to get the capacity of each object
    public String getcapacity() throws RemoteException {
      return capacit ;
    }
     @Override
      // this is used to get the Id of each object
    public String getId() throws RemoteException {
      return idnum ;
    }
     @Override
      // this is used to get the course number of each object
     public String getword0() throws RemoteException {
      return courseNum ;
    }
      @Override
       // this is used to get the course title of each object
      public String getword1() throws RemoteException {
      return courseName ;
    }
       @Override
        // this is used to get the course description of each object
       public String getword2() throws RemoteException {
      return courseDesc ;
    }
       // this is used to get the section of each object
        @Override
        public String getsection() throws RemoteException {
      return section ;
    }
        // // this is used to get the time Id of each object
         @Override
         public String gettimeID() throws RemoteException {
      return timeId ;
    }
          @Override
           // this is used to get the Room number of each object
          public String getroomNo() throws RemoteException {
      return roomNo ;
    }
               @Override
           // this is used to get the professor of each object
          public String getprof() throws RemoteException {
      return professor ;
    }
   
    public  YP2Service() throws RemoteException {
       
    }
   // public  MesImp() throws RemoteException {
       
    // System.out.println("Enroll this course   " + cap1);
       // return null;
   //  }
     
    // used to send the values to client to select the course
    @Override
    public String getCourse() throws RemoteException {
      return courseNum + " " + courseName + " " + courseDesc;
    }
    @Override
     public void selectedCourse(String selectedCourse, String user,String opt) throws RemoteException {
        
        String fileName1 = "YP2offered.txt";
        String username = user;
        String option = opt;
        
        try {
         
            
             Scanner s1 = new Scanner(new File("YP2offered.txt"));
            
            Scanner s = new Scanner(new File("YP2userDetails.txt"));
            
            ArrayList<String> list = new ArrayList<String>();
             ArrayList<String> list1 = new ArrayList<String>();
             ArrayList<String> list2 = new ArrayList<String>();
             while (s1.hasNext())
                    {
                    list2.add(s1.next());
                    }
                    s1.close();
            
            String newarr ="";   
            BufferedReader buf;
            buf = new BufferedReader(new FileReader("YP2userDetails.txt"));
                    while (s.hasNext())
                    {
                    list.add(s.next());
                    }
                    s.close();
                    
                    if(list.contains(username))
                    {
                        
               
                        int index = list.indexOf(username);
                        String course = list.get(index);
                     //  System.out.println("course"+course);
                     //   System.out.println("selected Course"+selectedCourse);
                        
                        
                        if(option.equals("1"))
                        {
                           // System.out.println("Enroll this course   " + selectedCourse);
                            ArrayList<String> words = new ArrayList<>(); 
                             String lineJustFetched = null;
                          String[] wordsArray;

                           while(true){ 
                            lineJustFetched = buf.readLine();
                            if(lineJustFetched == null)
                              {  
                                  break; 
                              }else
                              {
                                 // System.out.println("line just fetched"+lineJustFetched);
                                  wordsArray = lineJustFetched.split("\t");// dwarika+cpsm  dwamini
                                  for(String each : wordsArray)
                                  {
                                    // System.out.println("get dwarika cpsm, dwamini, dolly\n"+each);
                                      ArrayList aList= new ArrayList(Arrays.asList(each.split(" ")));
                                      if(aList.contains(username))
                                      {
                                          if(aList.contains(selectedCourse)){
                                             // System.out.println("User already enrolled this course");
                                          }
                                          else
                                          {
                                          aList.add(selectedCourse);
                                            if(list2.contains(selectedCourse))
                                        {
                                              int capacity = 0;
                                            int index2 = list2.indexOf(selectedCourse);
                                            String cap = list2.get(index2+4);
                                            
                                             capacity = Integer.parseInt(cap) + 1;
                                           //capacit = String.valueOf(capacity);
                                             
                                             
                                             incCount(opt,selectedCourse);
                                          //  capacit = String.valueOf(Integer.parseInt(capacit)+1);
                                            //if(Integer.parseInt(capacit)>=5){
                                              //  getCapacity();
                                            //} 
                                            //capacity = Integer.parseInt(cap) - 1; 
                                            

                                            String cap1 = String.valueOf(capacity);
                                            list2.set(index2+4,cap1);
                                            PrintWriter pw = new PrintWriter("YP2offered.txt");
                                            pw.close();
                                            // Rewrite the file with updated contents
                                            FileWriter writer = new FileWriter("YP2offered.txt"); 
                                            for(String str: list2)
                                            {
                                                 writer.write(str + "\t" );

                                            }

                                            writer.close();

                                        }   
                                          }
                                          for(Object r: aList){
                                           //   System.out.println("get dwarika, cpsm\n"+r);
                                              newarr = newarr + " " +r;
                                          }
                                           newarr = newarr + "\t";
                                      }
                                      else 
                                      {
                                         // System.out.println("printf user did not enroll this course");
                                          for(Object r: aList){
                                              newarr = newarr + " " + r;
                                           //   System.out.println("get dwamini cpsm and dollt"+newarr);
                                          }
                                      }
                                      newarr = newarr + "\t";
                                      
                                      
                                  }
                                //  System.out.println("new array after removing\n "+ newarr);
                                  
                                  
                                  
                                  
                              }}
                            
                            
                            
                                               String[] arr2 = newarr.split("\t");
                        // dwarika+cpsm  dwamini
                        list1.clear();
                                  for(String each : arr2){
                                      list1.add(each);
                                     // System.out.println("new array elements dwarika cpsm\n"+each);
                                  }
                                   PrintWriter pw = new PrintWriter("YP2userDetails.txt");
                        pw.close();
                        // Rewrite the file with updated contents
                        FileWriter writer = new FileWriter("YP2userDetails.txt"); 
                        for(String str: list1)
                        {
                             writer.write(str + "\t" );

                        }
System.out.println("check your file");
                        writer.close();
                        
                            buf.close();
                          
                        }
                        else if(option.equals("2"))
                        {
                            System.out.println("Drop this course   " + selectedCourse);
                            ArrayList<String> words = new ArrayList<>(); 
                             String lineJustFetched = null;
                          String[] wordsArray;

                          while(true)
                          {
                              lineJustFetched = buf.readLine();
                              if(lineJustFetched == null)
                              {  
                                  break; 
                              }else
                              {
                                 // System.out.println("line just fetched"+lineJustFetched);
                                  wordsArray = lineJustFetched.split("\t");// dwarika+cpsm  dwamini
                                  for(String each : wordsArray)
                                  {
                                    //  System.out.println("get dwarika cpsm aos, dwamini, dolly\n"+each);
                                      ArrayList aList= new ArrayList(Arrays.asList(each.split(" ")));
                                      if(aList.contains(username))
                                      {
                                          if(aList.contains(selectedCourse)){
                                          aList.remove(selectedCourse);
                                           if(list2.contains(selectedCourse))
                                        {
                                              int capacity = 0;
                                            int index2 = list2.indexOf(selectedCourse);
                                            String cap = list2.get(index2+4);
                                            
                                             capacity = Integer.parseInt(cap) - 1;
                                          //  capacit = String.valueOf(Integer.parseInt(capacit)-1);
                                                //capacity = Integer.parseInt(cap) - 1; 
                                            

                                            String cap1 = String.valueOf(capacity);
                                            list2.set(index2+4,cap1);
                                            PrintWriter pw = new PrintWriter("YP2offered.txt");
                                            pw.close();
                                            // Rewrite the file with updated contents
                                            FileWriter writer = new FileWriter("YP2offered.txt"); 
                                            for(String str: list2)
                                            {
                                                 writer.write(str + "\t" );

                                            }

                                            writer.close();

                                        } 
                                          
                                          
                                          
                                          
                                          }
                                          else
                                          {
                                              System.out.println("user did not enroll this course");
                                          }
                                          for(Object r: aList){
                                            //  System.out.println("get dwarika, cpsm\n"+r);
                                              newarr = newarr.trim() + " " +r;
                                          }
                                      }
                                      else 
                                      {
                                          //System.out.println("printf user did not enroll this course");
                                          for(Object r: aList){
                                              newarr = newarr.trim() +" " + r;
                                            //  System.out.println("get dwamini cpsm and dollt"+newarr);
                                          }
                                      }
                                      newarr = newarr.trim() + "\t";
                                      
                                      
                                  }
                               //   System.out.println("new array after removing\n "+ newarr);
                                  
                                  
                                  
                                  
                              }
                          }
                         String[] arr2 = (newarr.trim()).split("\t");
                        // dwarika+cpsm  dwamini
                        list1.clear();
                                  for(String each : arr2){
                                      list1.add(each);
                                    //  System.out.println("new array elements dwarika cpsm\n"+each);
                                  }
                                   PrintWriter pw = new PrintWriter("YP2userDetails.txt");
                        pw.close();
                        // Rewrite the file with updated contents
                        FileWriter writer = new FileWriter("YP2userDetails.txt"); 
                        for(String str: list1)
                        {
                             writer.write(str + "\t" );

                        }

                        writer.close();
                          
                            buf.close();  
                           
                                                  
                        } 
                       
                    }
                    
                   
                  
                 
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        
        
        
    }
    
     
}

// 
