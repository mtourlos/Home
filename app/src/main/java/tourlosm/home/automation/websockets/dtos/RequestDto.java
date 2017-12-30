package tourlosm.home.automation.websockets.dtos;

/**
 * Created by michael on 7/11/2017.
 */

public class RequestDto {

    boolean read;

    String action;

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}