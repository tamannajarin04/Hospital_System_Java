package HospitalManagementSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HospitalManagementSystem {

    private static ArrayList<User> users = new ArrayList<>();
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>(Arrays.asList(
        new Doctor("Dr. Alice Smith", "Cardiology", 10, "101", "9 AM - 5 PM"),
        new Doctor("Dr. Bob Johnson", "Dermatology", 5, "102", "10 AM - 4 PM"),
        new Doctor("Dr. Charlie Brown", "Pediatrics", 7, "103", "8 AM - 3 PM")
    ));
    private static List<Appointment> appointments = new ArrayList<>();
    private static ArrayList<Receptionist> receptionists = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Welcome to the Hospital Management System! ---");
            System.out.println("1. Registration");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    if (logIn(scanner)) {
                        System.out.println("Login successful!");
                        hospitalMenu(scanner);
                    } else {
                        System.out.println("Invalid credentials! Try again.");
                    }
                    break;    
                case 3:
                    System.out.println("Thank you for using the Hospital Management System!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void hospitalMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n--- HOSPITAL MANAGEMENT SYSTEM ---");
            System.out.println("1. Add Patient Information");
            System.out.println("2. View Patient's Information");
            System.out.println("3. Add Doctor's Information");
            System.out.println("4. View Doctor's Information");
            System.out.println("5. Add Receptionist's Information");
            System.out.println("6. View Receptionist's Information");
            System.out.println("7. Schedule Appointment");
            System.out.println("8. View Appointments");
            System.out.println("9. Log Out");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addPatient(scanner);
                    break;
                case 2:
                    viewPatients();
                    break;
                case 3:
                    addDoctor(scanner);
                    break;
                case 4:
                    viewDoctors();
                    break;
                case 5:
                    addReceptionist(scanner);
                    break;
                case 6:
                    viewReceptionists();
                    break;
                case 7:
                    scheduleAppointment(scanner);
                    break; 
                case 8:
                    viewAppointments();
                    break;      
                case 9:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Enter a valid choice!");
            }
        }
    }

    private static void addPatient(Scanner scanner) {
        System.out.println("\n--- Add Patient ---");
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Patient Gender: ");
        String gender = scanner.nextLine();

        patients.add(new Patient(name, age, gender));
        System.out.println("Patient added successfully!");
    }
       
    private static void viewPatients() {
        System.out.println("\n--- List of Patients ---");
        if (patients.isEmpty()) {
            System.out.println("No patients found!");
        } else {
            // Print header
            System.out.printf("%-10s %-20s %-10s %-10s\n", "ID", "Name", "Age", "Gender");
            System.out.println("------------------------------------------------");
            
            // Print each patient's information
            for (int i = 0; i < patients.size(); i++) {
                Patient p = patients.get(i);
                System.out.printf("%-10d %-20s %-10d %-10s\n", i + 1, p.getName(), p.getAge(), p.getGender());
            }
        }
    }

    private static void scheduleAppointment(Scanner scanner) {
        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine();
        System.out.print("Enter Doctor Name: ");
        String doctorName = scanner.nextLine();
        System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
        String date = scanner.nextLine();

        Doctor doctor = findDoctorByName(doctorName);
        if (doctor == null) {
            System.out.println("Doctor not found!");
            return;
        }

        if (doctor.isAvailableOn(date)) {
            doctor.bookAppointment(patientName, date);
            // Create and add the appointment
            Appointment newAppointment = new Appointment(patientName, doctorName, date);
            appointments.add(newAppointment);
            System.out.println("Appointment scheduled successfully!");
        } else {
            System.out.println("Doctor is not available on this date.");
        }
    }

    private static void viewAppointments() {
        System.out.println("\n--- Scheduled Appointments ---");
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled!");
        } else {
            System.out.printf("%-20s %-20s %-15s\n", "Patient Name", "Doctor Name", "Date");
            System.out.println("----------------------------------------------------------");
            for (Appointment appointment : appointments) {
                System.out.printf("%-20s %-20s %-15s\n", 
                    appointment.getPatientName(), 
                    appointment.getDoctorName(), 
                    appointment.getDate()
                );
            }
        }
    }
  
    private static Doctor findDoctorByName(String doctorName) {
        for (Doctor doctor : doctors) {
            if (doctor.getName().equalsIgnoreCase(doctorName)) {
                return doctor; // Return the doctor if found
            }
        }
        return null; // Return null if no matching doctor is found
    }

    private static void addDoctor(Scanner scanner) {
        System.out.println("\n--- Add Doctor ---");
        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Doctor Specialty: ");
        String specialty = scanner.nextLine();
        
        System.out.print("Enter Doctor Experience (in years): ");
        int experience = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        System.out.print("Enter Doctor Room Number: "); // Prompt for room number
        String roomNumber = scanner.nextLine();
        
        System.out.print("Enter Doctor Visiting Time: "); // Prompt for visiting time
        String visitingTime = scanner.nextLine();
        
        // Create a new Doctor object with the additional attributes
        doctors.add(new Doctor(name, specialty, experience, roomNumber, visitingTime));
        
        System.out.println("Doctor added successfully!");
    }

    private static void viewDoctors() {
        System.out.println("\n--- List of Doctors ---");
        if (doctors.isEmpty()) {
            System.out.println("No doctors found!");
        } else {
            // Print header for the output
            System.out.printf("%-5s %-25s %-20s %-10s %-10s\n", "ID", "Name", "Specialty", "Room No", "Visiting Time");
            System.out.println("-------------------------------------------------------------");
            
            for (int i = 0; i < doctors.size(); i++) {
                Doctor doctor = doctors.get(i);
                System.out.printf("%-5d %-25s %-20s %-10s %-10s\n",
                    (i + 1), 
                    doctor.getName(), 
                    doctor.getSpecialty(), 
                    doctor.getRoomNumber(),  // Assuming this method exists in the Doctor class
                    doctor.getVisitingTime()  // Assuming this method exists in the Doctor class
                );
            }
        }
    }

    private static void addReceptionist(Scanner scanner) {
        System.out.println("\n--- Add Receptionist ---");
        System.out.print("Enter Receptionist Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Receptionist Age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter Receptionist Gender: ");
        String gender = scanner.nextLine();

        receptionists.add(new Receptionist(name, age, gender));
        System.out.println("Receptionist added successfully!");
    }

    private static void viewReceptionists() {
        System.out.println("\n--- List of Receptionists ---");
        if (receptionists.isEmpty()) {
            System.out.println("No receptionists found!");
        } else {
            for (int i = 0; i < receptionists.size(); i++) {
                System.out.println((i + 1) + ". " + receptionists.get(i));
            }
        }
    }

    private static boolean logIn(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true; // Return true if the credentials match
            }
        }
        return false; // Return false if the credentials do not match
    }

    private static void register(Scanner scanner) {
        System.out.println("\n--- Register ---");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        users.add(new User(username, password));
        System.out.println("Registration successful!");
    }
}




