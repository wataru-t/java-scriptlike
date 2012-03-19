package scriptlike;

/**
 * ScriptLikeMethodCall .
 * @author wataru
 *
 */
public interface ScriptLikeMethodCall {
	/**
	 * メソッドを呼び出し（実行し）、戻り値を返します。.
	 * @return メソッドの戻り値
	 */
	Object call();
	
	/**
	 * メソッドを呼び出し（実行し）、戻り値を返します。.
	 * 戻り値は引数の型に内部でキャストして返します。
	 * @param <T> 型
	 * @param clazz クラス
	 * @return メソッドの戻り値
	 */
	<T> T call(Class<T> clazz);
}
