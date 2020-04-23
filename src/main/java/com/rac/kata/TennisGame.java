package com.rac.kata;

/**
 * Tennis game interface.
 */
public interface TennisGame {

    /**
     * Won point.
     *
     * @param playerName The player name
     */
    void wonPoint(String playerName);

    /**
     * @return The score.
     */
    String getScore();
}
