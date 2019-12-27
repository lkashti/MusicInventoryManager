
import java.util.ArrayList;
import java.util.Collections;


public class Inventory<E extends MusicalInstrument> implements InventoryFunc<E>{
	
	//variables
	private ArrayList<E> arr;
	private double total;
	private boolean isSorted;
	
	//constructor 1
	public Inventory() {
		setArr(new ArrayList<E>());
	}
	
	//constructor 2
	public Inventory(ArrayList<E> arr) {
		setArr(arr);
	}


	//getters and setters
	public ArrayList<E> getArr() {
		return arr;
	}

	public void setArr(ArrayList<E> arr) {
		this.arr = arr;
		setSorted(false);
		setTotal();		
	}

	public double getTotal() {
		return total;
	}
	
	public void setTotal(){
		double sum = 0;
		if(this.arr!=null)
			for(int i=0; i<this.arr.size(); i++)
				sum = genericSum(sum, arr.get(i).getPrice());
		this.total = sum;
	}
	
	public boolean isSorted() {
		return isSorted;
	}

	public void setSorted(boolean isSorted) {
		this.isSorted = isSorted;
	}

	//generic method. sums two numbers and returns double
	public  <N1 extends Number, N2 extends Number> double genericSum(N1 a, N2 b){
	    return a.doubleValue() + b.doubleValue(); 
	}

		
	//interface methods 	
	//add all string instruments to inventory list
	@Override
	public void addAllStringInstruments(ArrayList<? extends MusicalInstrument> src, ArrayList<? super MusicalInstrument> des){
		for(int i=0; i<src.size(); i++)
			if(src.get(i) instanceof StringInstrument)
				des.add((StringInstrument) src.get(i));
		setSorted(false);
		setTotal();	
	}
	
	//add all wind instruments to inventory list
	@Override
	public void addAllWindInstruments(ArrayList<? extends MusicalInstrument> src, ArrayList<? super MusicalInstrument> des){
		for(int i=0; i<src.size(); i++)
			if(src.get(i) instanceof WindInstrument)
				des.add((WindInstrument) src.get(i));
		setSorted(false);
		setTotal();	
	}
	
	//sort method
	@Override
	public void SortByBrandAndPrice(ArrayList<E> arr){
		 Collections.sort(arr);
		 setSorted(true);
	}

	
	
	//Binary search method with collection items
	@Override
	public int binSearchByBrandAndPrice(ArrayList<E> arr, String brand, Number price){
		int brandIndex = -1;
		if(isSorted){
			int low = 0;
			int high = arr.size() - 1;
			while (high >= low) {
				int mid = (low + high) / 2;
				if (brand.compareToIgnoreCase(arr.get(mid).getBrand()) < 0)
					high = mid - 1; 
				else 
					if (brand.compareToIgnoreCase(arr.get(mid).getBrand()) == 0){
						if(brandIndex == -1)	
							brandIndex = mid;
						else 
							if(Math.abs(price.doubleValue()-arr.get(brandIndex).getPrice().doubleValue()) > Math.abs(price.doubleValue()-arr.get(mid).getPrice().doubleValue()))
								brandIndex = mid;
						if((price.doubleValue() - arr.get(mid).getPrice().doubleValue()) < 0)		
							high = mid - 1;
						else 	
							if((price.doubleValue() - arr.get(mid).getPrice().doubleValue()) == 0)	return mid;
							else	low = mid + 1;
					}else
						low = mid + 1;
			}
		}
		return brandIndex;
	}

	
	//removes specific item from collection
	@Override
	public void addInstrument(ArrayList<E> arr, E e) {
		arr.add(e);
		setTotal();
	}
	
	//removes specific item from collection
	@Override
	public boolean removeInstrument(ArrayList<E> arr, E e) {
		if(arr.remove(e)){
			setTotal();
			return true;
		}
		return false;
	}
	
	
	
	//removeAll interface method
	@Override
	public boolean removeAll(ArrayList<E> arr) {
		if(arr.removeAll(arr)){
			setSorted(false);
			setTotal();
			return true;
		}
		return false;
	}	
	
	
	//toString method
	@Override
	public String toString() {
		String str = "\n-------------------------------------------------------------------------\n" 
					+ "AFEKA MUSICAL INSTRUMENTS INVENTORY"
					+ "\n-------------------------------------------------------------------------\n";
		if(arr.size() == 0)	
			str += "There Is No Instruments To Show\n";
		else
			for(int i=0;i<arr.size(); i++)
				str += arr.get(i) + "\n";
		return str +String.format("\nTotal Price:%7.2f \t Sorted: %5s ", total, isSorted);
	}



}
