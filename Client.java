import java.net.*;
import java.io.*;

public class Client {
	public static  String content;
	public static  Socket s;
	public static void main(String[]args){
		try{
			s = new Socket("127.0.0.1",9999);
			new Thread(new SecondThread(s)).start();
			PrintStream ps = new PrintStream(s.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String line;
			while((line=br.readLine())!=null){
				ps.println(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("谢谢使用");
		}
	}
}

class SecondThread implements Runnable{
	public Socket s;
	public BufferedReader br;
	public SecondThread(Socket s) throws Exception{
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	public void run(){
		try{
			String line;
			while((line=br.readLine())!=null){
				System.out.println(line);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}






















