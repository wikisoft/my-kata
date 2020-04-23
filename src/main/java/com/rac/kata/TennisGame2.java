package com.rac.kata;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;

import static com.rac.kata.GameUtils.*;

/**
 * Tennis game 2.
 */
@Builder
@AllArgsConstructor
public class TennisGame2 implements TennisGame {

    private int p1Point;
    private int p2Point;
    private final String player1Name;
    private final String player2Name;

    @Override
    public void wonPoint(String player) {
        if (PLAYER_1.equals(player)) {
            p1Point++;
        } else {
            p2Point++;
        }
    }

    /**
     * Get Score.
     *
     * @return The final score.
     */
    @Override
    public String getScore() {

        var scoreList = List.of(LOVE, FIFTEEN, THIRTY, FORTY);

        if (p1Point == p2Point) {
            return p1Point < 3 ? scoreList.get(p1Point) + ALL : DEUCE;
        }

        if (p1Point > 0 && p1Point < 4 && p2Point == 0) {
            return scoreList.get(p1Point) + HYPHEN + LOVE;
        }

        if (p2Point > 0 && p2Point < 4 && p1Point == 0) {
            return LOVE + HYPHEN + scoreList.get(p2Point);
        }

        if (p1Point > p2Point && p1Point < 4) {
            var p1Res = List.of(2, 3).contains(p1Point) ? scoreList.get(p1Point) : null;
            var p2Res = List.of(1, 2).contains(p2Point) ? scoreList.get(p2Point) : null;
            if (p1Res != null && p2Res != null) {
                return p1Res + HYPHEN + p2Res;
            }
        }

        if (p2Point > p1Point && p2Point < 4) {
            var p1Res = List.of(1, 2).contains(p1Point) ? scoreList.get(p1Point) : null;
            var p2Res = List.of(2, 3).contains(p2Point) ? scoreList.get(p2Point) : null;
            if (p1Res != null && p2Res != null) {
                return p1Res + HYPHEN + p2Res;
            }
        }

        return processWinner(p1Point, p2Point);
    }

    /**
     * Process winner player.
     *
     * @param p1Point The P1 point.
     * @param p2Point The P2 point.
     *
     * @return the winner score.
     */
    private static String processWinner(int p1Point, int p2Point) {
        var score  = NOT_SUPPORTED;
        if (p1Point > p2Point && p2Point >= 3) {
            score = ADVANTAGE + PLAYER_1;
        }

        if (p2Point > p1Point && p1Point >= 3) {
            score = ADVANTAGE + PLAYER_2;
        }

        if (p1Point >= 4 && p2Point >= 0 && (p1Point - p2Point) >= 2) {
            score = WIN_FOR + PLAYER_1;
        }

        if (p2Point >= 4 && p1Point >= 0 && (p2Point - p1Point) >= 2) {
            score = WIN_FOR + PLAYER_2;
        }

        return score;
    }
}
