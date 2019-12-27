import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class InstrumentsGui extends Application {
	private Stage window;
	private Scene scene;
	private BorderPane mainPanel;
	private SearchField searchField;
	private ShowInstrumentsGrid showInstrumentsGrid;
	private AdLine adLine;
	protected static ArrayList<MusicalInstrument> musicalInstruments;
	static final Insets PADDING_INSETS = new Insets(10);
	static int currentIndex = 0;
	protected static Button rightBtn, leftBtn;

	public InstrumentsGui() {
		musicalInstruments = new ArrayList<MusicalInstrument>();
		Instruments.loadInstrumentsFromFile(getInstrumentsFileFromUser(), musicalInstruments);
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage Stage) throws Exception {
		this.window = Stage;
		window.setTitle("Afeka Instruments Music Store");
		window.setResizable(false);

		this.mainPanel = new BorderPane();
		this.showInstrumentsGrid = new ShowInstrumentsGrid();
		this.searchField = new SearchField(musicalInstruments, showInstrumentsGrid);
		this.adLine = new AdLine();

		// right side
		rightBtn = new Button(">");
		BorderPane.setMargin(rightBtn, new Insets(80, 15, 0, 0));
		try {
			rightBtn.setOnAction(e -> {
				currentIndex++;
				if (currentIndex == musicalInstruments.size())
					currentIndex = 0;
				displayInstByIndex(currentIndex, (TextField) showInstrumentsGrid.getChildren().get(1),
						(TextField) showInstrumentsGrid.getChildren().get(3),
						(TextField) showInstrumentsGrid.getChildren().get(5));
			});
		} catch (IndexOutOfBoundsException e1) {
			e1.getMessage();
		}
		// left side
		leftBtn = new Button("<");
		BorderPane.setMargin(leftBtn, new Insets(80, 0, 0, 15));
		try {
			leftBtn.setOnAction(e -> {
				currentIndex--;
				if (currentIndex == -1)
					currentIndex = musicalInstruments.size() - 1;
				displayInstByIndex(currentIndex, (TextField) showInstrumentsGrid.getChildren().get(1),
						(TextField) showInstrumentsGrid.getChildren().get(3),
						(TextField) showInstrumentsGrid.getChildren().get(5));
			});
		} catch (IndexOutOfBoundsException e2) {
			e2.getMessage();
		}

		mainPanel.setTop(searchField);
		mainPanel.setCenter(showInstrumentsGrid);
		mainPanel.setBottom(adLine);
		mainPanel.setRight(rightBtn);
		mainPanel.setLeft(leftBtn);

		scene = new Scene(mainPanel);
		scene.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.A) {
				showInstrumentsGrid.requestFocus();
				showInstrumentsGrid.addInstrument();
			} else if (e.getCode() == KeyCode.DELETE) {
				showInstrumentsGrid.requestFocus();
				showInstrumentsGrid.deleteInstrument();
			} else if (e.getCode() == KeyCode.ENTER) {
				searchField.requestFocus();
				searchField.search(musicalInstruments, showInstrumentsGrid);
			}
		});
		window.setScene(scene);
		window.show();

	}

	public static void displayInstByIndex(int currentIndex, TextField fld1, TextField fld2, TextField fld3) {

		fld1.setText(musicalInstruments.get(currentIndex).getClass().getCanonicalName());
		fld2.setText(musicalInstruments.get(currentIndex).getBrand());
		fld3.setText(musicalInstruments.get(currentIndex).getPrice().toString());

	}

	public File getInstrumentsFileFromUser() {
		File file = null;
		boolean stopLoop = false;
		TextInputDialog dialogBox = new TextInputDialog();
		dialogBox.setHeaderText("Load Instruments From File");
		dialogBox.setContentText("Please enter file name");
		do {
			Optional<String> result = dialogBox.showAndWait();
			if (!result.isPresent())
				System.exit(0);
			file = new File(dialogBox.getResult());
			stopLoop = file.exists() && file.canRead();

			if (!stopLoop) {
				Alert alertBox = new Alert(AlertType.ERROR);
				alertBox.setTitle("Error");
				alertBox.setHeaderText("File Error!");
				alertBox.setContentText("Cannot read from file, please try again");
				alertBox.showAndWait();
			}

		} while (!stopLoop);
		return file;
	}

	// getters and setters

	public ShowInstrumentsGrid getShowInstrumentsGrid() {
		return showInstrumentsGrid;
	}

	public void setShowInstrumentsGrid(ShowInstrumentsGrid showInstrumentsGrid) {
		this.showInstrumentsGrid = showInstrumentsGrid;
	}
}
