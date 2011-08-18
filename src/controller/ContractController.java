package controller;

/*
 * @author Tu Thi Xuan Hien
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import model.Contract;
import model.Duration;
import model.Employee;
import model.Position;
import model.PositionTitle;
import model.FileProcessing;
import model.Staff;
import view.CContractFrame;

public class ContractController {
	private CContractFrame contractFrame = null;
	private static List<Position> positions = Staff.getInstance().getPositions();
	private Employee currentEmployee = null;
	private List<Employee> listEmployee = null;
	private Contract contract = null;
	private boolean bIsSave = false;

	public ContractController(String strTitle, String strCaption,
			Employee employee, List<Employee> listEmployee) {
		contractFrame = new CContractFrame(strTitle, strCaption);
		contractFrame.setEmployeeName(employee.getFullName());
		addTitleForTitleComboBox();
		addDurationForCombobox();
		currentEmployee = employee;
		this.listEmployee = listEmployee;
	}

	public boolean isSave() {
		return bIsSave;
	}

	public void doShow() {
		contractFrame.setSize(550, 350);
		contractFrame.setLocationRelativeTo(null);
		addEventForButton();
		doFillSalary();
		contractFrame.setModal(true);
		contractFrame.setVisible(true);
	}

	public void updateInformationForEdit() {
		if (currentEmployee == null)
			return;
		Contract con = currentEmployee.getCurrentContract();
		if (con == null)
			return;
		contractFrame.getTextFieldSalary().setText(
				con.getPosition().getSalary() + "");
		contractFrame.getTextFieldOtherSalary().setText(
				con.getPosition().getOtherSalary() + "");
		contractFrame.getComboBoxDuration().setSelectedIndex(
				con.getTime().ordinal());
		contractFrame.getComboBoxTitle().setSelectedIndex(
				con.getPosition().getTitle().ordinal());
		contractFrame.getTextFieldStartDate().setText(
				Staff.dateFormat.format(con.getStartDate()));
	}

	private void addTitleForTitleComboBox() {
		for (PositionTitle title : PositionTitle.values()) {
			contractFrame.getComboBoxTitle().addItem(PositionTitle.getTitleString(title));
		}
	}

	private void addDurationForCombobox() {
		for (Duration d : Duration.values()) {
			contractFrame.getComboBoxDuration().addItem(
					Duration.getDurationString(d));
		}
	}

	private Contract createContract() {
		contract = new Contract();
		Position pos = positions.get(contractFrame.getComboBoxTitle()
				.getSelectedIndex());
		contract.setPosition(pos);
		String date = contractFrame.getTextFieldStartDate().getText();
		try {
			contract.setStartDate(Staff.dateFormat.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		int selectedDuration = contractFrame.getComboBoxDuration()
				.getSelectedIndex();
		contract.setTime(Duration.values()[selectedDuration]);
		return contract;
	}

	private void doSave() {
		bIsSave = true;
		if (contractFrame.getCaption().indexOf("Edit") != -1) {
			// Update contract information
			currentEmployee.setCurrentContract(createContract());
		} else {
			if (currentEmployee.getCurrentContract() != null) {
				// Update list of old contracts
				ArrayList<Contract> listContract = (ArrayList<Contract>) currentEmployee
						.getContracts();
				if (listContract == null)
					listContract = new ArrayList<Contract>();

				listContract.add(currentEmployee.getCurrentContract());
				currentEmployee.setContracts(listContract);
			}
			// Set new current contract
			currentEmployee.setCurrentContract(createContract());
		}
		boolean bResult = FileProcessing.WriteData(listEmployee,
				FileProcessing.FILENAME_EMPLOYEE);
		if (bResult) {
			JOptionPane.showMessageDialog(null, "Successful Save");
		} else {
			JOptionPane.showMessageDialog(null, "Failed Save");
		}
		contractFrame.dispose();
		bIsSave = true;
	}

	/*
	 * This method updates the salary and other salary field corresponding to
	 * the chosen position title
	 */
	private void doFillSalary() {
		Position pos = positions.get(contractFrame.getComboBoxTitle()
				.getSelectedIndex());
		contractFrame.getTextFieldSalary().setText(
				String.valueOf(pos.getSalary()));
		contractFrame.getTextFieldOtherSalary().setText(
				String.valueOf(pos.getOtherSalary()));
	}

	public void addEventForButton() {
		// assign Event into BUtton
		contractFrame.getComboBoxTitle().addActionListener(
				new CMyProcessButtonEvent());
		contractFrame.getButtonSave().addActionListener(
				new CMyProcessButtonEvent());
		contractFrame.getButtonExit().addActionListener(
				new CMyProcessButtonEvent());
	}

	private class CMyProcessButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object myObj = e.getSource();
			if (myObj.equals(contractFrame.getComboBoxTitle())) {
				doFillSalary();
			} else if (myObj.equals(contractFrame.getButtonSave())) {
				doSave();
			} else if (myObj.equals(contractFrame.getButtonExit())) {
				doExit();
			}
		}

		private void doExit() {
			contractFrame.dispose();
		}

	}
}
