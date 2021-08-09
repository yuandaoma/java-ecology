package code;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class PreMainTraceAgent {

	public static void premain(String agentArgs, Instrumentation instrumentation) {
		System.out.println("agentArgs: " + agentArgs);
		instrumentation.addTransformer(new PreMainTransformer());
	}

	public static class PreMainTransformer implements ClassFileTransformer {

		@Override
		public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
			System.out.println("premain load class : " + className);
			return classfileBuffer;
		}
	}

}
