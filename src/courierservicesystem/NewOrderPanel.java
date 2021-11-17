
package courierservicesystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

public class NewOrderPanel {
    JPanel homePanel,titleBoard,orderPanel,shipperPanel, consigneePanel,shipmentPanel; 
    JLabel shipperTitle,consigneeTitle,shipmentTitle;
    JLabel[] shipperInfo=new JLabel[5];  //For labels in shipperPanel
    JLabel[] consigneeInfo=new JLabel[5];  //For labels in cosigneePanel
    JLabel[] shipmentInfo=new JLabel[5];  //For labels in shipmentPanel
    String info[]={" Origin"," Name"," Phone"," Email"," Address"};
    String shipmentLabels[]={"Order ID","Pieces","Weight(g)","Delivery charges","Product Description"};
    private JTextField nameField1,phoneField1,emailField1;  //shipper's textField
    private JTextField nameField2,phoneField2,emailField2;  //consignee's textField
    private JTextField idField,piecesField,weightField,chargesField; //shipment details textField
    private JTextArea addressArea1,addressArea2,descriptionArea;
    private JComboBox cityComboBox1,cityComboBox2;
    private JButton bookOrderBtn,clearBtn;
    private Customer shipper,consignee;
    String[] cities={"Hyderabad","Badin","Dadu","Ghotki","Karachi","Sukkur","Sanghar","Mirpur","Larkana","Nawabshah","Kotri","Khairpur","TandoAllahyar","Thatta","TandoAdam"}; 
    public static boolean test=true;  //for the complete insertion of data into db
    //titlePanel for title, order panel for ordering and contain shipper,cosignee,shipment Panel.
   
