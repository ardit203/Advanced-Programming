package Exam.SecondMidtermExam.Task51;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Team implements Comparable<Team> {
    private String name;
    private int goalsScored;
    private int goalsConceded;
    private int won;
    private int lost;
    private int draw;
    private int points;

    public Team(String name) {
        this.name = name;
        this.goalsScored = 0;
        this.goalsConceded = 0;
        this.won = 0;
        this.lost = 0;
        this.draw = 0;
        this.points = 0;
    }

    public void addMatch(int goalsScored, int goalsConceded) {
        this.goalsScored += goalsScored;
        this.goalsConceded += goalsConceded;
        calculateMatchOutcome(goalsScored, goalsConceded);
    }

    private void calculateMatchOutcome(int goalsScored, int goalsConceded) {
        if (goalsScored > goalsConceded) {
            won++;
            points += 3;
        } else if (goalsScored < goalsConceded) {
            lost++;
        } else {
            draw++;
            points++;
        }
    }

    public int gamesPlayed() {
        return won + lost + draw;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int goalDifference() {
        return goalsScored - goalsConceded;
    }

    @Override
    public String toString() {
        return String.format("%-15s%5d%5d%5d%5d%5d", name, gamesPlayed(), won, draw, lost, points);
    }

    @Override
    public int compareTo(Team o) {
        return Comparator.comparing(Team::getPoints, Comparator.reverseOrder())
                .thenComparing(Team::goalDifference, Comparator.reverseOrder())
                .thenComparing(Team::getName)
                .compare(this, o);
    }
}


class FootballTable {
    private Map<String, Team> teams;

    public FootballTable() {
        this.teams = new HashMap<>();
    }


    public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {
        addTeam(homeTeam, homeGoals, awayGoals);
        addTeam(awayTeam, awayGoals, homeGoals);
    }

    private void addTeam(String teamName, int goalsScored, int goalsConceded) {
        teams.computeIfAbsent(teamName, k -> new Team(teamName)).addMatch(goalsScored, goalsConceded);
    }

    public void printTable() {
        Set<Team> teamSet = new TreeSet<>(teams.values());

        int rank = 1;
        for (Team team : teamSet) {
            System.out.printf("%2d. ", rank);
            System.out.println(team);
            rank++;
        }
    }
}


public class FootballTableTest {
    public static void main(String[] args) throws IOException {
        FootballTable table = new FootballTable();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.lines()
                .map(line -> line.split(";"))
                .forEach(parts -> table.addGame(parts[0], parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3])));
        reader.close();
        System.out.println("=== TABLE ===");
        System.out.printf("%-19s%5s%5s%5s%5s%5s\n", "Team", "P", "W", "D", "L", "PTS");
        table.printTable();
    }
}


