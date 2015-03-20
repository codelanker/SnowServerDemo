
public class TestTime {
	public static void main(String[] args) {
		for (long i = 0L; i <= 70L; i++) {
			long seconds = i*365L*24L*3600L;
			//System.out.println(i+" the seconds from 1900 to 1970="+seconds);
		}
		
		long rs = (System.currentTimeMillis()/1000L+2208988800L);
		System.out.println(rs);
		System.out.println((int)rs);
		System.out.println((int)(System.currentTimeMillis()/1000L));
	}
}
