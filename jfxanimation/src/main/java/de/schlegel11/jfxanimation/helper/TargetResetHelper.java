package de.schlegel11.jfxanimation.helper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Helper class which takes a reset behaviour for a specific {@link javafx.animation.KeyValue}.
 *
 * @author Marcel Schlegel (schlegel11)
 * @version 1.0
 * @since 2018-12-12
 */
public class TargetResetHelper<KV> {

  private final Set<KV> resetTargets = new HashSet<>();
  private Consumer<Void> resetProcessConsumer = Void -> {};

  public void computeKeyValue(KV keyValue, Consumer<KV> resetProcessConsumer) {
    if (resetTargets.add(keyValue)) {
      this.resetProcessConsumer =
          this.resetProcessConsumer.andThen(Void -> resetProcessConsumer.accept(keyValue));
    }
  }

  public void computeKeyValues(Collection<KV> keyValues, Consumer<KV> resetProcessConsumer) {
    keyValues.forEach(keyValue -> computeKeyValue(keyValue, resetProcessConsumer));
  }

  public void reset() {
    resetProcessConsumer.accept(null);
  }
}
