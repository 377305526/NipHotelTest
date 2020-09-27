package com.NipHotel.GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import com.NipHotel.Entity.VIP;
import com.NipHotel.Service.VipService;

public class StaffVipOpr {

	private JFrame StaffVipOpr;
	private JTable tVip;
	private String[] tColumns = { "Vip编号", "Vip卡号", "Vip姓名", "Vip生日", "Vip等级",
			"Vip电话", "备注" };
	private JTextField txtVip;
	VipService vs = new VipService();
	ArrayList<VIP> vL = vs.allVipInfo();
	private JComboBox<String> cboVip;
	int row = 0;
	Object[][] vipInfo;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
					// UIManager.setLookAndFeel("org.jvnet.substance.painter.border.ClassicInnerBorderPainter");
					// SubstanceLookAndFeel.setCurrentTitlePainter(new
					// FlatBorderPainter());
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
					StaffVipOpr window = new StaffVipOpr();
					window.StaffVipOpr.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StaffVipOpr() {
		initialize();
	}

	public JFrame getStaffVipOpr() {
		return StaffVipOpr;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		StaffVipOpr = new JFrame();
		StaffVipOpr.setTitle("会员信息处理");
		StaffVipOpr.setBounds(100, 100, 977, 653);
		StaffVipOpr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		StaffVipOpr.setLocationRelativeTo(null);

		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		StaffVipOpr.getContentPane().add(splitPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(scrollPane, popupMenu);

		JMenuItem miFresh = new JMenuItem("刷新");
		popupMenu.add(miFresh);

		JMenuItem miUp = new JMenuItem("提交");
		popupMenu.add(miUp);

		tVip = new JTable();
		tVip.setModel(new DefaultTableModel(vipInfo, tColumns));

		scrollPane.setViewportView(tVip);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		splitPane.setLeftComponent(panel_1);

		JLabel lblVip = new JLabel("会员：");
		lblVip.setFont(new Font("微软雅黑", Font.BOLD, 20));
		lblVip.setBounds(116, 23, 75, 36);
		panel_1.add(lblVip);

		txtVip = new JTextField();
		txtVip.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		txtVip.setColumns(10);
		txtVip.setBounds(231, 23, 191, 36);
		panel_1.add(txtVip);

		cboVip = new JComboBox<String>();
		cboVip.setModel(new DefaultComboBoxModel(new String[] {"卡号", "姓名", "手机号"}));
		cboVip.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		cboVip.setBounds(480, 25, 127, 32);

		panel_1.add(cboVip);

		JButton btnOk = new JButton("确认");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = cboVip.getSelectedIndex();
				String txt = txtVip.getText();
				if (i == 0) {
					@SuppressWarnings("unused")
					int card = 0;
					try {

						card = Integer.parseInt(txt);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "会员号输入非法");
						txtVip.selectAll();
						txtVip.requestFocus();
						return;
					}
				}
				if (vs.oneVipInfo(i, txt).size() == 0) {
					JOptionPane.showMessageDialog(null, "没有该会员请重新输入");
					txtVip.selectAll();
				} else {
					
					OneVipInfo(vs.oneVipInfo(i, txt));
				}

			}
		});
		btnOk.setFont(new Font("宋体", Font.PLAIN, 18));
		btnOk.setBounds(116, 90, 119, 36);
		panel_1.add(btnOk);

		JButton btnCancel = new JButton("取消");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtVip.setText("");
			}
		});
		btnCancel.setFont(new Font("宋体", Font.PLAIN, 18));
		btnCancel.setBounds(410, 90, 119, 36);
		panel_1.add(btnCancel);

		JButton btnUp = new JButton("提交");
		
		btnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Update();
			}
		});
		btnUp.setFont(new Font("宋体", Font.PLAIN, 18));
		btnUp.setBounds(713, 90, 119, 36);
		panel_1.add(btnUp);
		splitPane.setDividerLocation(150);
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	/**
	 * 条件查询
	 * 
	 * @param al
	 */
	public void OneVipInfo(ArrayList<VIP> al) {
		row = al.size();
		vipInfo = new Object[row][7];
		for (int i = 0; i < row; i++) {
			vipInfo[i][0] = al.get(i).getVIP_ID();
			vipInfo[i][1] = al.get(i).getVIP_Card();
			vipInfo[i][2] = al.get(i).getVIP_Name();
			vipInfo[i][3] = al.get(i).getVIP_BD();
			vipInfo[i][4] = al.get(i).getVIP_Level();
			vipInfo[i][5] = al.get(i).getVIP_Tel();
			vipInfo[i][6] = al.get(i).getRemark();
		}

		DefaultTableModel model = new DefaultTableModel(vipInfo, tColumns);
		tVip.setModel(model);

	}
	/**
	 * 得到查出的vipInfo数组
	 * @param al
	 * @return
	 */
	public Object[][] changeVipInfo(ArrayList<VIP> al) {
		row = al.size();
		vipInfo = new Object[row][7];
		for (int i = 0; i < row; i++) {
			vipInfo[i][0] = al.get(i).getVIP_ID();
			vipInfo[i][1] = al.get(i).getVIP_Card();
			vipInfo[i][2] = al.get(i).getVIP_Name();
			vipInfo[i][3] = al.get(i).getVIP_BD();
			vipInfo[i][4] = al.get(i).getVIP_Level();
			vipInfo[i][5] = al.get(i).getVIP_Tel();
			vipInfo[i][6] = al.get(i).getRemark();
		}

		return vipInfo;
	}
	public void Update(){
		// 获取所有表格model值							
		for (int i = 0; i < tVip.getRowCount(); i++) {	 
			VIP v = new VIP();
			v.setVIP_ID(Integer.parseInt(vipInfo[i][0].toString()));
			v.setVIP_Card(Integer.parseInt(vipInfo[i][1].toString()));				
			v.setVIP_Name(tVip.getValueAt(i, 2).toString());
			v.setVIP_BD(tVip.getValueAt(i, 3).toString());
			v.setVIP_Level(Integer.parseInt(vipInfo[i][4].toString()));
			v.setVIP_Tel(tVip.getValueAt(i, 5).toString());
			v.setRemark(tVip.getValueAt(i, 6).toString());
			if (vs.updateVip(v) == true) {
								
				JOptionPane.showMessageDialog(null, "修改成功");
				
			}else{
				JOptionPane.showMessageDialog(null, "修改失败");
				
			}			
		}
	}
}
