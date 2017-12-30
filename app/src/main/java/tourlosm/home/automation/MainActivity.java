package tourlosm.home.automation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import tourlosm.home.automation.enums.ConnectionStatus;
import tourlosm.home.automation.ui.fragments.SectionsPagerAdapter;
import tourlosm.home.automation.ui.refreshers.ConnectionStatusRefresher;
import tourlosm.home.automation.ui.refreshers.DataDtoUiRefresher;
import tourlosm.home.automation.websockets.SocketClientReader;
import tourlosm.home.automation.websockets.SocketClientWritter;
import tourlosm.home.automation.websockets.dtos.RequestDto;
import tourlosm.home.automation.websockets.dtos.ResponseDto;

public class MainActivity extends AppCompatActivity {

    DataDtoUiRefresher dataRefresher;

    ConnectionStatusRefresher connectionStatusRefresher;

    OkHttpClient client;

    Gson gson;

    public static final String SIGNAL_STATUS_RECEIVER_TAG = "tourlosm.home.automation.SignalStatusReceiver";

    public static final String SIGNAL_STATUS_RECEIVER_DATA_TAG = "jsonData";

    public static final String SOCKET_CLIENT = "tourlosm.home.automation.SocketClient";

    public static final String SOCKET_CLIENT_STATUS = "status";

    public static final String SOCKET_CLIENT_STACKTRACE = "stacktrace";

    //TODO: move receivers in separate class
    private BroadcastReceiver dataReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String s = intent.getStringExtra(SIGNAL_STATUS_RECEIVER_DATA_TAG);
            dataRefresher.setContext(context);
            dataRefresher.setDto(gson.fromJson(s, ResponseDto.class));
            dataRefresher.run();
        }
    };

    private BroadcastReceiver connectionStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String s = intent.getStringExtra(SOCKET_CLIENT_STATUS);
            ConnectionStatus cs = ConnectionStatus.ERROR;
            if (s != null){
                cs = ConnectionStatus.valueOf(s);;
            }
            connectionStatusRefresher.setContext(context);
            connectionStatusRefresher.setConnectionStatus(cs);
            connectionStatusRefresher.run();
            if (ConnectionStatus.DISCONNECTED.equals(cs) || ConnectionStatus.ERROR.equals(cs)){
                connectionStatusRefresher.setConnectionStatus(ConnectionStatus.ATTEMPTING_RECONNECT);
                connectionStatusRefresher.run();
                try{
                    connectSocket();
                } catch (Exception e){
                    //empty
                    //TODO: handle
                }
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        connectSocket();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), getApplicationContext());

        // Set up the ViewPager with the sections adapter.
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        client = new OkHttpClient();

        dataRefresher = new DataDtoUiRefresher();
        connectionStatusRefresher = new ConnectionStatusRefresher();
        gson = new Gson();
        registerReceiver(dataReceiver, new IntentFilter(SIGNAL_STATUS_RECEIVER_TAG));
        registerReceiver(connectionStatusReceiver, new IntentFilter(SOCKET_CLIENT));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(dataReceiver);
        unregisterReceiver(connectionStatusReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void connectSocket(){
        Request r = new Request.Builder().url("ws://192.168.1.2:8080/HomeAutomation/actions").build();
        WebSocket webSocket = client.newWebSocket(r, new SocketClientReader(getApplicationContext()));
        client.dispatcher().executorService().shutdown();
    }

}
