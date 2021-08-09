package code;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * @author rickiyang
 * @date 2019-08-06
 * @Desc
 */
public class MyClassTransformer implements ClassFileTransformer {
	@Override
	public byte[] transform(final ClassLoader loader, final String className, final Class<?> classBeingRedefined,
		final ProtectionDomain protectionDomain, final byte[] classfileBuffer) {
		// 操作Date类
		if ("java/util/Date".equals(className)) {
			try {
				System.out.println("get java.util.Date");
				// 从ClassPool获得CtClass对象
				final ClassPool classPool = ClassPool.getDefault();
				final CtClass clazz = classPool.get("java.util.Date");
				CtMethod convertToAbbr = clazz.getDeclaredMethod("convertToAbbr");
				//这里对 java.util.Date.convertToAbbr() 方法进行了改写，在 return之前增加了一个 打印操作
				String methodBody = "{sb.append(Character.toUpperCase(name.charAt(0)));" +
					"sb.append(name.charAt(1)).append(name.charAt(2));" +
					"System.out.println(\"sb.toString()\");" +
					"System.out.println(\"hhhhhhhhhhhhhhhhhhhhhh\");" +
					"return sb;}";
				System.out.println("convertToAbbr setBody");
				convertToAbbr.setBody(methodBody);

				// 返回字节码，并且detachCtClass对象
				byte[] byteCode = clazz.toBytecode();
				//detach的意思是将内存中曾经被javassist加载过的Date对象移除，如果下次有需要在内存中找不到会重新走javassist加载
				clazz.detach();
				System.out.println("return new bytecode");
				return byteCode;
			} catch (Exception ex) {
				System.out.println("ex = " + ex.getMessage());
				ex.printStackTrace();
			}
		}
		// 如果返回null则字节码不会被修改
		// System.out.println("return null");
		return null;
	}
}