import constance.App;
import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setWidth(1280);
        primaryStage.setHeight(800);
        Scene scene = new Scene(new Group());


        WebView webView = new WebView();
        webView.setMaxWidth(9999);
        webView.setMaxHeight(9999);
        WebEngine webEngine = webView.getEngine();

        GridPane scrollPane = new GridPane();

        scrollPane.add(webView,0,0);
        GridPane.setHgrow(webView, Priority.ALWAYS);
        GridPane.setVgrow(webView, Priority.ALWAYS);

        webEngine.getLoadWorker().stateProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue == Worker.State.SUCCEEDED){
                        primaryStage.setTitle(App.NAME_APPLICATION);
                    }
                });

        webEngine.load(getClass().getResource("/web/index.html").toExternalForm());

        scene.setRoot(scrollPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
