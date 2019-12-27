import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddInstrumentsWindow extends Stage {
	private static Stage window;
	private static Scene chooseInst;
	private static ComboBox<String> instTypeCBox, typeBox, materialTypeBox;
	private static CheckBox frtlssBox;
	private VBox pane;
	private static final Insets PADDING_INSETS = InstrumentsGui.PADDING_INSETS;
	private static GridPane centerGrid;
	private static final int WIDTH = 420, HEIGHT = 310;

	public AddInstrumentsWindow() {

		window = new Stage();
		window.setTitle("Afeka Instruments - Add New Instrument");
		window.initModality(Modality.APPLICATION_MODAL);
		window.setResizable(false);

		instTypeCBox = new ComboBox<>();
		instTypeCBox.setPromptText("Choose Instrument Type Here");
		instTypeCBox.getItems().addAll("Guitar", "Bass", "Flute", "Saxophone");

		pane = new VBox();
		pane.getChildren().add(instTypeCBox);
		pane.setAlignment(Pos.CENTER);
		chooseInst = new Scene(pane, WIDTH, HEIGHT);

		window.setScene(chooseInst);
		window.show();
		instTypeCBox.setOnAction(e -> {
			if (instTypeCBox.getSelectionModel().getSelectedItem().equals("Guitar"))
				window.setScene(addGuitarWindow());
			if (instTypeCBox.getSelectionModel().getSelectedItem().equals("Bass"))
				window.setScene(addBassWindow());
			if (instTypeCBox.getSelectionModel().getSelectedItem().equals("Flute"))
				window.setScene(addFluteWindow());
			if (instTypeCBox.getSelectionModel().getSelectedItem().equals("Saxophone"))
				window.setScene(addSaxWindow());
		});
	}

	private static Scene addGuitarWindow() {
		centerGrid = addWindParamGrid();
		((TextInputControl) centerGrid.getChildren().get(1)).setPromptText("Ex: Gibson");
		((TextInputControl) centerGrid.getChildren().get(5)).setPromptText("Ex: 6");
		Label gtrType = new Label("Guitar Type:");
		typeBox = new ComboBox<String>();
		typeBox.setPromptText("Type");
		typeBox.setMinSize(115, 0);
		typeBox.getItems().addAll("Classic", "Acoustic", "Electric");
		GridPane.setConstraints(gtrType, 0, 3);
		GridPane.setConstraints(typeBox, 1, 3);
		GridPane.setMargin(typeBox, PADDING_INSETS);

		centerGrid.getChildren().addAll(gtrType, typeBox);
		return chooseInst;
	}

	private static Scene addBassWindow() {
		centerGrid = addWindParamGrid();
		((TextInputControl) centerGrid.getChildren().get(1)).setPromptText("Ex: Fender Jazz");
		((TextInputControl) centerGrid.getChildren().get(5)).setPromptText("Ex: 4");
		Label frtlssLbl = new Label("Fretless:");
		frtlssBox = new CheckBox();

		GridPane.setConstraints(frtlssLbl, 0, 3);
		GridPane.setConstraints(frtlssBox, 1, 3);
		GridPane.setMargin(frtlssBox, PADDING_INSETS);

		centerGrid.getChildren().addAll(frtlssLbl, frtlssBox);
		return chooseInst;
	}

	private static Scene addFluteWindow() {
		centerGrid = addLblAndPrcPane();
		((TextInputControl) centerGrid.getChildren().get(1)).setPromptText("Ex: Levit");
		((TextInputControl) centerGrid.getChildren().get(3)).setPromptText("Ex: 300");

		Label materialLbl = new Label("Material:");
		materialTypeBox = new ComboBox<>();
		materialTypeBox.setPromptText("Material");
		materialTypeBox.getItems().addAll("Wood", "Metal", "Plastic");

		Label fluteTypeLbl = new Label("Flute Type:");
		typeBox = new ComboBox<>();
		typeBox.setPromptText("Type");
		typeBox.setMinSize(115, 0);
		typeBox.getItems().addAll("Flute", "Recorder", "Bass");
		GridPane.setConstraints(materialLbl, 0, 3);
		GridPane.setConstraints(materialTypeBox, 1, 3);
		GridPane.setConstraints(typeBox, 1, 4);
		GridPane.setConstraints(fluteTypeLbl, 0, 4);
		GridPane.setMargin(materialTypeBox, PADDING_INSETS);
		GridPane.setMargin(typeBox, PADDING_INSETS);

		Button addBtn = new Button("Add");
		addBtn.setOnAction(e -> {
			Flute flt = new Flute(((TextInputControl) centerGrid.getChildren().get(1)).getText(),
					Double.parseDouble(((TextInputControl) centerGrid.getChildren().get(3)).getText()),
					materialTypeBox.getSelectionModel().getSelectedItem(),
					typeBox.getSelectionModel().getSelectedItem());
			InstrumentsGui.musicalInstruments.add(flt);
			window.close();
			if (InstrumentsGui.musicalInstruments.size() == 1) {
				InstrumentsGui.currentIndex =0;
			}
		});
		centerGrid.getChildren().addAll(materialLbl, materialTypeBox, fluteTypeLbl, typeBox, addBtn);
		BorderPane mainPane = new BorderPane();
		mainPane.setPadding(PADDING_INSETS);
		mainPane.setCenter(centerGrid);
		mainPane.setTop(instTypeCBox);
		mainPane.setBottom(addBtn);
		BorderPane.setAlignment(addBtn, Pos.CENTER);
		BorderPane.setAlignment(instTypeCBox, Pos.CENTER);

		chooseInst = new Scene(mainPane, WIDTH, HEIGHT);
		instTypeCBox.requestFocus();

		return chooseInst;
	}

	private static Scene addSaxWindow() {
		centerGrid = addLblAndPrcPane();
		Button addBtn = new Button("Add");
		VBox pane = new VBox();

		pane.getChildren().addAll(instTypeCBox, centerGrid, addBtn);
		pane.setAlignment(Pos.CENTER);

		addBtn.setOnAction(e -> {
			Saxophone sax = new Saxophone(((TextInputControl) centerGrid.getChildren().get(1)).getText(),
					Double.parseDouble(((TextInputControl) centerGrid.getChildren().get(3)).getText()));
			InstrumentsGui.musicalInstruments.add(sax);
			window.close();
			if (InstrumentsGui.musicalInstruments.size() == 1) {
				InstrumentsGui.currentIndex =0;
			}
		});
		chooseInst = new Scene(pane, 420, 310);
		return chooseInst;
	}

	private static GridPane addWindParamGrid() { // Common fields of wind type instruments are handled here
		centerGrid = addLblAndPrcPane();
		((TextInputControl) centerGrid.getChildren().get(3)).setPromptText("Ex: 7500");

		Label numOfStringsLbl = new Label("Number of strings:");
		TextField numOfStringsFld = new TextField();

		Button addBtn = new Button("Add");
		addBtn.setOnAction(e -> {
			if (instTypeCBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Guitar")) {
				Guitar gtr = new Guitar(((TextInputControl) centerGrid.getChildren().get(1)).getText(),
						Double.parseDouble(((TextInputControl) centerGrid.getChildren().get(3)).getText()),
						Integer.parseInt(numOfStringsFld.getText()), typeBox.getSelectionModel().getSelectedItem());
				InstrumentsGui.musicalInstruments.add(gtr);	
				window.close();
			} else if (instTypeCBox.getSelectionModel().getSelectedItem().equalsIgnoreCase("Bass")) {
				Bass bass = new Bass(((TextInputControl) centerGrid.getChildren().get(1)).getText(),
						Double.parseDouble(((TextInputControl) centerGrid.getChildren().get(3)).getText()),
						Integer.parseInt(numOfStringsFld.getText()), frtlssBox.isArmed());
				InstrumentsGui.musicalInstruments.add(bass);
				window.close();
			}
			if (InstrumentsGui.musicalInstruments.size() == 1) {
				InstrumentsGui.currentIndex =0;
			}
		});
		GridPane.setConstraints(numOfStringsLbl, 0, 2);
		GridPane.setConstraints(numOfStringsFld, 1, 2);
		GridPane.setMargin(numOfStringsFld, PADDING_INSETS);
		centerGrid.getChildren().addAll(numOfStringsLbl, numOfStringsFld);

		BorderPane mainPane = new BorderPane();
		mainPane.setPadding(PADDING_INSETS);
		mainPane.setCenter(centerGrid);
		mainPane.setTop(instTypeCBox);
		mainPane.setBottom(addBtn);
		BorderPane.setAlignment(addBtn, Pos.CENTER);
		BorderPane.setAlignment(instTypeCBox, Pos.CENTER);

		chooseInst = new Scene(mainPane, WIDTH, HEIGHT);
		instTypeCBox.requestFocus();
		return centerGrid;
	}

	public static GridPane addLblAndPrcPane() {

		Label brandLbl = new Label("Brand:");
		Label priceLbl = new Label("Price:");
		TextField brandField = new TextField();
		TextField priceField = new TextField();
		GridPane grid = new GridPane();
		grid.getChildren().addAll(brandLbl, brandField, priceLbl, priceField);
		grid.setHgap(15);
		grid.setPadding(InstrumentsGui.PADDING_INSETS);
		grid.setAlignment(Pos.TOP_CENTER);

		GridPane.setConstraints(brandLbl, 0, 0);
		GridPane.setConstraints(priceLbl, 0, 1);

		GridPane.setConstraints(brandField, 1, 0);
		GridPane.setConstraints(priceField, 1, 1);

		GridPane.setMargin(brandField, InstrumentsGui.PADDING_INSETS);
		GridPane.setMargin(priceField, InstrumentsGui.PADDING_INSETS);
		return grid;
	}

	public void setIntOrDbl(String price) {
		Double number = Double.parseDouble(price);
		if (number != Math.floor(number)) {
			price = number.toString();
		}
	}
}
