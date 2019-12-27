import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ShowInstrumentsGrid extends GridPane {
	private Label brandLbl, priceLbl, typeLbl;
	private TextField brandField, priceField, typeField;
	private HBox buttonBox;
	private Button btnAdd, btnDel, btnClr;
	private int currentIndex;

	public ShowInstrumentsGrid() {
		currentIndex = InstrumentsGui.currentIndex;
		typeLbl = new Label("Type:");
		typeField = new TextField();
		typeField.setEditable(false);
		typeField.setPromptText("No Items");
		brandLbl = new Label("Brand:");
		brandField = new TextField();
		brandField.setEditable(false);
		brandField.setPromptText("No Items");
		priceLbl = new Label("Price:");
		priceField = new TextField();
		priceField.setEditable(false);
		priceField.setPromptText("No Items");

		InstrumentsGui.displayInstByIndex(currentIndex, typeField, brandField, priceField);

		buttonBox = new HBox();
		btnAdd = new Button("Add");
		btnDel = new Button("Delete");
		btnClr = new Button("Clear");
		buttonBox.setSpacing(30);
		buttonBox.getChildren().addAll(btnAdd, btnDel, btnClr);

		setHgap(15);
		setVgap(15);
		setConstraints(typeLbl, 0, 0);
		setConstraints(brandLbl, 0, 1);
		setConstraints(priceLbl, 0, 2);
		setConstraints(typeField, 1, 0);
		setConstraints(brandField, 1, 1);
		setConstraints(priceField, 1, 2);
		setConstraints(buttonBox, 0, 4, 3, 1);
		setMargin(buttonBox, new Insets(0, 10, 0, 10));
		getChildren().addAll(typeLbl, typeField, brandLbl, brandField, priceLbl, priceField, buttonBox);

		btnAdd.setOnAction(e -> {
			new AddInstrumentsWindow();
		});
		btnDel.setOnAction(e -> {
			try {
				deleteInstrument();
			} catch (Exception ex) {
				ex.getMessage();
			}
		});
		btnClr.setOnAction(e -> {
			InstrumentsGui.musicalInstruments.clear();
			typeField.clear();
			brandField.clear();
			priceField.clear();
			currentIndex = -1;
		});

		BorderPane.setMargin(this, new Insets(20, 0, 0, 250));
	}

	public void deleteInstrument() {
		if (InstrumentsGui.musicalInstruments.size() > 1) {
			InstrumentsGui.musicalInstruments.remove(currentIndex);
			InstrumentsGui.displayInstByIndex(currentIndex, typeField, priceField, brandField);
		} else {
			InstrumentsGui.musicalInstruments.remove(currentIndex);
			typeField.clear();
			brandField.clear();
			priceField.clear();
		}
	}

	public void addInstrument() {
		new AddInstrumentsWindow();
	}
}
