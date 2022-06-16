package cg.mars2.ui;

import cg.mars2.Player;
import cg.mars2.game.Capsule;
import cg.mars2.game.Chrono;
import cg.mars2.game.Constant;
import cg.mars2.game.Land;
import cg.mars2.game.Map;
import cg.mars2.game.Point;
import cg.mars2.game.Population;
import cg.mars2.game.Simulation;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

public class MarsFX extends Application {

    Pane root = new Pane();

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        Capsule capsule = initCapsule();

        createContent(capsule);
        Scene scene = new Scene(root, 700, 300, Color.GHOSTWHITE);

        stage.setScene(scene);

        stage.show();

    }

    private void createContent(Capsule capsule) {

        //create polygon
        Polyline polyline = new Polyline();

        for (Point point : Map.points) {
            polyline.getPoints().add(getX(point.x));
            polyline.getPoints().add(getY(point.y));
        }

        root.getChildren().addAll(polyline);


        Circle circle = new Circle(getX(capsule.x), getY(capsule.y), 10, Color.BLUE);

        root.getChildren().addAll(circle);


        Circle zero = new Circle(getX(0), getY(0), 10, Color.RED);

        root.getChildren().addAll(zero);

        simulate(capsule);

        root.setScaleY(-1);


    }

    private void simulate(Capsule capsule) {


        capsule.debug("init capsule");

        Simulation simulation = new Simulation();
        for (int i = 0; i < 100; i++) {


            Chrono.startChrono();
            simulation.generateSimulation(capsule);
            simulation.mutate();

            Population best = simulation.getBest();

            capsule.apply(best.moves[0]);
            capsule.saveState();
            Color color = Color.GREEN;
            boolean loose = false;
            if (best.score <= Constant.LOOSE) {
                color = Color.RED;
                loose = true;
            }
            Circle circle = new Circle(getX(capsule.x), getY(capsule.y), 1, color);

            for(int j = 1;j<best.moves.length;j++){
                capsule.apply(best.moves[j]);
                Circle circle2 = new Circle(getX(capsule.x), getY(capsule.y), 1, Color.BLACK);
                root.getChildren().addAll(circle2);
            }
            capsule.restoreState();

            root.getChildren().addAll(circle);
            Chrono.stopChrono();
            if (loose) {
                break;
            }
        }


    }

    private Capsule initCapsule() {
        Point p1 = new Point(0, 100);
        Point p2 = new Point(1000, 500);
        Point p3 = new Point(1500, 1500);
        Point p4 = new Point(3000, 1000);
        Point p5 = new Point(4000, 150);
        Point p6 = new Point(5500, 150);
        Point p7 = new Point(6999, 800);


        Point[] points = {p1, p2, p3, p4, p5, p6, p7};
        Player.map = new Map(points);

        Capsule capsule = new Capsule(2500, 2700, 0, 0, 550, 0, 0);
        return capsule;
    }

    private double getX(double x) {
        return (x / 10);
    }

    private double getY(double y) {
        return (y / 10);
    }
}
