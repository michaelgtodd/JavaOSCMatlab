package com.illposed.osc;

import java.util.Date;

public class MatlabOSCListener implements OSCListener {

    MatlabOSCEvent oscEvent = new MatlabOSCEvent();

    @Override
    public void acceptMessage(Date arg0, OSCMessage arg1) {
        oscEvent.sendOscEvent(arg1);
    }
}