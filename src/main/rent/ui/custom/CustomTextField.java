package rent.ui.custom;

import javafx.scene.control.TextField;

/**
 * Implementation of {@link TextField} for application.
 *
 * @author zagart
 */
public class CustomTextField extends TextField {
    private String mTag;

    public String getTag() {
        return mTag;
    }

    public CustomTextField setTag(final String pTag) {
        mTag = pTag;
        return this;
    }
}