    public NewOrderPanel(Dashboard dashboard, JFrame frame){
        homePanel=new JPanel();
        homePanel.setPreferredSize(new Dimension(934,650));
        homePanel.setBackground(Color.white);
        homePanel.setLayout(new BorderLayout());
         
        
        frame.add(homePanel,BorderLayout.CENTER);
        setHomePanelComponents();     
        setShipperPanelComponents(); 
        setCosigneePanelComponents();
        setShipmentPanelComponents();
        homePanel.add(orderPanel,BorderLayout.CENTER);
      
        orderPanel.add(shipperPanel);
        orderPanel.add(consigneePanel);
        orderPanel.add(shipmentPanel);
        orderPanel.add(bookOrderBtn);
        orderPanel.add(clearBtn);
        
        shipperPanel.add(shipperTitle);
        for(int i=0;i<5;i++){
            shipperPanel.add(shipperInfo[i]);
        }
        shipperPanel.add(cityComboBox1);
        shipperPanel.add(nameField1);
        shipperPanel.add(phoneField1);
        shipperPanel.add(emailField1);
        shipperPanel.add(addressArea1);
        
        consigneePanel.add(consigneeTitle);
        for(int i=0;i<5;i++){
            consigneePanel.add(consigneeInfo[i]);
        }
        consigneePanel.add(cityComboBox2);
        consigneePanel.add(nameField2);
        consigneePanel.add(phoneField2);
        consigneePanel.add(emailField2);
        consigneePanel.add(addressArea2);
        
        shipmentPanel.add(shipmentTitle);
        for(int i=0;i<5;i++){
            shipmentPanel.add(shipmentInfo[i]);
        }
        shipmentPanel.add(idField);
        shipmentPanel.add(piecesField);
        shipmentPanel.add(weightField);
        shipmentPanel.add(chargesField);
        shipmentPanel.add(descriptionArea);
//    
        bookOrderBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean checkInput=checkingInput("shipper's",nameField1,phoneField1,emailField1,addressArea1); //return false if any of the textfield/info box is empty  
                checkInput=checkingInput("consignee's",nameField2,phoneField2,emailField2,addressArea2); //return false if any of the textfield/info box is empty  
                boolean checkShipmentDetails=checkingInput(idField,piecesField,weightField,descriptionArea);
                boolean checkEmail=checkingEmail("shipper's",emailField1); //check whether the email input from the user is correct or not
                checkEmail=checkingEmail("consignee's",emailField2); //check whether the email input from the user is correct or not
                
                boolean checkPhoneNo=checkingPhoneNo("shipper's",phoneField1);  ////check whether the phone number input from the user is correct
                checkPhoneNo=checkingPhoneNo("consignee's",phoneField2);  ////check whether the phone number input from the user is corrector not
                
                if(checkInput && checkEmail && checkPhoneNo && checkShipmentDetails){
                    shipper=new Customer();
                    shipper.setId(Long.parseLong(idField.getText()));
                    shipper.setName(nameField1.getText());
                    shipper.setPhoneNo(phoneField1.getText());
                    shipper.setEmail(emailField1.getText());
                    shipper.setCity(cityComboBox1.getSelectedItem().toString());
                    shipper.setAddress(addressArea1.getText());
                    shipper.connectDB("SHIPPER","courierservice");
                    shipper.connectDB("SHIPPER","booked");
                    
                    Customer consignee=new Customer();
                    consignee.setId(Long.parseLong(idField.getText()));
                    consignee.setName(nameField2.getText());
                    consignee.setEmail(emailField2.getText());
                    consignee.setPhoneNo(phoneField2.getText());
                    consignee.setCity(cityComboBox2.getSelectedItem().toString());
                    consignee.setAddress(addressArea2.getText());
                    consignee.connectDB("CONSIGNEE","courierservice");
                    consignee.connectDB("CONSIGNEE","booked");
//                    
                     Order order=new Order();
                     order.setWeight(weightField.getText());
                    order.setId(Long.parseLong(idField.getText()));
                    order.setPieces(piecesField.getText());
                    order.setDescription(descriptionArea.getText());
                    order.connectDB("courierservice");
                    order.connectDB("booked");
                    if(NewOrderPanel.test)
                    JOptionPane.showMessageDialog(null, "Your order will be couriered in two days.", "Information", JOptionPane.INFORMATION_MESSAGE);
                    test=true;
                }
            }
        });
        
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                nameField1.setText("");
                emailField1.setText("");
                phoneField1.setText("");
                addressArea1.setText("");
                nameField2.setText("");
                emailField2.setText("");
                phoneField2.setText("");
                addressArea2.setText("");
                idField.setText("");
                piecesField.setText("");
                weightField.setText("");
                descriptionArea.setText("");
            }
        });
        
        
    }
    
    public void setHomePanelComponents(){
        titleBoard=new JPanel();
        titleBoard.setPreferredSize(new Dimension(935,150));
        titleBoard.setBackground(Color.blue);
        
        orderPanel =new JPanel();
        orderPanel.setPreferredSize(new Dimension(935,470));
        orderPanel.setBackground(Color.WHITE);
        orderPanel.setLayout(new FlowLayout(FlowLayout.LEADING,25,10));
        
        shipperPanel=new JPanel();
        this.setPanelsOfOrderPanel(shipperPanel);
        consigneePanel=new JPanel();
        this.setPanelsOfOrderPanel(consigneePanel); 
        
        shipmentPanel=new JPanel();
        this.setPanelsOfOrderPanel(shipmentPanel);
        shipmentPanel.setPreferredSize(new Dimension(885,200));
        
        bookOrderBtn=new JButton("Book order");
        bookOrderBtn.setFocusable(false);
        bookOrderBtn.setForeground(Color.WHITE);
        bookOrderBtn.setBackground(new Color(0,0,50));
        
        clearBtn=new JButton("Clear");
        clearBtn.setFocusable(false);
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setBackground(new Color(0,0,50));
        
    }
    
    public void setPanelsOfOrderPanel(JPanel panel){
        panel.setPreferredSize(new Dimension(430,340));
        panel.setBackground(Color.white);
        panel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        panel.setLayout(null);
    }
     public void setShipperPanelComponents(){
            shipperTitle =new JLabel("Shipper INFO");
            shipperTitle.setBounds(0, 0, 430, 50);
            shipperTitle.setFont(new Font("MV Bli",Font.ITALIC,20));
            shipperTitle.setBackground(new Color(0,0,80));
        //shipperTitle.setPreferredSize(new Dimension(430,50));
            shipperTitle.setForeground(Color.WHITE);
            shipperTitle.setHorizontalAlignment(SwingConstants.CENTER);
            shipperTitle.setOpaque(true);
            
            for(int i=0;i<5;i++){
                shipperInfo[i]=new JLabel(this.info[i]);
                shipperInfo[i].setFont(new Font("MV Bli",Font.BOLD,15));
            }
            shipperInfo[0].setBounds(20, 60, 70, 20);
            shipperInfo[1].setBounds(20, 125, 70, 20);
            shipperInfo[2].setBounds(235, 125, 70, 20);
            shipperInfo[3].setBounds(20, 190, 70, 20);
            shipperInfo[4].setBounds(20, 255, 70, 20);
            
            cityComboBox1=new JComboBox(cities);
        cityComboBox1.setBounds(20, 85, 390, 30);
        cityComboBox1.setBackground(Color.white);
        cityComboBox1.setFocusable(false);
        
        nameField1=new JTextField();
        nameField1.setBounds(20, 150, 200, 30);
        
        phoneField1=new JTextField();
        phoneField1.setBounds(235, 150, 175, 30);
        
        emailField1 =new JTextField();
        emailField1.setBounds(20, 215, 390, 30);
        
        addressArea1 =new JTextArea();
        addressArea1.setBorder(BorderFactory.createLineBorder(Color.gray));
        addressArea1.setBounds(20, 280, 388, 40);
        
        shipperPanel.add(shipperTitle);
        for(int i=0;i<5;i++){
            shipperPanel.add(shipperInfo[i]);
        }
        shipperPanel.add(cityComboBox1);
        shipperPanel.add(nameField1);
        shipperPanel.add(phoneField1);
        shipperPanel.add(emailField1);
        shipperPanel.add(addressArea1);
    }
    public void setCosigneePanelComponents(){
            consigneeTitle =new JLabel("Consignee INFO");
            consigneeTitle.setBounds(0, 0, 430, 50);
            consigneeTitle.setFont(new Font("MV Bli",Font.ITALIC,20));
            consigneeTitle.setBackground(new Color(0,0,80));
        //shipperTitle.setPreferredSize(new Dimension(430,50));
            consigneeTitle.setForeground(Color.WHITE);
            consigneeTitle.setHorizontalAlignment(SwingConstants.CENTER);
            consigneeTitle.setOpaque(true);
            
            for(int i=0;i<5;i++){
                consigneeInfo[i]=new JLabel(this.info[i]);
                consigneeInfo[i].setFont(new Font("MV Bli",Font.BOLD,15));
            }
            consigneeInfo[0].setBounds(20, 60, 70, 20);
            consigneeInfo[1].setBounds(20, 125, 70, 20);
            consigneeInfo[2].setBounds(235, 125, 70, 20);
            consigneeInfo[3].setBounds(20, 190, 70, 20);
            consigneeInfo[4].setBounds(20, 255, 70, 20);
            
            cityComboBox2=new JComboBox(cities);
        cityComboBox2.setBounds(20, 85, 390, 30);
        cityComboBox2.setBackground(Color.white);
        cityComboBox2.setFocusable(false);
        
        nameField2=new JTextField();
        nameField2.setBounds(20, 150, 200, 30);
        
        phoneField2=new JTextField();
        phoneField2.setBounds(235, 150, 175, 30);
        
        emailField2 =new JTextField();
        emailField2.setBounds(20, 215, 390, 30);
        
        addressArea2 =new JTextArea();
        addressArea2.setBorder(BorderFactory.createLineBorder(Color.gray));
        addressArea2.setBounds(20, 280, 388, 40);
    }
    public void setShipmentPanelComponents(){
            shipmentTitle =new JLabel("Shipment Details");
            shipmentTitle.setBounds(0, 0, 885, 50);
            shipmentTitle.setFont(new Font("MV Bli",Font.ITALIC,20));
            shipmentTitle.setBackground(new Color(0,0,80));
        //shipperTitle.setPreferredSize(new Dimension(430,50));
            shipmentTitle.setForeground(Color.WHITE);
            shipmentTitle.setHorizontalAlignment(SwingConstants.CENTER);
            shipmentTitle.setOpaque(true);
            
            for(int i=0;i<5;i++){
                shipmentInfo[i]=new JLabel(this.shipmentLabels[i]);
                shipmentInfo[i].setFont(new Font("MV Bli",Font.BOLD,15));
            }
            shipmentInfo[0].setBounds(20, 60, 70, 20);
            shipmentInfo[1].setBounds(235, 60, 70, 20);
            shipmentInfo[2].setBounds(450, 60, 100, 20);
            shipmentInfo[3].setBounds(665, 60, 150, 20);
            shipmentInfo[4].setBounds(20, 125, 150, 20);
//            
           idField=new JTextField();
           idField.setBounds(20, 85, 175, 30);

           piecesField=new JTextField();
           piecesField.setBounds(235, 85, 175, 30);
           
           weightField=new JTextField();
           weightField.setBounds(450, 85, 175, 30);
           
           chargesField=new JTextField();
           chargesField.setBounds(665, 85, 185, 30);
           chargesField.setText("100 Rs");
           chargesField.setForeground(Color.gray);
           chargesField.setEditable(false);
           
//        
        descriptionArea =new JTextArea();
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.gray));
        descriptionArea.setBounds(20, 150, 830, 40);
    }
    public boolean checkingInput(String entity,JTextField nameField,JTextField emailField,JTextField phoneField,JTextArea addressArea){
        boolean checkInput=true;
        if(nameField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter "+entity+" name", "Information required", JOptionPane.WARNING_MESSAGE);
            checkInput=false;
        }
        if(emailField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter "+entity+" email", "Information required", JOptionPane.WARNING_MESSAGE);
            checkInput=false;
        }
        if(phoneField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter "+entity+" Phone number", "Information required", JOptionPane.WARNING_MESSAGE);
            checkInput=false;
        }
        if(addressArea.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter" +entity+" address" , "Information required", JOptionPane.WARNING_MESSAGE);
            checkInput=false;
        }
        return checkInput;
    }
    
    public boolean checkingInput(JTextField idField,JTextField piecesField,JTextField weightField,JTextArea desciptionArea){
        boolean checkInput=true;
        if(idField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter  Order ID", "Information required", JOptionPane.WARNING_MESSAGE);
            checkInput=false;
        }
        if(piecesField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter quantity of product", "Information required", JOptionPane.WARNING_MESSAGE);
            checkInput=false;
        }
        if(weightField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter weight of ordering product", "Information required", JOptionPane.WARNING_MESSAGE);
            checkInput=false;
        }
        if(desciptionArea.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Enter decription of ordering product" , "Information required", JOptionPane.WARNING_MESSAGE);
            checkInput=false;
        }
        if(!(idField.getText().isEmpty())){
            try{
                long l=Long.parseLong(idField.getText());
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"You have entered wrong order ID", "Error", JOptionPane.ERROR_MESSAGE);
                checkInput=false;
            }
        }
        return checkInput;
    }
    
    //checking email(return true if email has '@' and '.')
    public boolean checkingEmail(String entity, JTextField emailField){
        boolean checkEmail=true;
        if(!(emailField.getText().isEmpty())){
            int countAtTheRate=0,countDot=0;  
            String traverseEmail=emailField.getText();
            for(int i=0;i<traverseEmail.length();i++){
                if(traverseEmail.charAt(i)=='@')
                        countAtTheRate++;
                if(traverseEmail.charAt(i)=='.')
                    countDot++;
            }
            
            if(countAtTheRate!=1 || countDot<1){
                JOptionPane.showMessageDialog(null,"You have entered "+entity+" wrong email", "Error", JOptionPane.ERROR_MESSAGE);
                checkEmail=false;
            }
        }
        return checkEmail;
    }
    
    public boolean checkingPhoneNo(String entity,JTextField phoneField){
        boolean checkPhoneNo=true;
        if(!(phoneField.getText().isEmpty())){
            try{
                long l=Long.parseLong(phoneField.getText());
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"You have entered "+entity+ " wrong Number.", "Error", JOptionPane.ERROR_MESSAGE);
                checkPhoneNo=false;
            }
        }
        return checkPhoneNo;
    }
}
