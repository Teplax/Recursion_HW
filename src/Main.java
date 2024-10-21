import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[] memory = new int[365];
        compare(1, memory);
        compare(2, memory);
        compare(5, memory);
        compare(15, memory);
    }

    public static void compare(int day, int[] memory) {
        System.out.println("=== Day " + day + " ===");
        int[] startNumbers = {21, 1, 20, 23};
        int iterative = chooseHobbyIterative(startNumbers, day);
        int recursive = chooseHobbyRecursive(startNumbers, day, memory);
        System.out.println("Iterative = " + iterative + " | Recursive = " + recursive);
        System.out.println();
    }

    public static int chooseHobbyRecursive(int[] startNumbers, int day, int[] memory) {

        if (memory[day - 1] == 0) {
            int day1 = (startNumbers[startNumbers.length - 1] * startNumbers[startNumbers.length - 3]) % 10 + 1;

            if (day == 1) {
                memory[day - 1] = day1;
                return day1;
            }

            int day2 = (day1 * startNumbers[startNumbers.length - 2]) % 10 + 1;
            if (day == 2) {
                memory[day - 1] = day2;
                return day2;
            }

            int day3 = (day2 * startNumbers[startNumbers.length - 1]) % 10 + 1;
            if (day == 3) {
                memory[day - 1] = day3;
                return day3;
            }
            memory[day - 1] = (chooseHobbyRecursive(startNumbers, day - 1, memory) * chooseHobbyRecursive(startNumbers, day - 3, memory)) % 10 + 1;
        }
        return memory[day - 1];
    }

    public static int chooseHobbyIterative(int[] startNumbers, int day) {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(startNumbers[0]);
        numbers.add(startNumbers[1]);
        numbers.add(startNumbers[2]);
        numbers.add(startNumbers[3]);

        for (int d = 0; d < day; d++) {
            int index = d + 4; // индексы дней в массиве сдвинуты на 4
            int prev = numbers.get(index - 1); // предыдущее значение
            int prePrePrev = numbers.get(index - 3); // пре-пре-предыдущее значение
            numbers.add((prev * prePrePrev) % 10 + 1);
        }

        return numbers.get(numbers.size() - 1);
    }

}