package org.ucoz.intelstat.mh.i18n;

import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;

public class I18N {

	private static ResourceBundle bundle;
	
	private I18N() {}
	
	public static void load() {
		bundle = ResourceBundle.getBundle("org/ucoz/intelstat/mh/i18n/i18n");
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
    	return String.format(getBundle().getString(key), args);
    }
}
