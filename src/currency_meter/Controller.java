package currency_meter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Controller {
    @FXML
    Button refreshButton;
    @FXML
    Label usdrubLabel;
    @FXML
    Label eurrubLabel;
    @FXML
    Label lastUpdateLabel;

    @FXML
    private void onRefreshButtonClick(ActionEvent event) {
        this.updateCurrencies();
        this.setLastUpdateDate();
    }

    public void initialize() {
        updateCurrencies();
        setLastUpdateDate();

        final int UPDATE_TIME = 5 * 60;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(UPDATE_TIME), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                updateCurrencies();
                setLastUpdateDate();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateCurrencies() {
        try {
            final String SOURCE_URL = "https://andy.su/currency/latest";
            String result = this.readStringFromURL(SOURCE_URL);
            String[] rows = result.split("\n");
            for (String row : rows) {
                String[] pair = row.split(",");
                if (pair[0].equals("USD/RUB")) {
                    this.usdrubLabel.setText(pair[1]);
                }
                if (pair[0].equals("EUR/RUB")) {
                    this.eurrubLabel.setText(pair[1]);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setLastUpdateDate() {
        LocalDateTime dateTime = LocalDateTime.now();
        String formattedDate = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.lastUpdateLabel.setText(String.format("Last updated: %s", formattedDate));
    }

    private String readStringFromURL(String requestURL) throws IOException {
        try (Scanner scanner = new Scanner(new URL(requestURL).openStream(),
                StandardCharsets.UTF_8.toString())) {
            scanner.useDelimiter("\\A");
            return scanner.hasNext() ? scanner.next() : "";
        }
    }
}