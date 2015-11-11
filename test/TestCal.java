public class TestCal {

	public static int shl(int num) {
		int r = 1 << num;
		System.out.println("shl[" + num + "]:" + r);
		return r;
	}

	public static int and(int num1, int num2) {
		int r = num1 & num2;
		System.out.println("and[" + num1 + "," + num2 + "],Binary["
				+ Integer.toBinaryString(num1) + ","
				+ Integer.toBinaryString(num2) + "]:" + r);
		return r;
	}

	public static int or(int num1, int num2) {
		int r = num1 | num2;
		System.out.println("or[" + num1 + "," + num2 + "],Binary["
				+ Integer.toBinaryString(num1) + ","
				+ Integer.toBinaryString(num2) + "]:" + r);
		return r;
	}

	public static int shr(int num) {
		int r = 1 >> num;
		System.out.println("shr[" + num + "]:" + r);
		return r;
	}

	public static int xor(int num1, int num2) {
		int r = num1 ^ num2;
		System.out.println("xor[" + num1 + "," + num2 + "],Binary["
				+ Integer.toBinaryString(num1) + ","
				+ Integer.toBinaryString(num2) + "]:" + r + " Binary:"
				+ Integer.toBinaryString(r));
		return r;
	}

	public static void main(String[] args) {
		int total = shl(0) + shl(1) + shl(2) + shl(3) + shl(4);
		System.out.println("total:" + total);
		and(total, shl(0));
		and(total, shl(1));
		and(total, shl(2));
		and(total, shl(3));
		and(total, shl(4));
	}
}
