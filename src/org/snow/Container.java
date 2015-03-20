package org.snow;

public class Container {
	private static class InstanceHolder{
		static Container instance = new Container();
	}
	public static Container get(){
		return InstanceHolder.instance;
	}
	private Container() {
	}
	public void init(String[] args){
	}
	public void start(){
	}
	public void stop(){
	}
	public static void main(String[] args){
		//hook is a start but not run thread
		Runtime.getRuntime().addShutdownHook(new Thread("ShutDownHook"){
			@Override
			public void run() {
				try {
					Container.get().stop();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		Container container = Container.get();
		try {
			container.init(args);
			container.start();
		} catch (Exception e) {
			e.printStackTrace();
			//System.exit(1);//not 0 is not normal exist
		}
	}
}
