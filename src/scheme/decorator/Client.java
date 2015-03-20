package scheme.decorator;

/**
 * 装饰着模式 运行时(重写)多态
 * @author lanker.liu
 *
 */
public class Client {
	public static void main(String[] args) {
		Person employee = new Employee();
		employee = new ManagerA(employee);
		employee = new ManagerB(employee);
		employee = new ManagerB(employee);
		employee.doCoding();
	}
}
