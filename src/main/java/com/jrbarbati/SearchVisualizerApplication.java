package com.jrbarbati;

import com.jrbarbati.gui.Gui;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.awt.*;

@SpringBootApplication
public class SearchVisualizerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(SearchVisualizerApplication.class).headless(false).run(args);

        EventQueue.invokeLater(() -> {
            Gui gui = ctx.getBean(Gui.class);
            gui.create();
            gui.setVisible(true);
            gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        });
    }

}
