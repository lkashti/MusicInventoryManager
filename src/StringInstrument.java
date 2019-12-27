import java.util.InputMismatchException;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class StringInstrument extends MusicalInstrument {
	// variables
	protected int numOfStrings;

	// constructor 1
	public StringInstrument(String brand, double price, int numOfStrings) {
		super(brand, price);
		setNumOfStrings(numOfStrings);
	}

	// constructor 2
	public StringInstrument(Scanner scanner) {
		super(scanner);
		int numOfStrings;
		try {
			numOfStrings = scanner.nextInt();
		} catch (InputMismatchException e) {
			throw new InputMismatchException("Number of strings must be a positive integer");
		}
		setNumOfStrings(numOfStrings);
	}

	// getters and setters
	public void setNumOfStrings(int numOfStrings) {
		if (numOfStrings < 1) {
			Alert alertBox = new Alert(AlertType.ERROR);
			alertBox.setTitle("Error");
			alertBox.setHeaderText("Error");
			alertBox.setContentText("Number of strings cannot be negative!");
			alertBox.showAndWait();
		}
		this.numOfStrings = numOfStrings;
	}

	public int getNumOfStrings() {
		return numOfStrings;
	}

	// equals method
	@Override
	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;

		if (!(o instanceof StringInstrument))
			return false;

		return getNumOfStrings() == ((StringInstrument) o).getNumOfStrings();
	}

	// toString method
	@Override
	public String toString() {
		return super.toString() + String.format(" Number of strings: %2d| ", getNumOfStrings());
	}
}
