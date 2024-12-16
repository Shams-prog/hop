import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Hop {
    private Timer timer; // Enlever final
    private boolean gameStarted = false; // État du jeu : faux au début

    public Hop() {
        Field field = new Field(400, 600);

        if (field.getBlocks().isEmpty()) {
            System.err.println("Erreur : Aucun bloc généré !");
            System.exit(1);
        }

        Block firstBlock = field.getBlocks().get(0);
        Axel axel = new Axel(field, firstBlock.getX() + firstBlock.getWidth() / 2,
                             firstBlock.getY() - GamePanel.getAxelHeight());
        GamePanel gamePanel = new GamePanel(field, axel);

        JFrame frame = new JFrame("Hop!");
        frame.add(gamePanel);
        frame.setResizable(false);
        frame.pack();
        frame.setSize(field.getWidth(), field.getHeight());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        timer = new Timer(30, e -> {
            // Vérifier si Axel a quitté le premier bloc
            if (!gameStarted) {
                if (axel.getY() < firstBlock.getY() - GamePanel.getAxelHeight()) {
                    gameStarted = true; // Le jeu démarre quand Axel quitte le premier bloc
                }
            } else {
                // Jeu en cours : faire défiler les blocs et vérifier la condition Game Over
                if (!axel.isAlive()) {
                    timer.stop();
                    JOptionPane.showMessageDialog(frame, "Game Over ! "+"Votre score est de : "+field.getScore(), "Fin de la partie",  JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                }
                field.update(); // Faire défiler les blocs seulement si le jeu a commencé
            }
        
            axel.update(field); // Axel peut toujours se déplacer
            gamePanel.repaint(); // Toujours redessiner
        });
        

        timer.start();
    }

    public static void main(String[] args) {
        new Hop();
    }
}