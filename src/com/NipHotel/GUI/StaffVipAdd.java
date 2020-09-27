package com.NipHotel.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.NipHotel.Entity.VIP;
import com.NipHotel.Service.VipService;

public class StaffVipAdd {

	public JFrame getVipAddFrame() {
		return VipAddFrame;
	}
	
	
	VIP v = new VIP();


	private JFrame VipAddFrame;
	private JTextField txtVipName;
	private JTextField txtVip_Tel;
	private JTextField txtCard;
	private JTable table;
	private String[] tColumns = {"Vip编号", "Vip卡号", "Vip姓名", "Vip生日", "Vip等级","Vip电话", "备注"};
	private JTextField txtLastPage;
	@SuppressWarnings("rawtypes")
	private JComboBox cboYear;
	@SuppressWarnings("rawtypes")
	private JComboBox cboMonth;
	@SuppressWarnings("rawtypes")
	private JComboBox cboDay;
	VipService vs=new VipService();
	Object[][] vob ;
	ArrayList<VIP> aL = new ArrayList<VIP>();
	@SuppressWarnings("rawtypes")
	private JComboBox cboLevel;
	private JTextPane txtRemark;
	private JTextField txtThisPage;

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
					StaffVipAdd window = new StaffVipAdd();
					window.VipAddFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StaffVipAdd() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		VipAddFrame = new JFrame();
		VipAddFrame.setTitle("会员注册界面");
		VipAddFrame.setBounds(100, 100, 922, 633);
		VipAddFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		VipAddFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JSplitPane sp1 = new JSplitPane();
		sp1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		VipAddFrame.getContentPane().add(sp1, BorderLayout.CENTER);
		
		JPanel pTop = new JPanel();
		sp1.setLeftComponent(pTop);
		pTop.setLayout(null);
		
		JLabel lblVipName = new JLabel("姓名：");
		lblVipName.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblVipName.setBounds(21, 27, 54, 15);
		pTop.add(lblVipName);
		
		txtVipName = new JTextField();
		txtVipName.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		txtVipName.setBounds(74, 25, 79, 21);
		pTop.add(txtVipName);
		txtVipName.setColumns(10);
		
		JLabel lblVip_Tel = new JLabel("电话：");
		lblVip_Tel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblVip_Tel.setBounds(241, 33, 54, 15);
		pTop.add(lblVip_Tel);
		
		txtVip_Tel = new JTextField();
	
		txtVip_Tel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		txtVip_Tel.setColumns(10);
		txtVip_Tel.setBounds(290, 27, 183, 21);
		pTop.add(txtVip_Tel);
		
		JLabel lblVip_BD = new JLabel("生日：");
		lblVip_BD.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblVip_BD.setBounds(505, 29, 54, 15);
		pTop.add(lblVip_BD);
		
