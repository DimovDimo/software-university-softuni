import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Problem02SoftUniParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<String> vip = new TreeSet<>();
        Set<String> regular = new TreeSet<>();
        while (true){
            String line = scanner.nextLine();
            if (line.equals("PARTY")){
                break;
            } else {
                if (isVip(line)){
                    vip.add(line);
                } else {
                    regular.add(line);
                }
            }
        }

        while (true){
            String guest = scanner.nextLine();
            if (guest.equals("END")){
                break;
            } else {
                if (isVip(guest)){
                    vip.remove(guest);
                } else {
                    regular.remove(guest);
                }
            }
        }

        System.out.printf("%d%n", vip.size() + regular.size());
        for (String guestVip : vip) {
            System.out.println(guestVip);
        }

        for (String guest : regular) {
            System.out.println(guest);
        }
    }

    private static boolean isVip(String line) {
        if(line == null){
            return false;
        }

        char firstLetter = line.charAt(0);
        return Character.isDigit(firstLetter);
    }
}
