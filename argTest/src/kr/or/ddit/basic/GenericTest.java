package kr.or.ddit.basic;
/*
 * 데이터를 보관하는 용도로 많이 쓰인다.
	제네릭 클래스 만드는 방법
	
	형식) class 클래스명<제네릭타입글자> {
		 	제네릭타입글자 변수명;		// 변수 선언에 제네릭을 사용할 경우
		 	..
		 	
		 	제네릭타입글자 메서드명(파라미터 변수..) {		// 반환값이 있는 메서드에서 사용할 경우
		 		..
		 		return value;
		 	}
		 	
		 	반환값타입 메서드명(제네릭타입글자 변수명, ..) {	// 메서드의 파라미터 변수에 사용할 경우
		 		..
		 	}
		 }
*/

// 제네릭을 사용하지 않는 클래스 작성
class NonGenericClass {
	private Object val;
	
	public void setVal(Object val) {
		this.val = val;
	}
	
	public Object getVal() {
		return val;
	}
}

// 제네릭을 사용하는 클래스 작성
class MyGeneric<T> {
	private T val;	// 변수 선언
	
	public void setVal(T val) {	// 메서드의 파라미터 변수에 사용
		this.val = val;
	}
	
	public T getVal() {	// 메서드의 반환값 타입에 사용
		return val;
	}
}

public class GenericTest {
	public static void main(String[] args) {
		NonGenericClass ngc1 = new NonGenericClass();
		ngc1.setVal("장성보");

		NonGenericClass ngc2 = new NonGenericClass();
		ngc2.setVal(26);
		
		String rtnStr = (String) ngc1.getVal();
		
		/* Object를 int로 형변환하는데 컴파일 단계에서는 문제가없지만, 실제 데이터 String이 int로 형변환이 불가해 오류가 발생
		 * 제네릭을 사용해서 컴파일단계에서부터 미연에 방지할 수 있다.
			int rtnStrint = (int) ngc1.getVal();	
		*/
		System.out.println("문자열 반환값 : " + rtnStr);
		
		int rtnInt = (int) ngc2.getVal();
		System.out.println("정수형 반환값 : " + rtnInt);
		
		MyGeneric<String> mgc1 = new MyGeneric<>();
		mgc1.setVal("장성보");
		
		MyGeneric<Integer> mgc2 = new MyGeneric<>();
		mgc2.setVal(26);
		
		rtnStr = mgc1.getVal();
		System.out.println("\n문자열 반환값 : " + rtnStr);
		rtnInt = mgc2.getVal();
		System.out.println("정수형 반환값 : " + rtnInt);
	}
}
