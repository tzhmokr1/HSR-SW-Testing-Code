package ch.hsr.testing.unittest.tdd.worldcup;

import java.util.Objects;

public class GameResult {
    private int teamA, teamB;

    public GameResult(int teamA, int teamB) {
        this.teamA = teamA;
        this.teamB = teamB;
    }

    public int getTeamA() {
        return teamA;
    }

    public int getTeamB() {
        return teamB;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResult result = (GameResult) o;
        return teamA == result.teamA &&
                teamB == result.teamB;
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamA, teamB);
    }

    public int getDelta() {
        return teamA - teamB;
    }

    public boolean isTie() {
        return teamA == teamB;
    }

    public boolean sameWinner(GameResult other) {
        return Math.signum(teamA - teamB) == Math.signum(other.getTeamA() - other.getTeamB());
    }

    @Override
    public String toString() {
        return teamA + ":" + teamB;
    }
}
