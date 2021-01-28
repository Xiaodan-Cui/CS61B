
public class kk {

	public static void main(String[] args) {
		int[] numbers={1,2,-3,-4,4,5,4};
		int n=3;
		max(numbers,n);}
	public static void max (int[] a, int n){
		for (int s:a) {
			if (s<=0)
			{continue;}
			s=s+n;
			}
		System.out.println(java.util.Arrays.toString(a));
	}
}
