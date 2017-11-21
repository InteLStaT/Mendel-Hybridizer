package org.ucoz.intelstat.mh.settings;

import java.util.prefs.Preferences;

public class Settings {

	public final static Preferences prefs;

	private Settings() {}

	static {
		prefs = Preferences.userRoot().node("org/ucoz/intelstat/mh");
	}

}
