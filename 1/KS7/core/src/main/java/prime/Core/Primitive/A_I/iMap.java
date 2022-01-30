package prime.Core.Primitive.A_I;

public interface iMap<K, V> extends Iterable<K>, iGroup {

	public void put(K key, Object val);

	public void put(K key, Object... val);

	public iCollection<V> pull(K key);

	public default V get(K key) {
		return this.pull(key).get(0);
	}

	public boolean contains(K key, Object val);

	public default boolean containsKey(K key) {
		return this.getKeys().contains(key);
	}

	public default boolean containsValue(Object val) {
		return this.getValues().contains(val);
	}

	public iCollection getKeys();

	public iCollection getValues();

}
