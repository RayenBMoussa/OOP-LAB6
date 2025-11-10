package ca.bcit.cst.comp2522.lambdas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * models a simple hockey team
 *
 * @author Rayen Ben Moussa
 * @author Tony Chen
 * @author Blair C Tate
 * @version 2025
 */
public class Main
{
    /**
     * Creates a sample hockey team with a few players.
     *
     * @return a HockeyTeam instance containing sample players
     */
    private static HockeyTeam sampleTeam()
    {
        final List<HockeyPlayer> players;
        players = new ArrayList<>();

        players.add(new HockeyPlayer("Alex Morgan", "F", 2002, 21));
        players.add(new HockeyPlayer("Ben Carter", "D", 1999, 6));
        players.add(new HockeyPlayer("Casey Young", "F", 2004, 28));
        players.add(new HockeyPlayer("Drew Singh", "G", 2000, 0));
        players.add(new HockeyPlayer("Eva Chen", "D", 2001, 5));
        players.add(new HockeyPlayer("Frank Liu", "F", 1998, 12));

        final HockeyTeam team;
        team = new HockeyTeam("BCIT Blizzards", players);
        return team;
    }

    /**
     * Main entry point of the program.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args)
    {
        final HockeyTeam team;
        final List<HockeyPlayer> roster;
        int currentYear;


        currentYear = 2025;
        team = sampleTeam();
        roster = team.getRoster();

        System.out.println("Team: " + team.getName());
        System.out.println("Initial roster:");
        roster.forEach(player -> System.out.println("  " + player));
        System.out.println();


        // Supplier — creates and returns a new HockeyPlayer instance

        final Supplier<HockeyPlayer> callUp;
        callUp = () -> new HockeyPlayer("Jordan Park", "F", 2003, 3);

        roster.add(callUp.get());

        System.out.println("After call-up added:");
        roster.forEach(player -> System.out.println("  " + player));
        System.out.println();


        //Predicate — test conditions on HockeyPlayer objects

        final Predicate<HockeyPlayer> isForward;
        final Predicate<HockeyPlayer> has20Plus;
        final int maxGoals;

        isForward = player -> "F".equalsIgnoreCase(player.getPosition());
        maxGoals = 20;
        has20Plus = player -> player.getGoals() >= maxGoals;


        System.out.println("Forwards with 20+ goals:");

        for (final HockeyPlayer player : roster)
        {
            if (isForward.test(player) && has20Plus.test(player))
            {
                System.out.println("  " + player.getName() + " — " + player.getGoals() + "G");
            }
        }
        System.out.println();

        //Function — transforms a HockeyPlayer into a descriptive string

        final Function<HockeyPlayer, String> playerLabel;
        playerLabel = player -> String.format("%s — %dG", player.getName(), player.getGoals());

        System.out.println("Player labels:");
        for (HockeyPlayer player : roster)
        {
            System.out.println("  " + playerLabel.apply(player));
        }
        System.out.println();

        //Consumer — prints each player’s name

        final Consumer<HockeyPlayer> printName;
        printName = player -> System.out.println(player.getName());

        System.out.println("Roster names (Consumer):");
        roster.forEach(printName);
        System.out.println();

        // 5) UnaryOperator — converts player names to uppercase

        final UnaryOperator<String> toUpper;
        toUpper = text -> text.toUpperCase();

        System.out.println("Roster names (uppercase):");

        for (HockeyPlayer player : roster)
        {
            System.out.println("  " + toUpper.apply(player.getName()));
        }

        System.out.println();

        //Comparator — sorts players by goals (descending)

        final Comparator<HockeyPlayer> byGoalsDesc;
        byGoalsDesc = (a, b) -> Integer.compare(b.getGoals(), a.getGoals());

        Collections.sort(roster, byGoalsDesc);

        System.out.println("Roster sorted by goals (desc):");
        roster.forEach(player ->
                System.out.println("  " + player.getName() + " — " + player.getGoals() + "G"));
        System.out.println();

        //Aggregation — sums all team goals using a basic loop

        int totalGoals;
        totalGoals = 0;

        for (HockeyPlayer player : roster)
        {
            totalGoals += player.getGoals();
        }

        System.out.println("Total team goals: " + totalGoals);
        System.out.println();

        //Custom Functional Interface — EligibilityRule

        final EligibilityRule rule;
        rule = (player, minAge, minGoals, year) ->
        {
            final int age;
            age = year - player.getYearOfBirth();

            return age >= minAge && player.getGoals() >= minGoals;
        };

        final int minAge;
        minAge = 20;

        final int minGoals;
        minGoals = 15;

        System.out.println("Eligible players (age >= " +
                minAge + " and goals >= " +
                minGoals);

        for (HockeyPlayer player : roster)
        {
            if (rule.test(player, minAge, minGoals, currentYear))
            {
                final int age;
                age = currentYear - player.getYearOfBirth();

                System.out.println("  " + player.getName() +
                        " — age " + age +
                        ", " + player.getGoals() + "G");
            }
        }
    }
}
