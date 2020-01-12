package Main;

        import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.layout.Pane;
        import javafx.stage.Stage;

        import java.io.IOException;
/**
 * Třída start je hlavní třídou projektu
 */
public class Start extends Application {
    /**
     * Základní nastavení rozlišení aplikace
     */
    public final static int RESOLUTION_X = 640;
    public final static int RESOLUTION_Y = 640;
    public static Stage stage;

    /**
     * Metoda která spouští celou aplikaci
     * @param args
     */
    public static void main (String args[]) {
        launch(args);
    }

    /**
     * Metoda která vytváří prostředí aplikace
     * @param stage
     * @throws IOException
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream("/mainmenu.fxml"));
        MainMenuController mainMenuController = loader.getController();
        Scene scene = new Scene(mainPane);
        scene.getStylesheets().setAll(getClass().getResource("/menustyle.css").toExternalForm());

        this.stage = stage;
        stage.setTitle("VŠE Defense");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}