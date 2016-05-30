package UserEntities;

public class User extends Person{

	private static final long serialVersionUID = 2223678849011566801L;

	public User(String name, String password) {
		super(name, password);
	}

	@Override
	public boolean isUser() {
		return true;
	}
}
