%cd('C:/Users/ppmaes/Desktop/EEG/MATLAB/')
version -java
%javaaddpath('javaoscmatlab.jar');
import com.illposed.osc.*;
import java.lang.String;
global x;
global xlist;
xlist = [];
global ylist;
ylist = [];
i = 0;
while(i < 1000)
   xlist(end+1) = i;
   ylist(end+1) = 0;
   i = i + 1;
end
x = 0;
Interface = MatlabOSCInterface();
Interface.initialize(55830);
Interface.registerAddress(String('/bullshit'));
evt = MatlabOSCEvent;
h = handle(evt,'CallbackProperties');
set(evt,'ReceiveOscEventCallback',@(h,e)processCallback(h,e))
Interface.start();

while (1)
   pause(0.05);
   plot(xlist,ylist);
   drawnow;
end

function processCallback(hObject, eventData)
%global x;
%global xlist;
global ylist;
%disp(xlist);
ylist(1) = [];
ylist(1000) = eventData.messageNumerics(1);

%disp(eventData.messageNumerics);
%axis([x(n) x(n+Dx) y1 y2]);drawnow
%disp(x)
%x = x+1;
end