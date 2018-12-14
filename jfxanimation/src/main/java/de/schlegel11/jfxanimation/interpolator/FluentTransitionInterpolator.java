package de.schlegel11.jfxanimation.interpolator;

import de.schlegel11.jfxanimation.helper.Direction;
import javafx.animation.Interpolator;
import javafx.beans.value.WritableValue;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Interpolator which updates every new interpolation call its start- or end value from a given
 * target {@link WritableValue}.
 *
 * @author Marcel Schlegel (schlegel11)
 * @version 1.0
 * @since 2018-12-08
 */
public class FluentTransitionInterpolator extends Interpolator {

  private final Interpolator interpolator;
  private final WritableValue<Object> target;
  private final Supplier<Direction> directionSupplier;
  private boolean init = false;

  // Declare values for every type to avoid unnecessary boxing.
  private Object objectValue;
  private boolean booleanValue;
  private double doubleValue;
  private int intValue;
  private long longValue;

  public FluentTransitionInterpolator(
      Interpolator interpolator,
      WritableValue<Object> target,
      Supplier<Direction> directionSupplier,
      Consumer<Runnable> onFinishCallConsumer) {
    this.interpolator = interpolator;
    this.target = target;
    this.directionSupplier = directionSupplier;
    onFinishCallConsumer.accept(this::onFinish);
  }

  private void onFinish() {
    init = false;
  }

  @Override
  public Object interpolate(Object startValue, Object endValue, double fraction) {
    if (!init) {
      objectValue = target.getValue();
      init = true;
    }
    return directionSupplier.get() == Direction.BACKWARDS
        ? interpolator.interpolate(startValue, objectValue, fraction)
        : interpolator.interpolate(objectValue, endValue, fraction);
  }

  @Override
  public boolean interpolate(boolean startValue, boolean endValue, double fraction) {
    if (!init) {
      booleanValue = (boolean) target.getValue();
      init = true;
    }
    return directionSupplier.get() == Direction.BACKWARDS
        ? interpolator.interpolate(startValue, booleanValue, fraction)
        : interpolator.interpolate(booleanValue, endValue, fraction);
  }

  @Override
  public double interpolate(double startValue, double endValue, double fraction) {
    if (!init) {
      doubleValue = (double) target.getValue();
      init = true;
    }
    return directionSupplier.get() == Direction.BACKWARDS
        ? interpolator.interpolate(startValue, doubleValue, fraction)
        : interpolator.interpolate(doubleValue, endValue, fraction);
  }

  @Override
  public int interpolate(int startValue, int endValue, double fraction) {
    if (!init) {
      intValue = (int) target.getValue();
      init = true;
    }
    return directionSupplier.get() == Direction.BACKWARDS
        ? interpolator.interpolate(startValue, intValue, fraction)
        : interpolator.interpolate(intValue, endValue, fraction);
  }

  @Override
  public long interpolate(long startValue, long endValue, double fraction) {
    if (!init) {
      longValue = (long) target.getValue();
      init = true;
    }
    return directionSupplier.get() == Direction.BACKWARDS
        ? interpolator.interpolate(startValue, longValue, fraction)
        : interpolator.interpolate(longValue, endValue, fraction);
  }

  @Override
  protected double curve(double t) {
    return 0;
  }
}
