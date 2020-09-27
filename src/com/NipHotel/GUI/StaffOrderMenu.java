package com.NipHotel.GUI;

import java.awt.Color;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.NipHotel.Entity.UserInfo;
import com.NipHotel.Service.OrdersService;
import com.NipHotel.Service.RoomService;
import com.NipHotel.Service.UserService;
import com.NipHotel.Service.VipService;

public class StaffOrderMenu {

	private JFrame OrderFrame;
	private JTextField txtUserId;
	private JTextField txtUserName;
	private JTextField txtIdentity;
	private JTextField txtTel;
	private JTextField txtCardId;
	private JTextField txtRoomId;
	int roomId  = 0 ;
	VipService vs = new VipService();
	private JLabel lblIsVipOk;
	private JLabel lblIdentity;
	private JButton btnOk;
	private JComboBox<String> comboBox;
	private JButton btnIdentity;
	private JButton btnVip;
	UserService us = new UserService();
	RoomService rs = new RoomService();
	private JTextArea txtRemark;
	OrdersService os = new OrdersService();
	private int staffId;
	private JLabel lblStaffId;
	private JTextField txtStaffId;

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
					StaffOrderMenu window = new StaffOrderMenu();
					window.OrderFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StaffOrderMenu() {
		initialize();
		
	}
	
	/**
	 * 有带传入房间号、员工号的构造 
	 * @param room
	 */
	public StaffOrderMenu(int room , int staffId) {
		this.staffId = staffId;
		setRoomId(room);
		initialize();
		
	}



	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public JFrame getOrderFrame() {
		return OrderFrame;
	}



	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		OrderFrame = new JFrame();
		OrderFrame.setResizable(false);
		OrderFrame.setTitle("顾客录入");
		OrderFrame.setBounds(100, 100, 426,534);
		OrderFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		OrderFrame.setLocationRelativeTo(null);
		OrderFrame.getContentPane().setLayout(null);
		
		JLabel lblId = new JLabel("顾客编号：");
		lblId.setFont(new Font("宋体", Font.PLAIN, 14));
		lblId.setBounds(22, 26, 75, 15);
		OrderFrame.getContentPane().add(lblId);
		
		txtUserId = new JTextField();
		txtUserId.setEditable(false);
		txtUserId.setBounds(93, 23, 75, 21);
		OrderFrame.getContentPane().add(txtUserId);
		txtUserId.setColumns(10);
		
		JLabel lblName = new JLabel("顾客姓名：");
		lblName.setFont(new Font("宋体", Font.PLAIN, 14));
		lblName.setBounds(22, 68, 75, 15);
		OrderFrame.getContentPane().add(lblName);
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(93, 65, 75, 21);
		OrderFrame.getContentPane().add(txtUserName);
		
		JLabel lblSex = new JLabel("顾客性别：");
		lblSex.setFont(new Font("宋体", Font.PLAIN, 14));
		lblSex.setBounds(22, 104, 75, 15);
		OrderFrame.getContentPane().add(lblSex);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		comboBox.setBounds(93, 101, 75, 21);
		OrderFrame.getContentPane().add(comboBox);
		
		JLabel label = new JLabel("身份号码：");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(22, 139, 75, 15);
		OrderFrame.getContentPane().add(label);
		
		txtIdentity = new JTextField();
		txtIdentity.setColumns(10);
		txtIdentity.setBounds(93, 136, 193, 21);
		OrderFrame.getContentPane().add(txtIdentity);
		
