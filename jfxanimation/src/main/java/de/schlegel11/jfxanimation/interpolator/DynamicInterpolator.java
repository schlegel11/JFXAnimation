package de.schlegel11.jfxanimation.interpolator;

import javafx.animation.Interpolator;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Interpolator which executes every interpolation call a lambda with an {@link Interpolator} and/or
 * an end value.
 *
 * @author Marcel Schlegel (schlegel11)
 * @version 1.0
 * @since 2018-12-07
 */
public class DynamicInterpolator extends Interpolator {

  private final Supplier<Interpolator> interpolatorSupplier;
  private final UnaryOperator<Object> endValueUnaryOperator;

  public DynamicInterpolator(
      Supplier<Interpolator> interpolatorSupplier, UnaryOperator<Object> endValueUnaryOperator) {
    this.interpolatorSupplier = interpolatorSupplier;
    this.endValueUnaryOperator = endValueUnaryOperator;
  }

  public DynamicInterpolator(Supplier<Interpolator> interpolatorSupplier) {
    this(interpolatorSupplier, UnaryOperator.identity());
  }

  @Override
  public Object interpolate(Object startValue, Object endValue, double fraction) {
    return interpolatorSupplier
        .get()
        .interpolate(startValue, endValueUnaryOperator.apply(endValue), fraction);
  }

  @Override
  public boolean interpolate(boolean startValue, boolean endValue, double fraction) {
    return (boolean)
        interpolatorSupplier
            .get()
            .interpolate(startValue, endValueUnaryOperator.apply(endValue), fraction);
  }

  @Override
  public double interpolate(double startValue, double endValue, double fraction) {
    return (double)
        interpolatorSupplier
            .get()
            .interpolate(startValue, endValueUnaryOperator.apply(endValue), fraction);
  }

  @Override
  public int interpolate(int startValue, int endValue, double fraction) {
    return (int)
        interpolatorSupplier
            .get()
            .interpolate(startValue, endValueUnaryOperator.apply(endValue), fraction);
  }

  @Override
  public long interpolate(long startValue, long endValue, double fraction) {
    return (long)
        interpolatorSupplier
            .get()
            .interpolate(startValue, endValueUnaryOperator.apply(endValue), fraction);
  }

  @Override
  protected double curve(double t) {
    return 0;
  }
}
