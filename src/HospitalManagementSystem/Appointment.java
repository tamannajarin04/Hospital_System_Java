
package HospitalManagementSystem;

public class Appointment {
    private String patientName;
    private String doctorName;
    private String appointmentDate;

    public Appointment(String patientName, String doctorName, String appointmentDate) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.appointmentDate = appointmentDate;
    }

    // Getter methods
    public String getPatientName() {
        return patientName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDate() {
        return appointmentDate;
    }

    @Override
    public String toString() {
        return "Patient: " + patientName + ", Doctor: " + doctorName + ", Date: " + appointmentDate;
    }
}
