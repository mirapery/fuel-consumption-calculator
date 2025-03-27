import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class HelloController {

    @FXML public Button btnEN, btnFR, btnJP, btnIR, btnCalculate;
    @FXML public Label lblDistance, lblFuel, lblResult;
    @FXML public TextField txtDistance, txtFuel;

    private ResourceBundle bundle;

    public void initialize() {
        setLanguage(new Locale("en", "EN"));
    }

    @FXML
    private void setLanguage(Locale locale) {
        try {
            bundle = ResourceBundle.getBundle("messages", locale);
            btnCalculate.setText(bundle.getString("calculate.button"));
            lblDistance.setText(bundle.getString("distance.label"));
            lblFuel.setText(bundle.getString("fuel.label"));
            lblResult.setText("");
        } catch (MissingResourceException e) {
            e.printStackTrace();
            lblResult.setText("Error loading language resources");
        }
        onCalculateClick();
    }

    @FXML
    private void onCalculateClick() {
        try {
            double distance = Double.parseDouble(txtDistance.getText());
            double fuel = Double.parseDouble(txtFuel.getText());

            if (fuel == 0) {
                lblResult.setText(bundle.getString("invalid.input"));
                return;
            }

            double consumption = (fuel /distance) * 100;
            lblResult.setText(String.format(bundle.getString("result.label"), consumption));
        } catch (NumberFormatException e) {
            lblResult.setText(bundle.getString("invalid.input"));
        }
    }

    @FXML
    private void onENClick() {
        setLanguage(new Locale("en", "EN"));
    }

    @FXML
    private void onFRClick() {
        setLanguage(new Locale("fr", "FR"));
    }

    @FXML
    private void onJPClick() {
        setLanguage(new Locale("jp", "JP"));
    }

    @FXML
    private void onIRClick() {
        setLanguage(new Locale("ir", "PA"));
    }
}
