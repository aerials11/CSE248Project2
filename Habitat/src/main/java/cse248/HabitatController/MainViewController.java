package cse248.HabitatController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainViewController {
	
	Connection con; // Connection to mySql DB

    @FXML
    private TextField user_textfield;

    @FXML
    private PasswordField password_passfield;
    
    @FXML
    private Text invalid_text;
    
    @FXML
    void user_login(ActionEvent event) { //ActionEvent when user enters info
    	connectDB();		//Connect to DB
    	login(user_textfield.getText(), password_passfield.getText());
    	user_textfield.clear();
    	password_passfield.clear();
    }
    @FXML
    void launchCustomerViewFXML() {
    	launchCustomerView();
    }
    
    @FXML
    void connectToDBFXML() {
    	testToDB("user", "user");
    	
    }
       
    public void login(String id, String password) { //This will attempt to find the user, launch new window for each type
    	connectToDB(id, password);
    }
    
    public void toInvalid_Text(String message) { //This writes to the error text, ex invalid login info
    	invalid_text.setText(message);
    }
    
    public void connectDB() {
    	try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/habitatsql","root","U3Z3aacoskOO55ndVAOf");
    	}catch(Exception e){ System.out.println(e);} 
    }
    
    public void testToDB(String username, String userpass) {	//Connect to localhost mysql
    	try{  
    		Class.forName("com.mysql.jdbc.Driver");  
    		Connection con=DriverManager.getConnection(  
    		"jdbc:mysql://localhost:3306/habitatsql","root","U3Z3aacoskOO55ndVAOf"); 
    		//here sonoo is database name, root is username and password  
    		Statement stmt=con.createStatement();  
    		ResultSet rs=stmt.executeQuery("select user_name from users");  
    		while(rs.next()) {
    			String usernametest = rs.getString("user_name");
    			//String userpasstest = rs.getString("user_password");
    			System.out.println(usernametest);
    		}
    		System.out.println(rs);
    		//con.close();  
    		}catch(Exception e){ System.out.println(e);}  
    	} 
    
    public void connectToDB(String username, String userpass) {	//Connect to localhost mysql
    	try{      		
    		Statement stmt=con.createStatement();  
    		ResultSet rs=stmt.executeQuery("select * from users");  
    		while(rs.next()) {
    			String user = rs.getString("user_name");
    			if (user.equals(username)) {
    				System.out.println("Username found");
    				System.out.println(user);
    				checkpassword(rs, userpass);
    			} else {
    				System.out.println("Username not found");
    			}
    			
    			//String usernametest = rs.getString("user_name");
    		//	String userpasstest = rs.getString("user_password");
    			//System.out.println(usernametest + " " + userpasstest);
    		}
    		System.out.println(rs);
    		con.close();  
    		}catch(Exception e){ System.out.println(e);}  
    	}
    
    private void checkpassword(ResultSet rs, String password) {
		try {
			if (password.equals(rs.getString("user_password"))) {
				determineLaunchType(rs.getInt("user_type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void determineLaunchType(int type) {
    	if (type == 1) {
    		launchCustomerView();
    	} else if (type == 2) {
    		launchEmployeeView();
    	} else if (type == 3) {
    		launchManagerView();
    	} else {
    		System.out.println("Invalid launch type");
    	}
    }
    
    public void launchCustomerView() { // Launch Customer Window
    	try {
    		Stage secondaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/Habitat/src/main/java/cse248/HabitatView/InventoryViewFX.fxml"));
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("/cse248.HabitatView/material-fx-v0_3.css").toExternalForm());
			secondaryStage.setTitle("CustomerView");
			secondaryStage.setScene(scene);
			secondaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    public void launchEmployeeView() {  //Launch Employee Window
    	try {
    		Stage secondaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/cse248.HabitatView/CustomerViewFX.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/cse248.HabitatView/material-fx-v0_3.css").toExternalForm());
			secondaryStage.setTitle("CustomerView");
			secondaryStage.setScene(scene);
			secondaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    public void launchManagerView() { //Launch Manager Window
    	try {
    		Stage secondaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/cse248.HabitatView/CustomerViewFX.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/cse248.HabitatView/material-fx-v0_3.css").toExternalForm());
			secondaryStage.setTitle("CustomerView");
			secondaryStage.setScene(scene);
			secondaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

}
