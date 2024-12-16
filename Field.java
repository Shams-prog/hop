import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Field {
    private final int width, height;
    private final List<Block> blocks;

    public static final int ALTITUDE_GAP = 80; // Écart vertical entre les blocs
    public static final int START_ALTITUDE = 100; // Altitude initiale pour les blocs
    private static final int MAX_BLOCK_WIDTH = 100;
    private static final int MIN_BLOCK_WIDTH = 50;
    private int score = 0;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        this.blocks = new ArrayList<>();
        initializeBlocks();
    }

    private void initializeBlocks() {
        Random random = new Random();
        int currentAltitude = height - START_ALTITUDE; // Commencer en bas

        while (currentAltitude > 0) {
            int blockWidth = MIN_BLOCK_WIDTH + random.nextInt(MAX_BLOCK_WIDTH - MIN_BLOCK_WIDTH + 1);
            int blockX = random.nextInt(width - blockWidth);

            // Ajouter le bloc à la liste
            blocks.add(new Block(blockX, currentAltitude, blockWidth));

            // Monter à l'altitude suivante
            currentAltitude -= ALTITUDE_GAP;
        }
        System.out.println("Nombre de blocs générés : " + blocks.size());
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public static int getBlockHeight() {
        return 10;
    }
    

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
     public int getScore() {
        return this.score;
    }

    public void update() {
        // Faire défiler les blocs vers le bas
        for (Block block : blocks) {
            block.setY(block.getY() + 2); // Déplacer le bloc vers le bas (vitesse 2 pixels)
        }
    
        // Retirer les blocs qui sortent de l'écran et générer de nouveaux blocs en haut
        if (!blocks.isEmpty() && blocks.get(0).getY() > height) {
            blocks.remove(0); // Retirer le bloc en bas
            addNewBlockAtTop(); // Ajouter un nouveau bloc en haut
        }
    }
    
    // Méthode pour ajouter un bloc en haut
    private void addNewBlockAtTop() {
        Random random = new Random();
        int blockWidth = MIN_BLOCK_WIDTH + random.nextInt(MAX_BLOCK_WIDTH - MIN_BLOCK_WIDTH + 1);
        int blockX = random.nextInt(width - blockWidth);
        int blockY = blocks.get(blocks.size() - 1).getY() - ALTITUDE_GAP;
    
        blocks.add(new Block(blockX, blockY, blockWidth));
        score += 80;
    }
    
}