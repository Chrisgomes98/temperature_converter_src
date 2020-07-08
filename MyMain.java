package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class MyMain extends Application
{
	public static void main(String[] args) //executed 0 but not part of the javafx life cycle
	{
		launch(args);
	}

	@Override
	public void init() throws Exception { //executed 1st
		super.init();
	}

	@Override
	public void start(Stage stage) throws Exception //abstract of application class executed 2nd
	{

		FXMLLoader loader = new
				FXMLLoader(getClass().getResource("app_layout.fxml"));
		VBox rootNode = loader.load();//loads the root node of the fxml file


		MenuBar menuBar=createMenu();
		rootNode.getChildren().add(0, menuBar);


		Scene scene = new Scene(rootNode);//(root node,dim1,dim2)

		stage.setScene(scene);
		stage.setResizable(false);
		stage.setTitle("Hello World");//title
		stage.show();//to show the application
	}

	private MenuBar createMenu()
	{
		Menu menuFile=new Menu("file");

		SeparatorMenuItem separatorMenuItem=new SeparatorMenuItem();


		MenuItem newMenuItem= new MenuItem("New");
		newMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("new menu item clicked");
			}
		});
		MenuItem quitMenuItem=new MenuItem("Quit");
		quitMenuItem.setOnAction(actionEvent -> {
			Platform.exit();
			System.exit(0);
		});


		menuFile.getItems().addAll(newMenuItem,separatorMenuItem,quitMenuItem);


		Menu menuHelp=new Menu("help");
		menuHelp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				System.out.println("help button clicked");
			}
		});

		MenuItem about= new MenuItem("about");
		about.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				aboutApp();
			}
		});
		//MenuItem app=new MenuItem("app");
		menuHelp.getItems().addAll(about);



		MenuBar menuBar=new MenuBar();
		menuBar.getMenus().addAll(menuFile, menuHelp);

		return menuBar;

	}

	private void aboutApp()
	{
		Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("About");
		alert.setHeaderText("about this all");
		alert.setContentText("i am enjoying this");
		ButtonType yes=new ButtonType("yes");
		ButtonType no=new ButtonType("no");


		alert.getButtonTypes().setAll(yes,no);
		Optional<ButtonType> clicked= alert.showAndWait();
		if(clicked.isPresent() && clicked.get()==yes)
		{
			System.out.println("yes button clicked");
		}
		else
		{
			System.out.println("no button clicekd");
		}

		alert.show();
	}


	@Override
	public void stop() throws Exception {   //executed at the end
		super.stop();
	}
}

