package controller;

/*
 * @author Tu Thi Xuan Hien
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import model.TimeKeepingBook;
import model.TimeKeepingSheet;
import model.Contract;
import model.Employee;
import model.PositionTitle;
import view.PrintPayrollFrame;

public class PrintPayrollController {
	private PrintPayrollFrame printPreview;
	private List<Employee> listEmployee;
	private int nMonthSelected;
	private int nYearSelected;

	public PrintPayrollController() {
		printPreview = new PrintPayrollFrame();
		printPreview.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				printPreview.dispose();
			}
		});
	}

	public void setTittle(String strTitle) {
		printPreview.setTitle(strTitle);
	}

	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}

	public void setMonth(int nMonthSelected) {
		this.nMonthSelected = nMonthSelected;
	}

	public void setYear(int nYearSelected) {
		this.nYearSelected = nYearSelected;
	}

	public String createPayRollReport() {
		// String strContent="";
		String strContent = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
		strContent += "<html xmlns=\"http://www.w3.org/1999/xhtml\">";
		strContent += "<head>";
		strContent += "<meta http-equiv=\"Content-Type\" content=\"text/html\"; charset=\"UTF-8\" />";
		strContent += "<title>Payroll</title>";
		strContent += "</head>";

		strContent += "<body style=\"margin: 0;	padding: 0;\">";
		strContent += "<div class=\"wrapper\">";
		strContent += "<div style=\"background:#FF9933;border:1px\"></div>";
		strContent += "<h1 align=\"center\"  style=\"color: #779A00;font-size: 36px;font-weight: bold;\">Payroll </h1><center>";
		strContent += "<div style=\"background:#FC9D36\"></div>";
		strContent += "<form id=\"form1\" name=\"form1\" method=\"post\" action=\"\">";
		strContent += "Month: ";
		strContent += "<font color=\"red\" style=\"font-weight:bold;\">"
				+ nMonthSelected + "</font>";
		strContent += " Year: ";
		strContent += "<font color=\"red\"  style=\"font-weight:bold;\">"
				+ nYearSelected + "</font>";
		strContent += "</form></center>";
		strContent += "<table width=\"100%\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\">";
		strContent += "<tr>";
		strContent += "<th width=\"18%\">Full Name</th>";
		strContent += "<th width=\"19%\">Position</th>";
		strContent += "<th width=\"17%\">Salary (USD)</th>";
		strContent += "<th width=\"17%\">Other Salary (USD)</th>";
		strContent += "<th width=\"22%\">Working-day Number</th>";
		strContent += "<th width=\"24%\">Final Salary (USD)</th>";
		strContent += "</tr>";
		int nSumSalary = 0;
		if (listEmployee != null) {
			for (int i = 0; i < listEmployee.size(); i++) {
				Employee emp = listEmployee.get(i);
				Contract suitableContract = emp.searchCorrespondingContract(
						nYearSelected, nMonthSelected);
				if (suitableContract == null)
					continue; // skip this employee

				int salary = suitableContract.getPosition().getSalary();
				int otherSalary = suitableContract.getPosition()
						.getOtherSalary();

				strContent += "<tr>";
				strContent += "<td>&nbsp;" + emp.getFullName() + "</td>";
				strContent += "<td>&nbsp;"
						+ PositionTitle.getTitleString(suitableContract
								.getPosition().getTitle()) + "</td>";
				strContent += "<td>&nbsp;" + salary + "</td>";
				strContent += "<td>&nbsp;" + otherSalary + "</td>";

				TimeKeepingBook book = suitableContract.getTimeKeeping();
				TimeKeepingSheet sheet = book.get(nMonthSelected,
						nYearSelected);
				int numberOfWorkingDays = 0;
				int numberOfDaysInMonth = TimeKeepingController.getNumberOfDaysInMonth(
						nMonthSelected, nYearSelected);
				if (sheet != null)
					numberOfWorkingDays = sheet.getWorkingDay();
				strContent += "<td>&nbsp;" + numberOfWorkingDays + "</td>";
				int finalSalary = (numberOfWorkingDays == 0) ? 0
						: calculateSalary(salary, otherSalary, numberOfWorkingDays,
						numberOfDaysInMonth);
				strContent += "<td>&nbsp;" + finalSalary + "</td>";
				strContent += "</tr>";
				nSumSalary += finalSalary;
			}
		}
		strContent += "<tr>";
		strContent += "<td colspan=\"5\" align=\"center\" style=\"color:red;font-weight:bold\">&nbsp; Total Salary</td>";
		strContent += "<td>&nbsp;" + nSumSalary + "</td>";
		strContent += "</tr>";
		strContent += "</table>";
		strContent += "<p style=\"font-weight:bold;text-align:center\">Designed by <font color=\"blue\">Tu Thi Xuan Hien</font></p>";
		strContent += "</div>";
		strContent += "</body>";
		strContent += "</html>";
		return strContent;
	}

	public int calculateSalary(int salary, int otherSalary,
			int numberOfWorkingDays, int numberOfDaysInMonth) {
		return Math.round(((float) salary / numberOfDaysInMonth)
				* numberOfWorkingDays + otherSalary);
	}

	public void addEventforAllControl() {
		printPreview.getButtonClose().addActionListener(new CMyButtonEvent());
	}

	public void setContent(String strContent) {
		printPreview.getPrintAbleEditor().setText(strContent);
	}

	public void doShow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		printPreview.setSize(screenSize.width / 2, screenSize.height / 2);
		printPreview.setLocationRelativeTo(null);
		printPreview.setVisible(true);
		addEventforAllControl();
	}

	private class CMyButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if (o.equals(printPreview.getButtonClose())) {
				printPreview.dispose();
			}
		}

	}
}
