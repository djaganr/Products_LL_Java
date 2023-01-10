import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


class Main{
    public static void PrintOptions(){
        System.out.println("1. Add");
        System.out.println("2. Update");
        System.out.println("3. Delete");
        System.out.println("4. Search by name");
        System.out.println("5. List all products");
        System.out.println("6. Find product by id");
        System.out.println("7. Save File");
        System.out.println("8. Exit");
    }
    public static void main(String[] args) throws IOException{

        // scanner obj
        Scanner myObj = new Scanner(System.in);

        // read from file
        File toRead=new File("inventory.txt");
        FileInputStream fis=new FileInputStream(toRead); // create file input stream to read from a File object
        Scanner sc=new Scanner(fis);
        HashMap<Integer, List<String>> inventory = new HashMap<>();

        //read data from file line by line:
        String currentLine;
        while(sc.hasNextLine()) {
            currentLine=sc.nextLine();
            //now tokenize the currentLine:
            StringTokenizer st=new StringTokenizer(currentLine,",",false);
            //adding to the inventory
            inventory.put(Integer.parseInt(st.nextToken()), Arrays.asList(st.nextToken(), st.nextToken()));
        }
        fis.close();

        // options for operations user want to do
        while(true){
            PrintOptions();
            System.out.println("Enter the option");
            int option = myObj.nextInt();
            switch (option){
                case 1:
                    // Add product
                    System.out.println("Enter id name price of product:");
                    myObj.nextLine();
                    String addP = myObj.nextLine();
                    String[] values = addP.split(" ");
                    inventory.put(Integer.parseInt(values[0]),Arrays.asList(values[1], values[2]));
                    break;
                case 2:
                    // update product
                    System.out.println("Enter id of product:");
                    int updatePId = myObj.nextInt();
                    myObj.nextLine();
                    System.out.println("Enter name and price of product");
                    String[] updateValues = myObj.nextLine().split(" ");
                    inventory.put(updatePId, Arrays.asList(updateValues[0], updateValues[1]));
                    break;
                case 3:
                    // Delete product
                    System.out.println("Enter id of product:");
                    int deletePId = myObj.nextInt();
                    inventory.remove(deletePId);
                    break;
                case 4:
                    // Search by name
                    System.out.println("Enter the name of product:");
                    myObj.nextLine();
                    String name = myObj.nextLine();
                    inventory.forEach((key, value)->{
                        if(name.equals(value.get(0))){
                            System.out.println(inventory.get(key));
                        }
                    });
                    break;
                case 5:
                    // List all products
                    inventory.forEach((key, value)->{
                        System.out.println("key "+ key + " " + "value "+ value);
                    });
                    break;
                case 6:
                    // Find by id
                    System.out.println("Enter id of product:");
                    int productId = myObj.nextInt();
                    System.out.println(inventory.get(productId));
                    break;
                case 7:
                    // Save to file
                    // stream is wrapped inside out and it is directed towards the text file
                    PrintWriter out = new PrintWriter("inventory.txt");
                    inventory.forEach((key, value)->{
                        out.println(key+","+value.get(0)+","+value.get(1)); // println introduces line break after print contents
                    });
                    out.close();
                    break;
                case 8:
                    // Exit
                    return;
                default:
                    System.out.println("Invalid option \n");
                    break;
            }
        }
    }
}