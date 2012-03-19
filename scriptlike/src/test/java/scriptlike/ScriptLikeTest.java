package scriptlike;


import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * ScriptLikeクラスのテストクラスです。.
 * @author wataru
 *
 */
public class ScriptLikeTest extends TestCase {
	
	public static class Hoge {
		private String message;
		
		public Hoge() { }

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
		
		public void execute() {
			System.out.println(message);
		}
		
		public void execute(String addMessage) {
			System.out.println(message);
			System.out.println(addMessage);
		}
		
		public Integer getNum() {
			return 100;
		}
		
	}
	
	private Hoge hoge;
	
	@Before
	public void setUp() throws Exception {
		hoge = new Hoge();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testScriptLikeTime() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			ScriptLike.$(hoge);
		}
		long end = System.currentTimeMillis();
		System.out.println("ScriptLike 処理時間:" + (end - start) + "ミリ秒");
	}
	
	@Test
	public void testScriptLikePropertyTime() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			ScriptLike.$(hoge).property("message");
		}
		long end = System.currentTimeMillis();
		System.out.println("ScriptLikeProperty 処理時間:" + (end - start) + "ミリ秒");		
	}
	
	@Test
	public void testScriptLikeMethodTime() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 100000; i++) {
			ScriptLike.$(hoge).method("getNum").call();
		}
		long end = System.currentTimeMillis();
		System.out.println("ScriptLikeMethod 処理時間:" + (end - start) + "ミリ秒");				
	}
	
	@Test
	public void testCallMethodNgParam() {
		boolean ret = false;
		try {
			ScriptLike.$(hoge).method("execute").args(100).call();
		} catch (Throwable e) {
			ret = true;
		}
		assertTrue(ret);
	}
	
	@Test
	public void testCallMethodNullParam() {
		Object[] args = getArgs();
		ScriptLike.$(hoge).method("getNum").args(args).call();
	}
	
	private Object[] getArgs() {
		return null;
	}
	
	@Test
	public void testCallMethodRtnValueWithClass() {
		Integer rtn = ScriptLike.$(hoge).method("getNum").call(Integer.class);
		assertEquals(Integer.valueOf(100), rtn);
	}
	
	@Test
	public void testCallMethodRtnValue() {
		Integer rtn = (Integer) ScriptLike.$(hoge).method("getNum").call();
		assertEquals(Integer.valueOf(100), rtn);
	}
	
	@Test
	public void testCallMethodWithParam() {
		ScriptLike.$(hoge).property("message").set("Hello, World!");
		ScriptLike.$(hoge).method("execute").args("This is param").call();
	}
	
	@Test
	public void testCallMethod() {
		ScriptLike.$(hoge).property("message").set("Hello, World.");
		ScriptLike.$(hoge).method("execute").call();
	}
	
	@Test
	public void testSetProperty() {
		final String expected = "Hello, World!";
		assertNull(hoge.getMessage());
		ScriptLike.$(hoge).property("message").set(expected);
		assertEquals(expected, hoge.getMessage());
	}
	
	@Test
	public void testGetPropertyWithParam() {
		final String expected = "Hello, World!";
		hoge.setMessage(expected);
		String message = 
			ScriptLike.$(hoge).property("message").get(String.class);
		assertEquals(expected, message);		
	}
	
	@Test
	public void testGetProperty() {
		final String expected = "Hello, World!";
		hoge.setMessage(expected);
		String message = 
			(String) ScriptLike.$(hoge).property("message").get();
		assertEquals(expected, message);
	}
	
	@Test
	public void testScriptLikeProperty() {
		ScriptLikeProperty scriptLikeProperty = 
			ScriptLike.$(hoge).property("message");
		assertNotNull(scriptLikeProperty);
	}
	
	@Test
	public void testScriptLikeMethod() {
		ScriptLikeMethod scriptLikeMethod = 
			ScriptLike.$(hoge).method("execute");
		assertNotNull(scriptLikeMethod);
	}
	
	@Test
	public void testScriptLike() {
		ScriptLike scriptLike = ScriptLike.$(hoge);
		assertNotNull(scriptLike);
	}
}
