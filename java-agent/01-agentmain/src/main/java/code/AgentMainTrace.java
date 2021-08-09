package code;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class AgentMainTrace {

	public static void agentmain(String agentArgs, Instrumentation instrumentation) {
		System.out.println("agentmain method");
		instrumentation.addTransformer(new MyAgentMainTransform(), true);
	}

	static class MyAgentMainTransform implements ClassFileTransformer {
		@Override
		public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
			System.out.println("agentmain load class: " + className);
			return classfileBuffer;
		}
	}
}
