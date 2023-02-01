import java.util.Arrays;
import java.util.Scanner;

class EvenUpperCase {

    public static String upperEvenLetters(String message) {
        String[] messageArray = message.split("");
        for (int x = 0; x < messageArray.length; x += 2) {
            messageArray[x] = messageArray[x].toUpperCase();
        }
        StringBuilder output = new StringBuilder();
        Arrays.stream(messageArray).forEach(output::append);
        return output.toString();
    }

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message = scanner.next();
        System.out.println(upperEvenLetters(message));
    }
}