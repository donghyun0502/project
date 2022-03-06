package javaproject;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import oracle.sql.DATE;

public class BMI extends JFrame implements ActionListener {

	DbConnect db = new DbConnect();
	
	Container cp;
	Font f1,f2,f3,f4,f5;
	JLabel lbHeight,lbWeight,lbcm,lbkg,lbResult;
	JTextField tHeight,tWeight;
	JButton btnBmi,btnDiet,btnCheck,btnSave,btnHistory;
	
	public BMI(String title) {
		super(title);
		cp=this.getContentPane();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(800, 100, 400, 600);
		cp.setBackground(new Color(204,219,226));//..bmi
		//cp.setBackground(new Color(226,204,208));//..diet
		//cp.setBackground(new Color(204,226,211));//..main
		
		this.initDesign();
		setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void initDesign()
	{
		this.setLayout(null);
			
		//�۲�
		f1 = new Font("",Font.BOLD,30); //btnBmi,btnDiet
		f2 = new Font("",Font.PLAIN,23); //JLabel
		f3 = new Font("",Font.ITALIC,20); //JTextField
		f4 = new Font("",Font.BOLD,20); //btnCheck,btnSave,btnHistory
		f5 = new Font("",Font.PLAIN,15); //lbResult
		
		
		//��ư����..btnBmi,btnDiet
		btnBmi = new JButton("BMI");
		btnBmi.setFont(f1);
		btnBmi.setBackground(Color.white);
		btnBmi.setForeground(new Color(204,219,226));
		btnBmi.setBounds(30, 10, 140, 50);
		btnBmi.setBorderPainted(false); //jbutton border(�ܰ���) ���ֱ�
		this.add(btnBmi);
		
		btnDiet = new JButton("DIET");
		btnDiet.setFont(f1);
		btnDiet.setBackground(new Color(204,219,226));
		btnDiet.setForeground(Color.white);
		btnDiet.setBounds(210, 10, 140, 50);
		btnDiet.setBorderPainted(false);
		btnDiet.setOpaque(false);
		this.add(btnDiet);
		
		//�׼ǹ�ư����..btnCheck.btnSave,btnHistory
		btnCheck = new JButton("BMI Calculate");
		btnCheck.setFont(f4);
		btnCheck.setBackground(new Color(98,117,146));
		btnCheck.setForeground(Color.white);
		btnCheck.setBorderPainted(false);
		btnCheck.setBounds(60, 220, 260, 40);
		this.add(btnCheck);
		btnCheck.addActionListener(this);
		
		btnSave = new JButton("Save");
		btnSave.setFont(f4);
		btnSave.setBackground(new Color(98,117,146));
		btnSave.setForeground(Color.white);
		btnSave.setBounds(60, 470, 120, 40);
		this.add(btnSave);
		btnSave.addActionListener(this);
		
		btnHistory = new JButton("History");
		btnHistory.setFont(f4);
		btnHistory.setBackground(new Color(98,117,146));
		btnHistory.setForeground(Color.white);
		btnHistory.setBounds(200, 470, 120, 40);
		this.add(btnHistory);
		btnHistory.addActionListener(this);
		
				
		//�󺧻���..Height
		lbHeight = new JLabel("Height",JLabel.CENTER);
		lbHeight.setFont(f2);
		lbHeight.setBounds(30, 80, 140, 50);
		add(lbHeight);
		
		tHeight = new JTextField();
		tHeight.setHorizontalAlignment(JTextField.RIGHT); //�ؽ�Ʈ ����������
		tHeight.setFont(f3);
		tHeight.setBounds(170, 80, 140, 50);
		tHeight.setBorder(new LineBorder(new Color(204,219,226),5));
		add(tHeight);
		
		lbcm = new JLabel("cm");
		lbcm.setFont(f2);
		lbcm.setBounds(320, 80, 50, 50);
		add(lbcm);
		
		
		//�󺧻���..Weight
		lbWeight = new JLabel("Weight",JLabel.CENTER);
		lbWeight.setFont(f2);
		lbWeight.setBounds(30, 150, 140, 50);
		add(lbWeight);
		
		tWeight = new JTextField();
		tWeight.setHorizontalAlignment(JTextField.RIGHT); //�ؽ�Ʈ ����������
		tWeight.setFont(f3);
		tWeight.setBounds(170, 150, 140, 50);
		tWeight.setBorder(new LineBorder(new Color(204,219,226),5));
		add(tWeight);
		
		lbkg = new JLabel("kg");
		lbkg.setFont(f2);
		lbkg.setBounds(320, 150, 50, 50);
		add(lbkg);
		
		
		//�����
		lbResult = new JLabel("�������",JLabel.CENTER);
		lbResult.setFont(f3);
		lbResult.setBounds(60, 280, 260, 170);
		lbResult.setBorder(new LineBorder(new Color(98,117,146),5));
		this.add(lbResult);
		
		
		//�ǳڻ���..board
		JPanel board = new JPanel();
		board.setBackground(Color.white);
		board.setBounds(0, 60, 400, 480);
		this.add(board);
						
	}
		
	@Override //..��ư�̺�Ʈ
	public void actionPerformed(ActionEvent e) {
		
		Object ob=e.getSource();
		
		if(ob==btnCheck)
		{
			//BMI Calculate
			double calHeight = Double.parseDouble(tHeight.getText());
			double calWeight = Double.parseDouble(tWeight.getText());
			double calBmi = Math.round(calWeight/(calHeight*calHeight)*10000*100d)/100d;
			double stBmi = Math.round((calHeight*calHeight)*21*0.0001*100d)/100d;
			
			String sbmi; //���Ѻ���ȸ 2020���ڷ���ħ ����
			if(calBmi>=35)
				sbmi="3�ܰ��(����)";
			else if(calBmi>=30)
				sbmi="2�ܰ��";
			else if(calBmi>=25)
				sbmi="1�ܰ��";
			else if(calBmi>=23)
				sbmi="��ü��";
			else if(calBmi>=18.5)
				sbmi="����ü��";
			else
				sbmi="��ü��";
			
			lbResult.setFont(f5);
			lbResult.setText("<html>[BMI�������]<br>name���� ��ü��������(BMI)��<p>"+calBmi+"�� '"+sbmi+"' �Դϴ�.<p>���ü���� "+stBmi+"Kg �Դϴ�.</html>");
			lbResult.setHorizontalAlignment(SwingConstants.CENTER); 
		
		} else if(ob==btnSave) {
			insertData();
			JOptionPane.showMessageDialog(this, "������ �Ϸ�Ǿ����ϴ�.");
			
		} else if(ob==btnHistory) {
			BMI_History history = new BMI_History("BMI Data");
			history.setVisible(true);
		}
		
	}
	
	public void insertData()
	{
		double calHeight = Double.parseDouble(tHeight.getText());
		double calWeight = Double.parseDouble(tWeight.getText());
		double calBmi = Math.round(calWeight/(calHeight*calHeight)*10000*100d)/100d;
		
		String height=tHeight.getText();
		String weight=tWeight.getText();
		String bmi=Double.toString(calBmi);
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		String date=simpleDate.format(new java.util.Date());

		String sql="insert into bmi (seq,height,weight,bmi,writedate) values (seq_bmi.nextval,?,?,?,?)";
		System.out.println(height+", "+weight+", "+bmi);
		
		Connection conn=db.getOracle();
		PreparedStatement pstmt=null;
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			//?..7�� ���ε�
			pstmt.setString(1, height);
			pstmt.setString(2, weight);
			pstmt.setString(3, bmi);
			pstmt.setString(4, date);
			pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.dbClose(pstmt, conn);
		}
		
	}
	
	public static void main(String[] args) {
		new BMI("Diet Diary");
	}

}
