package de.schlegel11.jfxanimation;

import java.util.function.Function;

/**
 * Class which provides methods for the final animation configuration. <br>
 * This action is comparable to a {@link javafx.animation.Timeline} and represents a CSS like final
 * configuration of the general keyframe animation.
 *
 * @author Marcel Schlegel (schlegel11)
 * @version 1.0
 * @since 2018-10-04
 */
public interface JFXTemplateConfig<N> extends JFXTemplateAction<N> {

  /**
   * The method provides via a {@link Function} an config builder {@link
   * JFXAnimationTemplateConfig#builder()} which holds all the specific methods. <br>
   * These {@link JFXAnimationTemplateConfig#builder()} methods represents the methods e.g. from a
   * {@link javafx.animation.Timeline}. <br>
   * Example:
   *
   * <pre>{@code
   * .config(b -> b.duration(Duration.seconds(2)).interpolator(Interpolator.EASE_BOTH))
   * }</pre>
   *
   * @param configBuilderFunction a {@link Function} which provides and accepts an {@link
   *     JFXAnimationTemplateConfig} builder.
   * @return a {@link JFXTemplateBuilder} instance.
   */
  JFXTemplateBuilder<N> config(
      Function<JFXAnimationTemplateConfig.Builder, JFXAnimationTemplateConfig.Builder>
          configBuilderFunction);

  /**
   * Same as the {@link #config(Function)} method but without the lazy behaviour and doesn't provide
   * a {@link JFXAnimationTemplateConfig#builder()} instance.
   *
   * @see #config(Function)
   * @param configBuilder the {@link JFXAnimationTemplateConfig#builder()} instance.
   * @return a {@link JFXTemplateBuilder} instance.
   */
  JFXTemplateBuilder<N> config(JFXAnimationTemplateConfig.Builder configBuilder);
}
