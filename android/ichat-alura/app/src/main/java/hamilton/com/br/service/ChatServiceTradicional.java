//package hamilton.com.br.service;
//
//import android.util.Log;
//
//import org.json.JSONObject;
//import org.json.JSONStringer;
//
//import java.io.OutputStream;
//import java.io.PrintStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Scanner;
//
//import hamilton.com.br.activity.MainActivity;
//import hamilton.com.br.model.Mensagem;
//
///**
// * Created by hamilton on 06/02/17.
// */
//public class ChatServiceTradicional {
//
//    private static final String NOTEBOOK_HAMILTON = "192.168.25.2";
//    private MainActivity activity;
//
//    public ChatServiceTradicional(MainActivity activity) {
//        this.activity = activity;
//    }
//
//    public void enviar(final Mensagem mensagem)  {
//
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                String texto = mensagem.getText();
//
//                try {
//
//                    // Altere para o seu IP
//                    URL url = new URL("http://" + NOTEBOOK_HAMILTON + ":8080/polling");
//
//                    HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
//
//                    httpConnection.setRequestMethod("POST");
//
//                    httpConnection.setRequestProperty("Content-type", "application/json");
//
//                    JSONStringer json = new JSONStringer()
//                            .object()
//                            .key("text")
//                            .value(texto)
//                            .key("id")
//                            .value(mensagem.getId())
//                            .endObject();
//
//                    OutputStream saida = httpConnection.getOutputStream();
//                    PrintStream ps = new PrintStream(saida);
//
//                    ps.println(json.toString());
//
//                    httpConnection.connect();
//                    httpConnection.getInputStream();
//
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }).start();
//
//    }
//
//    public void ouvirMensagens(){
//
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//
//                try {
//
//                    // Altere para o seu IP
//                    URL url = new URL("http://" + NOTEBOOK_HAMILTON + ":8080/polling");
//
//                    HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
//
//                    httpConnection.setRequestMethod("GET");
//
//                    httpConnection.setRequestProperty("Accept", "application/json");
//
//                    httpConnection.connect();
//
//                    Scanner scanner = new Scanner(httpConnection.getInputStream());
//                    StringBuilder builder = new StringBuilder();
//                    while (scanner.hasNext()){
//                        builder.append(scanner.nextLine());
//                    }
//                    String json = builder.toString();
//                    JSONObject jsonObject = new JSONObject(json);
//                    Log.i(this.getClass().getSimpleName(), "jsonObject=" + jsonObject);
//
//
//                    final Mensagem mensagem = new Mensagem(jsonObject.isNull("id") ? 0 : jsonObject.getInt("id"), jsonObject.isNull("text") ? null : jsonObject.getString("text"));
//
//                    // coloca mensagem na lista
//
//                    activity.runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                           activity.colocaNaLista(mensagem);
//                        }
//                    });
//
//
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }).start();
//
//    }
//
//}
