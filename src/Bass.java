import java.util.InputMismatchException;
import java.util.Scanner;


public class Bass extends StringInstrument {
    
	//variables
    public static final int MIN_NUM_OF_STRINGS = 4, MAX_NUM_OF_STRINGS = 6;
    private boolean fretless;

    
    //constructor 1
    public Bass(String brand, double price, int numOfStrings, boolean isFretless){
        super(brand, price, numOfStrings);
        setFretless(isFretless);
    }

    
    //constructor 2
    public Bass(Scanner scanner){
        super(scanner);

        try {
            setFretless(scanner.nextBoolean());
        }catch (InputMismatchException ex){
            throw new InputMismatchException("Whether a bass is fretless or not is boolean, any other string than \"True\" or \"False\" is not acceptable");
        }
    }

    //getters and setters
    public boolean isFretless() {
        return fretless;
    }

    public void setFretless(boolean fretless) {
        this.fretless = fretless;
    }


    @Override
    public void setNumOfStrings(int numOfStrings) {
        if(numOfStrings >= MIN_NUM_OF_STRINGS || numOfStrings <= MAX_NUM_OF_STRINGS)
        	super.setNumOfStrings(numOfStrings);
        else
            throw new IllegalArgumentException("Bass number of strings is a number between "+ MIN_NUM_OF_STRINGS + " and "+ MAX_NUM_OF_STRINGS);
    }

    
    //equals method
    @Override
    public boolean equals(Object o) {
        if(!super.equals(o))
            return false;

        if(!(o instanceof  Bass))
            return false;

        return isFretless() == ((Bass)o).isFretless();
    }

    
    //toString method
    @Override
    public String toString() {
        return super.toString() + String.format(" Fretless: %3s", isFretless() ? "Yes" : "No");
    }
    
    // interface method clone()
	@Override
	public Bass clone() throws CloneNotSupportedException {
		return (Bass) super.clone();
	}
}
