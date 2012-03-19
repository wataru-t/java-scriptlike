package scriptlike;

import org.seasar.util.beans.MethodDesc;

public class ScriptLikeMethodCallImpl implements ScriptLikeMethodCall {
	
	private Object obj;
	
	private MethodDesc[] methodDescs;
	
	private Object[] args;
	
	/**
	 * コンストラクタ .
	 * @param obj オブジェクト 
	 * @param methodDesc メソッド定義
	 */
	protected ScriptLikeMethodCallImpl(Object obj
			, final MethodDesc[] methodDescs) {
		this(obj, methodDescs, new Object[]{});
	}
	
	/**
	 * コンストラクタ .
	 * @param obj オブジェクト
	 * @param methodDesc メソッド定義
	 * @param args メソッドへ渡す引数の配列
	 */
	protected ScriptLikeMethodCallImpl(Object obj
			, final MethodDesc[] methodDescs, Object...args) {
		this.obj = obj;
		this.methodDescs = methodDescs;
		this.args = args;
	}

	@Override
	public Object call() {
		MethodDesc methodDesc = getMethodDesc(args);
		return methodDesc.invoke(obj, args);
	}

	private MethodDesc getMethodDesc(Object[] args) {
		MethodDesc found = null;
		for (MethodDesc each : methodDescs) {
			Class<?>[] classes = each.getParameterTypes();
			if (isEquals(args, classes)) {
				found = each;
				break;
			}
		}
		return found;
	}

	private boolean isEquals(Object[] args, Class<?>[] classes) {
		if (args.length != classes.length) {
			return false;
		}
		int size = args.length;
		for (int i = 0; i < size; i++) {
			Class<?> clazz = args[i].getClass();
			if (!classes[i].isAssignableFrom(clazz)) {
				return false;
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T call(Class<T> clazz) {
		return (T) call();
	}

}
