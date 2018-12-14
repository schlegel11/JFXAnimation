package de.schlegel11.jfxanimation.helper;

import javafx.util.Duration;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Helper class which takes {@link javafx.animation.KeyValue}s and creates start- or/and end {@link
 * javafx.animation.KeyValue}s if necessary.
 *
 * @author Marcel Schlegel (schlegel11)
 * @version 1.0
 * @since 2018-12-04
 */
public class FromToKeyValueCreator<KV> {

  private final Set<KV> startKeyValues = new HashSet<>();
  private final Set<KV> ignoredStartKeyValues = new HashSet<>();
  private final Set<KV> endKeyValues = new HashSet<>();
  private final Set<KV> ignoredEndKeyValues = new HashSet<>();
  private final Duration startDuration;
  private final Duration endDuration;

  public FromToKeyValueCreator(Duration startDuration, Duration endDuration) {
    this.startDuration = startDuration;
    this.endDuration = endDuration;
  }

  public void computeKeyValue(Duration keyValueDuration, KV keyValue) {
    if (keyValueDuration.greaterThan(startDuration) && !ignoredStartKeyValues.contains(keyValue)) {
      startKeyValues.add(keyValue);
    } else {
      startKeyValues.remove(keyValue);
      ignoredStartKeyValues.add(keyValue);
    }

    if (keyValueDuration.lessThan(endDuration) && !ignoredEndKeyValues.contains(keyValue)) {
      endKeyValues.add(keyValue);
    } else {
      endKeyValues.remove(keyValue);
      ignoredEndKeyValues.add(keyValue);
    }
  }

  public Optional<Set<KV>> getStartKeyValues() {
    return startKeyValues.isEmpty()
        ? Optional.empty()
        : Optional.of(Collections.unmodifiableSet(startKeyValues));
  }

  public Optional<Set<KV>> getEndKeyValues() {
    return endKeyValues.isEmpty()
        ? Optional.empty()
        : Optional.of(Collections.unmodifiableSet(endKeyValues));
  }

  public Duration getStartDuration() {
    return startDuration;
  }

  public Duration getEndDuration() {
    return endDuration;
  }
}
