package de.schlegel11.jfxanimation;

import javafx.util.Duration;

/**
 * Class which provides methods for the animation process. <br>
 * This action is comparable to a {@link javafx.animation.KeyFrame} and represents a CSS like
 * percentage definition (for a specific part) in keyframe animations.
 *
 * @author Marcel Schlegel (schlegel11)
 * @version 1.0
 * @since 2018-10-04
 */
public interface JFXTemplateProcess<N> {

  /**
   * Takes a percentage value or percentage values between 0 and 100 which defines the specific
   * animation interval. <br>
   * If the value is smaller than 0 or bigger than 100 it will be ignored. <br>
   * Be careful with edge cases e.g. if the percentage value is a result of a calculation like 100 /
   * 1. <br>
   * The result might not be exact e.g. 100.0001. <br>
   *
   * @param percent the percentage value.
   * @param percents the percentage values.
   * @return A {@link JFXTemplateAction} instance.
   */
  JFXTemplateAction<N> percent(double percent, double... percents);

  /**
   * Defines a percentage value of 0 in the specific animation interval. <br>
   *
   * @return A {@link JFXTemplateAction} instance.
   */
  JFXTemplateAction<N> from();

  /**
   * Defines a percentage value of 100 in the specific animation interval.
   *
   * @return A {@link JFXTemplateAction} instance.
   */
  JFXTemplateAction<N> to();

  /**
   * Takes a {@link Duration} value or {@link Duration} values which defines the specific animation
   * interval. <br>
   * The {@link Duration} values are independent from a configured {@link
   * JFXAnimationTemplateConfig.Builder#duration(Duration)}. <br>
   * That means if a defined {@link Duration} value is greater than a configured {@link
   * JFXAnimationTemplateConfig.Builder#duration(Duration)} value, the animation will take longer.
   * <br>
   * If there are no {@link #percent(double, double...)} definitions, a configured {@link
   * JFXAnimationTemplateConfig.Builder#duration(Duration)} isn't necessary.
   *
   * @param time the {@link Duration} value.
   * @param times the {@link Duration} values.
   * @return A {@link JFXTemplateAction} instance.
   */
  JFXTemplateAction<N> time(Duration time, Duration... times);
}
