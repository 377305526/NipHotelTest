package com.NipHotel.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.NipHotel.Entity.Room;
import com.NipHotel.Service.RoomService;

import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RoomMain {

	private JFrame RoomFrame;
	public JPanel panel;
	private JPanel panel_1;
	public JSplitPane splitPane;
	private JLabel lblRoomRemark;
	private JLabel lblRemark;
	private JLabel lblState;
	private JLabel lblRoomState;
	private JLabel lblSize;
	private JLabel lblRoomSize;
	private JLabel lblPrice;
	private JLabel lblRoomPrice;
	private JLabel lblType;
	private JLabel lblRoomType;
	private JLabel lblId;
	private JLabel lblRoomId;
	RoomService rs=new RoomService();
	ArrayList<Room> rL =rs.allRoom();
	private JLabel lblImg;
	private   int staffId;
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
					RoomMain window = new RoomMain();
					window.RoomFrame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RoomMain() {

		initialize();
	}
	
	public Frame getRoomFrame(int staffId)
	{
		this.staffId = staffId;
		return RoomFrame ;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		RoomFrame = new JFrame();
		RoomFrame.getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==117)
				{
					
					panel.removeAll();
					getRoom();
				}
			}
		});
		RoomFrame.setEnabled(false);
		RoomFrame.setTitle("房间情况");
		RoomFrame.setBounds(100, 100, 1646, 1100);
		RoomFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RoomFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		RoomFrame.getContentPane().add(splitPane, BorderLayout.CENTER);
		panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(null);
		panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		panel_1.setLayout(null);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(panel_1, popupMenu);
		
		JMenuItem mntmFlash = new JMenuItem("刷新");
		mntmFlash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					panel.removeAll();
					getRoom();
					 
				
			}
		});
		popupMenu.add(mntmFlash);
		
		lblRoomId = new JLabel("房间号：");
		lblRoomId.setFont(new Font("宋体", Font.PLAIN, 17));
		lblRoomId.setBounds(10, 25, 77, 33);
		panel_1.add(lblRoomId);
		
		lblId = new JLabel("");
		lblId.setFont(new Font("宋体", Font.PLAIN, 17));
		lblId.setBounds(75, 25, 77, 33);
		panel_1.add(lblId);
		
		lblRoomType = new JLabel("房间类型：");
		lblRoomType.setFont(new Font("宋体", Font.PLAIN, 17));
		lblRoomType.setBounds(10, 60, 94, 33);
		panel_1.add(lblRoomType);
		
		lblType = new JLabel("");
		lblType.setFont(new Font("宋体", Font.PLAIN, 17));
		lblType.setBounds(85, 60, 124, 33);
		panel_1.add(lblType);
		
		lblRoomPrice = new JLabel("房间价格：");
		lblRoomPrice.setFont(new Font("宋体", Font.PLAIN, 17));
		lblRoomPrice.setBounds(10, 97, 94, 33);
		panel_1.add(lblRoomPrice);
	
		lblPrice = new JLabel("");
		lblPrice.setFont(new Font("宋体", Font.PLAIN, 17));
		lblPrice.setBounds(85, 97, 124, 33);
		panel_1.add(lblPrice);
		
		lblRoomSize = new JLabel("房间大小：");
		lblRoomSize.setFont(new Font("宋体", Font.PLAIN, 17));
		lblRoomSize.setBounds(10, 137, 94, 33);
		panel_1.add(lblRoomSize);
		
		lblSize = new JLabel("");
		lblSize.setFont(new Font("宋体", Font.PLAIN, 17));
		lblSize.setBounds(85, 137, 124, 33);
		panel_1.add(lblSize);
		 getRoom();
		lblRoomState = new JLabel("入住情况：");
		lblRoomState.setFont(new Font("宋体", Font.PLAIN, 17));
		lblRoomState.setBounds(10, 177, 94, 33);
		panel_1.add(lblRoomState);
		
		lblState = new JLabel("");
		lblState.setFont(new Font("宋体", Font.PLAIN, 17));
		lblState.setBounds(85, 177, 124, 33);
		panel_1.add(lblState);
		
		lblRemark = new JLabel("备注：");
		lblRemark.setFont(new Font("宋体", Font.PLAIN, 17));
		lblRemark.setBounds(10, 205, 94, 33);
		panel_1.add(lblRemark);
		
		lblRoomRemark = new JLabel("");
		lblRoomRemark.setFont(new Font("宋体", Font.PLAIN, 17));
		lblRoomRemark.setBounds(85, 205, 169, 33);
		panel_1.add(lblRoomRemark);
		
		lblImg = new JLabel("");
		lblImg.setBounds(0, 250, 424,453);
		panel_1.add(lblImg);
		
		
		
	
		splitPane.setDividerLocation(1200);
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
	 * 获取其中一件房间的信息
	 * @param btn
	 */
	public void btnGetInfo(final JButton btn)
	{
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				
				int ID = Integer.parseInt(btn.getText());
				try {
					Room a = rs.roomInfo(ID);

					lblId.setText(a.getRoom_ID() + "");
					lblType.setText(a.getRoom_Type());
					lblPrice.setText(a.getRoom_price()+"");
					lblSize.setText(a.getRoom_Size()+"");
					lblState.setText(a.getRoom_State());
					lblRoomRemark.setText(a.getRemark());
					String img ="";
					if((lblType.getText()).equals("激情双人房")){
						img = "../imags/2.jpg";
					}else if((lblType.getText()).equals("大床房")){
						img = "../imags/3.jpg";
					}else if((lblType.getText()).equals("豪华标间")){
						img = "../imags/4.jpg";
					}else if((lblType.getText()).equals("舒适单间")){
						img = "../imags/6.jpg";
					}else if((lblType.getText()).equals("豪华大床房")){
						img = "../imags/7.jpg";
					}else if((lblType.getText()).equals("舒适标间")){
						img = "../imags/8.jpg";
					}else if((lblType.getText()).equals("豪华单间")){
						img = "../imags/9.jpg";
					}
					ImageIcon ii = new ImageIcon(RoomMain.class.getResource(img));
					lblImg.setIcon(ii);
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	
	
	/**
	 * 按钮封装 
	 * @param btn
	 */
	public void btnChange(final JButton btn ,final JMenuItem ji , final JMenuItem out)
	{
		
		//未入住 btnRoom201.setBackground(new Color(102, 255, 153));
		//	 鼠标事件颜色 btnRoom201.setBackground(Color.PINK);
		
		//已入住   初始颜色  	btnRoom203.setBackground(new Color(153, 0, 102));
		//     鼠标事件颜色 btnRoom203.setBackground(new Color(255, 51, 102));

		try {
			Room a = new Room ();
			a =rs.roomInfo(Integer.parseInt(btn.getText()));
			if(a.getRoom_State().equals("未入住"))
			{
				btn.setBackground(new Color(102, 255, 153));
				btn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btn.setBackground(Color.PINK);
						
					}
					@Override
					public void mouseExited(MouseEvent e) {
						btn.setBackground(new Color(102, 255, 153));
					}
				});
				ji.setText("登记入住");
				ji.setEnabled(true);
				out.setEnabled(false);
				
			}
			else{
				btn.setBackground(new Color(255, 51, 102));
				btn.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						btn.setBackground(new Color(153, 0, 102));
						
					}
					@Override
					public void mouseExited(MouseEvent e) {
						btn.setBackground(new Color(255, 51, 102));
					}
				});
				ji.setText("登记入住");
				ji.setEnabled(false);
				out.setEnabled(true);
				
			}
		} catch (NumberFormatException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ji.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//入住表 传入房间号
				StaffOrderMenu som = new StaffOrderMenu(Integer.parseInt(btn.getText()),staffId);
				som.getOrderFrame().setVisible(true);
				
			}
		});
	}
	
	
	/**
	 * 更新事件
	 * @param mi
	 */
	public void refresh(final JMenuItem mi ,final JButton btn,final JMenuItem up ,final JMenuItem out )
	{
		mi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnChange(btn,up,out);		
				}
		});
		//退房事件
		out.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i =JOptionPane.showConfirmDialog(null, "是否要将"+btn.getText()+"退房");
				if(i == 0)
				{
					rs.updateRoom(Integer.parseInt(btn.getText()), 2);
				}
				
			}
		});
		
	}
	
	/**
	 * 获取房间 add相应按钮和方法
	 */
	public void getRoom()
	{
		//一共九行
		for(int i = 0 ; i < rL.size()/9 ; i++)
		{
		
			int k = 0 ;
				for(int j = 0 ; j <9 ; j++) //每九层换行
				{
					//btnRoom201.setBounds(10, 10);btnRoom202.setBounds(141, 10);
					//btnRoom301.setBounds(10, 114);btnRoom302.setBounds(141, 114);
					//btnRoom401.setBounds(10, 218);btnRoom402.setBounds(141, 218,);
					k = rL.get(9*i+j).getRoom_ID() ;
					
					final JButton btnRoom = new JButton(k+"");
					btnRoom.setFont(new Font("微软雅黑", Font.PLAIN, 25));
					btnRoom.setBounds(10+131*j, 10+131*i, 121, 94);
					String pm1 = "pm"+k ;
					final JPopupMenu pm = new JPopupMenu();
					pm.setName(pm1);
					pm.setFont(new Font("微软雅黑", Font.PLAIN, 15));
					addPopup(btnRoom, pm);
					final JMenuItem miUpdate = new JMenuItem();
					String mi = "miUpdate"+k ;
					miUpdate.setName(mi);
					miUpdate.setFont(new Font("微软雅黑", Font.PLAIN, 15));
					pm.add(miUpdate);
					final JMenuItem miRoomOut = new JMenuItem("退房");
					String out = "miRoomOut"+k ;
					miRoomOut.setName(out);
					miRoomOut.setFont(new Font("微软雅黑", Font.PLAIN, 15));
					pm.add(miRoomOut);
					final JMenuItem miFlash = new JMenuItem("刷新");
					String flash = "miFlash"+k;
					miFlash.setName(flash);
					miFlash.setFont(new Font("微软雅黑", Font.PLAIN, 15));
					pm.add(miFlash);
					panel.add(btnRoom);
					//点击显示房间信息
					btnGetInfo(btnRoom);
					//刷新更新房间状态
					refresh(miFlash,btnRoom,miUpdate,miRoomOut);
					btnChange(btnRoom,miUpdate,miRoomOut);	
				}
			
			
		}
		
		int l = rL.size()%9;
		 if(l>0)
		 {
			 int k = 0 ;
				for(int j = 0 ; j < l ; j++) //每九层换行
				{
					//btnRoom201.setBounds(10, 10);btnRoom202.setBounds(141, 10);
					//btnRoom301.setBounds(10, 114);btnRoom302.setBounds(141, 114);
					//btnRoom401.setBounds(10, 218);btnRoom402.setBounds(141, 218,);
					k = rL.get((9*(rL.size()/9))+j).getRoom_ID() ;
					
					final JButton btnRoom = new JButton(k+"");
					btnRoom.setFont(new Font("微软雅黑", Font.PLAIN, 25));
					btnRoom.setBounds(10+131*j, 10+131*(rL.size()/9), 121, 94);
					String pm1 = "pm"+k ;
					final JPopupMenu pm = new JPopupMenu();
					pm.setName(pm1);
					pm.setFont(new Font("微软雅黑", Font.PLAIN, 15));
					addPopup(btnRoom, pm);
					final JMenuItem miUpdate = new JMenuItem();
					String mi = "miUpdate"+k ;
					miUpdate.setName(mi);
					miUpdate.setFont(new Font("微软雅黑", Font.PLAIN, 15));
					pm.add(miUpdate);
					final JMenuItem miRoomOut = new JMenuItem("退房");
					String out = "miRoomOut"+k ;
					miRoomOut.setName(out);
					miRoomOut.setFont(new Font("微软雅黑", Font.PLAIN, 15));
					pm.add(miRoomOut);
					final JMenuItem miFlash = new JMenuItem("刷新");
					String flash = "miFlash"+k;
					miFlash.setName(flash);
					miFlash.setFont(new Font("微软雅黑", Font.PLAIN, 15));
					pm.add(miFlash);
					panel.add(btnRoom);
					//点击显示房间信息
					btnGetInfo(btnRoom);
					//刷新更新房间状态
					refresh(miFlash,btnRoom,miUpdate,miRoomOut);
					btnChange(btnRoom,miUpdate,miRoomOut);	
				}
			
		 }
	}
}

