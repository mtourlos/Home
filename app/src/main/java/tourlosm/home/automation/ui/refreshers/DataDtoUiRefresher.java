package tourlosm.home.automation.ui.refreshers;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PagerSnapHelper;
import android.widget.Switch;
import android.widget.TextView;

import okhttp3.Request;
import tourlosm.home.automation.R;
import tourlosm.home.automation.websockets.dtos.RequestDto;
import tourlosm.home.automation.websockets.dtos.ResponseDto;

/**
 * Created by michael on 10/12/2017.
 */

public class DataDtoUiRefresher implements Refresher {

    Context context;

    ResponseDto dto;

    @Override
    public void run() {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            updateTextView(activity, R.id.kitchenRoomLightStatus, dto.getKitchenLightStatus(), R.color.colorHealthy, R.color.colorWarning);
            updateTextView(activity, R.id.kitchenRoomMotionDetectorStatus, dto.getKitchenMotionStatus(), R.color.colorHealthy, R.color.colorWarning);
            updateTextView(activity, R.id.livingRoomLightStatus, dto.getLivingRoomLightStatus(), R.color.colorHealthy, R.color.colorWarning);
            updateTextView(activity, R.id.livingRoomMotionDetectorStatus, dto.getLivingRoomMotionStatus(), R.color.colorHealthy, R.color.colorWarning);
            updateTextView(activity, R.id.bedroomMotionDetectorStatus, dto.getBedRoomMotionStatus(), R.color.colorHealthy, R.color.colorWarning);
            updateTextView(activity, R.id.alarmArmedStatus, dto.getAlarmStatus(), R.color.colorHealthy, R.color.colorWarning);
            updateTextView(activity, R.id.alarmIntruderStatus, dto.getIntruderStatus(), R.color.colorHealthy, R.color.colorWarning);
            updateTextView(activity, R.id.heaterStatus, dto.getHeaterStatus(), R.color.colorHealthy, R.color.colorWarning);

            updateSwitch(activity, R.id.kitchenLightSwitch, dto.getKitchenLightStatus());
            updateSwitch(activity, R.id.livingRoomLightSwitch, dto.getLivingRoomLightStatus());
            updateSwitch(activity, R.id.heaterSwitch, dto.getHeaterStatus());
            updateSwitch(activity, R.id.alarmSwitch, dto.getAlarmStatus());
        }
    }

    void updateTextView(Activity activity, int id, boolean status, int healthyColor, int warningColor){
        TextView tv = (TextView) activity.findViewById(id);
        if (tv == null) {
            return;
        }
        tv.setText(String.valueOf(status));
        if(status) {
            tv.setTextColor(ContextCompat.getColor(activity, healthyColor));
            return;
        }
        tv.setTextColor(ContextCompat.getColor(activity, warningColor));
    }

    void updateSwitch(Activity activity, int id, boolean status){
        Switch s = (Switch) activity.findViewById(id);
        if (s == null) {
            return;
        }
        s.setChecked(status);
    }

    @Override
    public void setContext(Context context){
        nullCheck(context);
        this.context = context;
    }

    public void setDto(ResponseDto dto){
       nullCheck(dto);
        this.dto = dto;
    }

    void nullCheck(Object o){
        if (o == null){
            throw new NullPointerException("The object not be null");
        }
    }


}
