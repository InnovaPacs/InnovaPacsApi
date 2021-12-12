package innova.pacs.api.dto;

import innova.pacs.api.model.InnovaFile;

public class InnovaDiagnosticPdfDto {
	private String headerColor;
	private InnovaFile logo;
	private String patientName;
	private String patientBirth;
	private String patientAge;
	private String diagnosticDate;
	private String patientSex;
	private String diagnostic;
	private String doctorName;
	private String doctorTitle;
	private String doctorLicense;
	private String doctorUniversity;

	public String getHeaderColor() {
		return headerColor;
	}

	public void setHeaderColor(String headerColor) {
		this.headerColor = headerColor;
	}

	public InnovaFile getLogo() {
		return logo;
	}

	public void setLogo(InnovaFile logo) {
		this.logo = logo;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDiagnosticDate() {
		return diagnosticDate;
	}

	public void setDiagnosticDate(String diagnosticDate) {
		this.diagnosticDate = diagnosticDate;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorTitle() {
		return doctorTitle;
	}

	public void setDoctorTitle(String doctorTitle) {
		this.doctorTitle = doctorTitle;
	}

	public String getPatientSex() {
		return patientSex;
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	public String getPatientBirth() {
		return patientBirth;
	}

	public void setPatientBirth(String patientBirth) {
		this.patientBirth = patientBirth;
	}

	public String getPatientAge() {
		return patientAge;
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}

	public String getDoctorLicense() {
		return doctorLicense;
	}

	public void setDoctorLicense(String doctorLicense) {
		this.doctorLicense = doctorLicense;
	}

	public String getDoctorUniversity() {
		return doctorUniversity;
	}

	public void setDoctorUniversity(String doctorUniversity) {
		this.doctorUniversity = doctorUniversity;
	}

}
