package prime.Core.Primitive.A_I;


public interface iNode<T> {

	public T get();

	//public void set(T to);

	public default Object of() {
		if (this.get() != null)
			return this.get().getClass();
		else
			return Object.class;
	}

}
