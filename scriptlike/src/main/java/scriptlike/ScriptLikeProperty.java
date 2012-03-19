package scriptlike;

/**
 * ScriptLikeProperty .
 * @author wataru
 *
 */
public interface ScriptLikeProperty {
	/**
	 * プロパティの値を返します。.
	 * @return プロパティの値
	 */
	Object get();
	
	/**
	 * プロパティの値を返します。.
	 * 内部で引数のクラスの型にキャストします。
	 * @param <T> 型
	 * @param clazz クラス
	 * @return プロパティの値
	 */
	<T> T get(Class<T> clazz);
	
	/**
	 * プロパティに値を設定します。.
	 * @param value 値
	 */
	void set(Object value);
}
