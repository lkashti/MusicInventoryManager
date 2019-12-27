import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class SearchField extends HBox {
	private TextField searchBar;
	private Button goBtn;
	private String result;

	public SearchField(ArrayList<MusicalInstrument> musicalInstruments, GridPane grid) {
		searchBar = new TextField();
		searchBar.setPromptText("Search...");
		searchBar.setPrefWidth(750);

		goBtn = new Button("Go!");
		goBtn.setOnAction(e -> {
			search(musicalInstruments,grid);
		});
		getChildren().addAll(searchBar, goBtn);
		setSpacing(10);
		setPadding(InstrumentsGui.PADDING_INSETS);

	}
	public void search(ArrayList<MusicalInstrument> musicalInstruments,GridPane grid) {
		result = searchBar.getText().toLowerCase();
		for (int i = 0; i < musicalInstruments.size(); i++) {
			if (musicalInstruments.get(i).toString().toLowerCase().contains(result)) {
				InstrumentsGui.displayInstByIndex(i, (TextField) grid.getChildren().get(1),
						(TextField) grid.getChildren().get(3), (TextField) grid.getChildren().get(5));
				result = "";
				break;
			}
		}

	}
}
