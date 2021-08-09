package code;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AgentMainTest {

	public static void agentmain(String agentArgs, Instrumentation instrumentation) {
		// instrumentation.addTransformer(new MyAgentTransform(),true);
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			public void run() {
				JvmStack.printMemoryInfo();
				JvmStack.printGCInfo();
				System.out.println(
					"===================================================================================================");
			}
		}, 0, 5000, TimeUnit.MILLISECONDS);

	}

	// static class MyAgentTransform implements ClassFileTransformer{
	// 	@Override
	// 	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
	// 		ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
	// 		System.out.println("agentmain load class: " + className);
	// 		return classfileBuffer;
	// 	}
	// }
}
