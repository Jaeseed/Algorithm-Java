package tree;

public class Test {

	public static void main(String[] args) {
		int[] a = {1,2,3};
		test(a);
		System.out.println(a[1]);

	}
	static void test(int[] arr) {
		arr[0] = 0;
		arr[1] = 7;
	}

}
