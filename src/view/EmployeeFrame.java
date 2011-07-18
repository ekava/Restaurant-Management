package view;

/**
* Author: Le Duy Phong

* Purpose of this class: this class is used to create form for adding or modifying employees.
*/
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import controller.EmployeeController;
import model.Address;
import model.BankAccount;
import model.Certificate;
import model.Contract;
import model.Diploma;
import model.Employee;
import model.IDCard;
import model.Sex;
import model.Staff;
import model.TelephoneNumber;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeFrame extends JFrame {

private JPanel contentPane;
private JTextField fullNameTextField;
private JTextField birthdayTextField;
private final ButtonGroup buttonGroup = new ButtonGroup();
private JTextField emailAddressTextField;
private JTextField cellPhoneNumberTextField;
private JTextField NoIdentityCardTextField;
private JTextField issueDateText;
private JTextField issuePlaceTextField;
private JTextField permanentAddressTexField;
private JTextField currentAddressTextField;
private JTextField accountNoTextField;
private JTextField nameOfBankTextField;
private JTextField diplomaTextField;
private JRadioButton sexradioMale;
private JRadioButton sexradioFemale;
private JComboBox educationComboBox;
private JComboBox certificateOfITcombobox;
private JComboBox foreignLanguageCombobox;

/**
* Launch the application.
*/
public static void main(String[] args) {
EventQueue.invokeLater(new Runnable() {
public void run() {
try {
EmployeeFrame frame = new EmployeeFrame();
frame.setVisible(true);
} catch (Exception e) {
e.printStackTrace();
}
}
});
}

/**
* Create the frame.
*/
public EmployeeFrame() {
setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
setBounds(100, 100, 694, 427);
contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);
contentPane.setLayout(null);

JLabel lblBasicInformations = new JLabel("Basic informations");
lblBasicInformations.setBounds(272, 12, 138, 15);
contentPane.add(lblBasicInformations);

JLabel lblFullName = new JLabel("Full name");
lblFullName.setBounds(12, 47, 70, 15);
contentPane.add(lblFullName);

fullNameTextField = new JTextField();
fullNameTextField.setBounds(152, 45, 190, 19);
contentPane.add(fullNameTextField);
fullNameTextField.setColumns(10);

JLabel lblNewLabel = new JLabel("Birthday");
lblNewLabel.setBounds(360, 47, 70, 15);
contentPane.add(lblNewLabel);

birthdayTextField = new JTextField();
birthdayTextField.setText("yyyy-mm-dd");
birthdayTextField.setBounds(478, 45, 202, 19);
contentPane.add(birthdayTextField);
birthdayTextField.setColumns(10);

JLabel lblSex = new JLabel("Sex");
lblSex.setBounds(12, 76, 70, 15);
contentPane.add(lblSex);

sexradioMale = new JRadioButton("Male");
sexradioMale.setSelected(true);
buttonGroup.add(sexradioMale);
sexradioMale.setBounds(152, 72, 70, 23);
contentPane.add(sexradioMale);

sexradioFemale = new JRadioButton("Female");
buttonGroup.add(sexradioFemale);
sexradioFemale.setBounds(249, 72, 76, 23);
contentPane.add(sexradioFemale);

JLabel lblEmailAddress = new JLabel("Email address");
lblEmailAddress.setBounds(360, 76, 99, 15);
contentPane.add(lblEmailAddress);

emailAddressTextField = new JTextField();
emailAddressTextField.setBounds(478, 72, 202, 19);
contentPane.add(emailAddressTextField);
emailAddressTextField.setColumns(10);

JLabel lblCellphoneNumber = new JLabel("Cellphone number");
lblCellphoneNumber.setBounds(12, 105, 129, 15);
contentPane.add(lblCellphoneNumber);

cellPhoneNumberTextField = new JTextField();
cellPhoneNumberTextField.setBounds(152, 103, 190, 19);
contentPane.add(cellPhoneNumberTextField);
cellPhoneNumberTextField.setColumns(10);

JLabel lblNewLabel_1 = new JLabel("No identity card");
lblNewLabel_1.setBounds(360, 105, 113, 15);
contentPane.add(lblNewLabel_1);

NoIdentityCardTextField = new JTextField();
NoIdentityCardTextField.setBounds(478, 103, 202, 19);
contentPane.add(NoIdentityCardTextField);
NoIdentityCardTextField.setColumns(10);

JLabel lblIssueDate = new JLabel("Issue date");
lblIssueDate.setBounds(12, 134, 107, 15);
contentPane.add(lblIssueDate);

issueDateText = new JTextField();
issueDateText.setText("yyyy-mm-dd");
issueDateText.setBounds(152, 132, 190, 19);
contentPane.add(issueDateText);
issueDateText.setColumns(10);

JLabel lblIssuePlace = new JLabel("Issue place");
lblIssuePlace.setBounds(361, 134, 99, 15);
contentPane.add(lblIssuePlace);

issuePlaceTextField = new JTextField();
issuePlaceTextField.setBounds(478, 132, 202, 19);
contentPane.add(issuePlaceTextField);
issuePlaceTextField.setColumns(10);

JLabel lblNewLabel_2 = new JLabel("Permanent address");
lblNewLabel_2.setBounds(12, 163, 147, 15);
contentPane.add(lblNewLabel_2);

permanentAddressTexField = new JTextField();
permanentAddressTexField.setBounds(152, 161, 528, 19);
contentPane.add(permanentAddressTexField);
permanentAddressTexField.setColumns(10);

JLabel lblCurrentAddress = new JLabel("Current address");
lblCurrentAddress.setBounds(12, 197, 138, 15);
contentPane.add(lblCurrentAddress);

currentAddressTextField = new JTextField();
currentAddressTextField.setBounds(152, 195, 528, 19);
contentPane.add(currentAddressTextField);
currentAddressTextField.setColumns(10);

JLabel lblAcountNo = new JLabel("Acount No");
lblAcountNo.setBounds(12, 224, 107, 15);
contentPane.add(lblAcountNo);

accountNoTextField = new JTextField();
accountNoTextField.setBounds(152, 224, 190, 19);
contentPane.add(accountNoTextField);
accountNoTextField.setColumns(10);

JLabel lblNameOfBank = new JLabel("Name of bank");
lblNameOfBank.setBounds(360, 226, 113, 15);
contentPane.add(lblNameOfBank);

nameOfBankTextField = new JTextField();
nameOfBankTextField.setBounds(478, 226, 202, 19);
contentPane.add(nameOfBankTextField);
nameOfBankTextField.setColumns(10);

JLabel lblEducation = new JLabel("Education");
lblEducation.setBounds(12, 253, 70, 15);
contentPane.add(lblEducation);

educationComboBox = new JComboBox();
educationComboBox.setModel(new DefaultComboBoxModel(new String[] {"<5/12", "5/12", "6/12", "7/12", "8/12", "9/12", "10/12", "11/12", "12/12"}));
educationComboBox.setBounds(152, 255, 190, 19);
contentPane.add(educationComboBox);

JLabel lblDisploma = new JLabel("Diploma");
lblDisploma.setBounds(360, 253, 70, 15);
contentPane.add(lblDisploma);

diplomaTextField = new JTextField();
diplomaTextField.setBounds(478, 255, 202, 19);
contentPane.add(diplomaTextField);
diplomaTextField.setColumns(10);

JLabel lblForeignLanguage = new JLabel("Foreign language");
lblForeignLanguage.setBounds(12, 282, 129, 15);
contentPane.add(lblForeignLanguage);

foreignLanguageCombobox = new JComboBox();
foreignLanguageCombobox.setModel(new DefaultComboBoxModel(new String[] {"", "level A", "level B", "level C"}));
foreignLanguageCombobox.setBounds(152, 286, 190, 19);
contentPane.add(foreignLanguageCombobox);

JLabel lblCertificateOfIt = new JLabel("Certificate of IT");
lblCertificateOfIt.setBounds(360, 282, 113, 15);
contentPane.add(lblCertificateOfIt);

certificateOfITcombobox = new JComboBox();
certificateOfITcombobox.setModel(new DefaultComboBoxModel(new String[] {"", "level A", "level B", "level C"}));
certificateOfITcombobox.setBounds(478, 286, 202, 19);
contentPane.add(certificateOfITcombobox);
final JLabel lbResult = new JLabel("");
lbResult.setBounds(415, 345, 137, 15);
contentPane.add(lbResult);

JButton btnSave = new JButton("Save");
btnSave.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
EmployeeController.singleton.addAction();
}
});
btnSave.setBounds(280, 340, 117, 25);
contentPane.add(btnSave);

}

