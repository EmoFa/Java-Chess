package chessgame;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.*;
import javax.swing.*;
import java.util.*;
import javax.imageio.*;
import java.io.*;

/**
 *
 * @author nolca
 */
public class ChessGame {
    public static LinkedList<Piece> ps = new LinkedList<>();
    public static Piece selectedPiece = null;

    public static void main(String[] args) throws IOException {
        // Cut out each piece from the array so it can be drawn
        BufferedImage all = ImageIO.read(new File("src\\chessgame\\ChessPiecesArray.png"));
        Image imgs[] = new Image[12];
        int ind = 0;
        int width = all.getWidth();
        int height = all.getHeight();
        for(int y = 0; y < height && y + 200 <= height; y += 200) {
            for(int x = 0; x < width && x + 200 <= width; x += 200) {
                imgs[ind] = all.getSubimage(x, y, 200, 200).getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);
                ind++;
            }
        }
        
        // Place black pieces on board
        Piece brookl = new Piece(0, 0, false, "rook", ps);
        Piece bknightl = new Piece(1, 0, false, "knight", ps);
        Piece bbishopl = new Piece(2, 0, false, "bishop", ps);
        Piece bqueen = new Piece(3, 0, false, "queen", ps);
        Piece bking = new Piece(4, 0, false, "king", ps);
        Piece bbishopr = new Piece(5, 0, false, "bishop", ps);
        Piece bknightr = new Piece(6, 0, false, "knight", ps);
        Piece brookr = new Piece(7, 0, false, "rook", ps);
        Piece bpawn1 = new Piece(0, 1, false, "pawn", ps);
        Piece bpawn2 = new Piece(1, 1, false, "pawn", ps);
        Piece bpawn3 = new Piece(2, 1, false, "pawn", ps);
        Piece bpawn4 = new Piece(3, 1, false, "pawn", ps);
        Piece bpawn5 = new Piece(4, 1, false, "pawn", ps);
        Piece bpawn6 = new Piece(5, 1, false, "pawn", ps);
        Piece bpawn7 = new Piece(6, 1, false, "pawn", ps);
        Piece bpawn8 = new Piece(7, 1, false, "pawn", ps);
        
        // Place white pieces on board
        Piece wrookl = new Piece(0, 7, true, "rook", ps);
        Piece wknightl = new Piece(1, 7, true, "knight", ps);
        Piece wbishopl = new Piece(2, 7, true, "bishop", ps);
        Piece wqueen = new Piece(3, 7, true, "queen", ps);
        Piece wking = new Piece(4, 7, true, "king", ps);
        Piece wbishopr = new Piece(5, 7, true, "bishop", ps);
        Piece wknightr = new Piece(6, 7, true, "knight", ps);
        Piece wrookr = new Piece(7, 7, true, "rook", ps);
        Piece wpawn1 = new Piece(0, 6, true, "pawn", ps);
        Piece wpawn2 = new Piece(1, 6, true, "pawn", ps);
        Piece wpawn3 = new Piece(2, 6, true, "pawn", ps);
        Piece wpawn4 = new Piece(3, 6, true, "pawn", ps);
        Piece wpawn5 = new Piece(4, 6, true, "pawn", ps);
        Piece wpawn6 = new Piece(5, 6, true, "pawn", ps);
        Piece wpawn7 = new Piece(6, 6, true, "pawn", ps);
        Piece wpawn8 = new Piece(7, 6, true, "pawn", ps);
        
        // Create app window
        JFrame frame = new JFrame("Chess");
        frame.setUndecorated(true);
        frame.setBounds(10, 10, 512, 512); // 528, 551
        JPanel pn = new JPanel() {
            @Override
            // Draw board in window
            public void paint(Graphics g) {
                boolean white = true;
                for(int y = 0; y < 8; y++) {
                    for(int x = 0; x < 8; x++) {
                        if(white) {
                            g.setColor(new Color(235, 210, 183));
                        } else {
                            g.setColor(new Color(161, 111, 90));
                        }
                        g.fillRect(x * 64, y * 64, 64, 64);
                        white = !white;
                    }
                    white = !white;
                }
                // Find piece number in array based on given name
                for(Piece p: ps) {
                    int ind = 0;
                    if(p.name.equalsIgnoreCase("king")) {
                        ind = 0;
                    }
                    if(p.name.equalsIgnoreCase("queen")) {
                        ind = 1;
                    }
                    if(p.name.equalsIgnoreCase("bishop")) {
                        ind = 2;
                    }
                    if(p.name.equalsIgnoreCase("knight")) {
                        ind = 3;
                    }
                    if(p.name.equalsIgnoreCase("rook")) {
                        ind = 4;
                    }
                    if(p.name.equalsIgnoreCase("pawn")) {
                        ind = 5;
                    }
                    if(!p.isWhite) {
                        ind += 6;
                    }
                    // Draw piece
                    g.drawImage(imgs[ind], p.x, p.y, this);
                }
            }
        };
        frame.add(pn);
        frame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (selectedPiece != null) {
                    selectedPiece.x = e.getX() - 32;
                    selectedPiece.y = e.getY() - 32;
                    frame.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        });
        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                selectedPiece = getPiece(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedPiece.move(e.getX() / 64, e.getY() / 64);
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
            
        });
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
    
    public static Piece getPiece(int x, int y) {
        int xp = x / 64;
        int yp = y / 64;
        
        for(Piece p: ps) {
            if(p.xp == xp && p.yp == yp) {
                return p;
            }
        }
        return null;
    }
    
}
