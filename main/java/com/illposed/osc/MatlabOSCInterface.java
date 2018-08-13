package com.illposed.osc;

public class MatlabOSCInterface {

    private MatlabOSCListener Listener;

    private OSCPortIn InPort;

    public MatlabOSCInterface() {}

    public void initialize (int Port)
    {
        try {
            InPort = new OSCPortIn(Port);
            Listener = new MatlabOSCListener();
        }
        catch (Exception e)
        {
            System.out.println("It's all fucked, restart matlab.");
            this.finalize();
        }
    }

    public void registerAddress (String address)
    {
        InPort.addListener(address, Listener);
    }

    public void start ()
    {
        InPort.startListening();
    }



    public static Object[] getMessageArguments(OSCMessage message){
        Object[] arguments = null;
        if(message != null){
            arguments = message.getArguments().toArray();
            message = null;
        }
        return arguments;
    }

    public static double[] getNumericArguments(OSCMessage message){
        Object[] arguments = getMessageArguments(message);
        double[] doubleArguments = null;
        if(arguments != null && arguments.length > 0){
            doubleArguments = new double[arguments.length];
            for(int i = 0 ; i < arguments.length ; i++){
                //since it is not known if the object is either a float, integer or double
                // do a to string and cast it.
                // this will fail for non numeric data types
                try
                {
                    doubleArguments[i] = Double.valueOf(arguments[i].toString());
                }
                catch (Exception e)
                {

                }
            }
        }
        return doubleArguments;
    }

    public static String[] getStringArguments(OSCMessage message){
        Object[] arguments = getMessageArguments(message);
        String[] stringArguments = null;
        if(arguments != null && arguments.length > 0){
            stringArguments = new String[arguments.length];
            for(int i = 0 ; i < arguments.length ; i++){
                try {
                    Double.valueOf(arguments[i].toString());
                }
                catch (Exception e)
                {
                    stringArguments[i] = arguments[i].toString();
                }
            }
        }
        return stringArguments;
    }

    public static String getMessageName(OSCMessage message)
    {
        return message.getAddress();
    }

    public void close ()
    {
        this.finalize();
    }

    public void finalize ()
    {
        InPort.stopListening();
        InPort.close();
        InPort = null;
    }

    public static void main(String... args){}
}