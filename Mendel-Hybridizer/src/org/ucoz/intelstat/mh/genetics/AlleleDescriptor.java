package org.ucoz.intelstat.mh.genetics;

import java.util.HashMap;
import java.util.Map;

// There's no actual reason to do this with the builder pattern,
// but I want to keep this immutable (again, for no reason).
public class AlleleDescriptor {

	/**
	 * A descriptor which assigns each allele itself as description.
	 */
	public static final AlleleDescriptor DEFAULT_DESCRIPTOR;

	static {
		AlleleDescriptor.Builder builder = new AlleleDescriptor.Builder();
		for (int i = 65; i <= 90; i++) {
			builder.set((char) i, String.valueOf((char) i)).set((char) (i + 32), String.valueOf((char) (i + 32)));
		}
		DEFAULT_DESCRIPTOR = builder.build();
	}

	private Map<Character, String> descriptorMap = new HashMap<>();

	private AlleleDescriptor() {
	}

	public static class Builder {

		private Map<Character, String> descriptorMap = new HashMap<>();

		public Builder set(char allele, String description) {
			if (!isValidChar(allele)) {
				throw new IllegalArgumentException("Illegal allele name; must be a-z or A-Z");
			}
			descriptorMap.put(allele, description);
			return this;
		}

		public AlleleDescriptor build() {
			AlleleDescriptor ad = new AlleleDescriptor();
			ad.descriptorMap = descriptorMap;
			return ad;
		}
	}

	/**
	 * Returns the description of the given allele. If it doesn't exist, the
	 * description is taken from the default descriptor.
	 * 
	 * @param allele
	 * @return
	 */
	public String get(char allele) {
		if (!isValidChar(allele)) {
			throw new IllegalArgumentException("Illegal allele name; must be a-z or A-Z");
		}
		if (!descriptorMap.containsKey(allele)) {
			return DEFAULT_DESCRIPTOR.get(allele);
		}
		return descriptorMap.get(allele);
	}

	/**
	 * Returns the description of the opposite of the given allele. If it
	 * doesn't exist, the description is taken from the default descriptor.
	 * 
	 * @param allele
	 * @return
	 */
	public String getOpposite(char allele) {
		if (65 <= allele && allele <= 90) {
			allele = (char) (allele + 32);
		} else {
			allele = (char) (allele - 32);
		}
		return get(allele);
	}

	public static boolean isValidChar(char letter) {
		if ((65 <= letter && letter <= 90) || (97 <= letter && letter <= 122)) {
			return true;
		}
		return false;
	}

}
