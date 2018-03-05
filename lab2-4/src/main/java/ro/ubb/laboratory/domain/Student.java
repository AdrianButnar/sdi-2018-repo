package ro.ubb.laboratory.domain;

/**
 * @version 1.0.0
 * @autor Alexandru Buhai
 */

public class Student {
    private String serialNumber;
    private String name;

    public Student() {

    }

    public Student(String serialNumber, String Name) {
        this.serialNumber = serialNumber;
        this.name = name;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        //can we use ifs here?
        /*
        boolean result1 = this == o;
        boolean result2 = !(o == null || getClass() != o.getClass());

        Student student = (Student) o;
        boolean result3 = serialNumber.equals(student.getName()) && name.equals(student.getName());
        return result3 || result2 || result1;
        */
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (name.equals(student.getName())) return false;
        return serialNumber.equals(student.getSerialNumber());


    }

    @Override
    public int hashCode() {
        int result = serialNumber != null ? serialNumber.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student {" +
                "SerialNumber ='" + serialNumber + '\'' +
                ", Name='" + name + '\'' +
                '}';
    }
}
