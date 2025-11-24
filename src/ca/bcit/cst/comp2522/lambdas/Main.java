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
 * Demonstrates the use of Java functional interfaces and lambdas
 *
 * @author Rayen Ben Moussa
 * @author Tony Chen
 * @author Blair C tate
 * @version 1ç
 */
public class Main
{

    /**
     * Creates a Supplier that returns a predefined call-up HockeyPlayer.
     *
     * @return a Supplier producing a new HockeyPlayer
     */
    private static Supplier<HockeyPlayer> callUpSupplier()
    {
        return () -> new HockeyPlayer("Jordan Park", "F", 2003, 3);
    }

    /**
     * Predicate checking whether a player is a forward.
     *
     * @return a Predicate that tests player position equals "F"
     */
    private static Predicate<HockeyPlayer> isForwardPredicate()
    {
        return player -> "F".equalsIgnoreCase(player.getPosition());
    }

    /**
     * Creates a Predicate checking if a player has at least a minimum goal count.
     *
     * @param minGoals required minimum number of goals
     * @return Predicate testing whether player meets the goal threshold
     */
    private static Predicate<HockeyPlayer> hasMinGoalsPredicate(final int minGoals)
    {
        return player -> player.getGoals() >= minGoals;
    }

    /**
     * Function that formats a player's name and goal total into a label.
     *
     * @return Function that converts a player to a formatted String
     */
    private static Function<HockeyPlayer, String> labelFunction()
    {
        return p -> String.format("%s — %dG", p.getName(), p.getGoals());
    }

    /**
     * Consumer that prints a player's name.
     *
     * @return Consumer of HockeyPlayer that prints its name
     */
    private static Consumer<HockeyPlayer> printNameConsumer()
    {
        return p -> System.out.println(p.getName());
    }

    /**
     * UnaryOperator that converts a string to uppercase.
     *
     * @return operator converting text to uppercase
     */
    private static UnaryOperator<String> upperCaseOperator()
    {
        return String::toUpperCase;
    }

    /**
     * Comparator ordering players by goals in descending order.
     *
     * @return Comparator comparing players by goal totals
     */
    private static Comparator<HockeyPlayer> goalsDescendingComparator()
    {
        return (a, b) -> Integer.compare(b.getGoals(), a.getGoals());
    }

    /**
     * Creates the EligibilityRule used to test player eligibility.
     *
     * @return an EligibilityRule implementation
     */
    private static EligibilityRule eligibilityRule()
    {
        return (player, minAge, minGoals, currentYear) -> {
            final int ageYears;
            ageYears = currentYear - player.getYearOfBirth();
            return ageYears >= minAge && player.getGoals() >= minGoals;
        };
    }

    /**
     * Builds a sample hockey team with several predefined players.
     *
     * @return a HockeyTeam with sample roster data
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

        return new HockeyTeam("BCIT Blizzards", players);
    }


    public static void main(final String[] args)
    {

        final int currentYear;
        final int minAgeYears;
        final int minGoals;
        final int goalThreshold;
        final HockeyTeam team;
        final List<HockeyPlayer> roster;
        final Supplier<HockeyPlayer> callUp;
        final Predicate<HockeyPlayer> isForward;
        final Predicate<HockeyPlayer> hasMinPlusGoals;
        final Function<HockeyPlayer, String> playerLabel;
        final Consumer<HockeyPlayer> printName;
        final UnaryOperator<String> toUpper;
        final Comparator<HockeyPlayer> byGoalsDesc;
        final EligibilityRule rule;
        int totalGoals;
        int age;


        currentYear = 2025;
        minAgeYears = 20;
        minGoals = 15;
        goalThreshold = 20;
        team = sampleTeam();
        roster = team.getRoster();
        callUp = callUpSupplier();
        isForward = isForwardPredicate();
        hasMinPlusGoals = hasMinGoalsPredicate(goalThreshold);
        playerLabel = labelFunction();
        printName = printNameConsumer();
        toUpper = upperCaseOperator();
        byGoalsDesc = goalsDescendingComparator();
        rule = eligibilityRule();
        totalGoals = 0;


        System.out.println("Team: " + team.getName());
        System.out.println("Initial roster:");

        for (final HockeyPlayer player : roster)
        {
            System.out.println("  " + player);
        }
        System.out.println();

        // Supplier
        roster.add(callUp.get());

        System.out.println("After call-up added:");

        for (final HockeyPlayer player : roster)
        {
            System.out.println("  " + player);
        }
        System.out.println();

        // Predicates
        System.out.println("Forwards with " + goalThreshold + "+ goals:");
        for (final HockeyPlayer player : roster)
        {
            if (isForward.test(player) && hasMinPlusGoals.test(player))
            {
                System.out.println("  " + player.getName() + " — " + player.getGoals() + "G");
            }
        }
        System.out.println();

        // Function
        System.out.println("Player labels:");

        for (final HockeyPlayer player : roster)
        {
            System.out.println("  " + playerLabel.apply(player));
        }
        System.out.println();

        // Consumer
        System.out.println("Roster names:");

        for (final HockeyPlayer player : roster)
        {
            printName.accept(player);
        }
        System.out.println();

        // UnaryOperator
        System.out.println("Roster names (uppercase):");

        for (final HockeyPlayer player : roster)
        {
            System.out.println("  " + toUpper.apply(player.getName()));
        }
        System.out.println();

        // Comparator
        Collections.sort(roster, byGoalsDesc);
        System.out.println("Roster sorted by goals (desc):");

        for (final HockeyPlayer player : roster)
        {
            System.out.println("  " + player.getName() + " — " + player.getGoals() + "G");
        }
        System.out.println();

        // Aggregation
        for (final HockeyPlayer player : roster)
        {
            totalGoals += player.getGoals();
        }
        System.out.println("Total team goals: " + totalGoals);
        System.out.println();

        // EligibilityRule
        System.out.println("Eligible players (age >= " + minAgeYears +
                " and goals >= " + minGoals + "):");

        for (final HockeyPlayer player : roster)
        {
            age = currentYear - player.getYearOfBirth();

            if (rule.test(player, minAgeYears, minGoals, currentYear))
            {
                System.out.println("  " + player.getName() + " — age " + age + ", " +
                        player.getGoals() + "G");
            }
        }
    }


}
