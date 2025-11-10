package ca.bcit.cst.comp2522.lambdas;

/**
 * Represents a hockey player with basic attributes such as name,
 * position, year of birth, and goals scored.

 *
 * @author Rayen Ben Moussa
 * @author Tony Chen
 * @author Blair C Tate
 * @version 2025
 */
public class HockeyPlayer
{
    private final String name;
    private final String position;
    private final int yearOfBirth;
    private final int goals;

    /**
     * Constructs a HockeyPlayer object with the specified details.
     *
     * @param name        the player's full name
     * @param position    the player's position
     * @param yearOfBirth the player's year of birth
     * @param goals       the number of goals scored
     * @throws IllegalArgumentException if any argument fails validation
     */
    public HockeyPlayer(String name, String position, int yearOfBirth, int goals)
    {
        validateName(name);
        validatePosition(position);
        validateYearOfBirth(yearOfBirth);
        validateGoals(goals);


        this.name = name;
        this.position = position;
        this.yearOfBirth = yearOfBirth;
        this.goals = goals;
    }


    /**
     * Validates that the player's name.
     *
     * @param name the name to validate
     * @throws IllegalArgumentException if the name is not valid
     */
    private void validateName(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Name is not valid.");
        }
    }

    /**
     * Validates that the player's position.
     *
     * @param position the position to validate
     * @throws IllegalArgumentException if the position is not valid
     */
    private void validatePosition(String position)
    {
        if (position == null ||
                !(position.equals("F") ||
                        position.equals("D") ||
                        position.equals("G")))
        {
            throw new IllegalArgumentException("Position not valid");
        }
    }

    /**
     * Validates year of birth is valid.
     *
     * @param yearOfBirth the year to validate
     * @throws IllegalArgumentException if the year is not valid
     */
    private void validateYearOfBirth(int yearOfBirth)
    {
        final int maxYear;
        final int minYear;

        maxYear = 2025;
        minYear = 1970;

        if (yearOfBirth < minYear || yearOfBirth > maxYear)
        {
            throw new IllegalArgumentException(
                    "Year of birth must be between " +
                            minYear + " and " + minYear);
        }
    }

    /**
     * Validates that the goals count.
     *
     * @param goals the goal count to validate
     * @throws IllegalArgumentException if goals is not valid
     */
    private void validateGoals(int goals)
    {
        final int minGoals;
        minGoals = 0;

        if (goals < minGoals)
        {
            throw new IllegalArgumentException("Goals cannot be less than " +
                    minGoals);
        }
    }


    /**
     * Returns the player's full name.
     *
     * @return the player's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the player's position.
     *
     * @return the player's position
     */
    public String getPosition()
    {
        return position;
    }

    /**
     * Returns the player's year of birth.
     *
     * @return the player's year of birth
     */
    public int getYearOfBirth()
    {
        return yearOfBirth;
    }

    /**
     * Returns the number of goals scored by the player.
     *
     * @return the number of goals
     */
    public int getGoals()
    {
        return goals;
    }


    /**
     * Returns a string representation of a HockeyPlayer.
     *
     * @return a formatted string
     */
    @Override
    public String toString()
    {
        return String.format("%s — %s — born %d — %dG",
                name, position, yearOfBirth, goals);
    }
}
