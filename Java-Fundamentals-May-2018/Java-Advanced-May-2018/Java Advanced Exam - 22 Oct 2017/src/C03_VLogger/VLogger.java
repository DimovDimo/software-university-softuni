package C03_VLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VLogger {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, User> vloggers = new HashMap<>();

        while (true){
            String line = reader.readLine();
            if ("Statistics".equals(line)){
                break;
            }
            
            String[] tokens = line.split(" ");

            String name = tokens[0];
            String command = tokens[1];
            String nameFollowed = tokens[2];

            if (command.equals("joined")){
                if (vloggers.containsKey(name) == false){
                    User user = new User(name);
                    vloggers.put(name, user);
                }
            } else {
                if (vloggers.containsKey(name) &&
                        vloggers.containsKey(nameFollowed) &&
                        name.equals(nameFollowed) == false &&
                        vloggers.get(name).following.contains(nameFollowed) == false){
                    vloggers.get(name).following.add(nameFollowed);
                    vloggers.get(nameFollowed).followers.add(name);
                }
            }
        }

        StringBuilder statistics = new StringBuilder();
        statistics.append(String.format("The V-Logger has a total of %d vloggers in its logs.", vloggers.size()))
                .append(System.lineSeparator());
        final int[] counter = {0};
        List<String> nameOfFamousVloggers = new ArrayList<>();
        vloggers.entrySet().stream()
                .sorted((user1, user2) -> {
                    int amountFollowersUser1 = user1.getValue().followers().size();
                    int amountFollowersUser2 = user2.getValue().followers().size();
                    int comparatorByFollowers = amountFollowersUser2 - amountFollowersUser1;

                    if (comparatorByFollowers != 0){
                         return comparatorByFollowers;
                     }

                     int amountFollowingUser1 = user1.getValue().following().size();
                    int amountFollowingUser2 = user2.getValue().following().size();
                    return amountFollowingUser2 - amountFollowingUser1;
                }).forEach(famosUser -> {
                    nameOfFamousVloggers.add(famosUser.getKey());
        });

        String famous = nameOfFamousVloggers.get(0);
        statistics.append(String.format("%d. %s : %d followers, %d following", ++counter[0],
                famous,
                vloggers.get(famous).followers().size(),
                vloggers.get(famous).following().size()
                )).append(System.lineSeparator());

        if (vloggers.get(famous).followers.size() != 0){
            vloggers.get(famous).followers().stream()
                    .sorted((f1, f2) -> {
                        return f1.compareTo(f2);
                    }).forEach(follower -> {
                statistics.append(String.format("*  %s", follower))
                        .append(System.lineSeparator());
            });
        }

        vloggers.remove(famous);
        vloggers.entrySet().stream()
                .sorted((user1, user2) -> {
                    int amountFollowersUser1 = user1.getValue().followers().size();
                    int amountFollowersUser2 = user2.getValue().followers().size();
                    int comparatorByFollowers = amountFollowersUser2 - amountFollowersUser1;

                    if (comparatorByFollowers != 0){
                        return comparatorByFollowers;
                    }

                    int amountFollowingUser1 = user1.getValue().following().size();
                    int amountFollowingUser2 = user2.getValue().following().size();
                    return amountFollowingUser1 - amountFollowingUser2;
                }).forEach(user -> {
                    statistics.append(String.format("%d. %s : %d followers, %s following", ++counter[0],
                            user.getKey(),
                            user.getValue().followers.size(),
                            user.getValue().following.size()))
                            .append(System.lineSeparator());
        });

        System.out.println(statistics.toString());
    }
}

class User{
    
    public String name;

    public List<String> followers;

    public List<String> following;

    public User(String name) {
        this.name = name;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> followers() {
        return followers;
    }

    public List<String> following() {
        return following;
    }
}