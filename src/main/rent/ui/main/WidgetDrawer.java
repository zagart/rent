package rent.ui.main;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradientBuilder;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.RotateBuilder;

/**
 * Class which is responsible for creating meter widget.
 *
 * @author zagart
 */
@SuppressWarnings("deprecation")
public class WidgetDrawer {
    public static final int MIN_ANGLE = 0;
    private static final int MAX_ANGLE = 360;
    private static final byte PIVOT_X = 100;
    private static final byte PIVOT_Y = 100;
    private static final byte UNIT = 100;
    private int mMaxValue = 1;

    private Node arrow(double stretchRelativeToRim, Color color, int startAngle) {
        return PathBuilder.create()
                .fill(color)
                .stroke(Color.TRANSPARENT)
                .elements(
                        new MoveTo(UNIT, UNIT),
                        new LineTo(UNIT * 0.9, UNIT * 0.9),
                        new LineTo(UNIT, stretchRelativeToRim),
                        new LineTo(UNIT * 1.1, UNIT * 0.9),
                        new LineTo(UNIT, UNIT))
                .transforms(new Rotate(startAngle, PIVOT_X, PIVOT_Y))
                .build();
    }

    public Node centerPoint() {
        return CircleBuilder.create()
                .fill(Color.BLACK)
                .radius(0.05 * UNIT)
                .centerX(UNIT)
                .centerY(UNIT)
                .build();
    }

    public Node getArrowNode(final int pArrowValue) {
        float degree = (float) MAX_ANGLE / mMaxValue;
        final float actualValue = degree * pArrowValue;
        return arrow(UNIT * 0.2, Color.BLACK, (int) actualValue);
    }

    public Node marks() {
        Group tickMarkGroup = new Group();
        for (int n = 0; n < 12; n++) {
            tickMarkGroup.getChildren().add(step(n));
        }
        return tickMarkGroup;
    }

    public Node outerRim() {
        return CircleBuilder.create()
                .fill(
                        RadialGradientBuilder.create()
                                .stops(
                                        new Stop(0.8, Color.WHITE),
                                        new Stop(0.9, Color.BLACK),
                                        new Stop(0.95, Color.WHITE),
                                        new Stop(1.0, Color.BLACK)
                                )
                                .cycleMethod(CycleMethod.NO_CYCLE)
                                .centerX(0.5)
                                .centerY(0.5)
                                .radius(0.5)
                                .proportional(true)
                                .build()
                )
                .radius(UNIT)
                .centerX(UNIT)
                .centerY(UNIT)
                .build();
    }

    public void setMaxValue(int pMaxValue) {
        mMaxValue = pMaxValue;
    }

    private Node step(int n) {
        return LineBuilder.create()
                .startX(UNIT)
                .endX(UNIT)
                .startY(UNIT * 0.12)
                .endY(UNIT * (n % 3 == 0 ? 0.3 : 0.2))
                .transforms(
                        RotateBuilder.create()
                                .pivotX(UNIT)
                                .pivotY(UNIT)
                                .angle(360 / 12 * n)
                                .build()
                )
                .strokeWidth(2)
                .build();
    }
}
