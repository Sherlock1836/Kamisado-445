public class Player {
    private int score;
    private boolean isBot;

    public Player(boolean isBot) {
        this.isBot = isBot;
    }

    public Square[] getMove() {
        if(isBot)
            return calculateMove();
        else
            return Kamisado.getMoveFromGUI();
    }

    //bots algorithm
    private Square[] calculateMove() {
        return null;
    }
}
