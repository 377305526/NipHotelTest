package com.NipHotel.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.NipHotel.Entity.UserInfo;
import com.NipHotel.Service.UserService;

public class AdmUserMenu {

	
	private JFrame frame;
	private JTable table;
	private JTextField textdi;
	private JTextField textye;
	private JButton btnquery;
	private JTextField txtInput;
	String[] columns = new String[] {"顾客编号", "顾客姓名", "顾客性别", "身份证号", "顾客电话", "会员号", "其他","房间号"};
	Object[][] userInfo = null ;
	ArrayList<UserInfo> uL = new ArrayList<UserInfo>();
	UserService us = new UserService();
	@SuppressWarnings("rawtypes")
	JComboBox cboType;
	private JButton btnprep;
	private JButton btnTopp;
	private JButton btnNextp;
	private JButton btnBackp;
	private int thisPage = 1 ;
	private int lastPage = 1 ;
	@SuppressWarnings("rawtypes")
	private JComboBox cboPageSize;
	private JPanel panel_2;
	private JPanel panel_1;
	public JSplitPane splitPane_1;
	private JPanel panel;
	public JSplitPane splitPane;

	
	
	public JFrame getFrame(){
		return this.frame;
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
					AdmUserMenu window = new AdmUserMenu();
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
	public AdmUserMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("顾客界面");
		frame.setBounds(100, 100, 919, 577);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		frame.setLocationRelativeTo(null);
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		frame.getContentPane().add(splitPane);
		
		panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
		
		//查询判断
		btnquery = new JButton("查询");
		btnquery.setFont(new Font("宋体", Font.PLAIN, 20));
		btnquery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtInput.getText().isEmpty())
				{
					uL=us.limitSelectOrders(thisPage, Integer.parseInt(cboPageSize.getSelectedItem().toString()));
					thisPage=1 ;
					
					int a =us.getAllUser().size()% Integer.parseInt(cboPageSize.getSelectedItem().toString());
					if(a>0)
					{
						lastPage =us.getAllUser().size()/ Integer.parseInt(cboPageSize.getSelectedItem().toString())+1;
					}
					else{
						lastPage = us.getAllUser().size()/ Integer.parseInt(cboPageSize.getSelectedItem().toString());
					}
					textdi.setText(thisPage+"");
					textye.setText(lastPage+"");
					showUserInfos(uL);
				}
				else{
					
					uL.clear();
					int choose = cboType.getSelectedIndex() ;
					String input = txtInput.getText();
					try{
					uL=us.someUsers(choose, input);
					}catch(NumberFormatException e1)
					{
						JOptionPane.showMessageDialog(null, "房间号输入非法！");
						txtInput.requestFocus();
						txtInput.selectAll();
						return;
					}
					if(uL.size()==0)
					{
						JOptionPane.showMessageDialog(null, "没有该订单信息");
						txtInput.requestFocus();
						txtInput.selectAll();
					}
					showUserInfos(uL);

				}
			}
		});
		
		btnquery.setBounds(62, 104, 115, 35);
		panel.add(btnquery);
		
		txtInput = new JTextField();
		txtInput.setBounds(183, 25, 167, 35);
		panel.add(txtInput);
		txtInput.setColumns(10);
		
		cboType = new JComboBox();
		cboType.setModel(new DefaultComboBoxModel(new String[] {"顾客姓名", "预留手机号", "会员号"}));
		cboType.setBounds(372, 30, 104, 24);
		panel.add(cboType);
		
		JButton button_1 = new JButton("取消");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtInput.setText("");
				cboType.setSelectedIndex(0);
			}
		});
		button_1.setFont(new Font("宋体", Font.PLAIN, 18));
		button_1.setBounds(711, 103, 119, 36);
		panel.add(button_1);
		
		JButton button_2 = new JButton("提交");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					for (int i = 0; i < table.getRowCount(); i++) 
					{	
						UserInfo u = new UserInfo();
						u.setUser_ID((Integer.parseInt(table.getValueAt(i, 0).toString())));
						u.setUser_Name(table.getValueAt(i, 1).toString());
						u.setUser_Sex(table.getValueAt(i, 2).toString());
						u.setUser_Identity(table.getValueAt(i, 3).toString());
						//SimpleDateFormat df= new SimpleDateFormat("yyyy-MM-dd");
						//String date;
						//date =df.format(table.getValueAt(i, 4).toString()).toString();
						u.setUser_Tel(table.getValueAt(i, 4).toString());
						
						u.setVIP_ID(Integer.parseInt(table.getValueAt(i, 5).toString()));
						String remark = table.getValueAt(i, 6).toString();
						if(remark==null)remark="";
						u.setRemark(remark);
						u.setRoom_Id(Integer.parseInt(table.getValueAt(i, 7).toString()));
						uL.add(u);
					}
					
					if(us.changeUserInfo(uL)==true)
					{
						JOptionPane.showMessageDialog(null, "修改成功！");
					}
					else{
						JOptionPane.showMessageDialog(null, "主键关联修改失败！");
					}
					
					}catch(NumberFormatException e1)
					{
						JOptionPane.showMessageDialog(null, "修改了非法数值！");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}catch(IllegalArgumentException e1){
						JOptionPane.showMessageDialog(null, "时间格式错误");
					}
				
					
					
				
				
			}
		});
		button_2.setFont(new Font("宋体", Font.PLAIN, 18));
		button_2.setBounds(498, 103, 119, 36);
		panel.add(button_2);
		
		JButton btnDelOrder = new JButton("删除会员");
		btnDelOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(uL.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "没有选择任何顾客");
				}
				else{
					int row =table.getSelectedRow();
					int deleteId =Integer.parseInt(userInfo[row][0].toString());
					if(JOptionPane.showConfirmDialog(null, "是否要删除"+deleteId+"号的顾客")==0)
					{
						if(us.deleteUserInfo(deleteId)==true)
							{
							JOptionPane.showMessageDialog(null, "删除成功！");
							}
						else{
							JOptionPane.showMessageDialog(null, "删除失败！");
						}
					}
					else{
						
						return ;
					}
				}
				
			}
		});
		btnDelOrder.setFont(new Font("宋体", Font.PLAIN, 18));
		btnDelOrder.setBounds(263, 103, 119, 36);
		panel.add(btnDelOrder);
		
		splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);
		
		panel_1 = new JPanel();
		splitPane_1.setLeftComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(userInfo,columns));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("New button");
		scrollPane.setColumnHeaderView(btnNewButton);
		
		panel_2 = new JPanel();
		splitPane_1.setRightComponent(panel_2);
		panel_2.setLayout(null);
		
		btnprep = new JButton("上一页");
		btnprep.setBounds(186, 10, 95, 25);
		panel_2.add(btnprep);
		btnprep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtInput.getText().isEmpty()){
				if(thisPage==1)
				{
					return ;
				}
				else{
					thisPage-=1;
					textdi.setText(thisPage+"");
					uL=us.limitSelectOrders(thisPage, Integer.parseInt(cboPageSize.getSelectedItem().toString()));
					showUserInfos(uL);
				}
				}
			}
		});
		
		btnTopp = new JButton("首页");
		btnTopp.setBounds(53, 10, 95, 25);
		panel_2.add(btnTopp);
		btnTopp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtInput.getText().isEmpty()){
						thisPage=1;
						textdi.setText(thisPage+"");
						uL=us.limitSelectOrders(thisPage, Integer.parseInt(cboPageSize.getSelectedItem().toString()));
						showUserInfos(uL);
					
					}
			}
		});
		
		btnNextp = new JButton("下一页");
		btnNextp.setBounds(453, 10, 95, 25);
		panel_2.add(btnNextp);
		btnNextp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(txtInput.getText().isEmpty()){
					if(thisPage==lastPage)
					{
						return ;
					}
					else{
						thisPage+=1;
						textdi.setText(thisPage+"");
						uL=us.limitSelectOrders(thisPage, Integer.parseInt(cboPageSize.getSelectedItem().toString()));
						showUserInfos(uL);
					}
					}
				}
			
		});
		
		btnBackp = new JButton("尾页");
		btnBackp.setBounds(558, 10, 95, 25);
		panel_2.add(btnBackp);
		btnBackp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtInput.getText().isEmpty()){
					thisPage=lastPage;
					textdi.setText(thisPage+"");
					uL=us.limitSelectOrders(thisPage, Integer.parseInt(cboPageSize.getSelectedItem().toString()));
					showUserInfos(uL);
				
				}
			}
		});
		
		JLabel lbldi = new JLabel("第");
		lbldi.setBounds(306, 13, 19, 15);
		panel_2.add(lbldi);
		
		JLabel lblg = new JLabel("/");
		lblg.setBounds(359, 13, 33, 15);
		panel_2.add(lblg);
		
		JLabel lblye = new JLabel("页");
		lblye.setBounds(414, 13, 54, 15);
		panel_2.add(lblye);
		
		textdi = new JTextField();
		textdi.setBounds(325, 10, 24, 21);
		panel_2.add(textdi);
		textdi.setColumns(10);
		textdi.setText(thisPage+"");
		
		textye = new JTextField();
		textye.setBounds(380, 10, 24, 21);
		panel_2.add(textye);
		textye.setColumns(10);
		textye.setText(lastPage + "");
		
		JLabel lblmei = new JLabel("每");
		lblmei.setBounds(696, 14, 19, 15);
		panel_2.add(lblmei);
		
		cboPageSize = new JComboBox();
		cboPageSize.setModel(new DefaultComboBoxModel(new String[] {"5", "10", "15", "20"}));
		cboPageSize.setBounds(725, 10, 46, 23);
		panel_2.add(cboPageSize);
		
		JLabel lblxiang = new JLabel("项为一页");
		lblxiang.setBounds(785, 14, 77, 15);
		panel_2.add(lblxiang);
		splitPane_1.setDividerLocation(320);
		splitPane.setDividerLocation(150);
	}

	public JFrame getAdmUserMenu() {
		return frame;
	}
	
	public void showUserInfos(ArrayList<UserInfo> aoL)
	{
		userInfo = new Object[aoL.size()][8];
		for(int i = 0 ; i < aoL.size() ; i++)
		{
			userInfo[i][0] = aoL.get(i).getUser_ID();
			userInfo[i][1] = aoL.get(i).getUser_Name();
			userInfo[i][2] = aoL.get(i).getUser_Sex();
			userInfo[i][3] = aoL.get(i).getUser_Identity();
			userInfo[i][4] = aoL.get(i).getUser_Tel();
			userInfo[i][5] = aoL.get(i).getVIP_ID();
			userInfo[i][6] = aoL.get(i).getRemark();
			userInfo[i][7] = aoL.get(i).getRoom_Id();
		}
	
		
		table.setModel(new DefaultTableModel(userInfo,columns));
		
	}
	
	
}
