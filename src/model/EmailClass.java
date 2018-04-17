package model;

public enum EmailClass {
	SPAM ("spam"),
	HAM ("ham");
	
	private String name;
	
	EmailClass(String name) {
		this.name= name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
	
}
