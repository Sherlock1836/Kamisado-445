public class Player {
    private int score;
    private boolean isBot;

    public Player(boolean isBot) {
        this.isBot = isBot;
        score = 0;
    }

    public Square[] getMove() {
        if(isBot)
            return calculateMove();
        else
            return Kamisado.getMoveFromGUI();
    }

    public void addToScore(int n) {
        score += n;
    }

    public void resetScore() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    //bots algorithm
    private Square[] calculateMove() {
        return null;
    }
}
