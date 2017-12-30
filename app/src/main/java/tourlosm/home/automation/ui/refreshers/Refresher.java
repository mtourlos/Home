package tourlosm.home.automation.ui.refreshers;

import android.content.Context;

/**
 * Created by michael on 10/12/2017.
 */

public interface Refresher extends Runnable {

    void setContext(Context activity);

}
