
package courierservicesystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Customer {
    Connection con;
    private String name,phoneNo,city,email,address;
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void connectDB(String entity,String database){
        try {
            if(NewOrderPanel.test){
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver loaded");
                con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database, "root","root");
                System.out.println("Connected");
                PreparedStatement ps=con.prepareStatement("INSERT INTO "+entity+" VALUES(?,?,?,?,?,?)");
                ps.setString(1, String.valueOf(id));
                ps.setString(2,name);
                ps.setString(3, email);
                ps.setString(4, phoneNo);
                ps.setString(5, city);
                ps.setString(6, address);
                ps.execute();
                System.out.println("Inserted");
                con.close();
            }
        } catch (ClassNotFoundException ex) {
            NewOrderPanel.test=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            NewOrderPanel.test=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
