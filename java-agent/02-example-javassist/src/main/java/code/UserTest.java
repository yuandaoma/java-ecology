package code;

/**
 * 1.执行mvn clean package
 * 2.VM options：
 *   -javaagent:C:\Users\Administrator\Documents\mycode\java-ecology\java-agent\02-example-javassist\target\javassist-agent.jar
 */
public class UserTest {

	public static void main(String[] args) {
		User user = new User("Bill");
		System.out.println("user = " + user.toString());
	}
}
