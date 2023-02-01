package numbers;

import java.util.Objects;
import java.util.Scanner;


public class Main {

    private static final String INPUT = "Enter a request:";
    private static final String FIRST_ERROR = "The first parameter should be a natural number or zero.";
    private static final String SECOND_ERROR = "The second parameter should be a natural number.";

    private static final String AVIABLE_PROPERTIES1 = "Available properties:";
    private static final String AVIABLE_PROPERTIES2 = "[EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]";
    private static final String NO_NUMBERS = "There are no numbers with these properties.";
    private static final String WELCOME = """
Welcome to Amazing Numbers!

Supported requests:
- enter a natural number to know its properties;
- enter two natural numbers to obtain the properties of the list:
  * the first parameter represents a starting number;
  * the second parameter shows how many consecutive numbers are to be processed;
- two natural numbers and properties to search for;
- a property preceded by minus must not be present in numbers;
- separate the parameters with one space;
- enter 0 to exit.
                """;

    public static void main(String[] args) {

        System.out.println(WELCOME);
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println(INPUT);
            String[] inputNumbers = input.nextLine().toLowerCase().trim().split(" ");
            if (Objects.equals(inputNumbers[0], "0")) {
                break;
            }
            if (inputNumbers.length == 2) {
                long number = Long.parseLong(inputNumbers[0]);
                int numbersCount = Integer.parseInt(inputNumbers[1]);
                System.out.println();

                if (checkCorrectNumber(inputNumbers[1])) {
                    System.out.println(SECOND_ERROR);
                    continue;
                }

                for (int i = 0; i < numbersCount; i++) {
                    System.out.println(processSeveralNumber(number + i));
                }
                System.out.println();
            } else if (inputNumbers.length >= 3) {
                long number = Long.parseLong(inputNumbers[0]);
                int numbersCount = Integer.parseInt(inputNumbers[1]);

                String[] PositiveProperties = makePositiveProperties(inputNumbers);
                String[] NegativeProperties = makeNegativeProperties(inputNumbers);

                if (!checkProperty(inputNumbers)) {
                     continue;
                }

                if (!checkPropertySamePair(PositiveProperties, NegativeProperties)) {
                    continue;
                }

                int x = 0;
                while (numbersCount > 0) {
                    boolean allChecksTrigger = true;
                    for (String in: PositiveProperties) {
                        if (checkNumber(in, number + x)) {

                        }
                        else {
                            allChecksTrigger = false;
                            break;
                        }
                    }

                    if (allChecksTrigger) {
                        for (String in: NegativeProperties) {
                            if (checkNumber(in, number + x)) {
                                allChecksTrigger = false;
                                break;
                            }
                        }
                    }


                    if (allChecksTrigger) {
                        System.out.println(processSeveralNumber(number + x));
                        numbersCount--;
                    }
                    x++;
                }
                System.out.println();
            } else {
                processNumber(inputNumbers[0]);
            }
        }
        System.out.println();
        System.out.println("Goodbye!");
    }

    private static boolean checkPropertySamePair(String[] positiveProperties, String[] negativeProperties) {
        for (String x: positiveProperties) {
            for (String y: negativeProperties) {
                if (Objects.equals(x,y)) {
                    System.out.println("The request contains mutually exclusive properties: ["+ x.toUpperCase() + ", -" + y.toUpperCase() + "]");
                    System.out.println(NO_NUMBERS);
                    return false;
                }
            }
        }
        return true;
    }

    private static String[] makePositiveProperties(String[] inputNumbers) {
        String[] prePositiveList = new String[20];
        int x = 0;
        for (int i = 2; i < inputNumbers.length; i++) {
            if (!Objects.equals(inputNumbers[i].substring(0, 1),"-")) {
                prePositiveList[x] = inputNumbers[i];
                x++;
            }
        }
        int z = 0;
        for (String temp: prePositiveList) {
            if (!Objects.equals(temp, null)) {
                z++;
            }
        }
        String[] output = new String[z];
        System.arraycopy(prePositiveList, 0, output, 0, z);
        return output;
    }

    private static String[] makeNegativeProperties(String[] inputNumbers) {
        String[] preNegativeList = new String[20];
        int x = 0;
        for (int i = 2; i < inputNumbers.length; i++) {
            if (Objects.equals(inputNumbers[i].substring(0, 1),"-")) {
                preNegativeList[x] = inputNumbers[i];
                x++;
            }
        }
        int z = 0;
        for (String temp: preNegativeList) {
            if (!Objects.equals(temp, null)) {
                z++;
            }
        }
        String[] output = new String[z];
        for (int t = 0; t < z; t++) {
            output[t] = preNegativeList[t].substring(1);
        }
        return output;
    }

    private static boolean checkPropertyPair(String[] inputNumbers, String word1, String word2) {
        boolean firstWord = false;
        boolean secondWord = false;

        for (int i = 2; i < inputNumbers.length; i++) {
            if (Objects.equals(inputNumbers[i], word1)) {
                firstWord = true;
            }
            if (Objects.equals(inputNumbers[i], word2)) {
                secondWord = true;
            }
        }
        if (firstWord && secondWord) {
            System.out.println("The request contains mutually exclusive properties: ["+ word1.toUpperCase() + ", " + word2.toUpperCase() + "]");
            System.out.println(NO_NUMBERS);
            return true;
        }
        return false;
    }

    private static boolean checkProperty(String[] inputNumbers) {
        // Check all word
        StringBuilder output = new StringBuilder();
        int wordWithError = 0;
        for (int i = 2; i < inputNumbers.length; i++) {
            boolean errorTrigger = true;
            for (MAGIC x : MAGIC.values()) {
                if (Objects.equals(inputNumbers[i], x.name()) || Objects.equals(inputNumbers[i].substring(1), x.name())) {
                    errorTrigger = false;
                }
            }
            if (errorTrigger) {
                wordWithError++;
            }
            output.append(inputNumbers[i]).append(", ");
        }
        if (wordWithError == 1) {
            System.out.println("The property [" + output.substring(0,output.length() - 2) + "] is wrong.");
            System.out.println(AVIABLE_PROPERTIES1);
            System.out.println(AVIABLE_PROPERTIES2);
            return false;
        }
        if (wordWithError > 1) {
            System.out.println("The properties [" + output.substring(0,output.length() - 2) + "] are wrong.");
            System.out.println(AVIABLE_PROPERTIES1);
            System.out.println(AVIABLE_PROPERTIES2);
            return false;
        }



        if (checkPropertyPair(inputNumbers, "even", "odd")) return false;
        if (checkPropertyPair(inputNumbers, "duck", "spy")) return false;
        if (checkPropertyPair(inputNumbers, "sunny", "square")) return false;
        if (checkPropertyPair(inputNumbers, "happy", "sad")) return false;

        if (checkPropertyPair(inputNumbers, "even", "-even")) return false;
        if (checkPropertyPair(inputNumbers, "odd", "-odd")) return false;
        return !checkPropertyPair(inputNumbers, "-odd", "-even");
    }

    private static String processSeveralNumber(long number) {
        StringBuilder outputLine = new StringBuilder();
        outputLine.append(number);
        outputLine.append(" is ");

        if (isBuzzNumber(number)) {
            outputLine.append(" buzz,");
        }

        if (isGapfulNumber(number)) {
            outputLine.append(" gapful,");
        }

        if (isDuckNumber(number)) {
            outputLine.append(" duck,");
        }

        if (isPalindromicNumber(number)) {
            outputLine.append(" palindromic,");
        }

        if (isSpyNumber(number)) {
            outputLine.append(" spy,");
        }

        if (isSunnyNumber(number)) {
            outputLine.append(" sunny,");
        }

        if (isJumpingNumber(number)) {
            outputLine.append(" jumping,");
        }

        if (isSquareNumber(number)) {
            outputLine.append(" square,");
        }

        if (isHappyNumber(number)) {
            outputLine.append(" happy,");
        }

        if (isSadNumber(number)) {
            outputLine.append(" sad,");
        }

        if (isEvenNumber(number)) {
            outputLine.append(" even");
        } else {
            outputLine.append(" odd");
        }
        return outputLine.toString();
    }

    private static void processNumber(String inputNumber) {
        long digit;

        if (checkCorrectNumber(inputNumber)) {
            System.out.println(FIRST_ERROR);
            return;
        } else {
            digit = Long.parseLong(inputNumber);
        }

        if (digit > 0) {
            System.out.println("Properties of " + digit);
            System.out.println("\tbuzz: " + isBuzzNumber(digit));
            System.out.println("\tduck: " + isDuckNumber(digit));
            System.out.println("\t palindromic: " + isPalindromicNumber(digit));
            System.out.println("\tgapful: " + isGapfulNumber(digit));
            System.out.println("\tspy: " + isSpyNumber(digit));
            System.out.println("\tsquare: " + isSquareNumber(digit));
            System.out.println("\tsunny: " + isSunnyNumber(digit));
            System.out.println("\tjumping: " + isJumpingNumber(digit));
            System.out.println("\thappy: " + isHappyNumber(digit));
            System.out.println("\tsad: " + isSadNumber(digit));
            System.out.println("\teven: " + isEvenNumber(digit));
            System.out.println("\todd: " + isOddNumber(digit));
        } else if (digit < 0) {
            System.out.println(FIRST_ERROR);
        }

    }

    private static boolean checkCorrectNumber(String stringInput) {
        long parseNumber;
        try {
            parseNumber = Long.parseLong(stringInput);
        }
        catch (Exception e) {
            return true;
        }
        return parseNumber < 0;

    }

    private static boolean isGapfulNumber(long digit) {
        String[] digitInStringArray = Long.toString(digit).split("");
        int lenght = digitInStringArray.length;
        if (lenght <= 2) {
            return false;
        }
        int divider = 10 * Integer.parseInt(digitInStringArray[0]) + Integer.parseInt(digitInStringArray[lenght - 1]);
        return digit % divider == 0;
    }

    private static boolean isPalindromicNumber(long digit) {
        String numberStr = String.valueOf(digit);
        String reverseNymberStr = new StringBuilder(numberStr).reverse().toString();

        return Objects.equals(numberStr, reverseNymberStr);
    }

    private static boolean isDuckNumber(long digit) {
        return String.valueOf(digit).indexOf('0') > 0;
    }

    private static boolean isSpyNumber(long digit) {
        String[] digitInStringArray = Long.toString(digit).split("");
        int a = 0;
        int b = 1;
        for (String x : digitInStringArray) {
            a = a + Integer.parseInt(x);
            b = b * Integer.parseInt(x);
        }
        return a == b;
    }

    private static boolean isBuzzNumber(long digit) {
        return isDivisible7(digit) || isWith7(digit);
    }

    private static boolean isWith7(long digit) {
        return digit - 10 * (digit / 10) == 7;
    }

    private static boolean isDivisible7(long digit) {
        return digit % 7 == 0;
    }

    private static boolean isSunnyNumber(long digit) { return isSquareNumber(digit + 1);}

    private static boolean isJumpingNumber(long digit) {
        long sub;
        String[] stringDigitArray = Long.toString(digit).split("");
        if (stringDigitArray.length <= 1) {
            return true;
        }

        for (int x = 1; x < stringDigitArray.length; x++) {
            sub = Math.abs(Long.parseLong(stringDigitArray[x - 1]) - Long.parseLong(stringDigitArray[x]));
            if (sub == 0 || sub > 1 ) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSquareNumber(long digit) {return Math.sqrt(digit)%1.0 == 0; }


    private static long calculateSumSquareDigit(long digit) {
        long sumSquareDigit = 0;
        while (digit > 0) {
            sumSquareDigit = (long) (sumSquareDigit + Math.pow(digit % 10,2));
            digit = digit / 10;
        }
        return sumSquareDigit;
    }

    private static boolean isHappyNumber(long digit) {
        boolean firstCalculation = true;
        while (digit > 9 || firstCalculation) {
            digit = calculateSumSquareDigit(digit);
            firstCalculation = false;
        }
        return digit == 1;
    }


    private static boolean isSadNumber(long digit) {
        return !isHappyNumber(digit);
    }

    private static boolean isOddNumber(long digit) {
        return !isEvenNumber(digit);
    }



    private static boolean isEvenNumber(long digit) {
        return digit % 2 == 0;
    }



    public static boolean checkNumber(String inputMode, long currentNumber) {
        if (Objects.equals(inputMode, MAGIC.buzz.name())) {
            if (isBuzzNumber(currentNumber)) {
                return true;
            }
        }

        if (Objects.equals(inputMode, MAGIC.duck.name())) {
            if (isDuckNumber(currentNumber)) {
                return true;
            }
        }

        if (Objects.equals(inputMode, MAGIC.palindromic.name())) {
            if (isPalindromicNumber(currentNumber)) {
                return true;
            }
        }

        if (Objects.equals(inputMode, MAGIC.gapful.name())) {
            if (isGapfulNumber(currentNumber)) {
                return true;
            }
        }

        if (Objects.equals(inputMode, MAGIC.spy.name())) {
            if (isSpyNumber(currentNumber)) {
                return true;
            }
        }

        if (Objects.equals(inputMode, MAGIC.square.name())) {
            if (isSquareNumber(currentNumber)) {
                return true;
            }
        }

        if (Objects.equals(inputMode, MAGIC.happy.name())) {
            if (isHappyNumber(currentNumber)) {
                return true;
            }
        }

        if (Objects.equals(inputMode, MAGIC.sad.name())) {
            if (isSadNumber(currentNumber)) {
                return true;
            }
        }

        if (Objects.equals(inputMode, MAGIC.sunny.name())) {
            if (isSunnyNumber(currentNumber)) {
                return true;
            }
        }

        if (Objects.equals(inputMode, MAGIC.jumping.name())) {
            if (isJumpingNumber(currentNumber)) {
                return true;
            }
        }

        if (Objects.equals(inputMode, MAGIC.even.name())) {
            if (isEvenNumber(currentNumber)) {
                return true;
            }
        }

        if (Objects.equals(inputMode, MAGIC.odd.name())) {
            return !isEvenNumber(currentNumber);
        }
        return false;
    }
}

enum MAGIC {
    buzz, duck, palindromic, gapful, spy, square, sunny, jumping, happy, sad, even, odd

}