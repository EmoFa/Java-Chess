package chessgame;
import java.util.*;

/**
 *
 * @author nolca
 */
public class Piece {
    int xp;
    int yp;
    int x;
    int y;
    boolean isWhite;
    LinkedList<Piece> ps;
    String name;
    
    // Function used for creating piece
    public Piece(int xp, int yp, boolean isWhite, String n, LinkedList<Piece> ps) {
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
        this.isWhite = isWhite;
        this.ps = ps;
        name = n;
        ps.add(this);
    }
    
    // Function for moving a piece, if placed on a square with a piece it kills the piece
    public void move(int xp, int yp) {
        if(ChessGame.getPiece(xp * 64, yp * 64) != null) {
            if(ChessGame.getPiece(xp * 64, yp * 64).isWhite != isWhite) {
                ChessGame.getPiece(xp * 64, yp * 64).kill();
            } else {
                x = this.xp * 64;
                y = this.yp * 64;
                return;
            }
        }
        this.xp = xp;
        this.yp = yp;
        x = xp * 64;
        y = yp * 64;
    }
    
    // Kill a piece
    public void kill() {
        ps.remove(this);
    }
    
}
