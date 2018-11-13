package de.schlegel11.jfxanimation;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.WritableValue;
import javafx.event.ActionEvent;
import javafx.util.Duration;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Class which represents the specific animation implementations.
 *
 * @author Marcel Schlegel (schlegel11)
 * @version 1.0
 * @since 2018-09-22
 */
public class JFXAnimationTemplates {

  /**
   * The {@link Timeline} implementation which supports all {@link JFXAnimationTemplateAction} and
   * {@link JFXAnimationTemplateConfig} methods.
   */
  public static <N> Timeline buildTimeline(JFXAnimationTemplate<N> creator) {

    Timeline timeline = new Timeline();
    JFXAnimationTemplateConfig creatorConfig = creator.buildAndGetTemplateConfig();

    creator
        .buildAndGetAnimationValues()
        .forEach(
            (percent, animationValues) -> {

              // calc the percentage duration of total duration.
              Duration percentageDuration = creatorConfig.getDuration().multiply((percent / 100));

              // Create the key values.
              KeyValue[] keyValues =
                  animationValues
                      .stream()
                      .flatMap(
                          animationValue ->
                              animationValue.mapTo(
                                  createKeyValue(creatorConfig.getInterpolator(), animationValue)))
                      .toArray(KeyValue[]::new);

              // Reduce the onFinish events to one consumer.
              Consumer<ActionEvent> onFinish =
                  animationValues
                      .stream()
                      .map(
                          animationValue ->
                              (Consumer<ActionEvent>)
                                  actionEvent -> {
                                    if (animationValue.isExecuted()) {
                                      animationValue.handleOnFinish(actionEvent);
                                      animationValue.addExecution(1);
                                    }
                                  })
                      .reduce(action -> {}, Consumer::andThen);

              KeyFrame keyFrame = new KeyFrame(percentageDuration, onFinish::accept, keyValues);
              timeline.getKeyFrames().add(keyFrame);
            });

    timeline.setAutoReverse(creatorConfig.isAutoReverse());
    timeline.setCycleCount(creatorConfig.getCycleCount());
    timeline.setDelay(creatorConfig.getDelay());
    timeline.setRate(creatorConfig.getRate());
    timeline.setOnFinished(creatorConfig::handleOnFinish);

    return timeline;
  }

  private static Function<WritableValue<Object>, KeyValue> createKeyValue(
      Interpolator globalInterpolator, JFXAnimationTemplateAction<?, ?> animationValue) {
    return (writableValue) -> {
      Interpolator interpolator = animationValue.getInterpolator().orElse(globalInterpolator);
      return new KeyValue(
          writableValue,
          animationValue.getEndValue(),
          new ConditionalInterpolator(interpolator, writableValue, animationValue::isExecuted));
    };
  }
}
