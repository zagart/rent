package rent.ui;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradientBuilder;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.scene.transform.RotateBuilder;
import javafx.stage.Stage;

/**
 * @author zagart
 */
@SuppressWarnings("deprecation")
public class WidgetDrawer {
    private static final byte UNIT = 100;

    private Node arrow(double stretchRelativeToRim, Color color, int startAngle) {
        return PathBuilder.create()
                .fill(color)
                .stroke(Color.TRANSPARENT)
                .elements(
                        new MoveTo(UNIT, UNIT),
                        new LineTo(UNIT * 0.9, UNIT * 0.9),
                        new LineTo(UNIT, stretchRelativeToRim),
                        new LineTo(UNIT * 1.1, UNIT * 0.9),
                        new LineTo(UNIT, UNIT)
                )
                .transforms(
                        RotateBuilder.create()
                                .pivotX(UNIT)
                                .pivotY(UNIT)
                                .angle(startAngle)
                                .build()
                )
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

    public Node getArrowNode(final int pAngle) {
        return arrow(UNIT * 0.2, Color.BLACK, pAngle);
    }

    public Node marks() {
        Group tickMarkGroup = new Group();
        for (int n = 0; n < 12; n++) {
            tickMarkGroup.getChildren().add(step(n));
        }
        return tickMarkGroup;
    }

    private EventHandler<MouseEvent> moveWhenDragging(final Stage stage) {
        return mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - stage.getWidth() / 2);
            stage.setY(mouseEvent.getScreenY() - stage.getHeight() / 2);
        };
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

    private EventHandler<ScrollEvent> scaleWhenScrolling(final Stage stage, final Parent root) {
        return scrollEvent -> {
            double scroll = scrollEvent.getDeltaY();
            root.setScaleX(root.getScaleX() + scroll / 100);
            root.setScaleY(root.getScaleY() + scroll / 100);
            root.setTranslateX(root.getTranslateX() + scroll);
            root.setTranslateY(root.getTranslateY() + scroll);
            stage.sizeToScene();
        };
    }

    public void setUpMouseForScaleAndMove(final Stage stage, final Parent root) {
        root.onMouseDraggedProperty().set(moveWhenDragging(stage));
        root.onScrollProperty().set(scaleWhenScrolling(stage, root));
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
