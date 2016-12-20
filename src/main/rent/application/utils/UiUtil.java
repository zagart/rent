package rent.application.utils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import rent.constants.UiConstants;
import rent.ui.custom.CustomStage;
import rent.ui.custom.CustomTextField;

import java.net.URL;

/**
 * Utility class with methods which work at elements that
 * visible to user.
 *
 * @author zagart
 */
public class UiUtil {
    private static final int BUTTON_WIDTH = 100;
    private static final int FIELD_WIDTH = 100;
    private static final int STAGE_WIDTH = 300;

    public static Stage getStageByFields(final String... pStrings) {
        final CustomStage stage = new CustomStage();
        stage.setWidth(STAGE_WIDTH);
        final VBox mainLayout = new VBox();
        for (final String fieldName : pStrings) {
            final Label description = new Label();
            description.setText(fieldName);
            description.setPrefWidth(STAGE_WIDTH);
            description.setAlignment(Pos.CENTER);
            final CustomTextField field = new CustomTextField();
            field.setTag(fieldName);
            field.setPrefWidth(FIELD_WIDTH);
            field.setAlignment(Pos.CENTER);
            mainLayout.getChildren().add(description);
            mainLayout.getChildren().add(field);
        }
        final Button cancelButton = new Button(UiConstants.CANCEL);
        final Button okButton = new Button(UiConstants.OK);
        okButton.setPrefWidth(BUTTON_WIDTH);
        cancelButton.setPrefWidth(BUTTON_WIDTH);
        final HBox bottomTemplate = new HBox(cancelButton, okButton);
        bottomTemplate.setAlignment(Pos.CENTER);
        mainLayout.getChildren().add(bottomTemplate);
        stage.setScene(new Scene(mainLayout));
        stage.getIcons().add(getIcon());
        stage.setOkButton(okButton);
        stage.setCancelButton(cancelButton);
        stage.setFieldsLayout(mainLayout);
        return stage;
    }

    public static Image getIcon() {
        final URL path = UiUtil.class.getClassLoader().getResource("resources/icon.png");
        final String iconPath;
        if (path != null) {
            iconPath = path.toString();
            return new Image(iconPath);
        } else {
            return null;
        }
    }
}
