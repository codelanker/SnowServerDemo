public class Test {
public static void main(String[] args) throws InterruptedException {
	System.out.println("test");
	Runnable r = new Runnable() {
		@Override
		public void run() {
			for (int i = 0; i < 6; i++) {
				System.out.println(i+" ");
			}
		}
	};
	for (int i = 0; i < 2; i++) {
		Thread t = new Thread(r);
		t.start();
		Thread.sleep(500);
	}
	new Thread(){@Override
	public void run() {
		for (int i = 0; i < 6; i++) {
			System.out.println(i+" thread ");
		}
		super.run();
	}}.start();
}
}
