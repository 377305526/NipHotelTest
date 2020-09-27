
package com.NipHotel.GUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;



public class StaffMainMenu {

	private JFrame stafFrame;
	private JPanel panel;
	private RoomMain rm;
	private int staffId;
	
	public JFrame getStafFrame() {
		
		return stafFrame;
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
					StaffMainMenu window = new StaffMainMenu();
					window.stafFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StaffMainMenu() {
		initialize();
		 //构造方法里的把组件添加进去
	}
	public StaffMainMenu(int staffId) {
		this.staffId = staffId ;
		initialize();
		//构造方法里的把组件添加进去
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		stafFrame = new JFrame();
		stafFrame.setResizable(false);
		stafFrame.setTitle("酒店管理员系统");
		stafFrame.setBounds(100, 100, 1622, 980);
		stafFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		stafFrame.setLocationRelativeTo(null);
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setPreferredSize(new Dimension(0, 30));
		stafFrame.setJMenuBar(menuBar);
		
		JMenu mVip = new JMenu("会员信息管理");
		mVip.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		mVip.setHorizontalTextPosition(SwingConstants.CENTER);
		mVip.setHorizontalAlignment(SwingConstants.CENTER);
		mVip.setPreferredSize(new Dimension(150, 22));
		menuBar.add(mVip);
		
		JMenuItem miSelectVip = new JMenuItem("会员信息查询");
		miSelectVip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StaffVipSelect svs = new StaffVipSelect();
				svs.getStaffVipFrame().setVisible(true);
	
				
				
				
			}
		});
		miSelectVip.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mVip.add(miSelectVip);
		
		JMenuItem miAddVip = new JMenuItem("会员信息新增");
		miAddVip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StaffVipAdd add = new StaffVipAdd();
				add.getVipAddFrame().setVisible(true);
				
			}
		});
		miAddVip.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mVip.add(miAddVip);
		
		JMenuItem miUpdateVip = new JMenuItem("会员信息修改");
		miUpdateVip.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StaffVipOpr vo = new StaffVipOpr();
				vo.getStaffVipOpr().setVisible(true);
			}
		});
		miUpdateVip.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mVip.add(miUpdateVip);
		
		JMenu mRoom = new JMenu("房间信息管理");
		mRoom.setPreferredSize(new Dimension(150, 22));
		mRoom.setHorizontalTextPosition(SwingConstants.CENTER);
		mRoom.setHorizontalAlignment(SwingConstants.CENTER);
		mRoom.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		menuBar.add(mRoom);
		
		JMenuItem miSelectRoom = new JMenuItem("房间信息查询");
		miSelectRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RoomSelect rs = new RoomSelect();
				rs.getRoomSelectFrame().setVisible(true);
				
			}
		});
		miSelectRoom.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mRoom.add(miSelectRoom);
		
		JMenu mOrders = new JMenu("账单信息管理");
		mOrders.setPreferredSize(new Dimension(150, 22));
		mOrders.setHorizontalTextPosition(SwingConstants.CENTER);
		mOrders.setHorizontalAlignment(SwingConstants.CENTER);
		mOrders.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		menuBar.add(mOrders);
		
		JMenuItem menuItem_2 = new JMenuItem("账单信息查询");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StaffOrderOpr so = new StaffOrderOpr ();
				so.getStaffOderOpr().setVisible(true);
			}
		});
		menuItem_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		mOrders.add(menuItem_2);
		stafFrame.getContentPane().setLayout(new CardLayout(0, 0));
		
		panel = new JPanel();
		
		stafFrame.getContentPane().add(panel, "name_42111913914762");
		panel.setLayout(new CardLayout(0, 0));
		 rm = new RoomMain();
		 rm.getRoomFrame(staffId);
		panel.add(rm.splitPane);

		panel.setVisible(true);
		
	}

}
