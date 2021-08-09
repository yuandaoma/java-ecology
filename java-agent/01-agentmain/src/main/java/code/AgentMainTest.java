package code;

import java.util.List;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

/**
 *
 * 1.mvn clean package
 * 2.不能使用VM参数来对agentmain这种类型的进行加载
 */
public class AgentMainTest {

	public static void main(String[] args) throws Exception{
		System.out.println("=========> Agent main test print.");
		//获取当前系统中所有 运行中的 虚拟机
		System.out.println("running JVM start ");
		List<VirtualMachineDescriptor> list = VirtualMachine.list();
		for (VirtualMachineDescriptor vmd : list) {
			//如果虚拟机的名称为 xxx 则 该虚拟机为目标虚拟机，获取该虚拟机的 pid
			//然后加载 agent.jar 发送给该虚拟机
			System.out.println(vmd.displayName());
			if (vmd.displayName().endsWith("code.AgentMainTrace")) {
				VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
				virtualMachine.loadAgent("C:\\Users\\Administrator\\Documents\\mycode\\java-ecology\\java-agent\\01-agentmain\\target\\agentmain-agent.jar");
				virtualMachine.detach();
			}
		}
	}
}
