import java.util.InputMismatchException;
import java.util.Scanner;


public class Flute extends WindInstrument {
    //variables
    public static final String[] FLUET_TYPE= {"Flute", "Recorder","Bass"};
    private String fluteType;

    
    //constructor 1
    public Flute(String brand, double price, String material, String fluteType){
        super(brand, price, material);
        setFluteType(fluteType);
    }
    
    
    //constructor 2
    public Flute(Scanner scanner){
        super(scanner);
        setFluteType(scanner.nextLine());
    }

    
    //getters and setters
    public String getFluteType() {
        return fluteType;
    }

    
    public void setFluteType(String fluteType) {
        if(isFluteType(fluteType))
            this.fluteType = fluteType;
        else
        	throw new InputMismatchException("Invalid flute type: "+ fluteType);
    }

    
    private boolean isFluteType(String input){
        return isValidType(FLUET_TYPE, input);
    }

    
    //equals method
    @Override
    public boolean equals(Object o) {
        if(!super.equals(o))
            return false;

        if(!(o instanceof Flute))
            return false;

        return getFluteType().equals(((Flute)o).getFluteType());
    }

    
    //toString method
    @Override
    public String toString() {
        return super.toString() + String.format(" Type: %7s", getFluteType().toString());
    }
    
    
    // interface method clone()
	@Override
	public Flute clone() throws CloneNotSupportedException {
		return (Flute) super.clone();
	}
}
