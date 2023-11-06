import java.net.ServerSocket;
    import java.net.Socket;
    import java.io.*;
 
     public class klaienSocket {
        public static void main(String[] args) {
            try {
                // buat server socket dan mendengarkan koneksi dari client di port tertentu
                ServerSocket ServerSocket = new ServerSocket(696969);
 
                while (true) {
                // terima permintaan koneksi dari si client
                Socket clientSocket = ServerSocket.accept();
                ServerSocket.close();

                 // buat thread untuk menangani koneksi dengan si client
                Thread clientThread = new ClientHandlerThread(clientSocket);
                clientThread.start();
            }
        } catch (IOException e) {
             e.printStackTrace();
        }
    }
 } 

    class ClientHandlerThread extends Thread {
        private Socket clientSocket;
 
        public ClientHandlerThread(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }
 
        public void run() {
            try {
                // terima data dari client
                InputStream inputStream = clientSocket.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                char[] buffer = new char[1024];
                int bytesRead = reader.read(buffer);
 
                // tampilan data dari client
                System.out.println("Received from Client: " + new String(buffer, 0, bytesRead));
 
                // kirim data ke client
                OutputStream outputStream = clientSocket.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                writer.write("Selamat pagi juga :D");
                writer.flush();
 
                // tutup socket client
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
