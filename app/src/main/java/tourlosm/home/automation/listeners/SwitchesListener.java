package tourlosm.home.automation.listeners;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.CompoundButton;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import tourlosm.home.automation.websockets.SocketClientWritter;
import tourlosm.home.automation.websockets.dtos.RequestDto;

/**
 * Created by michael on 17/11/2017.
 */

public class SwitchesListener implements CompoundButton.OnCheckedChangeListener  {

    private static final String ENABLE = "enable";

    private static final String DISABLE = "disable";

    String action;

    public SwitchesListener(String action){
        this.action = action;
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        String finalAction = ENABLE + action;
        if (compoundButton.isChecked()) {
            finalAction = DISABLE + action;
        }
        AsyncTask<String, Void, Void> ansync = new AsyncTask<String, Void, Void>(){
            @Override
            protected Void doInBackground(String... action) {
                toogleSwitches(action[0]);
                return null;
            }
        };
        ansync.execute(finalAction);
    }

    void toogleSwitches(String finalAction){
        OkHttpClient client = new OkHttpClient();
        Request r = new Request.Builder().url("ws://192.168.1.2:8080/HomeAutomation/actions").build();
        RequestDto dto = new RequestDto();
        dto.setAction(finalAction);
        WebSocket webSocket = client.newWebSocket(r, new SocketClientWritter(dto));
        client.dispatcher().executorService().shutdown();
    }
}
