import java.util.InputMismatchException;
import java.util.Scanner;


public abstract class WindInstrument extends MusicalInstrument {
    //variables
	public final static String WIND_INSTRUMENT_MATERIAL[] = {"Wood", "Metal", "Plastic"};
    protected String material;

    
    //constructor 1
    public WindInstrument(String brand, double price, String material){
        super(brand, price);
        setMaterial(material);
    }
    
    
    //constructor 2
    public WindInstrument(Scanner scanner){
        super(scanner);
        String material = scanner.nextLine();
        setMaterial(material);
    }


    //isValidType method
    private boolean isMaterial(String substance){
        return isValidType(WIND_INSTRUMENT_MATERIAL, substance);
    }
    


    //getters and setters
    public String getMaterial() {
        return material;
    }
    
    
    public void setMaterial(String substance) {
        if(isMaterial(substance)) {
            this.material = substance;
            return;
        }
        throw new InputMismatchException(substance + " is not a valid wind instrument material");
    }


    //equals method
    @Override
    public boolean equals(Object o) {
        if(!super.equals(o))
            return false;

        if(!(o instanceof  WindInstrument))
            return false;

        return getMaterial().equals(((WindInstrument)o).getMaterial());
    }

    
    //toString Method
    @Override
    public String toString() {
        return super.toString() + String.format(" Made of: %12s| ", getMaterial().toString());
    }


}
