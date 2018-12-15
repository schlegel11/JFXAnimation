package de.schlegel11.jfxanimation.helper;

import de.schlegel11.jfxanimation.JFXAnimationTemplateAction;
import de.schlegel11.jfxanimation.JFXAnimationTemplateConfig;
import de.schlegel11.jfxanimation.interpolator.ConditionalInterpolator;
import de.schlegel11.jfxanimation.interpolator.DynamicInterpolator;
import de.schlegel11.jfxanimation.interpolator.FluentTransitionInterpolator;
import javafx.animation.Interpolator;
import javafx.beans.value.WritableValue;

import java.util.function.Supplier;

/**
 * Factory for the specific {@link Interpolator}s and {@link Interpolator} chains.
 *
 * @author Marcel Schlegel (schlegel11)
 * @version 1.0
 * @since 2018-12-15
 */
public class InterpolatorFactory {

  private InterpolatorFactory() {}

  public static Interpolator createKeyFrameInterpolator(
      WritableValue<Object> writableValue,
      JFXAnimationTemplateConfig config,
      JFXAnimationTemplateAction<?, ?> action) {

    Supplier<Interpolator> interpolatorSupplier = createDefaultInterpolatorSupplier(config, action);
    Interpolator interpolator = createDynamicInterpolator(config, action, interpolatorSupplier);
    interpolator = createFluentInterpolator(writableValue, config, action, interpolator);
    return createConditionalInterpolator(writableValue, action, interpolator);
  }

  public static Interpolator createFromToAutoKeyFrameInterpolator(
      WritableValue<Object> writableValue,
      JFXAnimationTemplateConfig config,
      JFXAnimationTemplateAction<?, ?> action) {

    Interpolator interpolator = createDefaultInterpolatorSupplier(config, action).get();
    return createFluentInterpolator(writableValue, config, action, interpolator);
  }

  public static Supplier<Interpolator> createDefaultInterpolatorSupplier(
      JFXAnimationTemplateConfig config, JFXAnimationTemplateAction<?, ?> action) {
    return action.hasInterpolator() ? action::getInterpolator : config::getInterpolator;
  }

  public static Interpolator createDynamicInterpolator(
      JFXAnimationTemplateConfig config,
      JFXAnimationTemplateAction<?, ?> action,
      Supplier<Interpolator> interpolator) {

    if (action.getEndValueInterpretationMode() == InterpretationMode.DYNAMIC) {

      return new DynamicInterpolator(interpolator, endValue -> action.getEndValue());
    } else if (action.getInterpolatorInterpretationMode() == InterpretationMode.DYNAMIC
        || config.getInterpolatorInterpretationMode() == InterpretationMode.DYNAMIC) {

      return new DynamicInterpolator(interpolator);
    }
    return interpolator.get();
  }

  public static Interpolator createConditionalInterpolator(
      WritableValue<Object> writableValue,
      JFXAnimationTemplateAction<?, ?> action,
      Interpolator interpolator) {

    return action.hasExecuteWhen()
        ? new ConditionalInterpolator(interpolator, writableValue, action::isExecuted)
        : interpolator;
  }

  public static Interpolator createFluentInterpolator(
      WritableValue<Object> writableValue,
      JFXAnimationTemplateConfig config,
      JFXAnimationTemplateAction<?, ?> action,
      Interpolator interpolator) {

    if (action.hasFluentTransition()) {
      return new FluentTransitionInterpolator(
          interpolator, writableValue, action::getFluentTransition, action::addOnFinishInternal);
    } else if (config.hasFluentTransition()) {
      return new FluentTransitionInterpolator(
          interpolator, writableValue, config::getFluentTransition, action::addOnFinishInternal);
    }
    return interpolator;
  }
}
