package org.ucoz.intelstat.mh.genetics;

public final class Allele {

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
	
	public boolean isAllelePairOf(Allele other) {
		return this.equals(other.oppositeAllele());
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
		if(descriptor == null) {
			throw new IllegalArgumentException("Descriptor can't be null");
		}
		this.descriptor = descriptor;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Allele) {
			Allele otherAllele = (Allele) other;
			if(this.letter == otherAllele.letter) {
				return true;
			}
		}
		return false;
	}
	
}
