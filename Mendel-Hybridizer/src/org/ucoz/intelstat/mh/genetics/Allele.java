package org.ucoz.intelstat.mh.genetics;

public final class Allele implements Comparable<Allele> {

	private char letter;
	private AlleleDescriptor descriptor;

	public Allele(char letter) {
		this(letter, AlleleDescriptor.DEFAULT_DESCRIPTOR);
	}

	public Allele(char letter, AlleleDescriptor descriptor) {
		if (!AlleleDescriptor.isValidChar(letter)) {
			throw new IllegalArgumentException("Illegal allele name; must be a-z or A-Z");
		}
		this.letter = letter;
		descriptor(descriptor);
	}

	public Allele oppositeAllele() {
		char opposite;
		if (65 <= letter && letter <= 90) {
			opposite = (char) (letter + 32);
		} else {
			opposite = (char) (letter - 32);
		}
		return new Allele(opposite, descriptor);
	}

	public boolean isAlleleOfSameGeneAs(Allele other) {
		char opposite;
		if (65 <= letter && letter <= 90) {
			opposite = (char) (letter + 32);
		} else {
			opposite = (char) (letter - 32);
		}
		return this.letter == other.letter || opposite == other.letter;
	}

	public boolean isDominant() {
		return 65 <= letter && letter <= 90;
	}

	public boolean isRecessive() {
		return !isDominant();
	}

	public String description() {
		return descriptor.get(letter);
	}

	public AlleleDescriptor descriptor() {
		return descriptor;
	}

	public void descriptor(AlleleDescriptor descriptor) {
		if (descriptor == null) {
			throw new IllegalArgumentException("Descriptor can't be null");
		}
		this.descriptor = descriptor;
	}

	public char letterRepresentation() {
		return letter;
	}

	@Override
	public String toString() {
		return String.valueOf(letterRepresentation());
	}

	@Override
	public boolean equals(Object other) {
		Allele otherAllele = (Allele) other;
		return this.equals(otherAllele);
	}

	public boolean equals(Allele other) {
		if(other == null) {
			return false;
		}
		if (this.letter == other.letter) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Allele other) {
		if(this.isAlleleOfSameGeneAs(other)) {
			return Character.compare(this.letter, other.letter);
		}
		return Character.compare(Character.toUpperCase(this.letter), Character.toUpperCase(other.letter));
	}

}