public JTextField getCurrentAddressTextField() {
return currentAddressTextField;
}

public JTextField getAccountNoTextField() {
return accountNoTextField;
}

public JTextField getNameOfBankTextField() {
return nameOfBankTextField;
}

public JTextField getDiplomaTextField() {
return diplomaTextField;
}

public JPanel getContentPane() {
return contentPane;
}

public JTextField getFullNameTextField() {
return fullNameTextField;
}

public JTextField getBirthdayTextField() {
return birthdayTextField;
}

public ButtonGroup getButtonGroup() {
return buttonGroup;
}

public JTextField getNoIdentityCardTextField() {
return NoIdentityCardTextField;
}

public JTextField getIssueDateText() {
return issueDateText;
}

public JTextField getIssuePlaceTextField() {
return issuePlaceTextField;
}

public JTextField getPermanentAddressTexField() {
return permanentAddressTexField;
}

public JTextField getCellPhoneNumberTextField() {
return cellPhoneNumberTextField;
}

public JTextField getEmailAddressTextField() {
return emailAddressTextField;
}

public JRadioButton getSexradioFemale() {
return sexradioFemale;
}

public JRadioButton getSexradioMale() {
return sexradioMale;
}

public JComboBox getEducationComboBox() {
return educationComboBox;
}
public JComboBox getCertificateOfITcombobox() {
return certificateOfITcombobox;
}

public JComboBox getForeignLanguageCombobox() {
return foreignLanguageCombobox;
}
}