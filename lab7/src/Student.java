public class Student {
    private String name;
    private int age;
    private String address;

    public Student(String name, int age, String address) {
        this.age = age;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}