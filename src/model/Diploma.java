package model;
/**
 * Author: Le Duy Phong

 * Purpose of this class: this class is used to save information about diploma of employee.
 */
import java.util.Date;

public class Diploma {
	private String name;
	private Date validityDate;

	public Diploma() {
		
	}

	public Diploma(String name, Date validityDate) {
		this.name = name;
		this.validityDate = validityDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(Date validityDate) {
		this.validityDate = validityDate;
	}

}
