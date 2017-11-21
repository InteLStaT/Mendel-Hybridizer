package org.ucoz.intelstat.mh.application;

import java.util.Locale;

import org.ucoz.intelstat.mh.i18n.I18N;
import org.ucoz.intelstat.mh.settings.Settings;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

public class TabContainer {

	public Menu menuLanguage;

	public void initialize() {
		ToggleGroup tg = new ToggleGroup();
		for(Locale l : I18N.availableLocales()) {
			RadioMenuItem rmiLang = new RadioMenuItem(l.getDisplayLanguage(l));
			if(l.equals(I18N.getBundle().getLocale())) {
				rmiLang.setSelected(true);
			}
			rmiLang.setOnAction((e) -> Settings.prefs.put("app.locale", l.toLanguageTag()));
			tg.getToggles().add(rmiLang);
			menuLanguage.getItems().add(rmiLang);
		}
	}

	public void actionAddTab() {
		if (Main.tabPane() != null) {
			Main.addNewHybridizerTab();
		}
	}

}
