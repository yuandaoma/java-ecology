package code;

import java.lang.instrument.Instrumentation;

public class UserAgent {


	public static void premain(String agentArgs, Instrumentation instrumentation){
		System.out.println("User Agent Start......");
		instrumentation.addTransformer(new UserTransformer());
	}
}
