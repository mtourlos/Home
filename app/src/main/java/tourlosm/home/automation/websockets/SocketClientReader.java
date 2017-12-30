package tourlosm.home.automation.websockets;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import tourlosm.home.automation.MainActivity;
import tourlosm.home.automation.enums.ConnectionStatus;
import tourlosm.home.automation.websockets.dtos.RequestDto;

/**
 * Created by michael on 17/10/2017.
 */

public class SocketClientReader extends WebSocketListener {

    Gson gson = new Gson();

    RequestDto r = new RequestDto();

    String requestString;

    Context context;

    public SocketClientReader(Context context){
        r.setRead(true);
        requestString = gson.toJson(r);
        this.context = context;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        webSocket.send(requestString);
        Map<String, String> extras = new HashMap<>();
        extras.put(MainActivity.SOCKET_CLIENT_STATUS, ConnectionStatus.CONNECTED.toString());
        broadcast(MainActivity.SOCKET_CLIENT, extras);
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        Map<String, String> extras = new HashMap<>();
        extras.put(MainActivity.SIGNAL_STATUS_RECEIVER_DATA_TAG, text);
        broadcast(MainActivity.SIGNAL_STATUS_RECEIVER_TAG, extras);
        webSocket.send(requestString);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        Map<String, String> extras = new HashMap<>();
        extras.put(MainActivity.SOCKET_CLIENT_STATUS, ConnectionStatus.DISCONNECTED.toString());
        broadcast(MainActivity.SOCKET_CLIENT, extras);
        webSocket.close(1000, null);
        Log.i("Socket", "Closing");
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        Map<String, String> extras = new HashMap<>();
        extras.put(MainActivity.SOCKET_CLIENT_STATUS, ConnectionStatus.ERROR.toString());
        extras.put(MainActivity.SOCKET_CLIENT_STACKTRACE, t.getMessage());
        broadcast(MainActivity.SOCKET_CLIENT, extras);
        Log.i("Socket", "Error" + t.getMessage());
    }

    void broadcast(String action, Map<String, String> extras){
        Intent intent = new Intent();
        intent.setAction(action);
        if(extras != null){
            for(Map.Entry<String, String> entry : extras.entrySet()){
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
        context.sendBroadcast(intent);
    }
}
