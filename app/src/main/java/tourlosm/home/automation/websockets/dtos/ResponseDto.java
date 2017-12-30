package tourlosm.home.automation.websockets.dtos;

/**
 * Created by michael on 16/11/2017.
 */

public class ResponseDto {

    boolean kitchenMotionStatus;

    boolean livingRoomMotionStatus;

    boolean bedRoomMotionStatus;

    boolean kitchenLightStatus;

    boolean livingRoomLightStatus;

    boolean heaterStatus;

    boolean alarmStatus;

    boolean intruderStatus;

    public boolean getKitchenMotionStatus() {
        return kitchenMotionStatus;
    }

    public void setKitchenMotionStatus(boolean kitchenMotionStatus) {
        this.kitchenMotionStatus = kitchenMotionStatus;
    }

    public boolean getLivingRoomMotionStatus() {
        return livingRoomMotionStatus;
    }

    public void setLivingRoomMotionStatus(boolean livingRoomMotionStatus) {
        this.livingRoomMotionStatus = livingRoomMotionStatus;
    }

    public boolean getBedRoomMotionStatus() {
        return bedRoomMotionStatus;
    }

    public void setBedRoomMotionStatus(boolean bedRoomMotionStatus) {
        this.bedRoomMotionStatus = bedRoomMotionStatus;
    }

    public boolean getKitchenLightStatus() {
        return kitchenLightStatus;
    }

    public void setKitchenLightStatus(boolean kitchenLightStatus) {
        this.kitchenLightStatus = kitchenLightStatus;
    }

    public boolean getLivingRoomLightStatus() {
        return livingRoomLightStatus;
    }

    public void setLivingRoomLightStatus(boolean livingRoomLightStatus) {
        this.livingRoomLightStatus = livingRoomLightStatus;
    }

    public boolean getHeaterStatus() {
        return heaterStatus;
    }

    public void setHeaterStatus(boolean heaterStatus) {
        this.heaterStatus = heaterStatus;
    }

    public boolean getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(boolean alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public boolean getIntruderStatus() {
        return intruderStatus;
    }

    public void setIntruderStatus(boolean intruderStatus) {
        this.intruderStatus = intruderStatus;
    }
}
