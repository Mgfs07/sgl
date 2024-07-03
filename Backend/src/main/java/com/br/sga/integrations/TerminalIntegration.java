package com.br.sga.integrations;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonStructure;
import jakarta.json.JsonValue;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.Scanner;

@Component
public class TerminalIntegration {
    private ServerSocket serverSocket = null;
    private String serverIp;

    public TerminalIntegration() {
        // Constructor is now empty
    }

    @PostConstruct
    public void initialize() {
        try {
            InetAddress inetAddress = InetAddress.getByName("192.168.1.100"); // IP Address server
            int port = 1025;
            serverSocket = new ServerSocket(port, 50, inetAddress);
            this.serverIp = inetAddress.getHostAddress();
            System.out.println("Server started on port " + serverSocket.getLocalPort() + "...");
            System.out.println("Server IP address: " + serverIp);
            System.out.println("Waiting for client...");
        } catch (IOException e) {
            System.out.println("Error initializing server socket: " + e.getMessage());
        }
    }

    public void start() {
        if (serverSocket == null) {
            System.out.println("Server socket is not initialized.");
            return;
        }
        while (true) {
            try (Socket socket = serverSocket.accept()) {
                System.out.println("Client " + socket.getRemoteSocketAddress() + " connected to the server...");

                try (BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
                     DataInputStream dis = new DataInputStream(bis);
                     DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    while (true) {
                        clearScreen(dos);
                        dos.writeBytes("Digite a matricula:" + "\r\n");

                        StringBuilder userInput = new StringBuilder();
                        while (true) {
                            int inputChar = in.read();
                            if (inputChar == 13) {  // Check if the user pressed Enter
                                break;
                            }
                            userInput.append((char) inputChar);
                            dos.writeByte(inputChar);
                        }

                        System.out.println("Chegando onde eu quero.");
                        System.out.println(userInput.toString());
                        String studentId = userInput.toString();
                        String response = getEvent(studentId);
                        System.out.println("Response: " + response);
                        showEvent(response, dos, studentId);

                        while (in.read() != 13) {

                        }
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } finally {
                    socket.close();
                    System.out.println("Client " + socket.getRemoteSocketAddress() + " disconnected from the server...");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private String getEvent(String matricula) throws IOException {
        String apiUrl = "http://" + this.serverIp + ":8080/api/aulas/proxima-aula/" + matricula;
        System.out.println(apiUrl);
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();
            return response.toString();
        } else {
            System.out.println("Error: Request failed");
            System.out.println("Status: " + responseCode);
            return "error";
        }
    }

    private void showEvent(String response, DataOutputStream dos, String matricula) {
        System.out.println("Entrando show event");
        try {
            JsonReader reader = Json.createReader(new StringReader(response));
            System.out.println("Reader created");
            JsonStructure jsonStructure = reader.read();
            System.out.println("jsonStruct created");

            if (jsonStructure.getValueType() == JsonValue.ValueType.OBJECT) {
                JsonObject aulaInfo = jsonStructure.asJsonObject();
                String disciplina = aulaInfo.getString("nomeAula", "N/A");
                String professor = aulaInfo.getString("professor", "N/A");

                LocalDateTime localDateTime = LocalDateTime.now();
                String turno = "Ola, ";
                System.out.println("Matricula: " + matricula);
                System.out.println("Aula: " + disciplina);

                clearScreen(dos);
//                dos.writeBytes(aluno + "!" + "\r\n");
//                dos.writeBytes("Matricula: " + matricula + "\r\n");
//                dos.writeBytes("Aula: " + disciplina + "\r\n");
                dos.writeBytes(disciplina + "\r\n");
                dos.writeBytes(professor + "\r\n");
            } else {
                clearScreen(dos);
                dos.writeBytes("Matricula nao foi\r\n" + "encontrada\r\n");
                System.out.println("Error: No event found for the given matricula.");
            }
        } catch (Exception e) {
            System.out.println("Error displaying class information: " + e.getMessage());
            try {
                clearScreen(dos);
                dos.writeBytes("Erro ao exibir as informações da aula.\r\n");
            } catch (IOException ioException) {
                System.out.println("Error: " + ioException.getMessage());
            }
        }
    }

    private String defineShift(int time) {
        if (time < 12) return "Bom dia";
        if (time < 18) return "Boa tarde";
        return "Boa noite";
    }

    private void clearScreen(DataOutputStream dos) throws IOException {
        dos.writeBytes("\033[H\033[2J");
        dos.flush();
    }

    public static void main(String[] args) {
        TerminalIntegration service = new TerminalIntegration();
        service.initialize();
        service.start();
    }
}
