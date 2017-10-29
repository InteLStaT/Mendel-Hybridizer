package org.ucoz.intelstat.mh.application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// TODO set program icon because why not
public class Main extends Application {
	
		private static TabPane tabPane;
		private static FXMLLoader hybridizerLoader;
		private static ResourceBundle bundle;
	
	 	public static void main(String[] args) {
	        launch(args);
	    }
	    
	    @Override
	    public void start(Stage primaryStage) throws IOException {
	    	// loads i18n and main UI (TabContainer, TabWelcome)
	    	bundle = ResourceBundle.getBundle("org/ucoz/intelstat/mh/i18n/i18n");
	    	
	    	FXMLLoader tabContainerLoader = new FXMLLoader(resource("TabContainer.fxml"));
	    	FXMLLoader tabWelcomeLoader = new FXMLLoader(resource("TabWelcome.fxml"));
	    	hybridizerLoader = new FXMLLoader(resource("TabHybridizer.fxml"));
	    	
	    	tabContainerLoader.setResources(bundle);
	    	tabWelcomeLoader.setResources(bundle);
	    	hybridizerLoader.setResources(bundle);
	    	
	    	Parent tabContainer = tabContainerLoader.load();
	    	Parent tabWelcome = tabWelcomeLoader.load();
	    	
	    	(tabPane = (TabPane)tabContainer.lookup("#tabPane")).getTabs().add(new Tab("Welcome", tabWelcome));
	    	tabPane.getTabs().get(0).setClosable(false); // Welcome Tab
	    	tabPane.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);
	    	
	    	Scene scene = new Scene(tabContainer, 900, 700);
	    	scene.getStylesheets().add(resource("style.css").toExternalForm());
	    	primaryStage.setScene(scene);
	    	primaryStage.show();
	    }

	    public static void addNewHybridizerTab() {
	    	try {
	    		TabHybridizer th = new TabHybridizer();
	    		hybridizerLoader.setController(th);
	    		hybridizerLoader.setRoot(th);
				Pane pane = hybridizerLoader.load();
				Tab tab = new Tab(i18n("tab.hybridizer.newtitle"), pane);
				tab.setClosable(true);
				tabPane().getTabs().add(tab);
				tabPane().getSelectionModel().select(tab);
				// SUGGESTION: ask for save on closing
			} catch (IOException e) {
				System.err.println("Was unable to load TabHybridizer.fxml.");
			}
	    }
	    
	    public static TabPane tabPane() {
	    	return tabPane;
	    }
	    
	    public static URL resource(String path) {
	    	return Main.class.getResource(path);
	    }
	    
	    public static String i18n(String key) {
	    	return bundle.getString(key);
	    }
}
