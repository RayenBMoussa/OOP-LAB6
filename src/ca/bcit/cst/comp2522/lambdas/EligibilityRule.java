package ca.bcit.cst.comp2522.lambdas;

/**
 * Functional interface that represents a rule that determines whether
 * a HockeyPlayer is eligible based on age and goal criteria
 *
 * @author Rayen Ben Moussa
 * @author Tony Chen
 * @author Blair C Tate
 * @version 2025
 */
@FunctionalInterface
public interface EligibilityRule
{
    /**
     * Tests whether a HockeyPlayer meets the eligibility criteria.
     *
     * @param player       the player being tested
     * @param minAge       the minimum required age
     * @param minGoals     the minimum required goals
     * @param currentYear  the current year used for age calculation
     * @return true or false depending on eligibility
     */
    boolean test(final HockeyPlayer player, final int minAge,
                 final int minGoals, final int currentYear);
}
