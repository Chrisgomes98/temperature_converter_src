package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable
{
	@FXML
	public Label welcomeLabel;

	@FXML
	public TextField userInputField;

	@FXML
	public Button convertButton;

	@FXML
	public ChoiceBox<String> choiceBox;

	private static final String C_to_F="C to F";
	private static final String F_to_C="F to C";

	private boolean isC_to_F=true;
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle)
	{
		choiceBox.getItems().add(C_to_F);
		choiceBox.getItems().add(F_to_C);
		choiceBox.setValue(C_to_F);
		choiceBox.getSelectionModel().selectedItemProperty().addListener((observableValue, oldV, newV) -> {
			System.out.println(newV);
			if(newV==C_to_F)
			{
				isC_to_F=true;
			}
			else
			{
				isC_to_F=false;
			}

		});

		convertButton.setOnAction(actionEvent -> {
			System.out.println("button clicked");
			convert();
		});

	}

	private void convert()
	{
		int flag=1;
		float input=0.0f;
		try {
			input = Float.parseFloat(userInputField.getText());
		} catch (Exception e) {
			Alert alert=new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error");
			alert.setContentText("error occored");
			alert.show();
			flag=0;
		}
		if(flag==1) {
			float newTemp = 0.0f;
			if (isC_to_F) {
				newTemp = (input * 9 / 5) + 32;
			} else {
				newTemp = (input - 32) * 5 / 9;
			}

			display(newTemp);
		}
	}

	private void display(float newTemp) {
		System.out.println("the new temperature is");
		System.out.print(newTemp);
		String unit;
		if(isC_to_F)
			unit=" F";
		else
			unit=" C";
		System.out.println();

		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("result");
		alert.setContentText("the new temperature is "+newTemp+unit);
		alert.show();
	}

}
