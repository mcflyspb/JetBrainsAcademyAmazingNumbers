
class Person {
    int age;
    Person(int age) {
        this.age = age;
}

// Do not change the code below
class Main {
    public static void main(String[] args) {
        Person p1 = new Person(1);
        Person p2 = new Person(2);
        Person temp = new Person(0);

        temp = p1;
        p1 = p2;
        p2 = temp;
        System.out.printf("p1: " + p1.age);
        System.out.printf("p2: " + p2.age);
        }
    }
}