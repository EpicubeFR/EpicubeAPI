package fr.epicube.api.website;

/**
 * Represents the statistics of an Epicube player.
 */
public class EpicubePlayerStats {
    
    private final String name;
    private final String rankName;
    private long playedTime;
    private int totalGame;

    /**
     * Constructs an instance of EpicubePlayerStats.
     *
     * @param name       the name of the player
     * @param rankName   the rank of the player
     * @param playedTime the total time played by the player
     * @param totalGame  the total games played by the player
     */
    public EpicubePlayerStats(String name, String rankName, long playedTime, int totalGame) {
        this.name = name;
        this.rankName = rankName;
        this.playedTime = playedTime;
        this.totalGame = totalGame;
    }

    /**
     * Gets the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the total games played by the player.
     *
     * @return the total games played
     */
    public int getTotalGame() {
        return totalGame;
    }

    /**
     * Sets the total games played by the player.
     *
     * @param totalGame the total games played
     */
    public void setTotalGame(int totalGame) {
        this.totalGame = totalGame;
    }

    /**
     * Gets the total time played by the player.
     *
     * @return the total time played
     */
    public long getPlayedTime() {
        return playedTime;
    }

    /**
     * Gets the rank name of the player.
     *
     * @return the rank name
     */
    public String getRankName() {
        return rankName;
    }

    @Override
    public String toString() {
        return "EpicubePlayerStats{" +
                "name='" + name + '\'' +
                ", rankName='" + rankName + '\'' +
                ", playedTime=" + playedTime +
                ", totalGame=" + totalGame +
                '}';
    }
}
