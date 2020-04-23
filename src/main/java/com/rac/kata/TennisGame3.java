package com.rac.kata;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;

import static com.rac.kata.GameUtils.*;

/**
 * Tennis game 3.
 */
@Builder
@AllArgsConstructor
public class TennisGame3 implements TennisGame {

    private int p1;
    private int p2;
    private final String p1N;
    private final String p2N;

    /**
     * Get Score.
     *
     * @return The final score.
     */
    @Override
    public String getScore() {

        if (p1 < 4 && p2 < 4 && (p1 + p2 != 6)) {
            var scoreList = List.of(LOVE, FIFTEEN, THIRTY, FORTY);
            var score = scoreList.get(p1);

            if (p1 == p2) {
                return score + ALL;
            }
            return score + HYPHEN + scoreList.get(p2);
        }

        if (p1 == p2) {
            return DEUCE;
        }

        var score = p1 > p2 ? p1N : p2N;
        return ((p1 - p2) * (p1 - p2) == 1) ? ADVANTAGE + score : WIN_FOR + score;
    }

    public void wonPoint(String playerName) {
        if (PLAYER_1.equals(playerName)) {
            this.p1++;
        } else {
            this.p2++;
        }
    }

}
