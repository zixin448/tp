package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class HelpCommandDisplay extends UiPart<Region> {
    private static final String FXML = "HelpCommandDisplay.fxml";
    @FXML
    private AnchorPane mainWindow;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox commandDisplay;

    @FXML
    private Accordion helpList;

    /**
     * Constructor for a UI window containing help commands.
     */
    public HelpCommandDisplay() {
        super(FXML);
        scrollPane.setContent(commandDisplay);
    }

    private void setAnchors(Node node, double top, double bottom, double left, double right) {
        AnchorPane.setTopAnchor(node, top);
        AnchorPane.setLeftAnchor(node, left);
        AnchorPane.setRightAnchor(node, right);
        AnchorPane.setBottomAnchor(node, bottom);
    }

    /**
     * Aligns the help command display window with the parent stage,
     * @param node The parent to be aligned with.
     */
    public void addCommandDisplay(AnchorPane node) {
        mainWindow.prefWidthProperty().bind(node.widthProperty());
        mainWindow.prefHeightProperty().bind(node.heightProperty());
        setAnchors(mainWindow, 0 , 0, 0, 0);
        node.getChildren().add(mainWindow);
        addAccordion();
    }

    private void addAccordion() {
        for (int i = 0; i < 20; i++) {
            HelpCommandBox curr = new HelpCommandBox();
            setAnchors(curr.getBox(), 0, 0, 0, 0);
            helpList.getPanes().add(curr.getBox());

        }
    }


}
