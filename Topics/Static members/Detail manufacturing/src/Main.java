import java.util.Scanner;

class ManufacturingController {
    public static int COUNT = 0;

    public static String requestProduct(String product) {
        COUNT++;
        return COUNT + ". Requested " + product;
    }

    public static int getNumberOfProducts() {
        return COUNT;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String product = scanner.nextLine();
            System.out.println(ManufacturingController.requestProduct(product));
            System.out.println(ManufacturingController.getNumberOfProducts());
        }
    }
}