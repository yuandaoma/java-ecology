package code;

/**
 *
 * 1.mvn clean package
 * 2.VM options：
 *   -javaagent:C:\Users\Administrator\Documents\mycode\java-ecology\java-agent\00-premain\target\premain-agent.jar
 */
public class PreAgentTest {

	public static void main(String[] args) {
		System.out.println("======>Pre agent test");
	}
}
