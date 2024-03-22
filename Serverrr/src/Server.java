import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        ServerSocket serverSocket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedreader = null;
        BufferedWriter bufferedwriter = null;

        serverSocket = new ServerSocket(1234);

        while(true){
            try{
                socket = serverSocket.accept();
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedwriter = new BufferedWriter(outputStreamWriter);
                bufferedreader = new BufferedReader(inputStreamReader);

                while (true){
                    String msgfrmclient = bufferedreader.readLine();

                    System.out.println("Client: " + msgfrmclient);

                    bufferedwriter.write("MSG recieved");
                    bufferedwriter.newLine();
                    bufferedwriter.flush();

                    if (msgfrmclient.equals("BYE"))
                        break;
                }
                socket.close();
                inputStreamReader.close();
                outputStreamWriter.close();
                bufferedreader.close();
                bufferedwriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
