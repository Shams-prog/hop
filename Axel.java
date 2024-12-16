public class Axel {
    private int x, y;
    private final Field field;
    private boolean surviving;

    private boolean left, right, jumping;
    
    
    private static final int LATERAL_SPEED = 8;

    private int velocityY; // Vitesse verticale
    private static final int JUMP_SPEED = -20; // Vitesse initiale du saut (vers le haut)
    private static final int GRAVITY = 1;      // Gravité appliquée en continu
    private static final int MAX_FALL_SPEED = 15; // Vitesse maximale de chute


    public Axel(Field field, int x, int y) {
        this.field = field;
        this.x = x;
        this.y = y;
    }

    

    public void update(Field field) {
        boolean onBlock = false;
    
        // Vérifie si Axel est en contact avec un bloc
        for (int i = 0; i<field.getBlocks().size();i++) {
            if (field.getBlocks().get(i).collidesWith(x, y + velocityY, GamePanel.getAxelWidth(), GamePanel.getAxelHeight())) {
                if (y + GamePanel.getAxelHeight() <= field.getBlocks().get(i).getY() + velocityY) {
                    y = field.getBlocks().get(i).getY() - GamePanel.getAxelHeight();
                    velocityY = 0;
                    onBlock = true;
                    break;
                }
            }
        }
    
        // Applique la gravité si Axel n'est pas sur un bloc
        if (!onBlock) {
            velocityY = Math.min(velocityY + GRAVITY, MAX_FALL_SPEED);
            y += velocityY;
        }
    
        // Déplacement horizontal autorisé tout le temps
        if (left) x = Math.max(0, x - LATERAL_SPEED);
        if (right) x = Math.min(field.getWidth() - GamePanel.getAxelWidth(), x + LATERAL_SPEED);
    
        // Gérer le saut uniquement si Axel est sur un bloc
        if (jumping && onBlock) {
            velocityY = JUMP_SPEED;
            jumping = false;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public void setMovingLeft(boolean movingLeft) { this.left = movingLeft; }
    public void setMovingRight(boolean movingRight) { this.right = movingRight; }
    public void setJumping(boolean jumping) { this.jumping = jumping; }

    public int getX() { return x; }
    public int getY() { return y; }

    public boolean isAlive() {
        return y <= field.getHeight();
    }
    

    public boolean isSurviving() {
        return surviving;
    }
}