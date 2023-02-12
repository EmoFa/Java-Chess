package chessgame;
import java.util.*;

/**
 *
 * @author nolca
 */
public class Piece {
    int xp;
    int yp;
    boolean isWhite;
    LinkedList<Piece> ps;
    String name;
    
    // Function used for creating piece
    public Piece(int xp, int yp, boolean isWhite, String n, LinkedList<Piece> ps) {
        this.xp = xp;
        this.yp = yp;
        this.isWhite = isWhite;
        this.ps = ps;
        name = n;
        ps.add(this);
    }
    
    // Function for moving a piece, if placed on a square with a piece it kills the piece
    public void move(int xp, int yp) {
        if (this.xp != xp || this.yp != yp) {
            ps.stream().filter(p -> (p.xp == xp && p.yp == yp)).forEachOrdered(p -> {
                p.kill();
            });
        }
        this.xp = xp;
        this.yp = yp;
    }
    
    // Kill a piece
    public void kill() {
        ps.remove(this);
    }
    
}
