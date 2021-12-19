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
		return headerColor != null ? this.headerColor : "";
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
		return patientName != null ? this.patientName : "";
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDiagnosticDate() {
		return diagnosticDate  != null ? this.diagnosticDate : "";
	}

	public void setDiagnosticDate(String diagnosticDate) {
		this.diagnosticDate = diagnosticDate;
	}

	public String getDiagnostic() {
		return diagnostic != null ? this.diagnostic : "";
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public String getDoctorName() {
		return doctorName != null ? this.doctorName : "";
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDoctorTitle() {
		return doctorTitle != null ? this.doctorTitle : "";
	}

	public void setDoctorTitle(String doctorTitle) {
		this.doctorTitle = doctorTitle;
	}

	public String getPatientSex() {
		return patientSex != null ? this.patientSex : "";
	}

	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	public String getPatientBirth() {
		return patientBirth != null ? this.patientBirth : "";
	}

	public void setPatientBirth(String patientBirth) {
		this.patientBirth = patientBirth;
	}

	public String getPatientAge() {
		return patientAge != null ? this.patientAge : "";
	}

	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}

	public String getDoctorLicense() {
		return doctorLicense != null ? this.doctorLicense : "";
	}

	public void setDoctorLicense(String doctorLicense) {
		this.doctorLicense = doctorLicense;
	}

	public String getDoctorUniversity() {
		return doctorUniversity != null ? this.doctorUniversity : "";
	}

	public void setDoctorUniversity(String doctorUniversity) {
		this.doctorUniversity = doctorUniversity;
	}

}
