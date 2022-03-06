package javaproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class JoinFrame extends JFrame implements ActionListener {

   DbConnect db = new DbConnect();
      
   Container cp;
   JRadioButton rbMan,rbWoman;
   JRadioButton[] rb=new JRadioButton[2];
   JTextField tfid,tfpw,tfweight,tfheight;
   JLabel lbljoin,lblid,lblpw,lblweight,lblheight;
   JButton btnSign;
   
   public JoinFrame(String title) {
      super(title);
      cp=this.getContentPane();
      this.setBounds(790, 380 ,220 ,330);
      cp.setBackground(new Color(255,217,236));
   
      //디자인 메서드 호출..
      initDesign();
   }
   
   public void initDesign()
   {
      this.setLayout(null);
      
      tfid=new JTextField();
      tfid.setBounds(100, 80, 80, 30);
      this.add(tfid);
      
      tfpw=new JTextField();
      tfpw.setBounds(100, 120, 80, 30);
      this.add(tfpw);
      
      tfheight=new JTextField();
      tfheight.setBounds(100, 160, 80, 30);
      this.add(tfheight);
      
      tfweight=new JTextField();
      tfweight.setBounds(100, 200, 80, 30);
      this.add(tfweight);
      
      lbljoin=new JLabel("회원가입",JLabel.CENTER);
      Font f1 = new Font("Serif", Font.BOLD, 20);
      lbljoin.setBorder(new LineBorder(new Color(204,153,255),4));
      lbljoin.setBounds(47, 20, 120, 42);
      lbljoin.setFont(f1);
      this.add(lbljoin);
      
      lblid=new JLabel("아이디",JLabel.CENTER);
      lblid.setBorder(new LineBorder(new Color(204,153,255),4));
      lblid.setBounds(34, 80, 60, 30);
      this.add(lblid);
      
      lblpw=new JLabel("비밀번호",JLabel.CENTER);
      lblpw.setBorder(new LineBorder(new Color(204,153,255),4));
      lblpw.setBounds(34, 120, 60, 30);
      this.add(lblpw);
      
      lblheight=new JLabel("키(cm)",JLabel.CENTER);
      lblheight.setBorder(new LineBorder(new Color(204,153,255),4));
      lblheight.setBounds(34, 160, 60, 30);
      this.add(lblheight);
      
      lblweight=new JLabel("몸무게",JLabel.CENTER);
      lblweight.setBorder(new LineBorder(new Color(204,153,255),4));
      lblweight.setBounds(34, 200, 60, 30);
      this.add(lblweight);
      
      btnSign=new JButton("가입");
      btnSign.setBounds(132, 250, 58, 30);
      this.add(btnSign);
      btnSign.addActionListener(this);
      
      rbMan=new JRadioButton("남자");
	  rbMan.setBounds(10, 250, 60, 30);
	  rbMan.setOpaque(false);
	  rbMan.addActionListener(this);
		
	  rbWoman=new JRadioButton("여자");
	  rbWoman.setBounds(60, 250, 60, 30);
	  rbWoman.setOpaque(false);
	  rbWoman.addActionListener(this);
	  
	  
	  ButtonGroup bg=new ButtonGroup();
      bg.add(rbMan);
	  bg.add(rbWoman);
	  
	  this.add(rbMan);
	  this.add(rbWoman);
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {

       Object ob=e.getSource();

       if(ob==btnSign)
       {
           String gender=null;
       
           if(rbMan.isSelected()){
               gender=rbMan.getText();
           }else if(rbWoman.isSelected()) {
               gender=rbWoman.getText();
           }
           
           String id=tfid.getText();
           String pw=tfpw.getText();
           String height=tfheight.getText();
           String weight=tfweight.getText();            
           
           String sql="insert into mainlogin (num,id,pw,height,weight,gender) values (seq_main.nextval,?,?,?,?,?)";
           
           Connection conn=db.getOracle();
           PreparedStatement pstmt=null;
           
           try {
               pstmt=conn.prepareStatement(sql);
               
               pstmt.setString(1, id);
               pstmt.setString(2, pw);
               pstmt.setString(3, height);
               pstmt.setString(4, weight);
               pstmt.setString(5, gender);
               
               pstmt.execute();
               
           } catch (SQLException e1) {
               System.out.println(e1.getMessage());
           } finally {
               db.dbClose(pstmt, conn);
           }
           
           System.out.println("가입성공!");
           this.setVisible(false);
       }
   }
}