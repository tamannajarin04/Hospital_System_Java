
package HospitalManagementSystem;

public class Receptionist {
    private String name;
    private int age;
    private String gender;

    // Constructor
    public Receptionist(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    // Getter for Name
    public String getName() {
        return name;
    }

    // Setter for Name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for Age
    public int getAge() {
        return age;
    }

    // Setter for Age
    public void setAge(int age) {
        this.age = age;
    }

    // Getter for Gender
    public String getGender() {
        return gender;
    }

    // Setter for Gender
    public void setGender(String gender) {
        this.gender = gender;
    }

    // Override toString for proper display
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Gender: " + gender;
    }
}
