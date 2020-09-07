package kr.or.ddit.rmiChat.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.rmiChat.inf.IClientChat;
import kr.or.ddit.rmiChat.inf.IServerChat;

// 서버용 RMI인터페이스를 구현한 클래스
public class RmiChatServer extends UnicastRemoteObject implements IServerChat{
	// 접속한 클라이언트가 저장될 List객체 변수 선언
	private List<IClientChat> clientList;
	
	// 생성자
	public RmiChatServer() throws RemoteException{
		clientList = new ArrayList<>();
	}
	
	// 접속한 클라이언트를 List에 추가하는 메서드
	@Override
	public void setClient(IClientChat client) throws RemoteException {
		clientList.add(client);
		System.out.println("새로운 클라이언트가 접속했습니다.");
	}

	// 리스트에 등록된 모든 클라이언트에게 메시지 보내는 메서드
	@Override
	public void transMessage(String msg) throws RemoteException {
		System.out.println("입력 받은 메시지 : " + msg);
		// 클라이언트가 저장된 리스트 개수만큼 반복
		for(IClientChat client : clientList){
			client.displayMessage(msg);
		}
	}
	
	public static void main(String[] args) {
		try {
			IServerChat server = new RmiChatServer();
			
			Registry reg = LocateRegistry.createRegistry(1099);	// 레지스트리 기본 포트
			
			reg.rebind("rmiChat", server);
			
			System.out.println("서버 준비 완료");
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
}
