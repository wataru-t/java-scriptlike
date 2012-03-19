package scriptlike;

import org.seasar.util.beans.BeanDesc;
import org.seasar.util.beans.MethodDesc;
import org.seasar.util.beans.PropertyDesc;
import org.seasar.util.beans.factory.BeanDescFactory;

/**
 * ScriptLike .
 * @author wataru
 *
 */
public final class ScriptLike {
	
	private Object obj;
	
	private BeanDesc beanDesc;
	
	/**
	 * コンストラクタ .
	 * 外部からはインスタンス化させない。
	 * @param Object オブジェクト
	 */
	private ScriptLike(Object obj) {
		this.obj = obj;
		beanDesc = BeanDescFactory.getBeanDesc(obj.getClass());
	}
	
	/**
	 * ScriptLikeオブジェクトを返します。.
	 * @param obj オブジェクト
	 * @return ScriptLike
	 */
	public static ScriptLike $(Object obj) {
		return new ScriptLike(obj);
	}
	
	/**
	 * ScriptLikeMethodを返します。.
	 * @param methodName メソッド名
	 * @return ScriptLikeMethod
	 */
	public ScriptLikeMethod method(String methodName) {
		MethodDesc[] descs = beanDesc.getMethodDescs(methodName);
		ScriptLikeMethod ret = new ScriptLikeMethodImpl(obj, descs);
		return ret;
	}
	
	/**
	 * ScriptPropertyを返します。.
	 * @param propertyName プロパティ名
	 * @return ScriptLikeProperty
	 */
	public ScriptLikeProperty property(String propertyName) {
		PropertyDesc propertyDesc = 
			beanDesc.getPropertyDesc(propertyName);
		ScriptLikeProperty ret = 
			new ScriptLikePropertyImpl(obj, propertyDesc);
		return ret;
	}
}
