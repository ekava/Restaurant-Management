package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.CUser;
import model.FileProcessing;
import view.LoginFrame;
import view.MainFrame;

public class LoginController {
	private LoginFrame m_loginFrame = null;
	private CUser m_userLogin = null;

	public LoginController() {
		m_loginFrame = new LoginFrame("Login Restaurant Management");
		m_userLogin = (CUser) FileProcessing
				.ReadData(FileProcessing.FILENAME_USERLOGIN);
	}

	public void doShow() {
		m_loginFrame.setSize(450, 300);
		m_loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m_loginFrame.setLocationRelativeTo(null);
		m_loginFrame.setResizable(false);
		m_loginFrame.setVisible(true);
		addEventforAllControl();
		if (m_userLogin != null && m_userLogin.getRememberPassword() == true) {
			m_loginFrame.getTextFieldUserName().setText(
					m_userLogin.getUserName());
			m_loginFrame.getPasswordField().setText(m_userLogin.getPassword());
			m_loginFrame.getCheckBoxRememberPassword().setSelected(
					m_userLogin.getRememberPassword());
		}
	}

	public void addEventforAllControl() {
		m_loginFrame.getButtonLogin().addActionListener(new CMyButtonEvent());
		m_loginFrame.getTextFieldUserName().addActionListener(
				new CMyButtonEvent());
		m_loginFrame.getPasswordField().addActionListener(new CMyButtonEvent());
		m_loginFrame.getButtonShutdown()
				.addActionListener(new CMyButtonEvent());
	}

	private void doProcessLogin() {
		String strUserName = m_loginFrame.getTextFieldUserName().getText();
		String strPwd = String.copyValueOf(m_loginFrame.getPasswordField()
				.getPassword());
		boolean bRemember = m_loginFrame.getCheckBoxRememberPassword()
				.isSelected();
		if (m_userLogin == null) {
			m_userLogin = new CUser(strUserName, strPwd, bRemember);
		}
		if (m_userLogin != null
				&& m_userLogin.getUserName().equals(strUserName)
				&& m_userLogin.getPassword().equals(strPwd)) {
			m_loginFrame.dispose();
			MainFrame frame = new MainFrame();
			frame.setVisible(true);
			m_userLogin.setRememeberPassword(bRemember);
			FileProcessing.WriteData(m_userLogin, FileProcessing.FILENAME_USERLOGIN);
		} else {
			JOptionPane.showMessageDialog(null, "Login Failed");
			m_loginFrame.getTextFieldUserName().requestFocus();
		}
	}

	private class CMyButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Object o = arg0.getSource();
			if (o.equals(m_loginFrame.getButtonShutdown())) {
				m_loginFrame.dispose();
			} else if (o.equals(m_loginFrame.getButtonLogin())
					|| o.equals(m_loginFrame.getTextFieldUserName())
					|| o.equals(m_loginFrame.getPasswordField())) {

				doProcessLogin();
			}
		}

	}
}