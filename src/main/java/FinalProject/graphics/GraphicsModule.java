package FinalProject.graphics;
import FinalProject.main.GameField;
public interface GraphicsModule {
    void draw(GameField field);
    boolean isCloseRequested();
    void destroy();
}
