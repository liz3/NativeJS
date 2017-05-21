package de.liz3.nativejs.gui;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by liz3 on 20.05.17.
 */
public class AreaOutputStream extends OutputStream {

    private TextArea area;

    AreaOutputStream(TextArea area) {
        this.area = area;
    }

    @Override
    public void write(int b) throws IOException {
        char x = (char) b;
        Platform.runLater(() -> area.appendText("" + x));
    }
}
