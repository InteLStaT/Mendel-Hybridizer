package org.ucoz.intelstat.mh.application;

import org.ucoz.intelstat.mh.i18n.I18N;

import javafx.scene.control.ListView;

public class TabWelcome {

	public ListView<String> listSpecial;

	public void initialize() {
		listSpecial.getItems().add(I18N.get("tab.welcome.special.pea"));
		listSpecial.getItems().add(I18N.get("tab.welcome.special.human"));
	}


	public void linkOpenSample() {
		Main.addNewHybridizerTab();
		System.out.println("hi");
	}

	public void actionOpenSelected() {

	}

}
