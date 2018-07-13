package ru.job4j.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        Rectangle rect2 = new Rectangle(limitX, 100, 10, 10);
        Thread one =  new Thread(new Rmoove(rect));
        Thread two = new Thread(new RmoveN(rect2));
        one.start();
        group.getChildren().add(rect);
        two.start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(true);
        stage.show();
    }
}

