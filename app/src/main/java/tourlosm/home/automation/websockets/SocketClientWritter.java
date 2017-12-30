package tourlosm.home.automation.websockets;

import android.util.Log;

import com.google.gson.Gson;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import tourlosm.home.automation.websockets.dtos.RequestDto;

/**
 * Created by michael on 17/10/2017.
 */

public class SocketClientWritter extends WebSocketListener {

    private Gson gson = new Gson();

    private RequestDto dto;

    public SocketClientWritter (RequestDto dto){
        dto.setRead(false);
        this.dto = dto;
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        webSocket.send(gson.toJson(dto));
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        //empty on purpose
        //we don't want to
        //handle anything since we
        //have separate reader
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(1000, null);
        Log.i("Socket", "Closing");
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        Log.i("Socket", "Error" + t.getMessage());
    }
}
