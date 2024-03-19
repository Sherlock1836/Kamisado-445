public class MoveValidator {
    public boolean checkValidityOf(Square[] move) {
        if(move[1].equals(move[0]))
            return false;
        if(move[1].getDragonTower() != null)
            return false;
        return true;
    }
}
