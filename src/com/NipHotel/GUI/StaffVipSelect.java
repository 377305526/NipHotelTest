package com.NipHotel.GUI;
/**
 * 会员信息查询
 */

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

import com.NipHotel.Entity.VIP;
import com.NipHotel.Service.VipService;

public class StaffVipSelect {

	private JFrame StaffVipSelectFrame;
	private JTextField txtVip;
	private JTable tVip;
	private JTextField txtThisPage;
	private JTextField txtAllPage;
	private JSplitPane sp1;
	@SuppressWarnings("unused")
	private String[] columns = {"卡号", "姓名", "手机号"};
	private String[] tColumns = {"Vip编号", "Vip卡号", "Vip姓名", "Vip生日", "Vip等级","Vip电话", "备注"};
	int row = 0 ;
	int count = 1;
	Object[][] vipInfo ;
	VipService vs = new VipService();
	ArrayList<VIP> vL = vs.allVipInfo();
	private JComboBox<String> cboType;
	private JButton btnNextPage;
	private JButton btnAllPage;
	private JButton btnPriPage;
	private JButton btnFirstPage;
	private int thisPage = 1 ;
	private int lastPage = 1 ;
	private int pageSize = 10 ;

	public JFrame getStaffVipFrame() {
		return StaffVipSelectFrame;
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
					StaffVipSelect window = new StaffVipSelect();
					window.StaffVipSelectFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StaffVipSelect() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		StaffVipSelectFrame = new JFrame();
		StaffVipSelectFrame.setTitle("会员信息查询");
		StaffVipSelectFrame.setResizable(false);
		StaffVipSelectFrame.setBounds(100, 100, 742, 616);
		StaffVipSelectFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		StaffVipSelectFrame.setLocationRelativeTo(null);
		StaffVipSelectFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		sp1 = new JSplitPane();
		sp1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		StaffVipSelectFrame.getContentPane().add(sp1, BorderLayout.CENTER);
		
		JPanel pTop = new JPanel();
		sp1.setLeftComponent(pTop);
		pTop.setLayout(null);
		
		JLabel lblVip = new JLabel("会员：");
		lblVip.setFont(new Font("微软雅黑", Font.BOLD, 20));
		lblVip.setBounds(116, 23, 75, 36);
		pTop.add(lblVip);
		
		txtVip = new JTextField();
		txtVip.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		txtVip.setBounds(231, 23, 191, 36);
		pTop.add(txtVip);
		txtVip.setColumns(10);
		
		cboType = new JComboBox<String>();
		cboType.setModel(new DefaultComboBoxModel(new String[] {"卡号", "姓名", "手机号"}));
		cboType.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		cboType.setBounds(480, 25, 127, 32);
		pTop.add(cboType);
		
		JButton btnOk = new JButton("确认");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtVip.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "请输入您需要查询的会员");
					return;
				}
				else{
					String txt = txtVip.getText();
					int type = cboType.getSelectedIndex();
					vL = vs.oneVipInfo(type, txt);
					if(vL.size()==0)
					{
						JOptionPane.showMessageDialog(null, "没有该会员~");
						return ;
					}
					else{
						VipInfo(vL);
						thisPage=1;
						lastPage=1 ;
						txtThisPage.setText(thisPage+"");
						txtAllPage.setText(lastPage+"");
					}
					
				}
				
			}
		});
		btnOk.setFont(new Font("宋体", Font.PLAIN, 18));
		btnOk.setBounds(116, 90, 119, 36);
		pTop.add(btnOk);
		
		JButton btnCancle = new JButton("取消");
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtVip.setText("");
				cboType.setSelectedIndex(0);
				
			}
		});
		btnCancle.setFont(new Font("宋体", Font.PLAIN, 18));
		btnCancle.setBounds(336, 90, 119, 36);
		pTop.add(btnCancle);
		
		JButton btnAll = new JButton("所有会员");
		btnAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thisPage = 1 ;
				if(vs.allVipInfo().size()%pageSize>0)
				{
					lastPage  = (vs.allVipInfo().size()/pageSize)+1;
				}
				else{
					
					lastPage  = (vs.allVipInfo().size()/pageSize);
				}
				txtThisPage.setText(thisPage+"");
				txtAllPage.setText(lastPage+"");	
				VipInfo(vs.allVipInfo(thisPage, pageSize));
			}
		});
		btnAll.setFont(new Font("宋体", Font.PLAIN, 18));
		btnAll.setBounds(550, 90, 119, 36);
		pTop.add(btnAll);
		
		JSplitPane sp2 = new JSplitPane();
		sp2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		sp1.setRightComponent(sp2);
		
		JPanel pCenter = new JPanel();
		sp2.setLeftComponent(pCenter);
		pCenter.setLayout(new BorderLayout(0, 0));
		
		JScrollPane spVip = new JScrollPane();
		pCenter.add(spVip);
		
		tVip = new JTable();
		spVip.setViewportView(tVip);
		tVip.setModel(new DefaultTableModel(vipInfo ,tColumns));
		
		JPanel pButtom = new JPanel();
		sp2.setRightComponent(pButtom);
		pButtom.setLayout(null);
		
		btnFirstPage = new JButton("首页");
		btnFirstPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(thisPage ==1)
				{
					return ;
				}
			thisPage = 1 ;
			txtThisPage.setText(thisPage+"");
			vL =vs.allVipInfo(thisPage, pageSize);
			VipInfo(vL);
			}
		});
		btnFirstPage.setFont(new Font("宋体", Font.PLAIN, 16));
		btnFirstPage.setBounds(38, 11, 93, 27);
		pButtom.add(btnFirstPage);
		
		btnPriPage = new JButton("上一页");
		btnPriPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(thisPage==1)
				{
					return ;
				}
				else{
					thisPage-=1;
					}
				txtThisPage.setText(thisPage+"");
				vL = vs.allVipInfo(thisPage, pageSize);
			VipInfo(vL);
				}
		});
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
		btnNextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(thisPage==lastPage)
				{
					return ;
				}
				else{
					thisPage+=1;
					}
				txtThisPage.setText(thisPage+"");
				vL = vs.allVipInfo(thisPage, pageSize);
			VipInfo(vL);
				
			}
		});
		btnNextPage.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNextPage.setBounds(483, 11, 93, 27);
		pButtom.add(btnNextPage);
		
		btnAllPage = new JButton("尾页");
		btnAllPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(thisPage ==lastPage)
				{
					return ;
				}
				thisPage = lastPage ;
				txtThisPage.setText(thisPage+"");
				vL = vs.allVipInfo(thisPage, pageSize);
				VipInfo(vL);
			}
		});
		btnAllPage.setFont(new Font("宋体", Font.PLAIN, 16));
		btnAllPage.setBounds(625, 11, 93, 27);
		pButtom.add(btnAllPage);
		sp2.setDividerLocation(370);
		sp1.setDividerLocation(150);
	}
	
	


	/**
	 * 将传入的房间对象数组设置model
	 * @param al
	 */
	public void VipInfo(ArrayList<VIP> al)
	{
		
		vipInfo = new Object[al.size()][7];
		for(int i = 0 ; i < al.size() ; i++)
		{
			vipInfo[i][0]= al.get(i).getVIP_ID();
			vipInfo[i][1]= al.get(i).getVIP_Card();
			vipInfo[i][2]= al.get(i).getVIP_Name();
			vipInfo[i][3]= al.get(i).getVIP_BD();
			vipInfo[i][4]= al.get(i).getVIP_Level();
			vipInfo[i][5]= al.get(i).getVIP_Tel();
			vipInfo[i][6]= al.get(i).getRemark();
		}
		DefaultTableModel model =new DefaultTableModel(vipInfo,tColumns);
		tVip.setModel(model);
		
	}
	

	
}
