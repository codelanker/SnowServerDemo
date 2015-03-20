package scheme.decorator;

public class ManagerA extends Manager {
	private Person person;
	public ManagerA(Person person) {
		super();
		this.person = person;
	}
	@Override
	public void doCoding() {
		doEarlyWork();
		person.doCoding();
	}
	private void doEarlyWork(){
		System.out.println("项目经理A做需求分析");
		System.out.println("项目经理A做架构设计");
		System.out.println("项目经理A做详细设计");
	}
}
