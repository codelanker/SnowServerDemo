package scheme.decorator;

public class ManagerB extends Manager {
	private Person person;
	public ManagerB(Person person) {
		super();
		this.person = person;
	}
	@Override
	public void doCoding() {
		person.doCoding();
		doAfter();
	}
	private void doAfter(){
		System.out.println("项目经理B 做收尾工作");
	}
}
