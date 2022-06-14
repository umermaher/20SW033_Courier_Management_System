package courierservicesystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class LoginPanel{
    private final String userName = "admin", password = "12345";
    public JFrame frame;
    private JPanel loginPanel,titlePanel,btnPanel,east,west;
    private JLabel title,nameTextView,passwordTextView;
    private JTextField nameField;
    private JButton loginBtn;
    private JPasswordField passwordField;
    private ImageIcon icon1,icon2;
    
    LoginPanel(){
        frame=new JFrame("Courier Management System");
        icon1=new ImageIcon("parcel4.jpg");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,650);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setIconImage(icon1.getImage());
        addComponents();
        
        
        loginBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                //frame.setSize(1000, 600);
                //if both fields are not empty
                if(!(nameField.getText().isEmpty()) && !(passwordField.getText().isEmpty())){
                    //if name and password fields are equal
                    if((nameField.getText().equals(userName)) && passwordField.getText().equals(password)){
                        titlePanel.setVisible(false);
                        loginPanel.setVisible(false);
                        btnPanel.setVisible(false);
                        east.setVisible(false);
                        west.setVisible(false);
                        Dashboard dashboard=new Dashboard(frame);
                    }
                    //if both name and password fields are not equal
                    else if(!(nameField.getText().equals(userName)) && !(passwordField.getText().equals(password)))
                        JOptionPane.showMessageDialog(null, "Wrong user name and password","Error",JOptionPane.ERROR_MESSAGE);
                    ////if name fields is not equal
                    else if(!(nameField.getText().equals(userName)) && passwordField.getText().equals(password))
                        JOptionPane.showMessageDialog(null, "Wrong user name","Error",JOptionPane.ERROR_MESSAGE);
                    //if password fields is not equal
                    else if(!(passwordField.getText().equals(password)) && nameField.getText().equals(userName))
                        JOptionPane.showMessageDialog(null, "Wrong Password","Error",JOptionPane.ERROR_MESSAGE);
                }
                //if both are empty
                else if(nameField.getText().isEmpty() && passwordField.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "Enter user name and password","Error",JOptionPane.ERROR_MESSAGE); 
                //if name is empty
                else if(nameField.getText().isEmpty() && (!(passwordField.getText().isEmpty())))
                    JOptionPane.showMessageDialog(null, "Enter user name","Error",JOptionPane.ERROR_MESSAGE);
                //if password is empty
                else if(!(nameField.getText().isEmpty()) && passwordField.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "Enter Password","Error",JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.setVisible(true);
    }
    
    public void addComponents(){
        loginPanel=new JPanel();
        titlePanel=new JPanel();
        btnPanel=new JPanel();
        east=new JPanel();
        west=new JPanel();
        
        titlePanel.setBackground(Color.WHITE);
        loginPanel.setBackground(Color.WHITE);
        btnPanel.setBackground(Color.WHITE);
        east.setBackground(Color.WHITE);
        west.setBackground(Color.WHITE);
        //setSize of panels
        titlePanel.setPreferredSize(new Dimension(1200,410));
        loginPanel.setPreferredSize(new Dimension(200,40));
        east.setPreferredSize(new Dimension(480,100));
        west.setPreferredSize(new Dimension(480,100));
        btnPanel.setPreferredSize(new Dimension(600,150));
        
        nameTextView=new JLabel("User Name:");
        passwordTextView=new JLabel("Password:");
        nameField=new JTextField();
        passwordField=new JPasswordField();
        loginBtn=new JButton("Log in");
//        title=new JLabel("Courier Service System");
//        title.setBounds(20,20,500,40);
//        title.setFont(new Font("MV Bli",Font.PLAIN,35));
//        title.setForeground(Color.WHITE);
        icon2=new ImageIcon("courierlogo.png");
        title=new JLabel();
       // title.setBounds(20,0,1100,0);
        title.setIcon(icon2);
        
        loginPanel.setBounds(450,450,250,50);
        loginPanel.setLayout(new GridLayout(2,2,-40,5));
        titlePanel.setLayout(new FlowLayout(FlowLayout.LEADING,30,-20));
        btnPanel.setLayout(new FlowLayout(FlowLayout.LEADING,540,20));
        nameTextView.setFont(new Font("MV Bli",Font.BOLD,15));
      //  nameTextView.setForeground(Color.WHITE);
        //passwordTextView.setForeground(Color.WHITE);
        passwordTextView.setFont(new Font("MV Bli",Font.BOLD,15));
        //loginBtn.setBounds(530, 20, 80, 25);
        loginBtn.setFocusable(false);
        loginBtn.setBackground(Color.LIGHT_GRAY);
        loginBtn.setPreferredSize(new Dimension(80,25));
        loginBtn.setBorder(BorderFactory.createEtchedBorder());
//        nameField.setPreferredSize(new Dimension(150,20));
//        passwordField.setPreferredSize(new Dimension(150,20));
        
        frame.add(titlePanel,BorderLayout.NORTH);
        frame.add(east,BorderLayout.EAST);
        frame.add(west,BorderLayout.WEST);
        frame.add(btnPanel,BorderLayout.SOUTH);
        frame.add(loginPanel,BorderLayout.CENTER);
        titlePanel.add(title);
        loginPanel.add(nameTextView);
        loginPanel.add(nameField);
        loginPanel.add(passwordTextView);
        loginPanel.add(passwordField);
        btnPanel.add(loginBtn);
    }
}