package org.ucoz.intelstat.mh.res;

import java.net.URL;

public class Resources {

	private Resources() {}

	public static URL get(String path) {
		return Resources.class.getResource(path);
	}

	public static URL get(String path, Class<?> base) {
		return base.getResource(path);
	}

}
