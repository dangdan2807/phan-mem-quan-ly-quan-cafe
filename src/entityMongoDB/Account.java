package entityMongoDB;

import org.bson.Document;

public class Account {
	private String username, password = null, displayName;
	private int type;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Account(String username, String password, String displayName, int type) {
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.type = type;
	}

	public Account(Document doc) {
		this(doc.getString("UserName"), "", doc.getString("DisplayName"), doc.getInteger("Type"));
	}

}
