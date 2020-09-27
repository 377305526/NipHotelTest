package com.NipHotel.GUI;

/**
 * 登录系统
 *
 * @author Nipppppp  2019年8月21日16:25:47
 */

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.NipHotel.Service.AdminService;
import com.NipHotel.Service.LoginService;

public class Login {

    private JFrame frame;
    private JTextField txtUserId;
    private JPasswordField pwdUserPwd;
    private JSplitPane spLogin;
    private JButton btnLogin;
    LoginService ls = new LoginService();
    private JLabel lblToday;
    SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
    private ButtonGroup button;
    private JRadioButton rbtAdmin;
    private JRadioButton rbtStaff;
    private JPanel pRight;
    AdminService as = new AdminService();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
			/*	try {
		            //设置外观
		            UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
		            JFrame.setDefaultLookAndFeelDecorated(true);
		            //设置主题
		            SubstanceLookAndFeel.setCurrentTheme(new SubstanceEbonyTheme());
		            //设置按钮外观
		            SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
		            //设置水印
		            SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBinaryWatermark());
		            //设置边框
		            SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());
		            //设置渐变渲染
		            SubstanceLookAndFeel.setCurrentGradientPainter(new StandardGradientPainter());
		            //设置标题
		            SubstanceLookAndFeel.setCurrentTitlePainter(new FlatTitlePainter());
		        } catch (Exception e) {
		            System.out.println(e.getMessage());
		        }
*/
                try {
//                    UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
                    UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceCremeLookAndFeel");

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
                    Login window = new Login();
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
    public Login() {

        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    @SuppressWarnings("unused")
    private void initialize() {

        frame = new JFrame();
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\NI\\Desktop\\logo.jpg"));
        frame.setResizable(false);
        TimeActionListener t = new TimeActionListener();
        frame.setTitle("闻闻的旅馆 - 酒店管理系统");
        frame.setBounds(100, 100, 728, 533);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);
        spLogin = new JSplitPane();
        spLogin.setBounds(0, 0, 722, 505);
        frame.getContentPane().add(spLogin);
        spLogin.setEnabled(false);
        JPanel pLeft = new JPanel();
        pLeft.setBackground(Color.WHITE);
        spLogin.setLeftComponent(pLeft);
        pLeft.setLayout(null);
        JLabel lblTodayis = new JLabel("今天是：");
        lblTodayis.setForeground(new Color(0, 0, 0));
        lblTodayis.setFont(new Font("微软雅黑", Font.BOLD, 20));
        lblTodayis.setBounds(12, 12, 91, 38);
        pLeft.add(lblTodayis);

        lblToday = new JLabel("");
        lblToday.setFont(new Font("微软雅黑", Font.BOLD, 20));
        lblToday.setBounds(97, 18, 264, 32);
        pLeft.add(lblToday);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("../imags/hotelLogin.jpg")));
        lblNewLabel.setBounds(-20, -41, 506, 544);
        pLeft.add(lblNewLabel);


        pRight = new JPanel();
        pRight.setBackground(Color.WHITE);
        spLogin.setRightComponent(pRight);
        pRight.setLayout(null);

        JLabel lblUserId = new JLabel("用户名：");
        lblUserId.setForeground(UIManager.getColor("CheckBox.foreground"));
        lblUserId.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        lblUserId.setBounds(36, 88, 76, 28);
        pRight.add(lblUserId);

        txtUserId = new JTextField();
        txtUserId.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        txtUserId.setBounds(36, 126, 167, 28);
        pRight.add(txtUserId);
        txtUserId.setColumns(10);

        JLabel lblUserPwd = new JLabel("密码：");
        lblUserPwd.setForeground(UIManager.getColor("Button.highlight"));
        lblUserPwd.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        lblUserPwd.setBounds(36, 178, 76, 28);
        pRight.add(lblUserPwd);

        pwdUserPwd = new JPasswordField();
        pwdUserPwd.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        pwdUserPwd.setBounds(36, 216, 167, 28);
        pRight.add(pwdUserPwd);


        // 密码输完的回车绑定按钮
        pwdUserPwd.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getSource() == pwdUserPwd) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        frame.getRootPane().setDefaultButton(btnLogin);
                    }

                }
            }
        });


        btnLogin = new JButton("登录");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (rbtStaff.isSelected()) {
                    try {

                        int id = Integer.parseInt(txtUserId.getText());
                        String pwd = "";
                        char[] cPwd = pwdUserPwd.getPassword();


                        //匹配输入框是否输入字符
                        if (id == 0 || cPwd.length == 0) {
                            JOptionPane.showMessageDialog(frame, "用户名或密码不得为空！");
                        } else {
                            for (char _p : cPwd) {
                                pwd += _p;
                            }
                            //匹配数据库里的数据是否相对
                            //给出结果

                            if (ls.isLogin(id, pwd) == false) {
                                JOptionPane.showMessageDialog(frame, "用户名或密码错误！");
                            } else {

                                JOptionPane.showMessageDialog(frame, "登录成功！");
                                StaffMainMenu sm = new StaffMainMenu(id);
                                sm.getStafFrame().setVisible(true);
                                frame.dispose();

                            }
                        }

                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(frame, "用户名只能为整数！");
                    }
                }
                if (rbtAdmin.isSelected()) {
                    String id = txtUserId.getText();
                    String pwd = "";
                    char[] cPwd = pwdUserPwd.getPassword();


                    //匹配输入框是否输入字符
                    if (id.length() == 0 || cPwd.length == 0) {
                        JOptionPane.showMessageDialog(frame, "用户名或密码不得为空！");
                    } else {
                        for (char _p : cPwd) {
                            pwd += _p;
                        }

                        if (as.isLogin(id, pwd) == true) {
                            JOptionPane.showMessageDialog(frame, "登陆成功");
                            AdminMenu am = new AdminMenu();
                            am.getAdminMenu().setVisible(true);
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(frame, "登陆失败 用户名或密码错误");

                        }
                    }
                }
            }
        });
        btnLogin.setFont(new Font("宋体", Font.PLAIN, 14));
        btnLogin.setBounds(10, 363, 93, 23);
        pRight.add(btnLogin);

        JButton btnCancle = new JButton("取消");
        btnCancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtUserId.setText("");
                pwdUserPwd.setText("");
            }
        });
        btnCancle.setFont(new Font("宋体", Font.PLAIN, 14));
        btnCancle.setBounds(153, 363, 93, 23);
        pRight.add(btnCancle);

        rbtStaff = new JRadioButton("工作人员");
        rbtStaff.setSelected(true);
        rbtStaff.setBackground(new Color(245, 222, 179));
        rbtStaff.setBounds(22, 282, 73, 23);
        pRight.add(rbtStaff);

        rbtAdmin = new JRadioButton("管理员");
        rbtAdmin.setBackground(new Color(245, 222, 179));
        rbtAdmin.setBounds(145, 282, 61, 23);
        pRight.add(rbtAdmin);
        //实现单选
        button = new ButtonGroup();
        button.add(rbtStaff);
        button.add(rbtAdmin);


        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("../imags/hotelLogin2.jpg")));
        lblNewLabel_1.setBounds(0, 0, 266, 503);
        pRight.add(lblNewLabel_1);
        spLogin.setDividerLocation(450);
    }

    /**
     * 计数器  时间每秒更新一次
     * @author Nippppp
     *
     */
    class TimeActionListener implements ActionListener {
        public TimeActionListener() {
            javax.swing.Timer t = new javax.swing.Timer(1000, this);
            t.start();
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            lblToday.setText(df.format(new java.util.Date()).toString());
        }
    }
}
