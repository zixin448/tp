package seedu.address.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Region;

public class HelpCommandBox extends UiPart<Region> {
    private static final String FXML = "HelpCommandBox.fxml";

    @FXML
    private TitledPane title;
    @FXML
    private Label description;

    /**
     * Constructor for a titled pane containing the description of a command.
     */
    public HelpCommandBox() {
        super(FXML);
        title.setText("testtitle");
        description.setText("testdescription");
    }

    public TitledPane getBox() {
        return title;
    }
}
