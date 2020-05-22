package FinalProject.graphics.lwjglmodule;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import FinalProject.graphics.GraphicsModule;
import FinalProject.main.Constants;
import FinalProject.main.ErrorCatcher;
import FinalProject.main.GameField;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static FinalProject.main.Constants.CELL_SIZE;
import static FinalProject.main.Constants.COUNT_CELLS_X;
import static FinalProject.main.Constants.COUNT_CELLS_Y;
public class LwjglGraphicsModule implements GraphicsModule {

    private LwjglSpriteSystem spriteSystem;
    public LwjglGraphicsModule() {
        initOpengl();
        spriteSystem = new LwjglSpriteSystem();
    }

    private void initOpengl() {
        try {
            Display.setDisplayMode(new DisplayMode(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
            Display.setTitle(Constants.SCREEN_NAME);
            Display.create();
        } catch (LWJGLException e) {
            ErrorCatcher.graphicsFailure(e);
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Constants.SCREEN_WIDTH,0, Constants.SCREEN_HEIGHT,1,-1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glClearColor(1,1,1,1);
    }
    @Override
    public void draw(GameField field) {
        glClear(GL_COLOR_BUFFER_BIT);

        for(int i = 0; i < COUNT_CELLS_X; i++) {
            for (int j = 0; j < COUNT_CELLS_Y; j++) {
                drawCell(CELL_SIZE*i, CELL_SIZE*j, field.getState(i,j));
            }
        }

        Display.update();
        Display.sync(60);
    }
    private void drawCell(int x, int y, int state) {
        spriteSystem.getSpriteByNumber(state).getTexture().bind();

        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(x,y+ Constants.CELL_SIZE);
        glTexCoord2f(1,0);
        glVertex2f(x+ Constants.CELL_SIZE,y+ Constants.CELL_SIZE);
        glTexCoord2f(1,1);
        glVertex2f(x+ Constants.CELL_SIZE, y);
        glTexCoord2f(0,1);
        glVertex2f(x, y);
        glEnd();
    }
    @Override
    public boolean isCloseRequested() {
        return Display.isCloseRequested();
    }
    @Override
    public void destroy() {
        Display.destroy();
    }
}
