
class Employee {
    String name;
    int salary;
    String address;

    Employee() {
        this.name = "unknown";
        this.address = "unknown";
        this.salary = 0;
    }

    Employee(String name, int salary) {
        this();
        this.name = name;
        this.salary = salary;
    }

    Employee(String name, int salary, String address) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }
}