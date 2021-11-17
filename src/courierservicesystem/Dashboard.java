
package courierservicesystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class Dashboard{
   // public static JFrame frame;
    NewOrderPanel newOrderPanel;  
    TablePanel bookedOrderPanel,deliveredOrderPanel,cancelledOrderPanel,totalOrderPanel;
    JLabel titleDashboard,homePanelLabel,southLabel;    //declaring for title
    JPanel dashboardPanel,titlePanel,btnPanel;
    JPanel dashboardSouth; //south part of dashboard panel just for managing dashboard buttons added on btnPanel/dashboard(centre)
    JPanel dashboardHomePanel;
    ImageIcon dashboardIcon,homePanelIcon,southIcon;
    JButton newOrder,bookedOrder,cancelledOrder,deliveredOrder,totalOrder;
    public Dashboard(JFrame frame){
        frame.setBackground(null);
        setComponents();
        
        frame.add(dashboardPanel,BorderLayout.WEST);
        frame.add(dashboardHomePanel,BorderLayout.EAST);
        
        dashboardPanel.add(titlePanel,BorderLayout.NORTH);
        dashboardPanel.add(btnPanel,BorderLayout.CENTER);
        dashboardPanel.add(dashboardSouth,BorderLayout.SOUTH);
        
        titlePanel.add(titleDashboard);
        
        btnPanel.add(newOrder);
        btnPanel.add(bookedOrder);
        btnPanel.add(cancelledOrder);
        btnPanel.add(deliveredOrder);
        btnPanel.add(totalOrder);
        
        dashboardHomePanel.add(homePanelLabel);
        titleDashboard.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent me) {
                if(newOrderPanel!=null && newOrderPanel.homePanel.isVisible()){
                    newOrderPanel.homePanel.setVisible(false);
                    newOrder.setBackground(new Color(0,0,80));
                    bookedOrder.setBackground(new Color(0,0,80));
                    cancelledOrder.setBackground(new Color(0,0,80));
                    deliveredOrder.setBackground(new Color(0,0,80));
                    totalOrder.setBackground(new Color(0,0,80));
                    dashboardHomePanel.setVisible(true);
                }
                check(bookedOrderPanel);
                check(deliveredOrderPanel);
                check(totalOrderPanel);
                check(cancelledOrderPanel);
            }
            public void check(TablePanel panel){
                if(panel!=null && panel.mainPanel.isVisible()){
                    panel.mainPanel.setVisible(false);
                    newOrder.setBackground(new Color(0,0,80));
                    bookedOrder.setBackground(new Color(0,0,80));
                    cancelledOrder.setBackground(new Color(0,0,80));
                    deliveredOrder.setBackground(new Color(0,0,80));
                    totalOrder.setBackground(new Color(0,0,80));
                    newOrder.setForeground(Color.WHITE);
                    bookedOrder.setForeground(Color.WHITE);
                    cancelledOrder.setForeground(Color.WHITE);
                    deliveredOrder.setForeground(Color.WHITE);
                    totalOrder.setForeground(Color.WHITE);
                    dashboardHomePanel.setVisible(true);
                }
            }
            @Override
            public void mousePressed(MouseEvent me) {}
            @Override
            public void mouseReleased(MouseEvent me) {}
            @Override
            public void mouseEntered(MouseEvent me) {}
            @Override
            public void mouseExited(MouseEvent me) {}
        });
        newOrder.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                //dashboard is by-default instantiate and visible.
                if(dashboardHomePanel.isVisible()){
                   dashboardHomePanel.setVisible(false);
                   if(newOrderPanel==null)
                       newOrderPanel = new NewOrderPanel(Dashboard.this,frame);
                   else
                       newOrderPanel.homePanel.setVisible(true);
                }
                //if any table panel is visible
                check(bookedOrderPanel);
                check(cancelledOrderPanel);
                check(deliveredOrderPanel);
                check(totalOrderPanel);
            }
            public void check(TablePanel panel){
                if(panel!=null && panel.mainPanel.isVisible()){
                   panel.mainPanel.setVisible(false);
                   if(newOrderPanel==null)
                        newOrderPanel = new NewOrderPanel(Dashboard.this,frame);
                   else
                        newOrderPanel.homePanel.setVisible(true);
                }  
            }
        });
        
        bookedOrder.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                //dashboard is by-default instantiated and visibled.
                if(dashboardHomePanel.isVisible()){
                   dashboardHomePanel.setVisible(false);
                   if(bookedOrderPanel==null){
                       bookedOrderPanel = new TablePanel(frame,"booked");
                       bookedOrderPanel.addActions();
                   }
                   else{
                       bookedOrderPanel=null;
                       bookedOrderPanel=new TablePanel(frame,"booked");
                       bookedOrderPanel.addActions();
//                       bookedOrderPanel.connectDb("booked");
//                       bookedOrderPanel.mainPanel.setVisible(true);
                   }
                }
                if(newOrderPanel!=null && newOrderPanel.homePanel.isVisible()){
                   newOrderPanel.homePanel.setVisible(false);
                   if(bookedOrderPanel==null){
                       bookedOrderPanel = new TablePanel(frame,"booked");
                       bookedOrderPanel.addActions();
                   }
                   else{
                       bookedOrderPanel=null;
                       bookedOrderPanel=new TablePanel(frame,"booked");
                       bookedOrderPanel.addActions();
//                       bookedOrderPanel.connectDb("booked");
//                       bookedOrderPanel.mainPanel.setVisible(true);
                   }
                }
                //if any table panel is visible
                check(cancelledOrderPanel);
                check(deliveredOrderPanel);
                check(totalOrderPanel);
            }
            public void check(TablePanel panel){
                if(panel!=null && panel.mainPanel.isVisible()){
                   panel.mainPanel.setVisible(false);
                   if(bookedOrderPanel==null){
                        bookedOrderPanel = new TablePanel(frame,"booked");
                        bookedOrderPanel.addActions();
                   }
                   else{
                       bookedOrderPanel=null;
                       bookedOrderPanel=new TablePanel(frame,"booked");
                       bookedOrderPanel.addActions();
//                       bookedOrderPanel.connectDb("booked");
//                        bookedOrderPanel.mainPanel.setVisible(true);
                   }
                }  
            }
        });
        cancelledOrder.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                //dashboard is by-default instantiate and visible.
                if(dashboardHomePanel.isVisible()){
                   dashboardHomePanel.setVisible(false);
                   if(cancelledOrderPanel==null)
                       cancelledOrderPanel = new TablePanel(frame,"cancelled");
                   else{
                       cancelledOrderPanel=null;
                       cancelledOrderPanel = new TablePanel(frame,"cancelled");
//                       cancelledOrderPanel.connectDb("cancelled");
//                       cancelledOrderPanel.mainPanel.setVisible(true);
                   }                
                }
                if(newOrderPanel!=null && newOrderPanel.homePanel.isVisible()){
                   newOrderPanel.homePanel.setVisible(false);
                   if(cancelledOrderPanel==null)
                       cancelledOrderPanel = new TablePanel(frame,"cancelled");
                   else{
                       cancelledOrderPanel=null;
                       cancelledOrderPanel = new TablePanel(frame,"cancelled");
//                       cancelledOrderPanel.connectDb("cancelled");
//                       cancelledOrderPanel.mainPanel.setVisible(true);
                   }
                }
                //if any table panel is visible
                check(bookedOrderPanel);
                check(deliveredOrderPanel);
                check(totalOrderPanel);
            }
            public void check(TablePanel panel){
                if(panel!=null && panel.mainPanel.isVisible()){
                   panel.mainPanel.setVisible(false);
                   if(cancelledOrderPanel==null)
                        cancelledOrderPanel = new TablePanel(frame,"cancelled");
                   else{
                       cancelledOrderPanel=null;
                       cancelledOrderPanel = new TablePanel(frame,"cancelled");
//                       cancelledOrderPanel.connectDb("cancelled");
//                        cancelledOrderPanel.mainPanel.setVisible(true);
                   }
                }  
            }
        });
        
        deliveredOrder.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                //dashboard is by-default instantiate and visible.
                if(dashboardHomePanel.isVisible()){
                   dashboardHomePanel.setVisible(false);
                   if(deliveredOrderPanel==null)
                       deliveredOrderPanel = new TablePanel(frame,"delivered");
                   else{
                       deliveredOrderPanel=null;
                       deliveredOrderPanel = new TablePanel(frame,"delivered");
//                       deliveredOrderPanel.connectDb("delivered");
//                       deliveredOrderPanel.mainPanel.setVisible(true);
                   }
                }
                if(newOrderPanel!=null && newOrderPanel.homePanel.isVisible()){
                   newOrderPanel.homePanel.setVisible(false);
                   if(deliveredOrderPanel==null)
                       deliveredOrderPanel = new TablePanel(frame,"delivered");
                   else{
                       deliveredOrderPanel=null;
                       deliveredOrderPanel = new TablePanel(frame,"delivered");
//                       deliveredOrderPanel.connectDb("delivered");
//                       deliveredOrderPanel.mainPanel.setVisible(true);
                   }
                }
                //if any table panel is visible
                check(cancelledOrderPanel);
                check(bookedOrderPanel);
                check(totalOrderPanel);
            }
            public void check(TablePanel panel){
                if(panel!=null && panel.mainPanel.isVisible()){
                   panel.mainPanel.setVisible(false);
                   if(deliveredOrderPanel==null)
                        deliveredOrderPanel = new TablePanel(frame,"delivered");
                   else{
                       deliveredOrderPanel=null;
                       deliveredOrderPanel = new TablePanel(frame,"delivered");
//                       deliveredOrderPanel.connectDb("delivered");
//                        deliveredOrderPanel.mainPanel.setVisible(true);
                    }
                }
            }
        });
        
        totalOrder.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                //dashboard is by-default instantiate and visible.
                if(dashboardHomePanel.isVisible()){
                   dashboardHomePanel.setVisible(false);
                   if(totalOrderPanel==null)
                       totalOrderPanel = new TablePanel(frame,"courierservice");
                   else{
                       totalOrderPanel=null;
                       totalOrderPanel = new TablePanel(frame,"courierservice");
//                       totalOrderPanel.mainPanel.setVisible(true);
//                       totalOrderPanel.connectDb("courierservice");
                   }
                }
                if(newOrderPanel!=null && newOrderPanel.homePanel.isVisible()){
                   newOrderPanel.homePanel.setVisible(false);
                   if(totalOrderPanel==null)
                       totalOrderPanel = new TablePanel(frame,"courierservice");
                   else{
                       totalOrderPanel=null;
                       totalOrderPanel = new TablePanel(frame,"courierservice");
//                       totalOrderPanel.mainPanel.setVisible(true);
//                       totalOrderPanel.connectDb("courierservice");
                   }
                }
                //if any table panel is visible
                check(cancelledOrderPanel);
                check(deliveredOrderPanel);
                check(bookedOrderPanel);
            }
            public void check(TablePanel panel){
                if(panel!=null && panel.mainPanel.isVisible()){
                   panel.mainPanel.setVisible(false);
                   if(totalOrderPanel==null)
                        totalOrderPanel = new TablePanel(frame,"courierservice");
                   else{
                       totalOrderPanel=null;
                       totalOrderPanel = new TablePanel(frame,"courierservice");
//                        totalOrderPanel.mainPanel.setVisible(true);
//                        totalOrderPanel.connectDb("courierservice");
                   }
                }  
            }
        });
    }
        
    
    public void setComponents(){
        //instantiating JPanel for the features
         dashboardHomePanel=new JPanel();
        dashboardHomePanel.setPreferredSize(new Dimension(940,650));
        dashboardHomePanel.setBackground(Color.white);
        dashboardHomePanel.setLayout(new FlowLayout());
        
        dashboardPanel=new JPanel();
        titlePanel=new JPanel();
        btnPanel=new JPanel();
        dashboardSouth=new JPanel();
        //setting dashBoard panel which is the parent of all panel.
        dashboardPanel.setPreferredSize(new Dimension(250,0));
        dashboardPanel.setBackground(new Color(0,0,80));
        dashboardPanel.setLayout(new BorderLayout());
        //dashboardPanel.setLayout(new GridLayout());
        
        //Instantiating titlePanel, and add title dashboard into it.
        //titlePanel then added to dashboardPanel.
        titlePanel.setPreferredSize(new Dimension(250,160));
        titlePanel.setBackground(new Color(0,0,80));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,-10));
        //instantiating JLabel for icon and dashboard title.
        titleDashboard=new JLabel();
        dashboardIcon=new ImageIcon("courierit1.png");
        
        titleDashboard.setIcon(dashboardIcon);
        titleDashboard.setText("Dashboard");
        titleDashboard.setPreferredSize(new Dimension(250,160));
        titleDashboard.setVerticalTextPosition(JLabel.BOTTOM);
        titleDashboard.setHorizontalTextPosition(JLabel.CENTER);
        titleDashboard.setFont(new Font("MV Bli",Font.PLAIN,30));
        titleDashboard.setForeground(Color.WHITE);
        //dashboard.setIconTextGap(15);
        
        btnPanel.setPreferredSize(new Dimension(250,240));
        btnPanel.setLayout(new GridLayout(5,1));
        
        newOrder=new JButton("New order");
        bookedOrder=new JButton("Booked orders");
        cancelledOrder=new JButton("Cancelled orders");
        deliveredOrder=new JButton("Delivered orders");
        totalOrder=new JButton("Total orders");
        
        newOrder.setBackground(new Color(0,0,80));
        bookedOrder.setBackground(new Color(0,0,80));
        cancelledOrder.setBackground(new Color(0,0,80));
        deliveredOrder.setBackground(new Color(0,0,80));
        totalOrder.setBackground(new Color(0,0,80));
       
        newOrder.setForeground(Color.WHITE);
        bookedOrder.setForeground(Color.WHITE);
        cancelledOrder.setForeground(Color.WHITE);
        deliveredOrder.setForeground(Color.WHITE);
        totalOrder.setForeground(Color.WHITE);
        
        newOrder.setFocusable(false);
        bookedOrder.setFocusable(false);
        cancelledOrder.setFocusable(false);
        deliveredOrder.setFocusable(false);
        totalOrder.setFocusable(false);
        
        newOrder.setBorder(BorderFactory.createCompoundBorder());
        bookedOrder.setBorder(BorderFactory.createCompoundBorder());
        cancelledOrder.setBorder(BorderFactory.createEtchedBorder(null, new Color(0,0,50)));
        deliveredOrder.setBorder(BorderFactory.createEtchedBorder());
        totalOrder.setBorder(BorderFactory.createEtchedBorder());
        
        mouseEntered(newOrder,bookedOrder,cancelledOrder,deliveredOrder,totalOrder);
        mouseEntered(bookedOrder,newOrder,cancelledOrder,deliveredOrder,totalOrder);
        mouseEntered(cancelledOrder,newOrder,bookedOrder,deliveredOrder,totalOrder);
        mouseEntered(deliveredOrder,bookedOrder,cancelledOrder,newOrder,totalOrder);
        mouseEntered(totalOrder,bookedOrder,cancelledOrder,deliveredOrder,newOrder);
        
        //south.setBackground(Color.WHITE);
        dashboardSouth.setPreferredSize(new Dimension(260,260));
        dashboardSouth.setBackground(Color.WHITE);
        southLabel=new JLabel();
        southIcon=new ImageIcon("JOP1.gif");
        southLabel.setIcon(southIcon);
        dashboardSouth.add(southLabel);
        dashboardSouth.setBackground(Color.WHITE);
        
        
        homePanelLabel=new JLabel();
        homePanelIcon=new ImageIcon("giftruck.gif");
        homePanelLabel.setPreferredSize(new Dimension(800,630));
        homePanelLabel.setIcon(homePanelIcon);   
    }
    
    public void mouseEntered(JButton b,JButton b1,JButton b2,JButton b3,JButton b4){
        JButton btn[]={b1,b2,b3,b4};
        b.addMouseListener(new MouseListener(){
             @Override
             public void mouseClicked(MouseEvent me) {
                 b.setBackground(Color.white);
                 b.setForeground(new Color(0,0,80));
                 b.setBorder(BorderFactory.createEtchedBorder(Color.white,Color.white));
                 for(int i=0;i<4;i++){
                     btn[i].setBackground(new Color(0,0,80));
                     btn[i].setForeground(Color.WHITE);
                     //b.setBorder(BorderFactory.createEtchedBorder(null, new Color(0,0,50)));
                 }
             }
             @Override
             public void mousePressed(MouseEvent me) {}
             @Override
             public void mouseReleased(MouseEvent me) {}
             @Override
             public void mouseEntered(MouseEvent me) {
                 b.setBorder(BorderFactory.createEtchedBorder(Color.white,null));
             }
             @Override
             public void mouseExited(MouseEvent me) {
                 b.setBorder(BorderFactory.createEtchedBorder(null, new Color(0,0,50)));
             }
            
        });
    }
}
