import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class AdLine extends HBox {
	Text ad;
	Line line;
	LocalDate date;
	Timeline timeline;
	PathTransition path;
	Pane bottomPane;
	public AdLine() {
		 ad = new Text();
		ad.setFont(Font.font("SanSerif", 15));
		ad.setStroke(Color.RED);

		 date = LocalDate.now();
		 timeline = new Timeline(new KeyFrame(Duration.millis(1), e -> {
			ad.setText(date + " " + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
					+ " Currently have Guitars, Basses, Flutes, Saxophones");
		}));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();

		bottomPane = new Pane();
		 line = new Line(0, 0, 750, 0);
		bottomPane.getChildren().add(line);
		getChildren().add(ad);
		path = new PathTransition();
		path.setDuration(Duration.millis(10000));
		path.setPath(line);
		path.setNode(ad);
		path.setOrientation(PathTransition.OrientationType.NONE);
		path.setCycleCount(Timeline.INDEFINITE);
		path.setAutoReverse(true);
		path.play();
		setOnMouseEntered(e -> path.pause());
		setOnMouseExited(e -> path.play());

		BorderPane.setMargin(this, InstrumentsGui.PADDING_INSETS);
	}
}
