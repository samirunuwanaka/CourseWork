import java.util.Arrays;
import java.util.Scanner;

class Student {
    String stID;
    String stName;
    int programingFundamentalsMarks;
    int databaseManagementSystemsMarks;
    int total;
    double avg;

    public final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (final Exception e) {
            e.printStackTrace();
            // Handle any exceptions.
        }
    }

    public static void insertionSort(Student[] s1) {
        Student temStudent=s1[s1.length-1];
        for(int i=0;i<s1.length-1;i++){
            if(s1[i].avg>temStudent.avg){
                for(int j=s1.length-2;i>=j;j--){
                    s1[j+1]=s1[j];
                }
                s1[i]=temStudent;
                return;
            }
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////

    public static Student[] bubbleSorting(int toWhat, Student[] s1){
        Student[] s2=new Student[s1.length];
        for(int i=0;i<s1.length;i++){
            s2[i]=s1[i];
        }
        if(toWhat==1){
            for(int i=(s1.length-1);i>0;i--){
                for(int j=0;j<i;j++){
                    if(s2[j].programingFundamentalsMarks>s2[j+1].programingFundamentalsMarks){
                        Student temStudent=new Student();
                        temStudent=s2[j+1];
                        s2[j+1]=s2[j];
                        s2[j]=temStudent;
                    }
                }
            }
        }else if (toWhat==2) {
            for(int i=(s1.length-1);i>0;i--){
                for(int j=0;j<i;j++){
                    if(s2[j].databaseManagementSystemsMarks>s2[j+1].databaseManagementSystemsMarks){
                        Student temStudent=new Student();
                        temStudent=s2[j+1];
                        s2[j+1]=s2[j];
                        s2[j]=temStudent;
                    }
                }
            }
        }
        return s2;
    }

    public static int welcome(Scanner scan) {
        System.out.println(" ---------------------------------------------------------------");
        System.out.println("|\t\tWECOME TO GDSEMARKS MANAGEMENT SYSTEM\t\t|");
        System.out.println(" ---------------------------------------------------------------");
        System.out.println();
        System.out.print("[1] Add New Student\t\t\t");
        System.out.println("[2] Add New Student With Marks");
        System.out.print("[3] Add Marks\t\t\t\t");
        System.out.println("[4] Update Student Details");
        System.out.print("[5] Update Marks\t\t\t");
        System.out.println("[6] Delete Student");
        System.out.print("[7] Print Student Details\t\t");
        System.out.println("[8] Print Student Ranks");
        System.out.print("[9] Best in Programing Fundamentals\t");
        System.out.println("[10] Best in Database Management Systems");
        System.out.println();
        System.out.print("Enter an option to continue > ");
        return scan.nextInt();
    }

    public static Student[] addNewStudent(Scanner scan, Student[] s1) {
            Student[] s2 = new Student[s1.length+1];
            Student temStudent=new Student();
            boolean newData = false;
            out: while (!newData) {
                System.out.print("Enter student ID : ");
                temStudent.stID = scan.next();
                for (Student a : s1) {
                    if (a.stID == temStudent.stID) {
                        System.out.println("The student ID already exists");
                        continue out;
                    }
                }
                newData = true;
                s2[s2.length-1]=temStudent;
            }
            System.out.print("Enter Student Name : ");
            s2[s1.length].stName=scan.next();
            int i=0;
            for (Student student : s1) {
                s2[i]=student;
                i++;
            }
            return s2;
    }

    public static Student[] addNewStudentWithMarks(Scanner scan, Student[] s1){
        s1=addNewStudent(scan, s1);
        System.out.println();
        int i=s1.length-1;
        addMarks(scan, s1, i);
        return s1;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////    

    public static void addMarks(Scanner scan, Student[] s1, int i){
        boolean inRange=false;
        while(!inRange){
            System.out.print("Programming Fundamental Marks : ");
            s1[i].programingFundamentalsMarks=scan.nextInt();
            if((s1[i].programingFundamentalsMarks>=0)&&(s1[i].programingFundamentalsMarks<=100)){
                inRange=true;
            }else{
            System.out.println("Invalid marks. Please enter correct marks.");
            }
        }
        inRange=false;
        while(!inRange){
            System.out.print("Data Base Management System Marks : ");
            s1[i].databaseManagementSystemsMarks=scan.nextInt();
            if((s1[i].databaseManagementSystemsMarks>=0)&&(s1[i].databaseManagementSystemsMarks<=100)){
                inRange=true;
            }else{
            System.out.println("Invalid marks. Please enter correct marks.");
            }
        }
        return;
    }

    public static void addMarks(Scanner scan, Student[] s1){
        int i;
        boolean in=false;
        while (!in) {
            System.out.print("Enter student ID : ");
            String s=scan.next();
            for(i=0;i<s1.length;i++){
                if(s1[i].stID==s){
                    if((s1[i].programingFundamentalsMarks==0)&&(s1[i].databaseManagementSystemsMarks==0)){
                        addMarks(scan, s1, i);
                        return;
                    }
                    System.out.println("This student's marks have been already added.\nIf you want to update the marks, please use [4] Update Marks");
                    return;
                }
            }
            System.out.println("Invalid student ID. Do you want to search again? (Y/n)");
            s=scan.next();
            if(s=="n"||s=="N"){
                return;
            }
        }
        return;
    }

    public static void updateStudentDetails(Scanner scan, Student[] s1) {
        int i;
        boolean in=false;
        while (!in) {
            System.out.println("Enter student ID : ");
            String s=scan.next();
            for(i=0;i<s1.length;i++){
                if(s1[i].stID==s){
                    System.out.println("Student name\t: "+s1[i].stName);
                    System.out.println();
                    System.out.print("Enter the new student name : ");
                    s1[i].stName=scan.next();
                    return;
                }
            }
            System.out.println("Invalid student ID. Do you want to search again? (Y/n)");
            s=scan.next();
            if(s=="n"||s=="N"){
                return;
            }
        }
    }

    public static void updateMarks(Scanner scan, Student[] s1){
        int i=-1;
        boolean isIn=false;
        isIn:while(!isIn){
            System.out.print("Enter student ID : ");
            String x=scan.next();
            for(i=0;i<s1.length;i++){
                if(x==s1[i].stID){
                    isIn=true;
                    break isIn;
                }
            }
            System.out.print("Invalid student ID. Do you want to search aagain (Y/n) ");
            String check=scan.next();
            if(check=="n"||check=="N"){
                return;
            }
        }
        System.out.println("Student Name\t: "+s1[i].stName);
        if( (s1[i].programingFundamentalsMarks!=0) && (s1[i].databaseManagementSystemsMarks!=0)){
            System.out.println("Programming Fundamental Marks\t: "+s1[i].programingFundamentalsMarks);
            System.out.println("Database Management System Marks : "+s1[i].databaseManagementSystemsMarks);
            if(!isIn){
                System.out.print("Enter new Programming Fundamental Marks : ");
                s1[i].programingFundamentalsMarks=scan.nextInt();
                System.out.println("Invalid marks. Please enter correct marks.");
                isIn=(s1[i].programingFundamentalsMarks>=0)&&(s1[i].databaseManagementSystemsMarks<=100);
            }
            isIn=false;
            if(!isIn){
                System.out.print("Enter new Database Management Marks : ");
                s1[i].databaseManagementSystemsMarks=scan.nextInt();
                System.out.println("Invalid marks. Please enter correct marks.");
                isIn=(s1[i].databaseManagementSystemsMarks>=0)&&(s1[i].databaseManagementSystemsMarks<=100);
            }
            System.out.println("Marks has been updated successfully.");
        }else{
            System.out.println("This student marks yet to be added");
        }
    }

    public static Student[] deleteStudent(Scanner scan, Student[] s1){
        boolean in=true;
        int i=-1;
        check:while(!in){
            System.out.print("Enter student ID : ");
            String stId=scan.next();
            for(i=0;i<s1.length;i++){
                if(s1[i].stID==stId){
                    in=true;
                    break check;
                }
            }
        }
        if(s1.length>0){
            Student[] s2=new Student[s1.length-1];
            for(int j=0;j<i;j++){
                s2[j]=s1[j];
            }
            for(int j=(i+1);j<s1.length;j++){
                s2[j-1]=s1[j];
            }
            s1=s2;
        }
        return s1;
    }
    
    public static void printStudentDetails(Scanner scan, Student[] s1){
        System.out.print("Enter student ID : ");
        String x=scan.next();
        int i=0;
        if(i<=s1.length-1){
            for(;i<s1.length;i++){
                if(x==s1[i].stID){
                    System.out.println("Student Name\t: "+s1[i].stName);
                    if(s1[i].programingFundamentalsMarks!=0&&s1[i].databaseManagementSystemsMarks!=0){
                        System.out.println("+-----------------------------------------------------+----------+");
                        System.out.println("|Programming Fundamental Marks\t|\t\t"+s1[i].programingFundamentalsMarks+"|");
                        System.out.println("+-----------------------------------------------------+----------+");
                        System.out.println("|Database Management System Marks\t|\t\t"+s1[i].databaseManagementSystemsMarks+"|");
                        System.out.println("+-----------------------------------------------------+----------+");
                        System.out.println("|Total Marks\t\t\t\t|\t\t"+s1[i].total+"|");
                        System.out.println("+-----------------------------------------------------+----------+");
                        System.out.println("|Avg Marks\t\t\t\t|\t\t"+s1[i].avg+"|");
                        System.out.println("+-----------------------------------------------------+----------+");
                        System.out.println("|Rank\t\t\t\t\t\t|\t\t"+(i+1)+"("+position(i+1)+")"+"|");
                        System.out.println("+-----------------------------------------------------+----------+");
                    }else{
                        System.out.println();
                        System.out.println("Marks yet to be added");
                        System.out.println();
                    }
                    return;
                }
            }
        }
    }

    public static String position(int i) {
        switch (i) {
            case 1:
                return "First";
            case 2:
                return "Second";
            case 3:
                return "Third";
            case 4:
                return "Forth";
            case 5:
                return "Fifth";
            case 6:
                return "Sixth";
            case 7:
                return "Seventh";
            case 8:
                return "Eighth";
            case 9:
                return "Nineth";
            case 10:
                return "Tenth";
            case 11:
                return "Eleventh";
            case 12:
                return "Twelveth";
            case 13:
                return "Thirteenth";
            case 14:
                return "Forteenth";
            case 15:
                return "Fifteenth";
            case 16:
                return "Sixteenth";
            case 17:
                return "Seventeenth";
            case 18:
                return "Eighteenth";
            case 19:
                return "Nineteenth";
            case 20:
                return "Twentyth";
            default:
                return "Not Good";
        }
    }



    public static void printStudentRanks(Scanner scan, Student[] s1) {
        System.out.println("------------------------------------------------");
        System.out.println("|\t\t\tPRINT STUDENTS' RANKS\t\t\t|");
        System.out.println("------------------------------------------------");
        System.out.println("+-----+-----+--------------------+-----------+----------+");
        System.out.println("|Rank|ID\t|Name\t\t\t|Total Marks|Avg Marks|");
        System.out.println("+-----+-----+--------------------+-----------+----------+");
        for(int i=0;i<s1.length;i++){
            System.out.println("|"+(i+1)+"|"+s1[i].stID+"|"+s1[i].stName+"\t\t|\t\t"+s1[i].total+"|\t"+s1[i].avg+"|");
        }
        System.out.println("+-----+-----+--------------------+-----------+----------+");
        String s="n";
        while (s=="n"||s=="N") {
            System.out.print("Do you want to go back to main menu? (Y/n): ");
            s=scan.next();
        }
    }



    public static void bestInProgrammingFundamentals(Scanner scan,Student[] s1){
        int toWhat=1;
        Student[] s2=bubbleSorting(toWhat, s1);
        System.out.println("---------------------------------------------------------");
        System.out.println("|\tBEST IN PROGRAMING FUNDAMENTALS\t|");
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println("+-----+--------------------+--------+----------+");
        System.out.println("ID\t|Name\t\t\t|PF Marks|DBMS Marks");
        System.out.println("+-----+--------------------+--------+----------+");
        for(int i=s2.length-1;i>=0;i--){
            System.out.println("|"+s2[i].stID+"|"+s2[i].stName+"\t|"+s2[i].programingFundamentalsMarks+"\t|"+s2[i].databaseManagementSystemsMarks+"\t|");
        }
        System.out.println("+-----+--------------------+--------+----------+");
        System.out.println();
        String s="n";
        while (s=="n"||s=="N") {
            System.out.print("Do you want to go back to main menu? (Y/n): ");
            s=scan.next();
        }
    }



    public static void bestInDatabaseManagementSystems(Scanner scan, Student[] s1){
        int toWhat=2;
        Student[] s2=bubbleSorting(toWhat, s1);
        System.out.println("---------------------------------------------------------");
        System.out.println("|\tBEST IN DATABASE MANAGEMENT SYSTEMS\t|");
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println("+-----+--------------------+--------+----------+");
        System.out.println("ID\t|Name\t\t\t|DBMS Marks|PF Marks");
        System.out.println("+-----+--------------------+--------+----------+");
        for(int i=s2.length-1;i>=0;i--){
            System.out.println("|"+s2[i].stID+"|"+s2[i].stName+"\t|"+s2[i].databaseManagementSystemsMarks+"\t|"+s2[i].programingFundamentalsMarks+"\t|");
        }
        System.out.println("+-----+--------------------+--------+----------+");
        System.out.println();
        String s="n";
        while (s=="n"||s=="N") {
            System.out.print("Do you want to go back to main menu? (Y/n): ");
            s=scan.next();
        }
    }



    public static void main(String[] args) {
        clearConsole();
        Scanner scan = new Scanner(System.in);
        Student[] s1 = new Student[0];
        again:while (true) {
            int check = welcome(scan);
            if(s1.length<1){
                if(!(check==1||check==2)){
                    continue again;
                }
            }
            clearConsole();
            String s;
            boolean stay=true;
            switch (check) {
                case 1:
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println("|\t\t\t\tADD NEW STUDENT\t\t\t\t|");
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println();
                    while (stay) {
                        s1=addNewStudent(scan, s1);
                        System.out.print("Student has been added successfully.Do you want to add a new student (Y/n): ");
                        s=scan.next();
                        stay=!(s=="n"||s=="N");
                    }
                    break;
                case 2:
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println("|\t\t\tADD NEW STUDENT WITH MARKS\t\t\t|");
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println();
                    while (stay) {
                        s1=addNewStudentWithMarks(scan, s1);
                        System.out.print("Student has been added successfully.Do you want to add a new student (Y/n): ");
                        s=scan.next();
                        stay=!(s=="n"||s=="N");
                    }
                    break;
                case 3:
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println("|\t\t\t\tADD MARKS\t\t\t\t|");
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println();
                    while (stay) {
                        addMarks(scan, s1);
                        System.out.print("Marks have been added. Do you want to addmarks for another student? (Y/n): ");
                        s=scan.next();
                        stay=!(s=="n"||s=="N");
                    }
                    break;
                case 4:
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println("|\t\tUPDATE STUDENT DETAILS\t\t|");
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println();
                    while (stay) {
                        updateStudentDetails(scan, s1);;
                        System.out.print("Student details has been updated successfully.\nDo you want to update another student details? (Y/n): ");
                        s=scan.next();
                        stay=!(s=="n"||s=="N");
                    }
                    break;
                case 5:
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println("|\t\t\t\tUPDATE MARKS\t\t\t\t|");
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println();
                    while (stay) {
                        updateMarks(scan, s1);
                        System.out.print("Do you want to update marks for another student details? (Y/n): ");
                        s=scan.next();
                        stay=!(s=="n"||s=="N");
                    }
                    break;
                case 6:
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println("|\t\t\t\tDELETE STUDENT\t\t\t\t|");
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println();
                    while (stay) {
                        s1=deleteStudent(scan, s1);
                        System.out.print("Student has been deleted successfully.\nDo you want to delete another student? (Y/n): ");
                        s=scan.next();
                        stay=!(s=="n"||s=="N");
                    }
                    break;
                case 7:
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println("|\t\t\t\tPRINT STUDENT DETAILS\t\t\t\t|");
                    System.out.println(" -----------------------------------------------------------------------");
                    System.out.println();
                    while (stay) {
                        printStudentDetails(scan, s1);
                        System.out.print("Do you want to search another student? (Y/n): ");
                        s=scan.next();
                        stay=!(s=="n"||s=="N");
                    }
                    break;
                case 8:
                    printStudentRanks(scan, s1);
                    break;
                case 9:
                    bestInProgrammingFundamentals(scan, s1);
                    break;
                case 10:
                    bestInDatabaseManagementSystems(scan, s1);
                    break;

                default:
                    break;
            }
            clearConsole();
            insertionSort(s1);
        }
    }
}