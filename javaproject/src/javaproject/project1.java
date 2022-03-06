package javaproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class project1 extends JFrame implements ActionListener {
	
	DbConnect db=new DbConnect();

	Container cp;
	JButton loginBtn,joinBtn;
	JLabel lblImg,imgtext;
	JTextField tfid,tfpw;
	ImageIcon img;
	
	JoinFrame jf=new JoinFrame("회원가입");
	BMI bmi=new BMI("BMI");
	
	public project1(String title) {
		
		super(title);
		cp=this.getContentPane();
		this.setBounds(750, 330 ,300 ,400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(255,204,255));
	
		//디자인 메서드 호출..
		initDesign();
		this.setVisible(true);
		
	}
	
	public void initDesign()
	{
		this.setLayout(null);
		
		//아이디 입력 텍스트
		tfid=new JTextField();
		tfid.setBounds(145, 200, 90, 30);
		this.add(tfid);
		
		//패스워드 입력 텍스트
		JPasswordField tfpw=new JPasswordField();
		tfpw.setBounds(145, 240, 90, 30);
		this.add(tfpw);
		
		JLabel lblLogin=new JLabel("ID",JLabel.CENTER);
		lblLogin.setBorder(new LineBorder(new Color(204,153,255),4));
		lblLogin.setBounds(73, 200, 50, 30);
		this.add(lblLogin);
		
		JLabel lblPassword=new JLabel("PW",JLabel.CENTER);
		lblPassword.setBorder(new LineBorder(new Color(204,153,255),4));
		lblPassword.setBounds(73, 240, 50, 30);
		this.add(lblPassword);
		
		//이미지 위에 텍스트
		JLabel imgtext=new JLabel("Diet Diary",JLabel.CENTER);
		Font myFont1 = new Font("Serif", Font.BOLD|Font.ITALIC , 35);
		imgtext.setBounds(44, 72, 200, 70);
		imgtext.setForeground(new Color(153,051,255));
		imgtext.setFont(myFont1);
		this.add(imgtext);
		
		//로그인 버튼
		loginBtn=new JButton("로그인");
		loginBtn.setBounds(150, 290, 85, 30);
		this.add(loginBtn);
		loginBtn.addActionListener(this);
		
		//회원가입 버튼
		joinBtn=new JButton("회원가입");
		joinBtn.setBounds(50, 290, 85, 30);
		this.add(joinBtn);
		joinBtn.addActionListener(this);

		//이미지 삽입
		cp.setLayout(null);
		
		img=new ImageIcon("C:\\sist0117\\image\\purple.jpg");
		lblImg=new JLabel(img);
		lblImg.setBounds(17, 30, 250, 150);
		cp.add(lblImg);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		Object ob=e.getSource();
		
		if(ob==joinBtn)
		{
			jf.setVisible(true);
		} else if(ob==loginBtn)
		{
			
		//	this.setVisible(false);
			bmi.setVisible(true);
		}
	}
	
	public static void main(String[] args) {

		new project1("로그인 화면");
		
	}
}
