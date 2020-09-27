package com.NipHotel.GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.NipHotel.Entity.Staff;
import com.NipHotel.Service.StaffService;

public class AdmAddStaff {

	private JFrame frame;
	private JTextField txtId;
	private JTextField txtName;
	private JTextField txtTel;
	private JTextField txtPwd;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JTextPane txtRemark;
	StaffService ss = new StaffService();

	public JFrame getAdmAddRoom(){
		return frame ;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					//UIManager.setLookAndFeel("org.jvnet.substance.painter.border.ClassicInnerBorderPainter");
					//SubstanceLookAndFeel.setCurrentTitlePainter(new FlatBorderPainter());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					AdmAddStaff window = new AdmAddStaff();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdmAddStaff() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("添加员工资料");
		frame.setBounds(100, 100, 392, 538);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblId = new JLabel("员工号：");
		lblId.setFont(new Font("宋体", Font.PLAIN, 18));
		lblId.setBounds(24, 25, 72, 31);
		frame.getContentPane().add(lblId);
		
		txtId = new JTextField();
		txtId.setBounds(124, 27, 97, 31);
		frame.getContentPane().add(txtId);
		txtId.setColumns(10);
		
		JLabel lblSex = new JLabel("性别：");
		lblSex.setFont(new Font("宋体", Font.PLAIN, 18));
		lblSex.setBounds(24, 76, 90, 31);
		frame.getContentPane().add(lblSex);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		comboBox.setBounds(124, 83, 97, 21);
		frame.getContentPane().add(comboBox);
		
		JLabel lblName = new JLabel("员工姓名：");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("宋体", Font.PLAIN, 18));
		lblName.setBounds(24, 124, 90, 31);
		frame.getContentPane().add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(124, 126, 97, 31);
		frame.getContentPane().add(txtName);
		
		JLabel lblTel = new JLabel("员工电话：");
		lblTel.setHorizontalAlignment(SwingConstants.CENTER);
		lblTel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblTel.setBounds(24, 174, 90, 31);
		frame.getContentPane().add(lblTel);
		
		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(124, 176, 166, 31);
		frame.getContentPane().add(txtTel);
		
		JLabel lblPwd = new JLabel("密码：");
		lblPwd.setHorizontalAlignment(SwingConstants.LEFT);
		lblPwd.setFont(new Font("宋体", Font.PLAIN, 18));
		lblPwd.setBounds(24, 229, 90, 31);
		frame.getContentPane().add(lblPwd);
		
		txtPwd = new JTextField();
		txtPwd.setColumns(10);
		txtPwd.setBounds(124, 231, 126, 31);
		frame.getContentPane().add(txtPwd);
		
		JLabel lblRemark = new JLabel("备注：");
		lblRemark.setFont(new Font("宋体", Font.PLAIN, 18));
		lblRemark.setBounds(24, 286, 90, 31);
		frame.getContentPane().add(lblRemark);
		
		txtRemark = new JTextPane();
		txtRemark.setBounds(123, 286, 212, 85);
		frame.getContentPane().add(txtRemark);
		
		JButton btnAddRoom = new JButton("确定");
		btnAddRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtId.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "会员号不可为空");
					txtId.requestFocus();
					txtId.selectAll();
				}
				else{
					try{
					int staffId = Integer.parseInt(txtId.getText());
					String tel = "";
					tel = txtTel.getText();
					String regex=  "(?:(\\(\\+?86\\))((1[0-9]{1}[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|" +     
			                "(?:86-?((1[0-9]{1}[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|" +
			                "(?:((1[0-9]{1}[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})";
				    Matcher m=Pattern.compile(regex).matcher(tel);
				    if(m.matches()==false)
				    {
				    	JOptionPane.showMessageDialog(null, "电话号输入非法");
				    	return ;
				    }
					if(ss.getOneStaff(txtId.getText(), 2)==null)
					{
						
						Staff s = new Staff();
						s.setRemark(txtRemark.getText());
						s.setStaff_ID(staffId);;
						s.setStaff_Name(txtName.getText());
						s.setStaff_Tel(tel);
						s.setStaff_PWD(txtPwd.getText());
						s.setStaff_Sex(comboBox.getSelectedItem().toString());

						
						if(ss.addStaff(s)==true)
						{
							JOptionPane.showMessageDialog(null, "添加完成！");
							
						}
						
						
					}
					else{
						JOptionPane.showMessageDialog(null, "已存在该员工号 请重新输入");
						txtId.requestFocus();
						txtId.selectAll();
					}
					}catch(NumberFormatException e1)
					{
						JOptionPane.showMessageDialog(null, "非法输入");
						txtId.requestFocus();
						txtId.selectAll();
					}
					
				}
				
			}
		});
		btnAddRoom.setFont(new Font("宋体", Font.PLAIN, 16));
		btnAddRoom.setBounds(21, 416, 126, 32);
		frame.getContentPane().add(btnAddRoom);
		
		JButton btnCancle = new JButton("取消");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtId.setText("");
				comboBox.setSelectedIndex(0);
				txtName.setText("");
				txtTel.setText("");
				txtRemark.setText("");
				txtPwd.setText("");
				
			}
		});
		btnCancle.setFont(new Font("宋体", Font.PLAIN, 16));
		btnCancle.setBounds(215, 416, 126, 32);
		frame.getContentPane().add(btnCancle);
	}
}
