import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;
    Login(){
        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2=i1.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel label =new JLabel(i3 );
        label.setBounds(70,10,100,100);
        add(label);


       // Code for Heading text -------------------------
        JLabel text =new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward",Font.BOLD,38));
        text.setBounds(200,40,400,40);
        add(text);


        // Code for card no text---------------------------
        JLabel cardno =new JLabel("Card No:");
        cardno.setFont(new Font("Raleway",Font.BOLD,28));
        cardno.setBounds(120,150,150,30);
        add(cardno);

        //Code for input field of Card no--------------------
        cardTextField=new JTextField();
        cardTextField.setBounds(300,150,230,30);
        cardTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(cardTextField);


        //Code for PIN text ---------------------------------
        JLabel pin =new JLabel("PIN:");
        pin.setFont(new Font("Raleway",Font.BOLD,28));
        pin.setBounds(120,220,250,30);
        add(pin);

        //Code for PIN input field---------------------------
        pinTextField=new JPasswordField();
        pinTextField.setBounds(300,220,230,30);
        pinTextField.setFont(new Font("Arial",Font.BOLD,14));
        add(pinTextField);


        //Code for SIGN button -------------------------------
        login =new JButton("SIGN IN");
        login.setBounds(300,300,100,30);
        login.addActionListener(this);
        add(login);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);

        //Code for clear button -------------------------------
        clear =new JButton("CLEAR");
        clear.setBounds(430,300,100,30);
        clear.addActionListener(this);
        add(clear);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);

        //Code for Signup button--------------------------------
        signup =new JButton("SIGN UP");
        signup.setBounds(300,350,230,30);
        signup.addActionListener(this);
        add(signup);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);


        //Code to set background of window------------------------
        getContentPane().setBackground(Color.WHITE);
        setSize(800,480);
        setVisible(true);
        setLocation(350,200);

    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==clear){
            cardTextField.setText("");
            pinTextField.setText("");

        }else if(ae.getSource()==login)
        {


            Conn conn=new Conn();
            String cardnumber=cardTextField.getText();
            String pinnumber=pinTextField.getText();
            String query="select * from login where cardnumber ='"+cardnumber+"'and pin ='"+pinnumber+"'";
            try{
                ResultSet rs =conn.s.executeQuery(query);
                if(rs.next()){
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or PIN");
                }
            }catch (Exception e){
                System.out.println(e);
            }
        } else if(ae.getSource()==signup){
            setVisible(false);
            new SignupOne().setVisible(true);
        }

    }
    public static void main(String args[]){
        new Login();

    }

}
