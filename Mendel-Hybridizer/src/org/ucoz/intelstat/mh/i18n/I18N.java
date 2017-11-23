package org.ucoz.intelstat.mh.i18n;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.ucoz.intelstat.mh.res.Resources;
import org.ucoz.intelstat.mh.settings.Settings;

import javafx.fxml.FXMLLoader;

public class I18N {

	private static ResourceBundle bundle;
	private static MessageFormat formatter;
	private static Locale[] locales;

	private I18N() {}

	static {
		// Uses bundle from settings
		bundle = ResourceBundle.getBundle("org/ucoz/intelstat/mh/i18n/i18n", Locale.forLanguageTag(Settings.prefs.get("app.locale", "")));
		formatter = new MessageFormat("", bundle.getLocale());

		// Read locales from available_locales.txt
		// First line contains number of locales.
		try (Scanner sc = new Scanner(Resources.get("available_locales.txt", I18N.class).openStream())) {
			sc.useDelimiter("[\\r\\n]+");
			locales = new Locale[sc.nextInt()];
			for (int i = 0; sc.hasNext(); i++) {
				locales[i] = Locale.forLanguageTag(sc.next());
			}
		} catch (IOException e) {
			System.err.println("Failed to obtain availables locales, reason:");
			e.printStackTrace();
		}
	}

	public static ResourceBundle getBundle() {
		return bundle;
	}

	public static void apply(FXMLLoader l) {
		l.setResources(getBundle());
	}

    public static String get(String key) {
    	return getBundle().getString(key);
    }

    public static String get(String key, Object... args) {
    	formatter.applyPattern(get(key));
    	return formatter.format(args);
    }

    public static Locale[] availableLocales() {
    	return locales;
    }
}
