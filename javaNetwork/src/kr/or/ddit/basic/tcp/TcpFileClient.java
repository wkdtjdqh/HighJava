package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class TcpFileClient {
	private Socket socket;
	private BufferedOutputStream bos;	// 소켓 전송용, 바이트 기반을 전송할 때
	private BufferedInputStream bis;	// 파일 읽기용, 문자 기반을 전송할 때
	private DataOutputStream dos;		// 문자 전송용
	
	public void clientStart(){
		/*
		// 전송할 파일을 이용한 File객체 생성
		String path = "d:/d_other/고양이.jpg";
		File file = new File(path);
		String fileName = file.getName();	// 파일 이름 구하기
		if (!file.exists()) {	// 전송할 파일이 있는지 검사
			System.out.println(fileName + " 파일이 없습니다.");
			return;
		}
		*/
		
		JFileChooser filechooser = new JFileChooser();
		filechooser.setCurrentDirectory(new File("d:/d_other"));
		
		String fileName = null;
		File file = null;
		
		int result = filechooser.showOpenDialog(new JPanel());
		if (result == JFileChooser.APPROVE_OPTION) {
			file = filechooser.getSelectedFile();	// 선택된 파일
			fileName = file.getName();
		} else {
			System.out.println("파일 전송을 취소합니다.");
			return;
		}
		
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("파일 전송 시작...");
			
			// 처음 접속되면 '파일이름'을 전송한다.
			dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF(fileName);
			
			// 파일 읽기용 스트림 객체 생성
			bis = new BufferedInputStream(new FileInputStream(file));
			
			// 서버로 전송할 스트림 객체 생성
			bos = new BufferedOutputStream(socket.getOutputStream());
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			// 파일 내용을 읽어와 소켓을 통해서 전송한다.
			while((len = bis.read(temp)) > 0){
				bos.write(temp, 0, len);
			} bos.flush();
			
			System.out.println("파일 전송 완료...");
			
		} catch (IOException e) {
			System.out.println("파일 전송 실패...");
			e.printStackTrace();
		} finally {
			// 사용한 스트림 닫기
			if(dos != null) try{ dos.close(); }catch(IOException e){}
			if(bis != null) try{ bis.close(); }catch(IOException e){}
			if(bos != null) try{ bos.close(); }catch(IOException e){}
			if(socket != null) try{ socket.close(); }catch(IOException e){}
		}
	}
	
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}
	
	/* 서버에서 보낸 데이터 받기
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 7777);
			System.out.println("서버와 연결되었습니다.");
			String path = "d:/d_other/download/고양이.jpg";
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
			
			int data;
			while((data = socket.getInputStream().read()) != -1){
				bos.write(data);
			}
			System.out.println("서버로부터 파일을 받았습니다.");
			
			bos.close();
			socket.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	*/
}
