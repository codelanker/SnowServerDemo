package arithmetic;

public class Shell {
	public static int[] a = {4,2,1,6,3,6,0,-5,1,1};
	public static void main(String[] args) {
		show("befor shell sort :",0);
		shellSort(a.length);
		show("after shell sort :",0);
	}
	public static void shellSort(int index){
		int dataLength = (int)index/2;
		int tmp = 0;
		int p = 0;
		boolean change = false;
		while(dataLength!=0){
			for(int i = dataLength;i<index;i++){
				System.out.print("\n---------------  i="+i+"  ------------------");
				change = false;
				tmp = a[i];
				p = i-dataLength;
				while(tmp<a[p]&&p>=0&&p<=index){
					a[p+dataLength] = a[p];
					p = p - dataLength;
					/*if(i==8){
						show("&&&&", 8);
					}*/
					change = true;
					if(p<0||p>index)break;
				}
				a[p+dataLength] = tmp;
				if(change){
					show("排序中：",p+dataLength);
				}
			}
			System.out.println("\ndataLength  "+dataLength);
			dataLength = dataLength/2;
		}
	}
	public static void show(String msg,int po){
		System.out.println("\n"+msg);
		int poo = 0;
		for (int i : a) {
			
			if(poo==po){
				System.out.print("["+i+"]  ");
			}else{
				System.out.print(i+"  ");
			}
			poo++;
		}
	}
}
