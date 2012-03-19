package scriptlike;

import org.seasar.util.beans.PropertyDesc;

/**
 * ScriptLikePropertyの実装クラスです。.
 * @author wataru
 *
 */
public class ScriptLikePropertyImpl implements ScriptLikeProperty {
	
	private Object obj;
	
	private PropertyDesc propertyDesc;
	
	/**
	 * コンストラクタ .
	 * @param Object オブジェクト
	 * @param propertyDesc プロパティ定義
	 */
	protected ScriptLikePropertyImpl(Object obj
			, final PropertyDesc propertyDesc) {
		this.obj = obj;
		this.propertyDesc = propertyDesc;
	}

	@Override
	public Object get() {
		return propertyDesc.getValue(obj);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> clazz) {
		return (T) propertyDesc.getValue(obj);
	}


	@Override
	public void set(Object value) {
		propertyDesc.setValue(obj, value);
	}
}
