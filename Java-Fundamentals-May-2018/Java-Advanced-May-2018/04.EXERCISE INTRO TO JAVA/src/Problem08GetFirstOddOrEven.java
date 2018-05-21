import java.util.Scanner;

public class Problem08GetFirstOddOrEven {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] numsAsStrings = scanner.nextLine()
                .split("\\s+");
        int[] nums = new int[numsAsStrings.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(numsAsStrings[i]);
        }

        String[] comands = scanner.nextLine()
                .split("\\s+");
        int count = Integer.parseInt(comands[1]);
        String type = comands[2];
        int currentCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int currentNum = nums[i];
            if (type.equals("odd") &&
                    currentNum % 2 != 0){
                currentCount++;
                System.out.printf("%d ", currentNum);

            } else if (type.equals("even") &&
                    currentNum % 2 == 0){
                currentCount++;
                System.out.printf("%d ", currentNum);
            }

            if (currentCount == count){
                break;
            }
        }
    }
}
