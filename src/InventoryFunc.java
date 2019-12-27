
import java.util.ArrayList;

public interface InventoryFunc<E> {
	
	//add all string instruments to inventory list
	void addAllStringInstruments(ArrayList<? extends MusicalInstrument> src, ArrayList<? super MusicalInstrument> des);
	
	//add all wind instruments to inventory list
	void addAllWindInstruments(ArrayList<? extends MusicalInstrument> src, ArrayList<? super MusicalInstrument> des);
	
	//sort method
	void SortByBrandAndPrice(ArrayList<E> arr);
	
	//binary search method on collection items
	int binSearchByBrandAndPrice(ArrayList<E> arr, String brand, Number price);
	
	//removes specific item from collection
	void addInstrument(ArrayList<E> arr, E e);
	
	//removes specific item from collection
	boolean removeInstrument(ArrayList<E> arr, E e);
	
	//removes all items from collection
	boolean removeAll(ArrayList<E> arr);
}
