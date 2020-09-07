package kr.or.ddit.basic.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.basic.inf.RemoteInterface;
import kr.or.ddit.basic.vo.FileInfoVO;
import kr.or.ddit.basic.vo.TestVO;

public class RemoteClient {
	public static void main(String[] args) {
		try {
			/*
				RMI용 객체를 서버에서 구해와서 사용하는 순서
				1. 서버에서 등록한 RMI용 객체를 찾기 위해 Registry객체를 생성한다.
					(서버의 IP주소와 제공하는 포트번호를 지정하여 생성한다.)
			*/
			Registry reg = LocateRegistry.getRegistry("localhost", 9999);
			/*
				2. 서버에서 등록한 '객체의 Alias'를 이용하여 객체를 불러온다.
				형식] Registry객체변수.lookup("객체의 Alias");
			*/
			RemoteInterface inf = (RemoteInterface) reg.lookup("rmiServer");
			// 3. 이제부터 불러온 객체의 메서드를 호출해서 사용할 수 있다.
			int a = inf.doRemotePrint("안녕하세요.");
			System.out.println("서버의 반환값 : " + a);
			System.out.println();
			
			List<String> list = new ArrayList<>();
			list.add("복숭아");
			list.add("포도");
			list.add("딸기");
			list.add("대추");
			
			inf.doPrintList(list);
			System.out.println("List 전송 끝");
			System.out.println();
			
			TestVO myVo = new TestVO();
			myVo.setNumber(123);
			myVo.setName("홍길동");
			
			inf.doPrintVo(myVo);
			System.out.println("VO객체 전송 끝");
			System.out.println();
			
			// 파일 전송처리 시작
			File file = new File("d:/d_other/고양이.jpg");
			if (!file.exists()) {
				System.out.println("복사할 원본 파일이 없습니다.");
				return;
			}
			
			FileInfoVO fileInfo = new FileInfoVO();	// 파일 정보 저장용 VO객체 생성
			
			// 파일 용량을 구해서 FileInfoVO의 fileData배열의 크기를 설정한다.
			int len = (int) file.length();
			byte[] data = new byte[len];
			
			// 파일을 읽어올 스트림 객체 생성
			FileInputStream fis = new FileInputStream(file);
			
			// 파일의 내용을 읽어와 byte배열에 저장한다.
			fis.read(data);
			
			// 파일 정보 VO객체에 파일이름과 파일 내용을 셋팅한다.
			fileInfo.setFileName(file.getName());	// 파일 이름 설정
			fileInfo.setFileData(data);				// 파일 데이터 설정
			
			// 서버의 파일 전송용 메서드 호출
			inf.setFile(fileInfo);

			System.out.println("파일 전송 작업 끝");
			
		} catch (RemoteException e) {
			// TODO: handle exception
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
