package com.NipHotel.GUI;
/**
 * 管理员房间新增页面
 *
 * @author Nippppp
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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

import com.NipHotel.Entity.Room;
import com.NipHotel.Service.OrdersService;
import com.NipHotel.Service.RoomService;
import com.NipHotel.Service.UserService;

public class AdmRoomOpr {

    private JFrame AdmRoomOpr;
    private JTextField txtRoom;
    private JTable tRoom;
    private JTextField txtThisPage;
    private JTextField txtAllPage;
    public JSplitPane sp1;
    private String[] tColumns = {"房间编号", "房间类型", "房间价格", "房间大小", "房间状态", "备注"};
    int row = 0;
    int count = 1;
    Object[][] roomInfo;
    RoomService rs = new RoomService();
    OrdersService os = new OrdersService();
    UserService us = new UserService();
    @SuppressWarnings("unused")
    private JComboBox<String> cboType;
    private JComboBox<String> comboBox;
    ArrayList<Room> r = new ArrayList<Room>();
    int thisPage = 1;
    int lastPage = 1;
    private JButton btnFirstPage;
    private JButton btnPriPage;
    private JButton btnNextPage;
    private JButton btnAllPage;
    int pageSize = 10;//页面容量
    private JButton btnDelete;
    private JButton btnUpdate;
    private JScrollPane spVip;


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

                    AdmRoomOpr window = new AdmRoomOpr();
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
    public AdmRoomOpr() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private void initialize() {
        AdmRoomOpr = new JFrame();
        AdmRoomOpr.setTitle("房间信息查询");
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

        txtRoom = new JTextField();
        txtRoom.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        txtRoom.setBounds(116, 24, 191, 36);
        pTop.add(txtRoom);
        txtRoom.setColumns(10);


        tRoom = new JTable();
        tRoom.setModel(new DefaultTableModel(roomInfo, tColumns));

        JButton btnOk = new JButton("确定");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                r.clear();
                String txt = txtRoom.getText();
                if (comboBox.getSelectedIndex() == 0) {
                    int card = 0;
                    try {

                        card = Integer.parseInt(txt);
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null, "房间号输入非法");
                        txtRoom.selectAll();
                        txtRoom.requestFocus();
                        return;
                    }

                    try {
                        if (rs.roomInfo(card).getRoom_ID() == 0) {
                            JOptionPane.showMessageDialog(AdmRoomOpr, "没有该房间请重新输入");
                            txtRoom.selectAll();
                            return;
                        }
                    } catch (NumberFormatException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (HeadlessException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                    try {
                        r.add(rs.roomInfo(card));
                    } catch (NumberFormatException | SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    tRoom.setModel(RoomInfo(r));
                    txtThisPage.setText(1 + "");
                    txtAllPage.setText(1 + "");
                } else if (comboBox.getSelectedIndex() == 1) {

                    //房间类型
                    r = rs.EmptyRoomType(txt);
                    if (r.size() == 0) {
                        JOptionPane.showMessageDialog(null, "没有该类型的房间");
                        return;

                    } else {

                        tRoom.setModel(RoomInfo(r));
                    }

                }

            }
        });
        btnOk.setFont(new Font("宋体", Font.PLAIN, 18));
        btnOk.setBounds(472, 25, 119, 36);
        pTop.add(btnOk);

        JButton btnCancle = new JButton("取消");
        btnCancle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtRoom.setText("");
                comboBox.setSelectedIndex(0);
            }
        });
        btnCancle.setFont(new Font("宋体", Font.PLAIN, 18));
        btnCancle.setBounds(335, 90, 119, 36);
        pTop.add(btnCancle);

        //分页查询
        JButton btnAll = new JButton("所有房间");
        btnAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                thisPage = 1;
                r.clear();
                if ((rs.allRoom()).size() % pageSize > 0) {
                    lastPage = (rs.allRoom()).size() / pageSize + 1;
                } else {
                    lastPage = (rs.allRoom()).size() / pageSize;
                }
                r = rs.roomLimit(thisPage, pageSize);
                txtThisPage.setText(thisPage + "");
                txtAllPage.setText(lastPage + "");
                tRoom.setModel(RoomInfo(r));
                limitSelect();
            }
        });
        btnAll.setFont(new Font("宋体", Font.PLAIN, 18));
        btnAll.setBounds(643, 90, 119, 36);
        pTop.add(btnAll);

        comboBox = new JComboBox<String>();
        comboBox.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        comboBox.setModel(new DefaultComboBoxModel(new String[]{"房间号", "房间类型"}));
        comboBox.setBounds(339, 24, 119, 36);
        pTop.add(comboBox);

        JButton btnEmpty = new JButton("查找空房");
        btnEmpty.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!txtRoom.getText().isEmpty()) {

                    String type = txtRoom.getText();
                    r = rs.EmptyRoom(type);
                    tRoom.setModel(RoomInfo(r));
                } else {
                    r = rs.EmptyRoom();
                }
                tRoom.setModel(RoomInfo(r));
                thisPage = 1;
                lastPage = 1;
                txtThisPage.setText(thisPage + "");
                txtAllPage.setText(lastPage + "");
            }
        });
        btnEmpty.setFont(new Font("宋体", Font.PLAIN, 18));
        btnEmpty.setBounds(496, 90, 119, 36);
        pTop.add(btnEmpty);

        btnDelete = new JButton("删除房间");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String a = txtRoom.getText();
                if (a.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "请输入您想要删除的房间号");
                } else {
                    if (comboBox.getSelectedItem().toString().equals("房间号")) {
                        int c = JOptionPane.showConfirmDialog(null, "是否继续删除" + a);
                        if (c == 0) {
                            try {
                                if (rs.deleteRoom(a)) {
                                    JOptionPane.showMessageDialog(null, "删除成功");
                                } else {
                                    int d = JOptionPane.showConfirmDialog(null, a + "房还有相关订单是否先删除？");
                                    if (d == 0) {
                                        int roomId = Integer.parseInt(a);
                                        if (os.deleteOrderByRoomId(roomId) == true) {

                                            int f = JOptionPane.showConfirmDialog(null, a + "房订单已删除还有相关顾客订单未删除是否继续？");
                                            if (f == 0) {
                                                if (us.deleteUserInfoByRoomId(roomId)) {

                                                    int h = JOptionPane.showConfirmDialog(null, a + "房所有顾客信息已删除是否继续删除房间？");
                                                    if (h == 0) {

                                                        if (rs.deleteRoom(a)) {

                                                            JOptionPane.showMessageDialog(null, "删除成功");
                                                            return;
                                                        } else {

                                                            JOptionPane.showMessageDialog(null, "删除失败 请联系工作人员");

                                                        }
                                                    }
                                                }
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "删除失败 请联系工作人员");
                                        }
                                    }
                                }
                            } catch (NumberFormatException e1) {
                                JOptionPane.showMessageDialog(null, "房间号输入非法");
                                txtRoom.requestFocus();
                                txtRoom.selectAll();
                            }


                        }
                    }
                }

            }
        });
        btnDelete.setFont(new Font("宋体", Font.PLAIN, 18));
        btnDelete.setBounds(47, 90, 119, 36);
        pTop.add(btnDelete);

        btnUpdate = new JButton("提交");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    for (int i = 0; i < tRoom.getRowCount(); i++) {
                        Room v = new Room();
                        v.setRoom_ID(Integer.parseInt(tRoom.getValueAt(i, 0).toString()));
                        v.setRoom_Type(tRoom.getValueAt(i, 1).toString());
                        v.setRoom_price(Double.parseDouble(tRoom.getValueAt(i, 2).toString()));
                        v.setRoom_Size(Double.parseDouble(tRoom.getValueAt(i, 3).toString()));
                        v.setRoom_State(tRoom.getValueAt(i, 4).toString());
                        System.out.println("re:" + tRoom.getValueAt(i, 5));
                        String remark = (String) tRoom.getValueAt(i, 5);

                        if (remark == null) remark = "";

                        v.setRemark(remark);
                        r.add(v);
                    }
                } catch (NumberFormatException e1) {
                    JOptionPane.showMessageDialog(null, "修改了非法数值！");
                }

                if (rs.updateRoom(r) == true) {
                    JOptionPane.showMessageDialog(null, "修改成功！");
                } else {
                    JOptionPane.showMessageDialog(null, "修改失败 可能修改了非法值");
                }
            }
        });
        btnUpdate.setFont(new Font("宋体", Font.PLAIN, 18));
        btnUpdate.setBounds(194, 90, 119, 36);
        pTop.add(btnUpdate);

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
        btnPriPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });

        btnPriPage.setFont(new Font("宋体", Font.PLAIN, 16));
        btnPriPage.setBounds(182, 11, 93, 27);
        pButtom.add(btnPriPage);

        txtThisPage = new JTextField();
        txtThisPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        txtThisPage.setBounds(313, 11, 56, 21);
        pButtom.add(txtThisPage);
        txtThisPage.setColumns(10);
        txtThisPage.setText(1 + "");

        JLabel lblCut = new JLabel("/");
        lblCut.setBounds(379, 14, 54, 15);
        pButtom.add(lblCut);

        txtAllPage = new JTextField();
        txtAllPage.setEditable(false);
        txtAllPage.setBounds(389, 11, 66, 21);
        pButtom.add(txtAllPage);
        txtAllPage.setColumns(10);
        txtAllPage.setText(1 + "");

        btnNextPage = new JButton("下一页");
        btnNextPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


            }
        });

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
    public void limitSelect() {
        btnFirstPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (thisPage == 1) {
                    return;
                }
                thisPage = 1;
                r = rs.roomLimit(thisPage, pageSize);
                txtThisPage.setText(thisPage + "");
                tRoom.setModel(RoomInfo(r));
            }
        });
        btnPriPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (thisPage == 1) {
                    return;
                } else {
                    thisPage -= 1;
                    txtThisPage.setText(thisPage + "");
                    r = rs.roomLimit(thisPage, pageSize);
                    txtThisPage.setText(thisPage + "");
                    txtAllPage.setText(lastPage + "");
                    tRoom.setModel(RoomInfo(r));
                }

            }
        });
        btnNextPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (thisPage == lastPage) {
                    return;
                } else {
                    thisPage += 1;
                    txtThisPage.setText(thisPage + "");
                    r = rs.roomLimit(thisPage, pageSize);
                    tRoom.setModel(RoomInfo(r));
                }

            }
        });
        btnAllPage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (thisPage == lastPage) {
                    return;
                }
                thisPage = lastPage;
                txtThisPage.setText(thisPage + "");
                r = rs.roomLimit(thisPage, pageSize);
                tRoom.setModel(RoomInfo(r));
            }
        });
    }


    /**
     * 将传入的房间对象数组设置model
     * @param al
     */
    public DefaultTableModel RoomInfo(ArrayList<Room> al) {
        row = al.size();
        roomInfo = new Object[row][tColumns.length];
        for (int i = 0; i < row; i++) {
            roomInfo[i][0] = al.get(i).getRoom_ID();
            roomInfo[i][1] = al.get(i).getRoom_Type();
            roomInfo[i][2] = al.get(i).getRoom_price();
            roomInfo[i][3] = al.get(i).getRoom_Size();
            roomInfo[i][4] = al.get(i).getRoom_State();
            roomInfo[i][5] = al.get(i).getRemark();
        }

        DefaultTableModel model = new DefaultTableModel(roomInfo, tColumns);
        return model;
    }


}
