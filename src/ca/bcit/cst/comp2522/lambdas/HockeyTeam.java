package ca.bcit.cst.comp2522.lambdas;

import java.util.List;

/**
 * Represents a hockey team consisting of a name and a roster of players.
 *
 * @author Rayen Ben Moussa
 * @author Tony Chen
 * @author Blair C Tate
 * @version 2025
 */
public class HockeyTeam
{
    private final String name;
    private final List<HockeyPlayer> roster;

    /**
     * Constructs a HockeyTeam with the specified name and roster.
     *
     * @param name   the name of the team
     * @param roster the list of players
     * @throws IllegalArgumentException if any argument fails validation
     */
    public HockeyTeam(String name, List<HockeyPlayer> roster)
    {
        validateName(name);
        validateRoster(roster);

        this.name = name;
        this.roster = roster;
    }

    /**
     * Validates that the team's name.
     *
     * @param name the team name to validate
     * @throws IllegalArgumentException if the name is not valid
     */
    private void validateName(String name)
    {
        if (name == null || name.trim().isEmpty())
        {
            throw new IllegalArgumentException("Team name is not valid.");
        }
    }

    /**
     * Validates that the team's roster is not valid.
     *
     * @param roster the list of players to validate
     * @throws IllegalArgumentException if the roster is not valid
     */
    private void validateRoster(List<HockeyPlayer> roster)
    {
        if (roster == null || roster.isEmpty())
        {
            throw new IllegalArgumentException("Roster is not valid.");
        }
    }

    /**
     * Returns the team's name.
     *
     * @return the name of the team
     */
    public String getName()
    {
        return name;
    }

    /**
     * Returns the team's roster.
     *
     * @return a list of HockeyPlayer objects
     */
    public List<HockeyPlayer> getRoster()
    {
        return roster;
    }
}
