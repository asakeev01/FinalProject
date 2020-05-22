package FinalProject.graphics.lwjglmodule;
import java.util.HashMap;

class LwjglSpriteSystem {
    private HashMap<Integer, LwjglSprite> spriteByNumber = new HashMap<>();
    LwjglSpriteSystem() {

        for (LwjglSprite sprite : LwjglSprite.values()) {
            if (sprite.getSpriteNumber() != null) {
                spriteByNumber.put(sprite.getSpriteNumber(), sprite);
            }
        }
    }
    public LwjglSprite getSpriteByNumber(int n) {
        return spriteByNumber.getOrDefault(n, LwjglSprite.EMPTY);
    }
}
