package tourlosm.home.automation.enums;

import tourlosm.home.automation.R;

/**
 * Created by michael on 17/12/2017.
 */

public enum ConnectionStatus {

    CONNECTED{
        @Override
        public int getColor() {
            return R.color.colorHealthy;
        }

        @Override
        public int getText() {
            return R.string.connected;
        }
    },

    DISCONNECTED{
        @Override
        public int getColor() {
            return R.color.colorWarning;
        }

        @Override
        public int getText() {
            return R.string.disconnected;
        }
    },

    ERROR{
        @Override
        public int getColor() {
            return R.color.colorError;
        }

        @Override
        public int getText() {
            return R.string.error;
        }
    },

    ATTEMPTING_RECONNECT{
        @Override
        public int getColor() {
            return R.color.colorWarning;
        }

        @Override
        public int getText() {
            return R.string.attempting_reconnect;
        }
    };

    public abstract int getColor();

    public abstract int getText();

}
