import java.net.*;
import java.util.List;
import java.io.*;
import java.util.*;

public class Server {
	public static List<Socket> ServerList = Collections.synchronizedList(new ArrayList<>());
	public static void main(String[] args) {
		try{
			ServerSocket ss = new ServerSocket(9999);
			while(true){
				Socket s = ss.accept();
				ServerList.add(s);
				new Thread(new ServerThread(s)).start();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("欢迎使用");
		}
		
	}

}

class ServerThread implements Runnable{
	public Socket s;
	public BufferedReader br;
	public ServerThread(Socket s){
		this.s = s;
		try{
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		try{
			String line ;
			while((line=getMessege())!=null){
				for(Socket s:Server.ServerList){
					PrintStream ps = new PrintStream(s.getOutputStream());
					ps.println(line);
				}
			}
		}catch(IOException ee){
			ee.printStackTrace();
		}
	}
	
	public String getMessege(){
		try{
			String hello = br.readLine();
			System.out.println(hello);
			return hello;
		}catch(IOException e){
			e.printStackTrace();
			Server.ServerList.remove(s);
		}
		return "";
	}
}




















