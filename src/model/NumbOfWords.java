package model;

public class NumbOfWords {
	private int CountInSpam;
	private int CountInHam;

	public NumbOfWords(int h, int s) {
		CountInHam = h;
		CountInSpam = s;
	}

	public int getCountInSpam() {
		return CountInSpam;
	}

	public int getCountInHam() {
		return CountInHam;
	}
}