		cboYear = new JComboBox();
		cboYear.setBounds(552, 27, 79, 21);
		pTop.add(cboYear);
		getYear();
		cboYear.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int year = Integer.parseInt(cboYear.getSelectedItem()
						.toString());
				int month = Integer.parseInt(cboMonth.getSelectedItem()
						.toString());
				getDay(year, month);
			
			}
		});
		
		
		
		cboMonth = new JComboBox();
		cboMonth.setBounds(662, 27, 54, 21);
		pTop.add(cboMonth);
		getMonth();
		cboMonth.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int year = Integer.parseInt(cboYear.getSelectedItem()
						.toString());
				int month = Integer.parseInt(cboMonth.getSelectedItem()
						.toString());
				
				getDay(year, month);

			

			}
		});
		
		JLabel lblYear = new JLabel("年");
		lblYear.setBounds(641, 30, 34, 15);
		pTop.add(lblYear);
		
		JLabel lblMonth = new JLabel("月");
		lblMonth.setBounds(726, 30, 34, 15);
		pTop.add(lblMonth);
		
		cboDay = new JComboBox();
		cboDay.setBounds(747, 27, 54, 21);
		getDay(Integer.parseInt(cboYear.getSelectedItem().toString()),
				Integer.parseInt(cboMonth.getSelectedItem().toString()));
		pTop.add(cboDay);
		JLabel lblDay = new JLabel("日");
		lblDay.setBounds(805, 30, 34, 15);
		pTop.add(lblDay);
		
		JLabel lblLevel = new JLabel("会员等级：");
		lblLevel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblLevel.setBounds(21, 63, 95, 15);
		pTop.add(lblLevel);
		
		cboLevel = new JComboBox();
		cboLevel.setModel(new DefaultComboBoxModel(new String[] {"", "白银", "黄金", "钻石"}));
		cboLevel.setFont(new Font("宋体", Font.PLAIN, 17));
		cboLevel.setBounds(104, 61, 87, 21);
		pTop.add(cboLevel);
		
		JLabel lblCard = new JLabel("卡号：");
		lblCard.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblCard.setBounds(480, 67, 54, 15);
		pTop.add(lblCard);
		
		txtCard = new JTextField();
		txtCard.setEditable(false);
		txtCard.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		txtCard.setColumns(10);
		txtCard.setBounds(547, 62, 79, 21);
		pTop.add(txtCard);
		
		JButton btnGetCard = new JButton("获取卡号");
		btnGetCard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag =false;
				int r;
				do{
					flag=false;
					r = (int)(Math.random()*10000+10000); 
				   flag=vs.isCardOk(r);
				   
				}while(flag);
				
				
				txtCard.setText(r+"");
	
			}
		});
		btnGetCard.setBounds(637, 61, 93, 23);
		pTop.add(btnGetCard);
		
		JButton btnOk = new JButton("确认");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				//电话号判断
				String Vip_Tel = "";
				 String mobilePhoneRegexp = "(?:(\\(\\+?86\\))((1[0-9]{1}[3]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|" +     
			                "(?:86-?((1[0-9]{1}[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})|" +
			                "(?:((1[0-9]{1}[0-9]{1})|(15[0-9]{1})|(18[0,5-9]{1}))+\\d{8})";

				Vip_Tel = txtVip_Tel.getText();
			    Matcher m=Pattern.compile(mobilePhoneRegexp).matcher(Vip_Tel);
			    if(m.matches()==false)
			    {
			    	JOptionPane.showMessageDialog(VipAddFrame, "电话号输入非法");
			    	return ;
			    }
				
				
			    else if(txtVipName.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(VipAddFrame, "姓名不可为空！");
					txtVipName.requestFocus();
					return ;
				}
			    else if(txtCard.getText().isEmpty())
				{
				JOptionPane.showMessageDialog(VipAddFrame, "卡号不可为空！");
				return ;
				}
				String vipName = txtVipName.getText();
				String vipBD = cboYear.getSelectedItem().toString()+"-"+cboMonth.getSelectedItem().toString()+"-"+cboDay.getSelectedItem().toString();
				int levelId = cboLevel.getSelectedIndex();
				int vipCard = Integer.parseInt(txtCard.getText());
				String remark = txtRemark.getText();
				
				//赋值
				VIP a = new VIP();
				a.setVIP_Name(vipName);
				a.setVIP_BD(vipBD);
				a.setVIP_Tel(Vip_Tel);
				a.setVIP_Level(levelId);
				a.setVIP_Card(vipCard);
				a.setRemark(remark);
				aL.add(a);
				//table显示
	            showInfo(aL);
				reset();
	
				
			}
		});
		btnOk.setBounds(104, 163, 93, 23);
		pTop.add(btnOk);
		
		JButton btnAdd = new JButton("提交");

		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(vs.addVipInfo(aL))
				{
					JOptionPane.showMessageDialog(null, "提交成功！");
					aL.clear();
				}
				else{
					JOptionPane.showMessageDialog(null, "提交失败！");
					
				}
			}
		});

		btnAdd.setBounds(437, 163, 93, 23);
		pTop.add(btnAdd);
		
		//添加暂时列表删除
		JButton btnDelete = new JButton("删除");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
					int columnId = table.getSelectedRow();
					if(aL.size()==1)
					{
						aL.clear();
					}
					else if(aL.size()==0)
					{
						JOptionPane.showMessageDialog(null, "还没有添加会员");
						return ;
					}
					else{
						aL.remove(columnId);
					}
					showInfo(aL);
	
			}
		});
		btnDelete.setBounds(705, 163, 93, 23);
		pTop.add(btnDelete);
		
		JLabel lblRemark = new JLabel("备注：");
		lblRemark.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblRemark.setBounds(21, 101, 95, 15);
		pTop.add(lblRemark);
		
		txtRemark = new JTextPane();
		txtRemark.setBounds(104, 91, 266, 45);
		pTop.add(txtRemark);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		sp1.setRightComponent(splitPane);
		
		JPanel pCenter = new JPanel();
		splitPane.setLeftComponent(pCenter);
		pCenter.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pCenter.add(scrollPane, BorderLayout.NORTH);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			vob,tColumns));
		
		JPanel pButtom = new JPanel();
		pButtom.setLayout(null);
		splitPane.setRightComponent(pButtom);
		
		JButton btnFirst = new JButton("首页");
		btnFirst.setFont(new Font("宋体", Font.PLAIN, 16));
		btnFirst.setBounds(84, 13, 93, 27);
		pButtom.add(btnFirst);
		
		JButton button_1 = new JButton("上一页");
		button_1.setFont(new Font("宋体", Font.PLAIN, 16));
		button_1.setBounds(228, 13, 93, 27);
		pButtom.add(button_1);
		
		JLabel label = new JLabel("/");
		label.setBounds(422, 20, 54, 15);
		pButtom.add(label);
		
		txtLastPage = new JTextField("1");
		txtLastPage.setEditable(false);
		txtLastPage.setColumns(10);
		txtLastPage.setBounds(432, 17, 66, 21);
		pButtom.add(txtLastPage);
		
		JButton btnNext = new JButton("下一页");
		btnNext.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNext.setBounds(529, 13, 93, 27);
		pButtom.add(btnNext);
		
		JButton btnLast = new JButton("尾页");
		btnLast.setFont(new Font("宋体", Font.PLAIN, 16));
		btnLast.setBounds(671, 13, 93, 27);
		pButtom.add(btnLast);
		
		txtThisPage = new JTextField("1");
		txtThisPage.setEditable(false);
		txtThisPage.setColumns(10);
		txtThisPage.setBounds(349, 17, 66, 21);
		pButtom.add(txtThisPage);
		splitPane.setDividerLocation(320);
		sp1.setDividerLocation(200);
		VipAddFrame.setLocationRelativeTo(null);
	}
	
	/**
	 * 显示刚刚输入的所有用户信息
	 * @param v
	 */
	public void showInfo(ArrayList<VIP> aL)
	{
		vob = new Object[aL.size()][7];
		for(int j = 0 ; j < aL.size() ; j++){
		vob[j][0] = aL.get(j).getVIP_ID();
		vob[j][1] = aL.get(j).getVIP_Card();
		vob[j][2] = aL.get(j).getVIP_Name();
		vob[j][3] = aL.get(j).getVIP_BD();
		vob[j][4] = aL.get(j).getVIP_Level();
		vob[j][5] = aL.get(j).getVIP_Tel();
		vob[j][6] = aL.get(j).getRemark();
		}
		DefaultTableModel model =new DefaultTableModel(vob,tColumns);
		table.setModel(model);
		
	}
	
	
	/**
	 * 获取年份
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getYear() {
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		ArrayList<Integer> years = new ArrayList<Integer>();
		for (int i = year; i >= year - 60; i--) {
			years.add(i);
		}
		// 设置距离现在60年的年份
		cboYear.setModel(new DefaultComboBoxModel(years.toArray()));

	}
	/**
	 * 获取月份
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getMonth() {
		ArrayList<Integer> months = new ArrayList<Integer>();
		for (int i = 1; i <= 12; i++) {
			months.add(i);
		}

		cboMonth.setModel(new DefaultComboBoxModel(months.toArray()));
	}


	/**
	 * 获取对应日
	 * 
	 * @param year
	 * @param month
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void getDay(int year, int month) {
		int day = 0;

		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 2:
			day = 28;
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
				day = 29;
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			day = 30;
			break;
		}

		ArrayList<Integer> days = new ArrayList<Integer>();
		for (int i = 1; i <= day; i++) {
			days.add(i);
		}

		cboDay.setModel(new DefaultComboBoxModel(days.toArray()));
	}
	
	/**
	 * 重置
	 */
	public void reset()
	{
		txtVipName.setText("");
		txtVip_Tel.setText("");
		cboLevel.setSelectedIndex(0);
		txtCard.setText("");
		txtRemark.setText("");
	}
}