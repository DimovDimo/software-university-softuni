import java.util.Scanner;

public class Problem10XBits {
    public static void main(String[] args) {
        int[][] matrix = new int[32][32];
        Scanner scanner = new Scanner(System.in);
        int lastMatrixRow = 0;
        for (int i = 0; i < 8; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            String binaryString = Integer.toBinaryString(number);
            String binaryStringIn32Bits = String.format("%32s", binaryString)
                    .replace(" ", "0");

            //System.out.println(binaryStringIn32Bits);

            char[] binaryCharacters = binaryStringIn32Bits.toCharArray();
            for (int j = 0; j < 32; j++) {
                char currentChar = binaryCharacters[j];
                if (currentChar == '0'){
                    matrix[lastMatrixRow][j] = 0;
                } else {
                    matrix[lastMatrixRow][j] = 1;
                }
            }

            lastMatrixRow++;
        }

        int countXBits = 0;
        for (int row = 0; row < matrix.length - 2; row++) {
            for (int col = 0; col < matrix[0].length - 2; col++) {
                String topRow = "" + matrix[row][col] + matrix[row][col+1] + matrix[row][col+2];
                String middleRow = "" + matrix[row+1][col] + matrix[row+1][col+1] + matrix[row+1][col+2];
                String bottomRow = "" + matrix[row+2][col] + matrix[row+2][col+1] + matrix[row+2][col+2];
                String stringMatrix = topRow + middleRow + bottomRow;
                if (stringMatrix.equals("101010101")){
                    countXBits++;
                }
            }
        }

        System.out.println(countXBits);
    }
}
