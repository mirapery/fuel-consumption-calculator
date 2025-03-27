// This class controls the logic of the application. It handles user interactions, such as
//clicking the "Calculate" button or selecting a language. It also loads the appropriate
//language resources and updates the UI elements with the localized text.

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML public Button btnEN, btnFR, btnJP, btnIR, btnCalculate;
    @FXML public Label lblDistance, lblFuel, lblResult;
    @FXML public TextField txtDistance, txtFuel;

    private Locale currentLocale = Locale.ENGLISH;
    private ResourceBundle bundle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // When the application starts, it initializes with the default language (English) by calling the
        //setLanguage() method with new Locale("en").
        setLanguage(new Locale("en", "US"));
    }

    @FXML
    private void setLanguage(Locale locale) {
        // The setLanguage() method loads the appropriate language resources based on the
        //selected locale (en, fr, ja, fa).
        //o When a user clicks one of the language buttons, the UI labels are updated according to
        //the corresponding resource bundle (e.g., "Distance" in English, "Distance" in French,
        //etc.).
        //o If a language resource file is missing, an error message is displayed in the result label.

        currentLocale = locale;
        bundle = ResourceBundle.getBundle("messages", currentLocale);

        btnCalculate.setText(bundle.getString("calculate.button"));
        lblDistance.setText(bundle.getString("distance.label"));
        lblFuel.setText(bundle.getString("fuel.label"));
        lblResult.setText("");
    }

    @FXML
    private void onCalculateClick() {
        // Retrieves the values entered for distance and fuel,
        // calculates the fuel consumption (liters per 100 kilometers),
        // and displays the result in the lblResult label.
        // If the user enters invalid input (non-numeric values), an error message is shown.
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
        setLanguage(new Locale("en"));
    }

    @FXML
    private void onFRClick() {
        setLanguage(new Locale("fr"));
    }

    @FXML
    private void onJPClick() {
        setLanguage(new Locale("ja"));
    }

    @FXML
    private void onIRClick() {
        setLanguage(new Locale("fa"));
    }
}
