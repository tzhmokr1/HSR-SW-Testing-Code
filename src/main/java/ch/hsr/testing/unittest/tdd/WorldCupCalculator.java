package ch.hsr.testing.unittest.tdd;

public class WorldCupCalculator {
    public int calculate(GameResult tipp, GameResult result) {
        if (tipp==null || result== null){
            throw new IllegalArgumentException("GameResults must not be null!");
        }
        if (tipp.equals(result)) {
            return 3;
        } else if (tipp.getDelta() == result.getDelta()) {
            if (tipp.isTie()) {
                return 1;
            } else {
                return 2;
            }
        } else if (tipp.sameWinner(result)) {
            return 1;
        }
        return 0;
    }
}
