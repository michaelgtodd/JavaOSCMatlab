package com.illposed.osc;

public class MatlabOSCEvent
{
    private static OscListener listen = null;

    public synchronized void addOscListener(OscListener lis) {
        listen = lis;
    }

    public synchronized void removeOscListener(OscListener lis) {
        listen = lis;
    }

    public interface OscListener extends java.util.EventListener {
        void receiveOscEvent(OscEvent event);
    }

    public class OscEvent extends java.util.EventObject {
        private static final long serialVersionUID = 1L;
        public String[] messageStrings;
        public double[] messageNumerics;
        public String messageName;
        OscEvent(Object obj, OSCMessage message) {
            super(obj);
            messageStrings = MatlabOSCInterface.getStringArguments(message);
            messageNumerics = MatlabOSCInterface.getNumericArguments(message);
            messageName = MatlabOSCInterface.getMessageName(message);
        }
    }

    public void sendOscEvent(OSCMessage message)
    {
        OscEvent event = new OscEvent(this, message);
        if(listen != null)
        {
            listen.receiveOscEvent(event);
        }
    }

}