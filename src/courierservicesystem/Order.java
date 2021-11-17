
package courierservicesystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Order {
    private long id;
    private Connection con;
    private String pieces,weight,description;

    public void setId(long id) {
        this.id = id;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void connectDB(String database){
        try {
            if(NewOrderPanel.test){
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver loaded");
                con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database, "root","root");
                System.out.println("Connected");
            
                PreparedStatement ps=con.prepareStatement("INSERT INTO SHIPMENT VALUES(?,?,?,?)");
                ps.setString(1,String.valueOf(id));
                ps.setString(2, pieces);
                ps.setString(3, weight);
                ps.setString(4, description);
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
