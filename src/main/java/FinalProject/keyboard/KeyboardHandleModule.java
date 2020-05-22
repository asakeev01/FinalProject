package FinalProject.keyboard;
public interface KeyboardHandleModule {

    void update();
    FinalProject.main.Direction lastDirectionKeyPressed();
    boolean wasEscPressed();

}
