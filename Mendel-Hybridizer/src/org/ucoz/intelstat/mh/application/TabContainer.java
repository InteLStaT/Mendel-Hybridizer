package org.ucoz.intelstat.mh.application;

public class TabContainer {

	public void actionAddTab() {
		if (Main.tabPane() != null) {
			Main.addNewHybridizerTab();
			Main.tabPane().getSelectionModel().select(Main.tabPane().getTabs().size() - 2);
		}
	}

}
