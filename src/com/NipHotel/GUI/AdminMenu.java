package com.NipHotel.GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
/**
 * 超级管理员登录主界面 
 * @author   Nippppp
 *
 */
public class AdminMenu {

	private JFrame frame;
	private JTree tree;
	private JPanel panel;
	private CardLayout cardLayOut  = new CardLayout(0, 0);
	private JLabel lblRoot;
			
	public JFrame getAdminMenu()
	{
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
					AdminMenu window = new AdminMenu();
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
	public AdminMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1071, 624);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setLocationRelativeTo(null);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, "name_410688678580500");
		
		panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(cardLayOut);
		
		tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("后台信息管理") {
				{
					DefaultMutableTreeNode node_1;
					add(new DefaultMutableTreeNode("会员信息管理"));
					add(new DefaultMutableTreeNode("订单信息管理"));
					add(new DefaultMutableTreeNode("顾客信息管理"));
					node_1 = new DefaultMutableTreeNode("前台账户管理");
						node_1.add(new DefaultMutableTreeNode("前台用户操作"));
						node_1.add(new DefaultMutableTreeNode("新增员工"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("房间信息管理");
						node_1.add(new DefaultMutableTreeNode("房间信息处理"));
						node_1.add(new DefaultMutableTreeNode("新增房间"));
					add(node_1);
					add(new DefaultMutableTreeNode("退出"));
				}
			}
		));
		splitPane.setLeftComponent(tree);
		splitPane.setDividerLocation(150);
		
		lblRoot = new JLabel("欢迎登陆闻闻的旅馆-酒店后台系统");
		
		//测试鼠标事件
		lblRoot.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseExited(MouseEvent e) {
			
					lblRoot.setForeground(Color.GREEN);

			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRoot.setForeground(Color.RED);
			}
			
		});
		lblRoot.setForeground(new Color(0, 0, 0));
		lblRoot.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoot.setFont(new Font("宋体", Font.PLAIN, 35));
		panel.add(lblRoot, "lblRoot");
		panel.add(new AdmRoomOpr().sp1, "room"); 
		panel.add(new AdmVipMenu().sp1,"vip");
		panel.add(new AdmOrderMenu().splitPane , "orders");
		panel.add(new AdmUserMenu().splitPane,"userinfos");
		panel.add(new AdmStaffMenu().sp1 , "staff");

		

		
		
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)(e.getPath().getLastPathComponent());
				
				String nodeName = node.getUserObject().toString();
				if(nodeName.equals("会员信息管理"))
				{
					cardLayOut.show(panel, "vip");
				}
				if(nodeName.equals("顾客信息管理"))
				{
					cardLayOut.show(panel, "userinfos");
				}
				if(nodeName.equals("房间信息处理"))
				{
					cardLayOut.show(panel, "room");
				}
				if(nodeName.equals("订单信息管理"))
				{
					cardLayOut.show(panel, "orders");
				}
				if(nodeName.equals("退出"))
				{
					frame.dispose();
				}
				
				if(nodeName.equals("新增房间"))
				{
					AdmAddRoom ar = new AdmAddRoom();
					ar.getAdmAddRoom().setVisible(true);
				}
				
				if(nodeName.equals("前台用户操作"))
				{
					cardLayOut.show(panel, "staff");
				}
				if(nodeName.equals("新增员工"))
				{
					AdmAddStaff as = new AdmAddStaff();
					as.getAdmAddRoom().setVisible(true);
				}
				
		
				
			}
		});
	}

}
