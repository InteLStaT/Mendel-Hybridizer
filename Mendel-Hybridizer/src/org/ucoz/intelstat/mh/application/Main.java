package org.ucoz.intelstat.mh.application;

import java.io.IOException;
import java.util.Locale;

import org.ucoz.intelstat.mh.i18n.I18N;
import org.ucoz.intelstat.mh.res.Resources;
import org.ucoz.intelstat.mh.settings.Settings;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.stage.Stage;

// TODO set program icon because why not
public class Main extends Application {

	private static TabPane tabPane;
	private static FXMLLoader hybridizerLoader;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		// loads main UI (TabContainer, TabWelcome)
		FXMLLoader tabContainerLoader = new FXMLLoader(Resources.get("TabContainer.fxml"));
		FXMLLoader tabWelcomeLoader = new FXMLLoader(Resources.get("TabWelcome.fxml"));
		hybridizerLoader = new FXMLLoader(Resources.get("TabHybridizer.fxml"));

		I18N.apply(tabContainerLoader);
		I18N.apply(tabWelcomeLoader);
		I18N.apply(hybridizerLoader);

		Parent tabContainer = tabContainerLoader.load();
		Parent tabWelcome = tabWelcomeLoader.load();

		(tabPane = (TabPane) tabContainer.lookup("#tabPane")).getTabs().add(0,
				new Tab(I18N.get("tab.welcome.title"), tabWelcome));
		tabPane.getTabs().get(0).setClosable(false); // Welcome Tab
		tabPane.getSelectionModel().select(0);
		tabPane.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);

		Scene scene = new Scene(tabContainer, 700, 500);
		scene.getStylesheets().add(Resources.get("style.css").toExternalForm());
		primaryStage.setMinWidth(450);
		primaryStage.setMinHeight(400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void addNewHybridizerTab() {
		try {
			TabHybridizer controller = new TabHybridizer();
			hybridizerLoader.setController(controller);
			hybridizerLoader.setRoot(controller);
			Parent pane = hybridizerLoader.load();
			Tab tab = new Tab(I18N.get("tab.hybridizer.newtitle"), pane);
			controller.tab = tab;
			tab.setClosable(true);
			tab.setOnClosed((e) -> controller.freeResources());
			tabPane().getTabs().add(tabPane().getTabs().size() - 1, tab);
			tabPane().getSelectionModel().select(tab);
			// SUGGESTION: ask for save on closing
		} catch (IOException e) {
			System.err.println("Was unable to load TabHybridizer.fxml.");
			e.printStackTrace();
		}
	}

	public static TabPane tabPane() {
		return tabPane;
	}

}
