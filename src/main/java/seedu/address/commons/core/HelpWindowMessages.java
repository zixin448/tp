package seedu.address.commons.core;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class HelpWindowMessages {
    private static final String defaultFont = "System";

    private static final String headerFont = "System";
    private static final FontWeight headerFontWeight = FontWeight.BOLD;
    private static final int headerFontSize = 40;

    private static final String introFont = defaultFont;
    private static final int introFontSize = 25;

    private static Text createSectionHeader(String header) {
        Text text = new Text(header);
        text.setFont(Font.font(headerFont, headerFontWeight, headerFontSize));
        text.setUnderline(true);
        text.setFill(Color.WHITE);
        return text;
    }

    /**
     * Method for creating the introduction text for the Help Window.
     * @return A TextFlow object containing the introduction text.
     */
    public static TextFlow createIntroText() {
        Text[] textArray = {
                createSectionHeader("Introduction\n\n"),
                createNormalIntroText("camNUS is a "),
                createBoldIntroText("desktop app "),
                createNormalIntroText("for Teaching Assistants (TAs) to "),
                createBoldIntroText("manage their own contacts, as well as assessments "
                        + "and class participation among students "),
                createNormalIntroText("in their contact book. It is optimized for use via a Command "
                        + "Line Interface (CLI) while still having the benefits of a Graphical "
                        + "User Interface (GUI).")
        };
        return new TextFlow(textArray);
    }

    /**
     * Method for creating the developer guide text for the Help Window.
     * @return A TextFlow object containing the developer guide text.
     */
    public static TextFlow createDevGuideText() {
        Text header = createSectionHeader("Developer Guide\n\n");
        Text text = createNormalIntroText("Refer to the developer guide here:\n");
        Hyperlink hyperlink = new Hyperlink(
                "https://github.com/AY2122S2-CS2103T-W13-2/tp/blob/master/docs/DeveloperGuide.md");
        hyperlink.setFont(Font.font(introFont, FontWeight.NORMAL, introFontSize));
        hyperlink.setWrapText(true);
        AnchorPane.setLeftAnchor(hyperlink, 0.0);
        AnchorPane.setRightAnchor(hyperlink, 15.0);
        TextFlow textFlow = new TextFlow(header, text, hyperlink);
        hyperlink.prefWidthProperty().bind(textFlow.widthProperty());
        prepareHyperlink(hyperlink);
        return textFlow;
    }

    private static Text createBoldIntroText(String message) {
        Text text = new Text(message);
        text.setFont(Font.font(introFont, FontWeight.BOLD, introFontSize));
        text.setFill(Color.WHITE);
        return text;
    }

    private static Text createNormalIntroText(String message) {
        Text text = new Text(message);
        text.setFont(Font.font(introFont, FontWeight.NORMAL, introFontSize));
        text.setFill(Color.WHITE);
        return text;
    }

    private static void prepareHyperlink(Hyperlink hyperlink) {
        hyperlink.setBorder(Border.EMPTY);
        hyperlink.setStyle("-fx-text-fill: #e06519;");
        hyperlink.setUnderline(true);
        hyperlink.setOnAction(e -> {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse(new URI(hyperlink.getText()));
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