		//身份证判断
		btnIdentity = new JButton("身份认证");
		btnIdentity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtIdentity.getText().isEmpty())
				{
					lblIdentity.setText("身份证不得为空！");
					lblIdentity.setForeground(Color.RED);
					return ;
				}
				//身份证认证合法
				String identity =txtIdentity.getText();
				 String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
			                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
				 Matcher m=Pattern.compile(regularExpression).matcher(identity);
				 if(m.matches()==true)
				    {
				    	
				    	lblIdentity.setText("身份证联网查询合法！");
				    	lblIdentity.setForeground(Color.GREEN);
				    	txtIdentity.setEditable(false);
				    }
				 else{
					 lblIdentity.setText("身份证输入非法！");
						lblIdentity.setForeground(Color.RED);
						return ;
				 }
					
				
				
				if( lblIdentity.getForeground()==Color.GREEN)
				{
					btnOk.setEnabled(true);
				}
				else{
					btnOk.setEnabled(false);
				}
			}
		});
		btnIdentity.setBounds(93, 164, 93, 23);
		OrderFrame.getContentPane().add(btnIdentity);
		
		lblIdentity = new JLabel("身份是否合法");
		lblIdentity.setBounds(196, 168, 146, 15);
		OrderFrame.getContentPane().add(lblIdentity);
		
		JLabel lblTel = new JLabel("联系电话：");
		lblTel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblTel.setBounds(22, 206, 75, 15);
		OrderFrame.getContentPane().add(lblTel);
		
		txtTel = new JTextField();
		txtTel.setColumns(10);
		txtTel.setBounds(93, 203, 193, 21);
		OrderFrame.getContentPane().add(txtTel);
		
		JLabel lblVip = new JLabel("会员卡号：");
		lblVip.setFont(new Font("宋体", Font.PLAIN, 14));
		lblVip.setBounds(22, 249, 75, 15);
		OrderFrame.getContentPane().add(lblVip);
		
		txtCardId = new JTextField();
		txtCardId.setColumns(10);
		txtCardId.setBounds(93, 246, 75, 21);
		OrderFrame.getContentPane().add(txtCardId);
		
		//会员号存在事件
		btnVip = new JButton("会员认证");
		btnVip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id =0 ;
				try{
					id  = Integer.parseInt(txtCardId.getText());
				}catch(NumberFormatException e1)
				{
					lblIsVipOk.setText("会员号非法");
					lblIsVipOk.setForeground(Color.RED);
					return ;
				}
				if(vs.isCardOk(id)==true){
					lblIsVipOk.setText("会员号存在");
					lblIsVipOk.setForeground(Color.GREEN);
					txtCardId.setEditable(false);
				}
				else{
					lblIsVipOk.setText("会员号不存在");
					lblIsVipOk.setForeground(Color.RED);
				}
			
			}
		});
		btnVip.setBounds(93, 277, 93, 23);
		OrderFrame.getContentPane().add(btnVip);
		
		 lblIsVipOk = new JLabel("会员是否合法");
		lblIsVipOk.setBounds(196, 281, 90, 15);
		OrderFrame.getContentPane().add(lblIsVipOk);
		
		JLabel lblRemark = new JLabel("备注：");
		lblRemark.setFont(new Font("宋体", Font.PLAIN, 14));
		lblRemark.setBounds(22, 310, 75, 15);
		OrderFrame.getContentPane().add(lblRemark);
		
		txtRemark = new JTextArea();
		txtRemark.setBounds(93, 310, 193, 87);
		OrderFrame.getContentPane().add(txtRemark);
		
		btnOk = new JButton("确认");
		btnOk.setEnabled(false);
	
		
		//添加数据
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int i = 1 ;
				UserInfo u = new UserInfo();
				u.setUser_Name(txtUserName.getText());
				u.setUser_Sex(comboBox.getSelectedItem().toString());
				try{
				u.setVIP_ID(Integer.parseInt(txtCardId.getText()));
				}catch(NumberFormatException e1)
				{
					i = 2 ;
				}
				String Vip_Tel = "";
				Vip_Tel = txtTel.getText();
				if(Vip_Tel.length()<11)
				{
					JOptionPane.showMessageDialog(null, "电话号长度错误");
				    return ;
				}
				String regex=  "(?:(\\(\\+?86\\))((1[0-9]{1}[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|" +     
		                "(?:86-?((1[0-9]{1}[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|" +
		                "(?:((1[0-9]{1}[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})";
			    Matcher m=Pattern.compile(regex).matcher(Vip_Tel);
			    if(m.matches()==false)
			    {
			    	JOptionPane.showMessageDialog(null, "电话号输入非法");
			    	return ;
			    }
				u.setUser_Identity(txtIdentity.getText());
				u.setUser_Tel(Vip_Tel);
				u.setRoom_Id(roomId);
				u.setRemark(txtRemark.getText());
				
				
				if(btnOk.isEnabled())
				{
	
					if(lblIsVipOk.getForeground()==Color.GREEN)
					{
						if(us.addUserInfo(u,i)==true){
							rs.updateRoom(roomId , 1);
							if(os.addOrders(us.getUserId() ,staffId)==true)
							{
								JOptionPane.showMessageDialog(null, "添加成功");
								reset();
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "添加失败");
						}
						
					}
					else if (lblIsVipOk.getForeground()!=Color.GREEN){
						
						if(us.addUserInfo(u,i)==true){
							reset();
							rs.updateRoom(roomId , 1);
							if(os.addOrders(us.getUserId(),staffId))
							{
								JOptionPane.showMessageDialog(null, "添加成功");
							}
							
						}
						else{
							JOptionPane.showMessageDialog(null, "添加失败");
						}
					}
				
				}
				
			}
		});
		btnOk.setFont(new Font("宋体", Font.PLAIN, 17));
		btnOk.setBounds(22, 441, 115, 35);
		OrderFrame.getContentPane().add(btnOk);
		
		JButton btnCancel = new JButton("取消");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				reset();
				
				
			}
		});
		btnCancel.setFont(new Font("宋体", Font.PLAIN, 17));
		btnCancel.setBounds(260, 441, 105, 35);
		OrderFrame.getContentPane().add(btnCancel);
		
		JLabel lblRoomId = new JLabel("房间编号：");
		lblRoomId.setFont(new Font("宋体", Font.PLAIN, 14));
		lblRoomId.setBounds(196, 29, 75, 15);
		OrderFrame.getContentPane().add(lblRoomId);
		
		txtRoomId = new JTextField();
		txtRoomId.setEditable(false);
		txtRoomId.setColumns(10);
		txtRoomId.setBounds(267, 26, 75, 21);
		OrderFrame.getContentPane().add(txtRoomId);
		txtRoomId.setText(roomId+"");
		
		lblStaffId = new JLabel("员工号：");
		lblStaffId.setFont(new Font("宋体", Font.PLAIN, 14));
		lblStaffId.setBounds(196, 65, 75, 15);
		OrderFrame.getContentPane().add(lblStaffId);
		
		txtStaffId = new JTextField();
		txtStaffId.setText(staffId+"");
		txtStaffId.setEditable(false);
		txtStaffId.setColumns(10);
		txtStaffId.setBounds(267, 62, 75, 21);
		OrderFrame.getContentPane().add(txtStaffId);
	}
	
	
	public void reset()
	{
		txtIdentity.setEditable(true);;
		txtUserName.setText("");
		txtIdentity.setText("");
		txtCardId.setText("");
		comboBox.setSelectedIndex(0);
		txtTel.setText("");
		lblIdentity.setText("身份是否合法");
		lblIdentity.setForeground(Color.BLACK);
		lblIsVipOk.setText("会员是否存在");
		lblIsVipOk.setForeground(Color.BLACK);
		btnOk.setEnabled(false);
	} 
}
