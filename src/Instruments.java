import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Instruments {

    private static Scanner consoleScanner;

	public static void main(String[] args) {
        ArrayList<MusicalInstrument> allInstruments = new ArrayList<MusicalInstrument> ();
        File file = getInstrumentsFileFromUser();
        loadInstrumentsFromFile(file, allInstruments);
        if(allInstruments.size() == 0) {
            System.out.println("There are no instruments in the store currently");
            return;
        }
        printInstruments(allInstruments);

        int different = getNumOfDifferentElements(allInstruments);
        System.out.println("\n\nDifferent Instruments: " + different);

        MusicalInstrument mostExpensive = getMostExpensiveInstrument(allInstruments);
        System.out.println("\n\nMost Expensive Instrument:\n" + mostExpensive);
        
        //HW2 Method
        startMenu(allInstruments);
        
    }

    public static File getInstrumentsFileFromUser(){
        boolean stopLoop = true;
        File file;
        consoleScanner = new Scanner(System.in);

        do {
            System.out.println("Please enter instruments file name / path:");
            String filepath = consoleScanner.nextLine();
            file = new File(filepath);
            stopLoop = file.exists() && file.canRead();

            if(!stopLoop)
                System.out.println("\nFile Error! Please try again\n\n");
        }while (!stopLoop);
        //consoleScanner.close();
        return file;
    }

    public static void loadInstrumentsFromFile(File file, ArrayList<MusicalInstrument> allInstruments){
        Scanner scanner = null;

        try {

            scanner = new Scanner(file);

            addAllInstruments(allInstruments ,loadGuitars(scanner));

            addAllInstruments(allInstruments ,loadBassGuitars(scanner));

            addAllInstruments(allInstruments ,loadFlutes(scanner));

            addAllInstruments(allInstruments ,loadSaxophones(scanner));

        }catch (InputMismatchException | IllegalArgumentException ex){
            System.err.println("\n"+ ex.getMessage());
            System.exit(1);
        }catch (FileNotFoundException ex){
            System.err.println("\nFile Error! File was not found");
            System.exit(2);
        } finally {
            //scanner.close();
        }
        System.out.println("\nInstruments loaded from file successfully!\n");

    }

    public static ArrayList<MusicalInstrument> loadGuitars(Scanner scanner){
        int numOfInstruments = scanner.nextInt();
        ArrayList<MusicalInstrument> guitars = new ArrayList<MusicalInstrument>(numOfInstruments);
        for(int i = 0; i < numOfInstruments ; i++)
            guitars.add(new Guitar(scanner));
        return guitars;
    }

    public static ArrayList<MusicalInstrument> loadBassGuitars(Scanner scanner){
        int numOfInstruments = scanner.nextInt();
        ArrayList<MusicalInstrument> bassGuitars = new ArrayList<MusicalInstrument>(numOfInstruments);

        for(int i = 0; i < numOfInstruments ; i++)
            bassGuitars.add(new Bass(scanner));

        return bassGuitars;
    }

    public static ArrayList<MusicalInstrument> loadFlutes(Scanner scanner){
        int numOfInstruments = scanner.nextInt();
        ArrayList<MusicalInstrument> flutes = new ArrayList<MusicalInstrument>(numOfInstruments);

        for(int i = 0; i < numOfInstruments ; i++)
            flutes.add(new Flute(scanner));


        return flutes;
    }

    public static ArrayList<MusicalInstrument> loadSaxophones(Scanner scanner){
        int numOfInstruments = scanner.nextInt();
        ArrayList<MusicalInstrument> saxophones = new ArrayList<MusicalInstrument>(numOfInstruments);

        for(int i = 0; i < numOfInstruments ; i++)
            saxophones.add(new Saxophone(scanner));

        return saxophones;
    }

    public static void addAllInstruments(ArrayList<MusicalInstrument> instruments, ArrayList<MusicalInstrument> moreInstruments){
        for(int i = 0 ; i < moreInstruments.size() ; i++){
            instruments.add(moreInstruments.get(i));
        }
    }

    public static void printInstruments(ArrayList<MusicalInstrument> instruments){
        for(int i = 0 ; i < instruments.size() ; i++)
            System.out.println(instruments.get(i));
    }

    public static int getNumOfDifferentElements(ArrayList<MusicalInstrument> instruments){
        int numOfDifferentInstruments;
        ArrayList<MusicalInstrument> differentInstruments = new ArrayList<MusicalInstrument>();
        System.out.println();

        for(int i = 0 ; i < instruments.size() ; i++){
            if(!differentInstruments.contains((instruments.get(i)))){
                differentInstruments.add(instruments.get(i));
            }
        }

        if(differentInstruments.size() == 1)
            numOfDifferentInstruments = 0;

        else
            numOfDifferentInstruments = differentInstruments.size();


        return numOfDifferentInstruments;
    }

    public static MusicalInstrument getMostExpensiveInstrument(ArrayList<MusicalInstrument> instruments){
        double maxPrice = 0;
        MusicalInstrument mostExpensive = (MusicalInstrument) instruments.get(0);
        for(int i = 0 ; i < instruments.size() ; i++){
            MusicalInstrument temp = (MusicalInstrument)instruments.get(i);
            if(temp.getPrice().doubleValue() > maxPrice){
                maxPrice = temp.getPrice().doubleValue();
                mostExpensive = temp;
            }
        }
        return mostExpensive;
    }

    //HW2 Method
    public static void startMenu(ArrayList<MusicalInstrument> src){
    	Inventory<MusicalInstrument> InstInventory = new Inventory<MusicalInstrument>();
        boolean stopLoop = false;
        Scanner scn = new Scanner(System.in);
        do {
        	System.out.println("\n-------------------------------------------------------------------------");
            System.out.println("AFEKA MUSICAL INSTRUMENT INVENTORY MENU");
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("1. Copy All String Instruments To Inventory");
            System.out.println("2. Copy All Wind Instruments To Inventory");
            System.out.println("3. Sort Instruments By Brand And Price");
            System.out.println("4. Search Instrument By Brand And Price");
            System.out.println("5. Delete Instrument");
            System.out.println("6. Delete all Instruments");
            System.out.println("7. Print Inventory Instruments");
            System.out.println("Choose your option or any other key to EXIT\n");
            System.out.print("Your Option: ");
            String op = scn.next();
            String brand="",input="";
            Number price = 0;
            int index = -1;
            switch(op){
            	case "1":
            		InstInventory.addAllStringInstruments(src, InstInventory.getArr());
            		System.out.println("\nAll String Instruments Added Successfully!\n");
            		break;
            	case "2":
            		InstInventory.addAllWindInstruments(src, InstInventory.getArr());
            		System.out.println("\nAll Wind Instruments Added Successfully!\n");
            		break;
            	case "3":
            		InstInventory.SortByBrandAndPrice(InstInventory.getArr());
            		System.out.println("\nInstruments Sorted Successfully!\n");
            		break;
            	case "4":
            		System.out.println("\nSEARCH INSTRUMENT:");
            		System.out.print("Brand:");
            		brand = scn.next();
            		System.out.print("Price:");
            		price = 0;
                    try {
                       	input = scn.next();
                   		price = Integer.parseInt(input);
                   	}catch(NumberFormatException e1){
                       	try{
                       		price = Double.parseDouble(input);
                       	}catch(NumberFormatException e2){}
                   	}
            		index = InstInventory.binSearchByBrandAndPrice(InstInventory.getArr(), brand, price);
            		if(index<0)
            			System.out.println("Instrument Not Found!\n");
            		else
            			System.out.printf("Result:\n\t%s\n", InstInventory.getArr().get(index));
            		break;
            	case "5":
            		System.out.println("\nDELETE INSTRUMENT:");
            		System.out.print("Brand:");
            		brand = scn.next();
            		System.out.print("Price:");
            		price = 0;
                    try {
                       	input = scn.next();
                   		price = Integer.parseInt(input);
                   	}catch(NumberFormatException e1){
                       	try{
                       		price = Double.parseDouble(input);
                       	}catch(NumberFormatException e2){}
                   	}
            		index = InstInventory.binSearchByBrandAndPrice(InstInventory.getArr(), brand, price);
            		if(index<0)
            			System.out.println("Instrument Not Found!\n");
            		else{
            			System.out.printf("Result:\n\t%s\n", InstInventory.getArr().get(index));
            			System.out.print("Are You Sure?(Y/N) ");
                        op = scn.next();
                        if(op.toLowerCase().equals("y")){
                        	InstInventory.removeInstrument(InstInventory.getArr(), InstInventory.getArr().get(index));
                    		System.out.println("Instrument Deleted Successfully!\n");
                        }else
                        	System.out.println("Operation Canceled!");
            		}
            		break;
            	case "6":
            		System.out.println("\nDELETE ALL INSTRUMENTS:");
        			System.out.print("Are You Sure?(Y/N) ");
                    op = scn.next();
                    if(op.toLowerCase().equals("y")){
                    	InstInventory.removeAll(InstInventory.getArr());
                    	System.out.println("\nAll Instruments Deleted Successfully!\n");
                    }else	
                    	System.out.println("Operation Canceled!");
            		break;
            	case "7":
            		System.out.println(InstInventory);
            		break;
            	default:
            		stopLoop = true;
            		System.out.println("Finished!");
            		break;
            }

        }while (!stopLoop);
        scn.close();
    }
}
