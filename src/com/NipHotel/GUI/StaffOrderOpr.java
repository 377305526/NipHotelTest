package com.NipHotel.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.NipHotel.Entity.Orders;
import com.NipHotel.Service.OrdersService;

public class StaffOrderOpr {

	
	private JFrame frame;
	private JTable table;
	private JTextField textdi;
	private JTextField textye;
	private JButton btnquery;
	private JTextField txtInput;
	String[] columns = new String[] {"订单编号", "顾客编号", "工作人员编号", "房间号", "下单时间", "押金", "其他"};
	Object[][] orders = null ;
	ArrayList<Orders> oL = new ArrayList<Orders>();
	OrdersService os = new OrdersService();
	JComboBox<String> cboType;
	private JButton btnprep;
	private JButton btnTopp;
	private JButton btnNextp;
	private JButton btnBackp;
	int thisPage = 1 ;
	int lastPage = 1 ;
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
					StaffOrderOpr window = new StaffOrderOpr();
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
	public StaffOrderOpr() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("订单界面");
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
				btnprep.setEnabled(true);
				btnNextp.setEnabled(true);
				btnTopp.setEnabled(true);
				btnBackp.setEnabled(true);
				if(txtInput.getText().isEmpty())
				{
					oL=os.limitSelectOrders(thisPage, Integer.parseInt(cboPageSize.getSelectedItem().toString()));
					thisPage=1 ;
					
					int a = os.getAllOrders().size()% Integer.parseInt(cboPageSize.getSelectedItem().toString());
					if(a>0)
					{
						lastPage =os.getAllOrders().size()/ Integer.parseInt(cboPageSize.getSelectedItem().toString())+1;
					}
					else{
						lastPage = os.getAllOrders().size()/ Integer.parseInt(cboPageSize.getSelectedItem().toString());
					}
					textdi.setText(thisPage+"");
					textye.setText(lastPage+"");
					showOrders(oL);
				}
				else{
					oL.clear();
					int choose = cboType.getSelectedIndex() ;
					String input = txtInput.getText();
					try{
					oL=os.someOrders(choose, input);
					}catch(NumberFormatException e1)
					{
						JOptionPane.showMessageDialog(null, "房间号输入非法！");
						txtInput.requestFocus();
						txtInput.selectAll();
						return;
					}
					if(oL.size()==0)
					{
						JOptionPane.showMessageDialog(null, "没有该订单信息");
						txtInput.requestFocus();
						txtInput.selectAll();
					}
					showOrders(oL);

				}
			}
		});
		
		btnquery.setBounds(590, 58, 115, 35);
		panel.add(btnquery);
		
		txtInput = new JTextField();
		txtInput.setBounds(222, 58, 167, 35);
		panel.add(txtInput);
		txtInput.setColumns(10);
		
		cboType = new JComboBox<String>();
		cboType.setModel(new DefaultComboBoxModel(new String[] {"房间号", "顾客姓名", "电话号"}));
		cboType.setBounds(403, 65, 104, 24);
		panel.add(cboType);
		
		splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);
		
		panel_1 = new JPanel();
		splitPane_1.setLeftComponent(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(orders,columns));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("New button");
		scrollPane.setColumnHeaderView(btnNewButton);
		
		panel_2 = new JPanel();
		splitPane_1.setRightComponent(panel_2);
		panel_2.setLayout(null);
		
		btnprep = new JButton("上一页");
		btnprep.setEnabled(false);
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
					oL=os.limitSelectOrders(thisPage, Integer.parseInt(cboPageSize.getSelectedItem().toString()));
					showOrders(oL);
				}
				}
			}
		});
		btnprep.setBounds(186, 10, 95, 25);
		panel_2.add(btnprep);
		
		btnTopp = new JButton("首页");
		btnTopp.setEnabled(false);
		btnTopp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtInput.getText().isEmpty()){
						thisPage=1;
						textdi.setText(thisPage+"");
						oL=os.limitSelectOrders(thisPage, Integer.parseInt(cboPageSize.getSelectedItem().toString()));
						showOrders(oL);
					
					}
			}
		});
		btnTopp.setBounds(53, 10, 95, 25);
		panel_2.add(btnTopp);
		
		btnNextp = new JButton("下一页");
		btnNextp.setEnabled(false);
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
						oL=os.limitSelectOrders(thisPage, Integer.parseInt(cboPageSize.getSelectedItem().toString()));
						showOrders(oL);
					}
					}
				}
			
		});
		btnNextp.setBounds(453, 10, 95, 25);
		panel_2.add(btnNextp);
		
		btnBackp = new JButton("尾页");
		btnBackp.setEnabled(false);
		btnBackp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtInput.getText().isEmpty()){
					thisPage=lastPage;
					oL=os.limitSelectOrders(thisPage, Integer.parseInt(cboPageSize.getSelectedItem().toString()));
					textdi.setText(thisPage+"");
					showOrders(oL);
				
				}
			}
		});
		btnBackp.setBounds(558, 10, 95, 25);
		panel_2.add(btnBackp);
		
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

	public JFrame getStaffOderOpr() {
		return frame;
	}
	
	/**
	 * 显示到table上
	 * @param aoL
	 */
	public void showOrders(ArrayList<Orders> aoL)
	{
		orders = new Object[aoL.size()][7];
		for(int i = 0 ; i < aoL.size() ; i++)
		{
			orders[i][0] = aoL.get(i).getOrder_ID();
			orders[i][1] = aoL.get(i).getUser_ID();
			orders[i][2] = aoL.get(i).getStaff_ID();
			orders[i][3] = aoL.get(i).getRoom_ID();
			orders[i][4] = aoL.get(i).getOrder_Time();
			orders[i][5] = aoL.get(i).getOrder_Front();
			orders[i][6] = aoL.get(i).getRemark();
		}
	
		
		table.setModel(new DefaultTableModel(orders,columns));
		
	}
	
	
}
