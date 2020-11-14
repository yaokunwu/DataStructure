import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import java.util.Scanner;

public class JavaFXMazeDemo extends Application {

    private Maze m;
    private final int margin = 50;
    private final int sideLen = 10;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Please enter the number of cols: ");
        int cols = scanner.nextInt();

        int width = cols * sideLen + margin * 2;
        int height = rows * sideLen + margin * 2;

        m = new Maze(rows, cols);
        m.generatMaze();

        Group root = new Group();
        Button btn = new Button();
        root.getChildren().add(btn);
        btn.setText("Draw Maze");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                displayMaze(root);
            }
        });

        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("Maze");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void displayMaze(Group root) {
        for (int i = 0; i < m.c; i++) {
            int currRow = i / m.n;
            int currCol = i % m.n;
            if (m.top[currRow][currCol]) {
                int startX = margin + sideLen * currCol;
                int startY = margin + sideLen * currRow;
                int endX = margin + sideLen * currCol + sideLen;
                int endY = margin + sideLen * currRow;
                Line tmp = new Line(startX, startY, endX, endY);
                root.getChildren().add(tmp);
            }
            if (m.bottom[currRow][currCol]) {
                int startX = margin + sideLen * currCol;
                int startY = margin + sideLen * (currRow + 1);
                int endX = margin + sideLen * currCol + sideLen;
                int endY = margin + sideLen * (currRow + 1);
                Line tmp = new Line(startX, startY, endX, endY);
                root.getChildren().add(tmp);
            }
            if (m.left[currRow][currCol]) {
                int startX = margin + sideLen * currCol;
                int startY = margin + sideLen * currRow;
                int endX = margin + sideLen * currCol;
                int endY = margin + sideLen * currRow + sideLen;
                Line tmp = new Line(startX, startY, endX, endY);
                root.getChildren().add(tmp);
            }
            if (m.right[currRow][currCol]) {
                int startX = margin + sideLen * (currCol + 1);
                int startY = margin + sideLen * currRow;
                int endX = margin + sideLen * (currCol + 1);
                int endY = margin + sideLen * (currRow + 1);
                Line tmp = new Line(startX, startY, endX, endY);
                root.getChildren().add(tmp);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
