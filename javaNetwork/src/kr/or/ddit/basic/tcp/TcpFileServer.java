package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
	private ServerSocket server;
	private Socket socket;
	private DataInputStream dis;		// 문자 수신용
	private BufferedInputStream bis;	// 데이터 수신용
	private BufferedOutputStream bos;	// 파일 저장용
	
	private void serverStart(){
		File saveDir = new File("d:/d_other/download");	// 저장할 폴더
		if (!saveDir.exists()) {	// 저장할 폴더가 없으면 저장할 폴더를 새로 만들어 준다.
			saveDir.mkdirs();
		}
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다.");
			
			socket = server.accept();	// 클라이언트의 접속요청을 기다린다.
			
			System.out.println("파일 다운로드 시작...");
			
			// 클라이언트가 처음으로 보내는 데이터 받기 ==> 파일이름이 처음에 전송되어 온다.
			dis = new DataInputStream(socket.getInputStream());
			String fileName = dis.readUTF();
			
			// 저장할 파일 위치와 파일이름을 지정하여 File객체 생성
			File saveFile = new File(saveDir, fileName);
			
			bis = new BufferedInputStream(socket.getInputStream());	// 수신용 스트림 객체
			
			// 파일 저장용 스트림 객체 생성
			bos = new BufferedOutputStream(new FileOutputStream(saveFile));
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			// 소켓으로 수신된 데이터를 파일로 저장한다.
			while((len = dis.read(temp)) > 0){
				bos.write(temp, 0, len);
			} bos.flush();
			
			System.out.println("파일 다운로드 완료...");
			
		} catch (IOException e) {
			System.out.println("파일 다운로드 실패!!");
			e.printStackTrace();
		} finally {
			if(dis != null) try{ dis.close(); }catch(IOException e){}
			if(bis != null) try{ bis.close(); }catch(IOException e){}
			if(bos != null) try{ bos.close(); }catch(IOException e){}
			if(socket != null) try{ socket.close(); }catch(IOException e){}
			if(server != null) try{ server.close(); }catch(IOException e){}
		}
	}
	
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
	}
	
	/*	서버에서 클라이언트로 데이터 보내기
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(7777);
			System.out.println("서버가 준비중입니다.");
			
			Socket socket = server.accept();
			System.out.println("파일을 전송합니다.");
			String path = "d:/d_other/고양이.jpg";
			
			File f = new File(path);
			DataOutputStream dos = null;
			FileInputStream fin = new FileInputStream(f);
			BufferedInputStream bin = new BufferedInputStream(fin);
			
			if (f.exists()) {
				dos = new DataOutputStream(socket.getOutputStream());
				
				byte[] arr = new byte[1024];
				int len = 0;
				while((len = bin.read(arr)) > 0) {
					dos.write(arr, 0, len);
				} dos.flush();
				
				System.out.println("파일 전송이 완료되었습니다.");
			} else {
				System.out.println("해당 파일이 존재하지 않습니다.");
			}
			
			bin.close();
			dos.close();
			server.close();

		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	*/
}
