package de.schlegel11.jfxanimation.helper;

import javafx.beans.value.WritableValue;

/**
 * Helper wrapper class which takes a {@link javafx.animation.KeyValue} and the target {@link
 * WritableValue}. <br>
 * Provides equals method which is based on the target {@link WritableValue}.
 *
 * @author Marcel Schlegel (schlegel11)
 * @version 1.0
 * @since 2018-12-12
 */
public class KeyValueWrapper<KV> {

  private final KV keyValue;
  private final WritableValue<Object> writableValue;

  public KeyValueWrapper(KV keyValue, WritableValue<Object> writableValue) {
    this.keyValue = keyValue;
    this.writableValue = writableValue;
  }

  public KV getKeyValue() {
    return keyValue;
  }

  public WritableValue<Object> getWritableValue() {
    return writableValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    KeyValueWrapper<?> that = (KeyValueWrapper<?>) o;
    return writableValue == that.writableValue;
  }

  @Override
  public int hashCode() {
    return writableValue.hashCode();
  }
}
