package GameRoom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Guest.guestDAO;
import Guest.superguest;

public class login extends JFrame implements ItemListener, ActionListener {
	JLabel label = new JLabel("");
	JLabel title = new JLabel("***바다이야기***");
	private JPanel back;
	private JTextField textField;
	ArrayList<guestDAO> memberlist = null;
	guestDAO gdao = null;
	ArrayList<superguest> glist = new ArrayList<superguest>();

	login() {

		setDB();
		this.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});

		setBounds(100, 100, 900, 753);
		back = new JPanel();
		back.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(back);
		back.setLayout(null);
		this.add(title, "South");
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("D:\\myDate\\java_src\\seastory\\img\\로그인배경2.jpg"));
		lblNewLabel.setBounds(0, -16, 884, 715);
		back.add(lblNewLabel);

		JButton LoginB = new JButton("LOGIN");
		LoginB.setBounds(344, 298, 97, 23);
		back.add(LoginB);

		JButton SignB = new JButton("SIGN");
		SignB.setBounds(453, 298, 97, 23);
		back.add(SignB);

		textField = new JTextField();
		textField.setBounds(344, 331, 206, 21);
		back.add(textField);
		textField.setColumns(10);

		this.setVisible(true);
		LoginB.addActionListener(this);
		SignB.addActionListener(this);
	}

	private void setDB() {

		gdao = gdao.getInstance();
		glist = gdao.gselect();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		String btnName = ae.getActionCommand();
		int logchk = 0;
		if (btnName.equals("LOGIN")) {
			System.out.println("로그인");
			for (int i = 0; i < glist.size(); i++) {
				if (glist.get(i).getId().equals(textField.getText())) {
					this.setVisible(false);
					new gameroom(glist.get(i).getId());
					break;
				} else if (!glist.get(i).getId().equals(textField.getText())) {
					logchk = 1;
				}
			}
			if (logchk == 1) {
				textField.setText("입력하신 아이디가 없습니다");
			}
		} else if (btnName.equals("SIGN")) {
			int idchk = 0;
			for (int i = 0; i < glist.size(); i++) {
				System.out.println("회원가입");
				System.out.println(glist.get(i).getId());
				if (glist.get(i).getId().equals(textField.getText())) {
					textField.setText("ID가 중복입니다");
					idchk++;
					break;
				}
			}
			if (idchk == 0) {
				String signtext = textField.getText();
				gdao.insert(signtext);
			}
		}
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {

	}
}
