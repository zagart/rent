package rent.ui.custom;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Custom {@link javafx.stage.Stage} class.
 *
 * @author zagart
 */
public class CustomStage extends Stage {
    private Button mCancelButton;
    private VBox mFieldsLayout;
    private Button mOkButton;

    public Button getCancelButton() {
        return mCancelButton;
    }

    public void setCancelButton(final Button pCancelButton) {
        mCancelButton = pCancelButton;
    }

    public VBox getFieldsLayout() {
        return mFieldsLayout;
    }

    public void setFieldsLayout(final VBox pFieldsLayout) {
        mFieldsLayout = pFieldsLayout;
    }

    public Button getOkButton() {
        return mOkButton;
    }

    public void setOkButton(final Button pOkButton) {
        mOkButton = pOkButton;
    }
}
