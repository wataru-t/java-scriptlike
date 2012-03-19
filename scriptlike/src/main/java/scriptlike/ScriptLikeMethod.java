package scriptlike;

/**
 * ScriptLikeMethod .
 * @author wataru
 *
 */
public interface ScriptLikeMethod extends ScriptLikeMethodCall {
	/**
	 * メソッドへ渡す引数を設定します。.
	 * @param args メソッドへ渡す引数
	 * @return メソッドを実行するCallオブジェクト
	 */
	ScriptLikeMethodCall args(Object...args);
}
