import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/*
----------------------------------------------------------------
 Mason Dinardi & Conner Cross
 Homework 2 - Implementation of Strategy Pattern
 September 18, 2019

 As we were asked to submit just one java file, all of our
 classes appear in this single file. In a better implementation,
 each class would have it's own file.
----------------------------------------------------------------
*/

//----------------------------------------------------------------
//main class and driver method
public class StrategyPattern {

    public static void main(String args[]) {

        //deletes previous output file
        File file = new File("./output.txt");
        if(file.delete())
        {
            System.out.println("Output file deleted successfully");
        }
        else
        {
            System.out.println("Failed to delete the output file");
        }

        //simple data to be used in this example
        String[] data = {"dog", "cat", "fish"};

        //main loop, outer loop allows user to create new databases
        //inner loop allows user to change storage strategy on database
        String input;
        do {
            boolean flag = true;

            //get user input for database type
            Scanner reader = new Scanner(System.in);
            System.out.println("\nWhat type of database would you like? (Relational, NoSQL, Graph, or enter 'done' to end): ");
            input = reader.next().toLowerCase();

            //instantiate specified database type, or end program execution
            Database database = new Database();
            switch (input) {
                case "relational":
                    database =  new RelationalDB();
                    break;
                case "nosql":
                    database = new NoSQLDB();
                    break;
                case "graph":
                    database = new GraphDB();
                    break;
                case "done" :
                    flag = false;
                    writeToOutput("\n--------------------------------" +
                            "\nFinished Execution");
                    break;
                default:
                    System.out.println("Input unrecognized.");
                    flag = false;
            }

            //write database header to output file
            if (flag) {
                writeToOutput("\n--------------------------------" +
                        "\nDatabase type: " + input.toUpperCase());

                //call default storage strategy for database type
                database.store(data);

                //allow user to change storage strategy
                String choice;
                do {
                    System.out.println("Would you like to select a different storage strategy? (Y/N)");
                    choice = reader.next().toLowerCase();

                    if (choice.equals("y")) {
                        boolean flag2 = true;
                        System.out.println("Enter the new storage strategy: (Table, Document, Node)");
                        String storageStrategy = reader.next().toLowerCase();

                        switch (storageStrategy) {
                            case "table":
                                database.setStore(new TableStore());
                                break;
                            case "document":
                                database.setStore(new DocumentStore());
                                break;
                            case "node":
                                database.setStore(new NodeStore());
                                break;
                            default:
                                System.out.println("Strategy unrecognized.");
                                flag2 = false;
                        }

                        //call store method for newly set storage strategy
                        if(flag2)
                            database.store(data);

                    }
                } while (!choice.equals("n"));
            }

        } while(!input.equals("done"));
    }

    //method to append line to output file
    public static void writeToOutput(String textToAppend) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("./output.txt", true)  //Set true for append mode
            );
            writer.newLine();   //Add new line
            writer.write(textToAppend);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to the output file");
        }
    }
}
//----------------------------------------------------------------


//----------------------------------------------------------------
// Parent Database class and child classes for each database type

//Parent Database class
class Database {
    IDatabaseStore ds;
    public void store(String[] data){}
    public void setStore(IDatabaseStore storeStrategy){}
}

//Relational database class
class RelationalDB extends Database {

    //constructor
    public RelationalDB() {
        ds = new TableStore();
    }

    // method to call store method of set store strategy
    // store() would be a unique implementation in real use
    public void store(String[] data) {
        ds.store(data);
        System.out.println("\nData stored using the " + ds.toString() + "strategy.");
    }

    public void setStore(IDatabaseStore storeStrategy) {
        ds = storeStrategy;
    }
}

//NoSQL database class
class NoSQLDB extends Database {

    //constructor
    public NoSQLDB() {
        ds = new DocumentStore();
    }

    // method to call store method of set store strategy
    // store() would be a unique implementation in real use
    public void store(String[] data) {
        ds.store(data);
        System.out.println("\nData stored using the " + ds.toString() + "strategy.");
    }

    //method to set store strategy
    public void setStore(IDatabaseStore storeStrategy) {
        ds = storeStrategy;
    }
}

//Graph database class
class GraphDB extends Database {

    //constructor
    public GraphDB() {
        ds = new NodeStore();
    }

    // method to call store method of set store strategy
    // store() would be a unique implementation in real use
    public void store(String[] data) {
        ds.store(data);
        System.out.println("\nData stored using the " + ds.toString() + "strategy.");
    }

    //method to set store strategy
    public void setStore(IDatabaseStore storeStrategy) {
        ds = storeStrategy;
    }
}
//----------------------------------------------------------------


//----------------------------------------------------------------
//Strategy Interface and strategy classes for each storage strategy

//Interface IDatabaseStore
interface IDatabaseStore {
    void store(String[] data);
}

//Document store strategy
//writes array of data to output.txt
class TableStore implements IDatabaseStore {

    //implementation of store method for Table store
    public void store(String[] data) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("./output.txt", true)
            );
            writer.newLine();   //Add new line
            writer.write("\nData stored using the Table Store strategy\n" + Arrays.toString(data));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to the output file");
        }
    }

    //method to return string representation of class
    public String toString(){return "Table Store ";}
}

//Document store strategy
//writes array of data to output.txt
class DocumentStore implements IDatabaseStore {

    //implementation of store method for Document store
    public void store(String[] data) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("./output.txt", true)  //Set true for append mode
            );
            writer.newLine();   //Add new line
            writer.write("\nData stored using the Document Store strategy\n" + Arrays.toString(data));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to the output file");
        }
    }

    //method to return string representation of class
    public String toString(){return "Document Store ";}
}

//Node store strategy
//writes array of data to output.txt
class NodeStore implements IDatabaseStore {

    //implementation of store method for Node store
    public void store(String[] data) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("./output.txt", true)  //Set true for append mode
            );
            writer.newLine();   //Add new line
            writer.write("\nData stored using the Node Store strategy\n" + Arrays.toString(data));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to the output file");
        }
    }

    //method to return string representation of class
    public String toString(){return "Node Store ";}
}
//----------------------------------------------------------------