import java.util.Scanner;



public class Saxophone extends WindInstrument{
    //variables
	public static final int METAL = 1;

	
	
	//constructor 1
    public Saxophone(String brand, double price){
        super(brand, price, WIND_INSTRUMENT_MATERIAL[METAL]);
    }

    
    
    //constructor 2
    public Saxophone(Scanner scanner){
        super(scanner);

        if(!getMaterial().equals(WIND_INSTRUMENT_MATERIAL[METAL]))
            throw new IllegalArgumentException("Illegal Saxophone material: " + getMaterial());
    }

    
    //equals method
    @Override
    public boolean equals(Object o) {
        if(!super.equals(o))
            return false;

        return o instanceof  Saxophone;
    }
    
    
    //interface method clone()
	@Override
	public Saxophone clone() throws CloneNotSupportedException{
	      return (Saxophone) super.clone();
	}
}
