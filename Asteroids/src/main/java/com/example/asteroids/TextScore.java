package com.example.asteroids;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Scanner;

public class TextScore {

    private static Text text = null;

    public TextScore() {
        text = new Text(10,20,"Points: 0");
        text.setFont(Font.font("Verdana",20));
        text.setFill(Color.BLACK);
    }

    public Text getText() {
        return text;
    }

    public static long textParsing()
    {
        String parsed=text.toString();
        return new Scanner(parsed).useDelimiter("\\D+").nextLong();
    }


}
