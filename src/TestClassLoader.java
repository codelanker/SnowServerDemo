
public class TestClassLoader {
	public static void main(String[] args) {
		System.out.println("TestClassLoader.getClass().getClassLoader():"
				+TestClassLoader.class.getClassLoader()
				);
		try {
			Class.forName("TestClassLoader", true, 
					TestClassLoader.class.getClassLoader().getParent());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
