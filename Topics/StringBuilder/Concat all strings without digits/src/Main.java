import java.util.Arrays;
import java.util.Scanner;

class ConcatenateStringsProblem {

    public static String concatenateStringsWithoutDigits(String[] strings) {
        StringBuilder string = new StringBuilder();
        Arrays.stream(strings).forEach(x -> string.append(x.replaceAll("\\d","")));
        return string.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}