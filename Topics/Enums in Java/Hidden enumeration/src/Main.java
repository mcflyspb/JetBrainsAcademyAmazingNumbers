public class Main {

    public static void main(String[] args) {
        int counter = 0;


        Secret[] statuses = Secret.values();
        for (Secret x : statuses) {
            if (x.name().startsWith("STAR")) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
