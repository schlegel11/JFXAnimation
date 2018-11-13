package de.schlegel11.jfxanimation;

import java.util.function.Function;

/**
 * Class which provides methods for an animation action. <br>
 * This action is comparable to a {@link javafx.animation.KeyValue} and represents a CSS like modify
 * block (for a specific part) in keyframe animations.
 *
 * @author Marcel Schlegel (schlegel11)
 * @version 1.0
 * @since 2018-10-04
 */
public interface JFXTemplateAction<N> extends JFXTemplateProcess<N> {

  /**
   * The action method is similar like a {@link javafx.animation.KeyValue}. <br>
   * The method provides via a {@link Function} an action builder {@link
   * JFXAnimationTemplateAction#builder()} which holds all the specific methods. <br>
   * Example:
   *
   * <pre>{@code
   * .action(b -> b.target(Node::opacityProperty).endValue(0))
   * }</pre>
   *
   * @param valueBuilderFunction a {@link Function} which provides and accepts an {@link
   *     JFXAnimationTemplateAction} builder.
   * @return a {@link JFXTemplateConfig} instance.
   */
  JFXTemplateConfig<N> action(
      Function<JFXAnimationTemplateAction.InitBuilder<N>, JFXAnimationTemplateAction.Builder<?, ?>>
          valueBuilderFunction);

  /**
   * Same as the {@link #action(Function)} method but without the lazy behaviour and doesn't provide
   * a {@link JFXAnimationTemplateAction#builder()} instance.
   *
   * @see #action(Function)
   * @param animationValueBuilder the {@link JFXAnimationTemplateAction#builder()} instance.
   * @return a {@link JFXTemplateConfig} instance.
   */
  JFXTemplateConfig<N> action(JFXAnimationTemplateAction.Builder<?, ?> animationValueBuilder);
}
