package com.NipHotel.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

import com.NipHotel.Entity.Staff;
import com.NipHotel.Service.OrdersService;
import com.NipHotel.Service.StaffService;

public class AdmStaffMenu {

	private JFrame AdmRoomOpr;
	private JTable tRoom;
	private JTextField txtThisPage;
	private JTextField txtAllPage;
	public JSplitPane sp1;
	private String[] tColumns = {"员工账号", "员工姓名", "员工性别", "员工电话", "员工密码","备注"};
	int row = 0 ;
	int count = 1;
	Object[][] staffInfo ;
	StaffService ss = new StaffService();
	@SuppressWarnings("unused")
	private JComboBox<String> cboType;
	ArrayList<Staff> sL = new ArrayList<Staff>();
	OrdersService os = new OrdersService();
	int thisPage = 1 ;
	int lastPage = 1 ;
	private JButton btnFirstPage;
	private JButton btnPriPage;
	private JButton btnNextPage;
	private JButton btnAllPage;
	int pageSize = 10 ;//页面容量 
	private JButton btnDelete;
	private JButton btnUpdate;
	private JScrollPane spVip;
	private JTextField txtInput;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JButton btnOk;
	

	public JFrame getAdmRoomOprFrame() {
		return AdmRoomOpr;
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
					
					AdmStaffMenu window = new AdmStaffMenu();
					window.AdmRoomOpr.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public AdmStaffMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		AdmRoomOpr = new JFrame();
		AdmRoomOpr.setTitle("员工信息操作");
		AdmRoomOpr.setResizable(false);
		AdmRoomOpr.setBounds(100, 100, 809, 602);
		AdmRoomOpr.getContentPane().setLayout(new BorderLayout(0, 0));
		AdmRoomOpr.setLocationRelativeTo(null);
		AdmRoomOpr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		sp1 = new JSplitPane();
		sp1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		AdmRoomOpr.getContentPane().add(sp1, BorderLayout.CENTER);
		
		JPanel pTop = new JPanel();
		sp1.setLeftComponent(pTop);
		pTop.setLayout(null);
		
		
		tRoom = new JTable();
		tRoom.setModel(new DefaultTableModel(staffInfo ,tColumns));
		
		JButton btnCancle = new JButton("取消");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtInput.setText("");				
				comboBox.setSelectedIndex(0);
			}
		});
		btnCancle.setFont(new Font("宋体", Font.PLAIN, 18));
		btnCancle.setBounds(595, 103, 119, 36);
		pTop.add(btnCancle);
		
		//分页查询
		JButton btnAll = new JButton("查询");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				thisPage=1;
				sL = ss.getAllStaff();
				if(sL.size()%pageSize>0)
				{
					lastPage=(sL).size()/pageSize+1;
				}else{
					lastPage=(sL).size()/pageSize;
				}
				txtThisPage.setText(thisPage+"");
				txtAllPage.setText(lastPage+"");
				tRoom.setModel(StaffInfo(sL));
			}
		});
		btnAll.setFont(new Font("宋体", Font.PLAIN, 18));
		btnAll.setBounds(430, 103, 119, 36);
		pTop.add(btnAll);
		
		btnDelete = new JButton("删除");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int id =tRoom.getSelectedRow();
				if(JOptionPane.showConfirmDialog(null, "是否删除"+staffInfo[id][1])==0)
				{
					if(ss.deleteStaff( (int) staffInfo[id][0])==true)
					{
						JOptionPane.showMessageDialog(null, "删除成功！");
					}	
					else{
						int i = JOptionPane.showConfirmDialog(null, "该员工还拥有一大笔订单是否一同删除？");
						
						if(i==0)
						{
							if(os.deleteOrderByStaff((int) staffInfo[id][0])==true)
							{
								ss.deleteStaff( (int) staffInfo[id][0]);
								JOptionPane.showMessageDialog(null, "删除成功！ 员工与相关账单均已删除！");
							}
						}
						else{
							
							return ;
						}
						
					}
				}
				
				
			}
		});
		btnDelete.setFont(new Font("宋体", Font.PLAIN, 18));
		btnDelete.setBounds(39, 103, 119, 36);
		pTop.add(btnDelete);
		
		btnUpdate = new JButton("提交");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{			
			
				try{
				for (int i = 0; i < tRoom.getRowCount(); i++) 
				{	
					Staff s = new Staff();

					s.setStaff_ID((Integer.parseInt(tRoom.getValueAt(i, 0).toString())));
					s.setStaff_Name(tRoom.getValueAt(i, 1).toString());
					s.setStaff_Sex(tRoom.getValueAt(i, 2).toString());
					s.setStaff_Tel(tRoom.getValueAt(i, 3).toString());
					s.setStaff_PWD(tRoom.getValueAt(i, 4).toString());
					String remark=(String)tRoom.getValueAt(i, 5);
					if(remark==null)remark="";
					
					s.setRemark(remark);
					sL.add(s);
				}
				}catch(NumberFormatException e1)
				{
					JOptionPane.showMessageDialog(null, "修改了非法数值！");
				}
				
				if(ss.updateStaff(sL)==true)
				{
					JOptionPane.showMessageDialog(null, "修改成功！");
				}
				else{
					JOptionPane.showMessageDialog(null, "修改失败 可能修改了非法值");
				}
			}
		});
		btnUpdate.setFont(new Font("宋体", Font.PLAIN, 18));
		btnUpdate.setBounds(229, 103, 119, 36);
		pTop.add(btnUpdate);
		
		txtInput = new JTextField();
		txtInput.setBounds(39, 29, 204, 21);
		pTop.add(txtInput);
		txtInput.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"员工姓名", "员工电话"}));
		comboBox.setBounds(253, 29, 95, 21);
		pTop.add(comboBox);
		
		btnOk = new JButton("确定");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sL.clear();
				String input = txtInput.getText();
				if(txtInput.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "单个查询不可为空");
					txtInput.selectAll();
					txtInput.requestFocus();
					return ;
				}
				if(comboBox.getSelectedItem().equals("员工姓名"))
				{
			
					sL.add(ss.getOneStaff(input, 0));
					if(sL.isEmpty()){
						JOptionPane.showMessageDialog(null, "没有该员工");
						return;
					}
				
					
				}
				if(comboBox.getSelectedItem().equals("员工电话"))
				{
					
					sL.add(ss.getOneStaff(input, 1));
					if(sL.isEmpty()){
						JOptionPane.showMessageDialog(null, "没有该员工");
						return;
					}
					StaffInfo(sL);
					
				}
				tRoom.setModel(StaffInfo(sL));
			}
		});
		btnOk.setFont(new Font("宋体", Font.PLAIN, 18));
		btnOk.setBounds(375, 19, 80, 36);
		pTop.add(btnOk);
		
		JSplitPane sp2 = new JSplitPane();
		sp2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		sp1.setRightComponent(sp2);
		
		JPanel pCenter = new JPanel();
		sp2.setLeftComponent(pCenter);
		pCenter.setLayout(new BorderLayout(0, 0));
		
		spVip = new JScrollPane();
		pCenter.add(spVip);
	
		spVip.setViewportView(tRoom);

		
		JPanel pButtom = new JPanel();
		sp2.setRightComponent(pButtom);
		pButtom.setLayout(null);
		
		btnFirstPage = new JButton("首页");
		btnFirstPage.setFont(new Font("宋体", Font.PLAIN, 16));
		btnFirstPage.setBounds(38, 11, 93, 27);
		pButtom.add(btnFirstPage);
		
		btnPriPage = new JButton("上一页");

	
		btnPriPage.setFont(new Font("宋体", Font.PLAIN, 16));
		btnPriPage.setBounds(182, 11, 93, 27);
		pButtom.add(btnPriPage);
		
		txtThisPage = new JTextField();
	
		txtThisPage.setBounds(313, 11, 56, 21);
		pButtom.add(txtThisPage);
		txtThisPage.setColumns(10);
		txtThisPage.setText(1+"");
		
		JLabel lblCut = new JLabel("/");
		lblCut.setBounds(379, 14, 54, 15);
		pButtom.add(lblCut);
		
		txtAllPage = new JTextField();
		txtAllPage.setEditable(false);
		txtAllPage.setBounds(389, 11, 66, 21);
		pButtom.add(txtAllPage);
		txtAllPage.setColumns(10);
		txtAllPage.setText(1+"");
		
		btnNextPage = new JButton("下一页");

		
		btnNextPage.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNextPage.setBounds(483, 11, 93, 27);
		pButtom.add(btnNextPage);
		
		btnAllPage = new JButton("尾页");
		btnAllPage.setFont(new Font("宋体", Font.PLAIN, 16));
		btnAllPage.setBounds(625, 11, 93, 27);
		pButtom.add(btnAllPage);
		sp2.setDividerLocation(360);
		sp1.setDividerLocation(150);
	}
	
	/**
	 * 查询所有房间的分页查询
	 */
	public void limitSelect(){
		btnFirstPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisPage=1;
				txtThisPage.setText(thisPage+"");
				tRoom.setModel(StaffInfo(sL));
			}
		});
		btnPriPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(thisPage==1)
				{
					return ;
				}
				else
				{
					thisPage-=1;
					txtThisPage.setText(thisPage+"");
					txtThisPage.setText(thisPage+"");
					txtAllPage.setText(lastPage+"");
					tRoom.setModel(StaffInfo(sL));
				}

			}
		});
		btnNextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(thisPage == lastPage)
				{
					return ;
				}else{
					thisPage+=1;
					txtThisPage.setText(thisPage+"");
					tRoom.setModel(StaffInfo(sL));
				}
	
			}
		});
		btnAllPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
				thisPage=lastPage;
				txtThisPage.setText(thisPage+"");
				tRoom.setModel(StaffInfo(sL));
			}
		});
	}

	
	/**
	 * 将传入的员工对象数组设置model
	 * @param al
	 */
	public DefaultTableModel StaffInfo(ArrayList<Staff> al)
	{
		row =  al.size();
		staffInfo = new Object[row][tColumns.length];
		for(int i = 0 ; i <  row ; i++)
		{
			staffInfo[i][0]= al.get(i).getStaff_ID();
			staffInfo[i][1]= al.get(i).getStaff_Name();
			staffInfo[i][2]= al.get(i).getStaff_Sex();
			staffInfo[i][3]= al.get(i).getStaff_Tel();
			staffInfo[i][4]= al.get(i).getStaff_PWD();
			staffInfo[i][5]= al.get(i).getRemark();
		}
		
		DefaultTableModel model =new DefaultTableModel(staffInfo,tColumns);
		return model ;
	}


}
