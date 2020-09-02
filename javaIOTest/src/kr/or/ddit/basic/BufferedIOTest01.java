package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {
	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해서 Buffered스트림을 사용한다.
		try {
			FileOutputStream fout = new FileOutputStream("d:/d_other/bufferTest.txt");
			
			// 버퍼의 크기를 지정한 스트림 객체 생성(크기가 5인 객체)
			BufferedOutputStream bos = new BufferedOutputStream(fout, 5);
			
			for(int i = '1'; i <= '9'; i++) {
				bos.write(i);
			} // 버퍼의 크기가 5 -> 1부터 5 : 5, 채워진 후 6부터 9 : 4, 때문에 값이 입력되지 않는다.
//			bos.flush();	// 작업을 종료하기 전에 버퍼에 남아있는 데이터를 모두 출력시킨다.
			
			//fout.close();
			bos.close();	// 보조스트림을 닫으면 보조스트림에서 사용한 기반이 되는 스트림도 자동으로 닫힌다.
			
			System.out.println("출력 작업 끝..");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
