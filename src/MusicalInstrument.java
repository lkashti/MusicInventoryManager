import java.util.InputMismatchException;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public abstract class MusicalInstrument implements InstrumentFunc {
	// variables
	protected String brand;
	protected Number price;

	// constructor 1
	public MusicalInstrument(String brand, Number price) {
		setBrand(brand);
		setPrice(price);
	}

	// constructor 2
	public MusicalInstrument(Scanner scanner) {
		String line = null;
		Number price = 0;
		try {
			line = scanner.next();
			price = Integer.parseInt(line);
		} catch (NumberFormatException e1) {
			try {
				price = Double.parseDouble(line);
			} catch (NumberFormatException e2) {
				throw new InputMismatchException("Price not found!");
			}
		}
		setPrice(price);
		scanner.nextLine();
		line = scanner.nextLine();
		setBrand(line);
	}

	// getters and setters
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Number getPrice() {
		return price;
	}

	public void setPrice(Number price) {
		if (price.intValue() > 0)
			this.price = price;
		else {
		Alert alertBox = new Alert(AlertType.ERROR);
		alertBox.setContentText("Price must be a positive number!");
		alertBox.showAndWait();
		throw new IllegalArgumentException("Price must be a positive number!");
		}
	}

	// isValidType method
	protected boolean isValidType(String[] typeArr, String material) {
		for (int i = 0; i < typeArr.length; i++) {
			if (material.equals(typeArr[i])) {
				return true;
			}
		}
		return false;
	}

	// equals method
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof MusicalInstrument))
			return false;
		MusicalInstrument otherInstrument = (MusicalInstrument) o;
		return getPrice().doubleValue() == otherInstrument.getPrice().doubleValue()
				&& getBrand().equals(otherInstrument.getBrand());
	}

	// toString method
	@Override
	public String toString() {
		// return String.format("%-8s %-9s| Price: %7.2f,", getBrand(),
		// getClass().getCanonicalName(), getPrice());
		return String.format("%-8s %-9s| Price: %7s,", getBrand(), getClass().getCanonicalName(), getPrice());
	}

	// interface method compareTo
	@Override
	public int compareTo(MusicalInstrument m) {
		int brandIndex = this.getBrand().compareTo(m.getBrand());
		if (brandIndex == 0)
			return (int) ((this.getPrice().doubleValue() - m.getPrice().doubleValue()) * 100);
		else
			return brandIndex;
	}

}
