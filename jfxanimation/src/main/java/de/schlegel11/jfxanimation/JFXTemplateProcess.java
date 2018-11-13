package de.schlegel11.jfxanimation;

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
   * animation interval.
   *
   * @param percent the percentage value.
   * @param percents the percentage values.
   * @return A {@link JFXTemplateAction} instance.
   */
  JFXTemplateAction<N> percent(double percent, double... percents);

  /**
   * Defines a percentage value of 0 in the specific animation interval.
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
}
