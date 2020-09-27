package com.NipHotel.GUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.NipHotel.Entity.Room;
import com.NipHotel.Service.RoomService;

public class AdmAddRoom {

	private JFrame frame;
	private JTextField txtRoomId;
	private JTextField txtPrice;
	private JTextField txtRoomSize;
	private JTextField txtState;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JTextPane txtRemark;
	RoomService rs = new RoomService();

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
					AdmAddRoom window = new AdmAddRoom();
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
	public AdmAddRoom() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("添加房间");
		frame.setBounds(100, 100, 392, 538);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JLabel lblRoomId = new JLabel("房间号：");
		lblRoomId.setFont(new Font("宋体", Font.PLAIN, 18));
		lblRoomId.setBounds(24, 25, 72, 31);
		frame.getContentPane().add(lblRoomId);
		
		txtRoomId = new JTextField();
		txtRoomId.setBounds(124, 27, 97, 31);
		frame.getContentPane().add(txtRoomId);
		txtRoomId.setColumns(10);
		
		JLabel lblRoomType = new JLabel("房间类型：");
		lblRoomType.setFont(new Font("宋体", Font.PLAIN, 18));
		lblRoomType.setBounds(24, 76, 90, 31);
		frame.getContentPane().add(lblRoomType);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"大床房", "激情双人房", "舒适单间", "舒适标间", "豪华单间", "豪华大床房", "豪华标间"}));
		comboBox.setBounds(124, 83, 97, 21);
		frame.getContentPane().add(comboBox);
		
		JLabel lblRoomPrice = new JLabel("售价：");
		lblRoomPrice.setFont(new Font("宋体", Font.PLAIN, 18));
		lblRoomPrice.setBounds(24, 124, 72, 31);
		frame.getContentPane().add(lblRoomPrice);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(124, 126, 97, 31);
		frame.getContentPane().add(txtPrice);
		
		JLabel lblRoomSize = new JLabel("房间大小：");
		lblRoomSize.setFont(new Font("宋体", Font.PLAIN, 18));
		lblRoomSize.setBounds(24, 174, 90, 31);
		frame.getContentPane().add(lblRoomSize);
		
		txtRoomSize = new JTextField();
		txtRoomSize.setColumns(10);
		txtRoomSize.setBounds(124, 176, 97, 31);
		frame.getContentPane().add(txtRoomSize);
		
		JLabel lblRoomState = new JLabel("房间状态：");
		lblRoomState.setFont(new Font("宋体", Font.PLAIN, 18));
		lblRoomState.setBounds(24, 229, 90, 31);
		frame.getContentPane().add(lblRoomState);
		
		txtState = new JTextField();
		txtState.setEnabled(false);
		txtState.setEditable(false);
		txtState.setText("未入住");
		txtState.setColumns(10);
		txtState.setBounds(124, 236, 97, 31);
		frame.getContentPane().add(txtState);
		
		JLabel lblRemark = new JLabel("备注：");
		lblRemark.setFont(new Font("宋体", Font.PLAIN, 18));
		lblRemark.setBounds(24, 286, 90, 31);
		frame.getContentPane().add(lblRemark);
		
		txtRemark = new JTextPane();
		txtRemark.setBounds(123, 286, 212, 85);
		frame.getContentPane().add(txtRemark);
		
		JButton btnAddRoom = new JButton("确定");
		btnAddRoom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(txtRoomId.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "房间号不可为空");
					txtRoomId.requestFocus();
					txtRoomId.selectAll();
				}
				else{
					try{
					int roomId = Integer.parseInt(txtRoomId.getText());
					if(rs.roomInfo(roomId)==null)
					{
						double roomPrice = Double.parseDouble(txtPrice.getText());
						double roomSize = Double.parseDouble(txtRoomSize.getText());
						String roomType = comboBox.getSelectedItem().toString();
						String roomState = txtState.getText();
						String remark = txtRemark.getText();
						Room r = new Room();
						r.setRemark(remark);
						r.setRoom_ID(roomId);
						r.setRoom_price(roomPrice);
						r.setRoom_Size(roomSize);
						r.setRoom_State(roomState);
						r.setRoom_Type(roomType);
						
						if(rs.addRoom(r)==true)
						{
							JOptionPane.showMessageDialog(null, "添加完成！");
							
						}
						
						
					}
					else{
						JOptionPane.showMessageDialog(null, "已存在该房间");
						txtRoomId.requestFocus();
						txtRoomId.selectAll();
					}
					}catch(NumberFormatException e1)
					{
						JOptionPane.showMessageDialog(null, "非法输入");
						txtRoomId.requestFocus();
						txtRoomId.selectAll();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
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
				txtRoomId.setText("");
				comboBox.setSelectedIndex(0);
				txtPrice.setText("");
				txtRoomSize.setText("");
				txtRemark.setText("");
				
			}
		});
		btnCancle.setFont(new Font("宋体", Font.PLAIN, 16));
		btnCancle.setBounds(215, 416, 126, 32);
		frame.getContentPane().add(btnCancle);
	}
}
