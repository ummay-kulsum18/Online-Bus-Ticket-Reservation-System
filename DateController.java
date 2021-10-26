package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.time.LocalDate;

public class DateController {

    @FXML
    public DatePicker datePicker;

    public void init() {
        LocalDate date = LocalDate.now();

        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell()
        {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);

                if (item.isBefore(date.minusDays(7)) || item.isAfter(date.plusDays(7))) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        };

        datePicker.setDayCellFactory(dayCellFactory);
    }
}
