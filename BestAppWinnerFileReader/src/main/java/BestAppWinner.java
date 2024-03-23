
import java.io.*;
import java.util.Scanner;

public class BestAppWinner {
//---------------------------------------------------------------------------------------------------------------------   
// the start of main method, which will create refrences to the IO files and call the methods that will deal with them

    public static void main(String[] args) throws FileNotFoundException {

        //the creation of I/O files, Input Scanner & PrintWriter
        File inputFile = new File("C:\\Users\\the_e\\OneDrive\\Documents\\NetBeansProjects\\Assignment1\\input.txt"); // refrence to the input file
        File outputFile = new File("C:\\Users\\the_e\\OneDrive\\Documents\\NetBeansProjects\\Assignment1\\print.txt"); //refrence to the output file
        if (!(inputFile.exists())) // file availability checker
        {
            System.out.println(inputFile + "does not exist, please try again!");
        }

        PrintWriter fileWriter = new PrintWriter(outputFile); // a print writer for adding data to the output file
        Scanner input = new Scanner(inputFile); // scanner for using data inside the input file

        fileWriter.println("\n***** Welcome to Best App Winner System *****\n");

        //the creation of arrays
        String[] university = new String[input.nextInt()];      // new array to hold diffrent universities

        String[] day = new String[input.nextInt()];         // new array to hold days of contest for each university

        String[] date = new String[day.length];         // new array to hold date of contest for each university

        String[][] student = new String[university.length][];        // new array for holding student names under each university
        for (int i = 0; i < university.length; i++) {
            student[i] = new String[input.nextInt()];       // ragged array! a loop must be used for this case
        }

        String[] criteria = new String[input.nextInt()];         // new array for holding the criteria for winning

        int[][][] score = new int[university.length][][];         // new array for holding scores of each student from each university
        for (int i = 0; i < student.length; i++) {
            score[i] = new int[student[i].length][criteria.length];       // ragged array! a loop must be used for this case
        }

        // the loop that guide the program through the methods
        String command;
        do {
            command = input.next();
            if (command.equalsIgnoreCase("addUniversity")) {
                addUniversity(university, input);
            } else if (command.equalsIgnoreCase("addDays")) {
                addDays(day, input);
            } else if (command.equalsIgnoreCase("addDates")) {
                addDates(date, input);
            } else if (command.equalsIgnoreCase("addStudentsName")) {
                addStudentsName(student, university, input);
            } else if (command.equalsIgnoreCase("addAwardCriteria")) {
                addAwardCriteria(criteria, input);
            } else if (command.equalsIgnoreCase("addScore")) {
                addScore(score, criteria, student, input);
            } else if (command.equalsIgnoreCase("printcontestDetails")) {
                printcontestDetails(university, day, date, fileWriter);
            } else if (command.equalsIgnoreCase("printcontestdetailResults")) {
                printcontestdetailResults(score, student, criteria, university, fileWriter);
            } else if (command.equalsIgnoreCase("printwinnerAwardByEachCriteria")) {
                printwinnerAwardByEachCriteria(score, student, criteria, university, fileWriter);
            }
        } while (!(command.equalsIgnoreCase("Quit")));
        fileWriter.println("	Thank you for using Best App Winner System, Good Bye!");
        fileWriter.flush();     // flushing the printWriter to make sure it prints the required data on the output file 
        fileWriter.close();     // closing the printWriter

    } // end of main method
//---------------------------------------------------------------------------------------------------------------------   

    
    
//---------------------------------------------------------------------------------------------------------------------   
// a method that reads university names from the input file and assign them to the university array
    public static void addUniversity(String[] university, Scanner input) {
        for (int i = 0; i < university.length; i++) {
            university[i] = textSplit(input.next());

        }
    } // Adding university method ends here
//---------------------------------------------------------------------------------------------------------------------   

    
    
//---------------------------------------------------------------------------------------------------------------------           
// a method that reads days from the input file and assign them to the day array
    public static void addDays(String[] days, Scanner input) {
        for (int i = 0; i < days.length; i++) {
            days[i] = textSplit(input.next());

        }
    }// Adding days method ends here
//---------------------------------------------------------------------------------------------------------------------   

    
    
//---------------------------------------------------------------------------------------------------------------------       
// a method that reads dates from the input file and assign them to the date array
    public static void addDates(String[] dates, Scanner input) {
        for (int i = 0; i < dates.length; i++) {
            dates[i] = input.next().replaceAll("_", "/");

        }
    }// Adding dates method ends here
//---------------------------------------------------------------------------------------------------------------------   

    
    
//---------------------------------------------------------------------------------------------------------------------       
// a method that reads student names from the input file and assign them to the student array
    public static void addStudentsName(String[][] names, String[] university, Scanner input) {
        for (int i = 0; i < names.length; i++) {
            for (int j = 0; j < names[i].length; j++) {
                names[i][j] = textSplit(input.next());
            }

        }
    }// Adding students method ends here
//---------------------------------------------------------------------------------------------------------------------   

    
    
//---------------------------------------------------------------------------------------------------------------------       
// a method that reads contest criteria from the input file and assign it to the criteria array
    public static void addAwardCriteria(String[] awardCri, Scanner input) {
        for (int i = 0; i < awardCri.length; i++) {
            awardCri[i] = textSplit(input.next());

        }
    }// Adding Criteria method ends here
//---------------------------------------------------------------------------------------------------------------------   

    
    
//---------------------------------------------------------------------------------------------------------------------       
// a method that reads scores from the input file and assign them to the score array
    public static void addScore(int[][][] score, String[] criteria, String[][] names, Scanner input) {
        for (int i = 0; i < score.length; i++) {
            for (int j = 0; j < names[i].length; j++) {
                for (int k = 0; k < criteria.length; k++) {
                    score[i][j][k] = input.nextInt();
                }
            }

        }
    }// Adding score method ends here
//---------------------------------------------------------------------------------------------------------------------   

    
    
//---------------------------------------------------------------------------------------------------------------------       
// a method that prints the datails of the contest in the output file
    public static void printcontestDetails(String[] university, String[] days, String[] dates, PrintWriter output) {
        output.println("------------  Contest App details are as follows ------\n"
                + "University Name                Contest Day                    Contest Date                   \n"
                + "------------------------------------------------------------------------------");
        for (int i = 0; i < university.length; i++) {
            output.printf("%-30s %-30s %-30s %n", university[i], days[i], dates[i]);

        }
        output.println();
    }// printing contest details method ends here
//---------------------------------------------------------------------------------------------------------------------   

    
    
//---------------------------------------------------------------------------------------------------------------------       
// a method that prints the results of each contestant in the output file
    public static void printcontestdetailResults(int[][][] scores, String[][] names, String[] criteria, String[] university, PrintWriter output) {
        for (int x = 0; x < university.length; x++) {
            output.println("---Contest Results of  " + university[x] + " is as Follows ---\n");
            for (int y = 0; y < names[x].length; y++) {
                output.println("---Student Name   " + names[x][y] + " are as Follows ---");
                for (int z = 0; z < criteria.length; z++) {
                    output.println(criteria[z] + " : " + scores[x][y][z]);
                }
                output.println();
            }
        }

    }// printng results method ends here
//---------------------------------------------------------------------------------------------------------------------   

    
    
//---------------------------------------------------------------------------------------------------------------------      
// a method that prints the university winners of each criteria in the output file
    public static void printwinnerAwardByEachCriteria(int[][][] scores, String[][] names, String[] criteria, String[] university, PrintWriter output) {
        String[][] winner = new String[criteria.length][university.length];
        int max = scores[0][0][0];
        for (int z = 0; z < criteria.length; z++) {
            for (int x = 0; x < university.length; x++) {
                for (int y = 0; y < names[x].length; y++) {
                    if (max < scores[x][y][z]) {
                        max = scores[x][y][z];
                        winner[z][x] = names[x][y];
                    }
                }
                output.println("--- Results of  " + university[x] + " is as Follows ---");
                output.println("Contest Winner name in Category:   " + criteria[z] + " : \n"
                        + winner[z][x]);
                max = 0;
                output.println();
            }

        }

    }// printing winner of each criteria ends here
//---------------------------------------------------------------------------------------------------------------------   

    
    
//---------------------------------------------------------------------------------------------------------------------       
// a method that replaces every underscore in text with a space    
    public static String textSplit(String text) {
        text = text.replaceAll("_", " ");
        return text;
    }

}// text splitter method ends here
//---------------------------------------------------------------------------------------------------------------------   
