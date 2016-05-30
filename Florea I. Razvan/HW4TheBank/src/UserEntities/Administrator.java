package UserEntities;

public class Administrator extends Person{

	private static final long serialVersionUID = 5145752996698584286L;

	public Administrator(String name, String password) {
		super(name, password);
		
	}

	@Override
	public boolean isAdmin() {
		return true;
	}
	
}
