package tourlosm.home.automation.ui.refreshers;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.widget.TextView;

import tourlosm.home.automation.R;
import tourlosm.home.automation.enums.ConnectionStatus;

/**
 * Created by michael on 17/12/2017.
 */

public class ConnectionStatusRefresher implements Refresher {

    Context context;

    ConnectionStatus cs;

    @Override
    public void run() {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            TextView tv = (TextView) activity.findViewById(R.id.connection_status);
            tv.setText(activity.getString(cs.getText()));
            tv.setTextColor(context.getColor(cs.getColor()));
        }
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    public void setConnectionStatus(ConnectionStatus cs){
        this.cs = cs;
    }
}
