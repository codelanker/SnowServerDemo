package scheme.decorator;

public class Employee implements Person {

	@Override
	public void doCoding() {
		System.out.println("码农终于写完了代码...");
	}

}
