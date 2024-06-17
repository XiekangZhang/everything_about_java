package de.xiekang.comparison;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class test {
    public static void main(String... args) {
        List<Player> footballTeam = new ArrayList<>();
        Player player1 = new Player(59, "John", 20);
        Player player2 = new Player(67, "Roger", 22);
        Player player3 = new Player(45, "Steven", 24);
        footballTeam.add(player1);
        footballTeam.add(player2);
        footballTeam.add(player3);

        System.out.println("Before Sorting: " + footballTeam);
        Collections.sort(footballTeam);
        System.out.println("After Sorting: " + footballTeam);
        Collections.sort(footballTeam, new PlayerRankingComparator());
        System.out.println("Ranking sorting: " + footballTeam);
        Collections.sort(footballTeam, new PlayerAgeComparator());
        System.out.println("Age sorting: " + footballTeam);

        // since java 8
        Comparator byRanking = Comparator.comparingInt(Player::getRanking);
    }
}
