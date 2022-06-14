
package courierservicesystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class TablePanel {
    JPanel mainPanel;
    JPanel titlePanel,tablePanel;  // for adding title and table of booked orders
    JTable table;         
    JLabel icon,insertId;
    JTextField insertIdField;
    JScrollPane scroll;       //For scroll bar in a table
    ImageIcon truck;          //image
    JButton cancelOrderBtn,deliveredBtn,searchBtn;
    Connection con,con2,con3;
    boolean checkInsertion=true,checkedInsertion=false; 
//checkInsertion is for checking whether the data is fetched and inserted into delivered or cancelled database from booked ordered database.
//checkedInsertion is to check the above statement then delete data from the booked order database    
    
    public String data[][]=new String[100][5];
    public String columns[]={"Order ID","Date","Shipper details","Consignee details","Shipment details"};
    
    public TablePanel(JFrame frame,String database){
        mainPanel=new JPanel();
        mainPanel.setPreferredSize(new Dimension(934,650));
        mainPanel.setBackground(Color.blue);
        mainPanel.setLayout(new BorderLayout());
         
      
        frame.add(mainPanel,BorderLayout.CENTER);  //adding main Panel to JFrame, main Panel is basically BookedOrderPanel
        setComponents(); 
        connectDb(database);
        //mainPanel.add(titlePanel,BorderLayout.NORTH);     //title panel is on the top of mainPanel
        mainPanel.add(tablePanel,BorderLayout.CENTER);    //table Panel is on center

        tablePanel.add(scroll);
        
        //frame.validate();
    }
    
    public void setComponents(){
//        titlePanel=new JPanel();
//        titlePanel.setPreferredSize(new Dimension(0,160));
//        titlePanel.setBackground(Color.white);
//        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));
        
        tablePanel=new JPanel();
        tablePanel.setBackground(Color.white);
        tablePanel.setLayout(new FlowLayout(FlowLayout.LEADING,10,10));
        
        icon=new JLabel();
        truck=new ImageIcon("delivery2.gif");
        icon.setIcon(truck);
        //icon.setText("Now");
        table=new JTable(data,columns);
            table.setEnabled(false);    //table cannot be edit
            table.setRowHeight(96);     
            table.setDefaultRenderer(Object.class, new MultiLine());  //table's cell renderer act as JTextArea 
//            table.setGridColor(Color.BLACK);
//            table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            table.getTableHeader().setBackground(new Color(0,0,80));  //set table bar color
            table.getTableHeader().setForeground(Color.WHITE);       //set table bar text color
            table.getTableHeader().setBorder(BorderFactory.createEtchedBorder());  //set table bar border
            
            //setting column's width
            table.getColumnModel().getColumn(0).setPreferredWidth(50);
            table.getColumnModel().getColumn(1).setPreferredWidth(100);
            table.getColumnModel().getColumn(2).setPreferredWidth(300);
            table.getColumnModel().getColumn(3).setPreferredWidth(300);
            table.getColumnModel().getColumn(4).setPreferredWidth(170);
            
            scroll=new JScrollPane(table);   //instantiate scrolPane and add table of booked orders.
            scroll.setBackground(new Color(0,0,80));
            scroll.setPreferredSize(new Dimension(920,550));
    }
    public void connectDb(String database){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
            Connection con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database, "root","root");
            System.out.println("Connected");
        
        
            Statement st=con.createStatement();
            ResultSet rs1=st.executeQuery("SELECT * FROM "+database+".shipper");
            int i=0;
            while(rs1.next()){
                //adding data in the rows of shipper's column
                data[i][2]="  Name:   "+rs1.getString(2)+"\n"+"  Email:   "+rs1.getString(3)+"\n"+"  Phone No:  "+rs1.getString(4)+"\n"+"  City:   "+rs1.getString(5)+"\n"+"  Address:   "+rs1.getString(6);
                i++;
            } 
            i=0;
            ResultSet rs2=st.executeQuery("SELECT * FROM "+database+".consignee");
            while(rs2.next()){
                //adding data in the rows of consignee's column of table
                data[i][3]="  Name:   "+rs2.getString(2)+"\n"+"  Email:   "+rs2.getString(3)+"\n"+"  Phone No:  "+rs2.getString(4)+"\n"+"  City:   "+rs2.getString(5)+"\n"+"  Address:   "+rs2.getString(6);
                i++;
            }
            i=0;
            ResultSet rs3=st.executeQuery("SELECT * FROM "+database+".shipment");
            while(rs3.next()){
                //adding data in the rows of order id column
                data[i][0]="\n\n       "+rs3.getString(1);
                
                //adding data in the rows of order id column
                data[i][1]="\n\n       "+rs3.getString(5)+"\n       "+rs3.getString(6);
                
                //adding data in the rows of shipment's column
                data[i][4]=" Order ID: "+rs3.getString(1)+"\n"+" Pieces: "+rs3.getString(2)+"\n"+" Weight(g): "+rs3.getString(3)+"\n"+" Description: "+rs3.getString(4);
                i++;
            }
        }catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void addActions(){
            insertId=new JLabel("Enter Order ID:");
            
            insertIdField=new JTextField();
            insertIdField.setPreferredSize(new Dimension(100,20));
            
            deliveredBtn=new JButton("Delivered");
            deliveredBtn.setBackground(new Color(0,0,80));
            deliveredBtn.setForeground(Color.WHITE);
            deliveredBtn.setFocusable(false);
            
            cancelOrderBtn=new JButton("Cancel Order");
            cancelOrderBtn.setBackground(new Color(0,0,80));
            cancelOrderBtn.setForeground(Color.WHITE);
            cancelOrderBtn.setFocusable(false);
            
            tablePanel.add(insertId);
            tablePanel.add(insertIdField);
            tablePanel.add(deliveredBtn);
            tablePanel.add(cancelOrderBtn);
            
        deliveredBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if(!(insertIdField.getText().isEmpty())){
                    int option=JOptionPane.showConfirmDialog(null, "Do you want to set the order to be considered as delivered", "Information", JOptionPane.YES_NO_OPTION);
                    if(checkingId() && option==0){
                    //shipper
                    connectDbForDeliverOrCancelOrder("delivered",Integer.parseInt(insertIdField.getText()),"shipper");
                    //consigneee
                    connectDbForDeliverOrCancelOrder("delivered",Integer.parseInt(insertIdField.getText()),"consignee");
                    //shipment
                    connectDbForDeliverOrCancelOrder("delivered",Integer.parseInt(insertIdField.getText()));
                    //done
                    if(checkInsertion && checkedInsertion)
                        JOptionPane.showMessageDialog(null, "The Order("+insertIdField.getText()+") has been delivered", "DONE", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                if(insertIdField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Enter Order ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cancelOrderBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if(!(insertIdField.getText().isEmpty()) && checkingId()){
                    int option=JOptionPane.showConfirmDialog(null, "Do you really want to cancel the order", "Information", JOptionPane.YES_NO_OPTION);
                    if(option==0){
                    //shipper
                    connectDbForDeliverOrCancelOrder("cancelled",Integer.parseInt(insertIdField.getText()),"shipper");
                    //consigneee
                    connectDbForDeliverOrCancelOrder("cancelled",Integer.parseInt(insertIdField.getText()),"consignee");
                    //shipment
                    connectDbForDeliverOrCancelOrder("cancelled",Integer.parseInt(insertIdField.getText()));
                    //cancelled
                    if(checkInsertion && checkedInsertion)
                        JOptionPane.showMessageDialog(null, "The Order("+insertIdField.getText()+") has been cancelled", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                if(insertIdField.getText().isEmpty()){
                    //if empty
                    JOptionPane.showMessageDialog(null, "Enter Order ID", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
    }
    
    public boolean checkingId(){
        boolean checkId=true;
        if(!(insertIdField.getText().isEmpty())){
            try{
                int l=Integer.parseInt(insertIdField.getText());
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"You have entered wrong ID.", "Error", JOptionPane.ERROR_MESSAGE);
                checkId=false;
            }
        }
        return checkId;
    }
    //
    public void connectDbForDeliverOrCancelOrder(String database,int id,String entity){
        
            if(checkInsertion){
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver loaded");
                con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/booked", "root","root");
                System.out.println("Connected");
            
                Statement st=con.createStatement();
                ResultSet rs1=st.executeQuery("SELECT * FROM booked."+entity+" where id="+id);
                String[] data=new String[6];
                while(rs1.next()){
                    data[0]=rs1.getString(1);
                    data[1]=rs1.getString(2);
                    data[2]=rs1.getString(3);
                    data[3]=rs1.getString(4);
                    data[4]=rs1.getString(5);
                    data[5]=rs1.getString(6);
                }
                    con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database, "root","root");
                PreparedStatement ps=con.prepareStatement("INSERT INTO "+database+"."+entity+" VALUES(?,?,?,?,?,?)");
                ps.setString(1, data[0]);
                ps.setString(2, data[1]);
                ps.setString(3, data[2]);
                ps.setString(4, data[3]);
                ps.setString(5, data[4]);
                ps.setString(6, data[5]);
                
                ps.execute();
                System.out.println("Inserted");
                checkedInsertion=true;
                con.close();
                
                
                
                if(checkedInsertion){
                    con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/booked", "root","root");
                System.out.println("Connected");
                PreparedStatement delete=con.prepareStatement("DELETE FROM booked."+entity+" where id="+id);
                delete.execute();
                    System.out.println("Deleted");
                con.close();
                }                
                } catch (ClassNotFoundException ex) {
                    checkedInsertion=false; checkInsertion=false;
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);    
                } catch (SQLException ex) {
                    checkedInsertion=false; checkInsertion=false;
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            
            }
    }   
        
    public void connectDbForDeliverOrCancelOrder(String database,int id){
        try {
                if(checkInsertion){
                    Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver loaded");
                con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/booked", "root","root");
                System.out.println("Connected");
            
                Statement st=con.createStatement();
                ResultSet rs1=st.executeQuery("SELECT * FROM booked.shipment where id="+id);
                String[] data=new String[4];
                while(rs1.next()){
                    data[0]=rs1.getString(1);
                    data[1]=rs1.getString(2);
                    data[2]=rs1.getString(3);
                    data[3]=rs1.getString(4);
                    data[4]=rs1.getString(5);
                    data[5]=rs1.getString(6);
                }
                    con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database, "root","root");
                PreparedStatement ps=con.prepareStatement("INSERT INTO SHIPMENT VALUES(?,?,?,?,?,?)");
                ps.setString(1, data[0]);
                ps.setString(2, data[1]);
                ps.setString(3, data[2]);
                ps.setString(4, data[3]);
                ps.setString(5, data[4]);
                ps.setString(6, data[5]);
                ps.execute();
                System.out.println("Inserted");
                checkedInsertion=true;
                con.close();
                
                
                
                if(checkedInsertion){
                    con=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/booked", "root","root");
                System.out.println("Connected");
                PreparedStatement delete=con.prepareStatement("DELETE FROM booked.shipment where id="+id);
                delete.execute();
                    System.out.println("Deleted");
                con.close();
                }
                }
            
        }catch (ClassNotFoundException ex) {
            checkInsertion=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch (SQLException ex) {
            checkInsertion=false;
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public void search(){
        JPanel p=new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.TRAILING));
        p.setPreferredSize(new Dimension(500,40));
        p.setBackground(Color.WHITE);
        
        searchBtn =new JButton("Search");
        searchBtn.setBackground(new Color(0,0,80));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFocusable(false);
        
        p.add(searchBtn);
        tablePanel.add(p);
        searchBtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae) {
                    
                }
        });
    }
}
