package HospitalManagementSystem;

import java.util.ArrayList;
import java.util.List;

class Doctor {
    private String name;
    private String specialty;
    private int experience;
    private String roomNumber;     
    private String visitingTime;   
    private List<String> appointments; // List to store appointment dates

    public Doctor(String name, String specialty, int experience, String roomNumber, String visitingTime) {
        this.name = name;
        this.specialty = specialty;
        this.experience = experience;
        this.roomNumber = roomNumber;
        this.visitingTime = visitingTime;
        this.appointments = new ArrayList<>(); // Initialize the list
    }

    // Existing getters...
    
    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getExperience() {
        return experience;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getVisitingTime() {
        return visitingTime;
    }

    // Method to check availability
    public boolean isAvailableOn(String date) {
        return !appointments.contains(date); // Return true if the date is not booked
    }

    // Method to book an appointment
    public void bookAppointment(String patientName, String date) {
        appointments.add(date); // Add the date to the appointments list
        System.out.println("Appointment for " + patientName + " booked with Dr. " + name + " on " + date);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Specialty: " + specialty + ", Experience: " + experience + " years";
    }
}


