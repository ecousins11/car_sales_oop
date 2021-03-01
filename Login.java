package login.privacy;

import java.util.ArrayList;

public class Login {
    public String username;
    private String password;
    public String role;
    
    
	public Login(String username, String password, String role) {
        if (role.equals("admin") || role.equals("customer") ||role.equals("staff") ) {
        	this.role = role;
        }  else {
        	
        	System.out.println("Invalid role; must be 'staff', 'customer' or 'admin'.");
        }
        if (username.equals("") ) {
        	System.out.println("Please input username");
        } else {
        	this.username = username;
        }
        if (password.equals("") ) {
        	System.out.println("Please fill password");
        } else {
        	setPassword(password);
        } 
	}
	
	public static boolean checkUser(String usernameIn, String passwordIn, String roleIn) {
		AdminClass example  = new AdminClass();
		ArrayList<Login> loginExamples = example.getUsers();
		for (int i=0; i<loginExamples.size(); i++) {
			Login log = loginExamples.get(i);
			if (usernameIn.equals(log.username) && passwordIn.equals(log.getPassword()) && roleIn.equals(log.role)) {
				return true;
			}
		}
		return false;
	}

    public String getPassword() {
    	return decrypt(password);
    }
    
    public void setPassword(String password) {
    	this.password = encrypt(password);
    }
    private String encrypt(String key) {
		String result = "";
		char ch;
		for (int i = 0; i<key.length(); i++) {
			ch = key.charAt(i);
			ch += 2;
			result += ch;
		}
		return result;
	}
    private String decrypt(String key) {
		String result = "";
		char ch;
		for (int i = 0; i<key.length(); i++) {
			ch = key.charAt(i);
			ch -= 2;
			result += ch;
		}
		return result;
	}

	public static void main(String[] args) {
		
		

	}

}
