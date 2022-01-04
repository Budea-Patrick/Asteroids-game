package com.example.asteroids;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Scanner;

public class TextScore {

    private final Text text;

    public TextScore() {
        this.text = new Text(10,20,"Points: 0");
        text.setFont(Font.font("Verdana",20));
        text.setFill(Color.WHITE);
    }

    public Text getText() {
        return text;
    }

    public long textParsing()
    {
        String parsed=text.toString();
        return new Scanner(parsed).useDelimiter("\\D+").nextLong();
    }
}
