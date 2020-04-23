package com.rac.kata;

import lombok.AllArgsConstructor;
import lombok.Builder;

import static com.rac.kata.GameUtils.*;

/**
 * Tennis game 1.
 */
@Builder
@AllArgsConstructor
public class TennisGame1 implements TennisGame {

    private int mScore1;
    private int mScore2;
    private final String player1Name;
    private final String player2Name;

    @Override
    public void wonPoint(String playerName) {
        if (PLAYER_1.equals(playerName)) {
            mScore1++;
        } else {
            mScore2++;
        }
    }

    /**
     * Get Score.
     *
     * @return The final score.
     */
    @Override
    public String getScore() {

        if (mScore1 == mScore2) {
            if (mScore1 >= 3) {
                return DEUCE;
            }
            return processScore(mScore1) + ALL;
        }

        if (mScore1 >= 4 || mScore2 >= 4) {
            return processBigScores(mScore1, mScore2);
        }

        return processScore(mScore1) +
                HYPHEN +
                processScore(mScore2);
    }

    /**
     * Process player score.
     *
     * @param score score.
     *
     * @return The score.
     */
    private static String processScore(int score) {
        switch (score) {
            case 0:
                return LOVE;
            case 1:
                return FIFTEEN;
            case 2:
                return THIRTY;
            case 3:
                return FORTY;
            default:
                return NOT_SUPPORTED;
        }
    }

    /**
     * Process scores when higher than 3.
     *
     * @param score1 Score 1
     * @param score2 Score 2
     *
     * @return The winner.
     */
    private static String processBigScores(int score1, int score2) {
        int minusResult = score1 - score2;

        if (minusResult == 1) {
            return ADVANTAGE + PLAYER_1;
        }

        if (minusResult == -1) {
            return ADVANTAGE + PLAYER_2;
        }

        if (minusResult >= 2) {
            return WIN_FOR + PLAYER_1;
        }

        return WIN_FOR + PLAYER_2;
    }
}
