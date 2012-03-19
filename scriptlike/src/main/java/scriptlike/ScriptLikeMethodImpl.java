package scriptlike;

import org.seasar.util.beans.MethodDesc;

/**
 * ScriptLikeMethodの実装クラスです。.
 * @author wataru
 *
 */
public class ScriptLikeMethodImpl implements ScriptLikeMethod {
	
	private Object obj;
	
	private MethodDesc[] methodDescs;
	
	/**
	 * コンストラクタ .
	 * @param obj オブジェクト
	 * @param methodDescs メソッド定義配列
	 */
	protected ScriptLikeMethodImpl(Object obj
			, final MethodDesc[] methodDescs) {
		this.obj = obj;
		this.methodDescs = methodDescs;
	}

	@Override
	public Object call() {
		ScriptLikeMethodCall methodCall = 
			new ScriptLikeMethodCallImpl(obj, methodDescs);
		return methodCall.call();
	}

	@Override
	public ScriptLikeMethodCall args(Object...args) {
		if (args == null) {
			args = new Object[]{};
		}
		ScriptLikeMethodCall methodCall = 
			new ScriptLikeMethodCallImpl(obj, methodDescs, args);
		return methodCall;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T call(Class<T> clazz) {
		return (T) call();
	}

}
