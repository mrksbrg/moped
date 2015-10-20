package plugins;

import java.io.IOException;
import com.sun.squawk.VM;
import sics.port.PluginPPort;
import sics.port.PluginRPort;
import sics.plugin.PlugInComponent;

public class Run extends PlugInComponent {
	private PluginRPort ab;
    public PluginPPort speed;
    public PluginPPort steering;

class WorkThread extends Thread {

    PluginRPort port;
    Object obj;

    WorkThread(PluginRPort port) {
	this.port = port;
	this.obj = null;
    }

    public void run() {
	Object obj = port.receive();

	if (false) {
	    try {
		Thread.sleep(4);
	    } catch (InterruptedException e) {
		//VM.println("Interrupted.");
	    }
	}
	//obj = (Object) "hej";

	this.obj = obj;
	//VM.println("WorkThread done");
    }
}

        	public Run(String[] args) {
        		super(args);
        	}
        
        	public static void main(String[] args) {
        		VM.println("Run.main()");
        		Run autoBrake = new Run(args);
        		autoBrake.init();
        		autoBrake.doFunction();
        		VM.println("Run-main done");
        	}

	@Override
	public void init() {
		// Initiate PluginPPort
		ab = new PluginRPort(this, "ab");
	}
	
	public void run() {
	    VM.println("Run.run()");
	    init();
	    doFunction();
	    VM.println("Run-main done");
	}

    private Object getval(PluginRPort port) {
	WorkThread p = new WorkThread(ab);
	p.start();
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    VM.println("Interrupted.");
	}
	Object o2 = p.obj;
	//VM.println("plupp " + o2);
	if (p.obj != null) {
	    //p.stop();
	}
	return o2;
    }

	public void doFunction() {
	    String data;
		
	    VM.println("[Run is running] 2");

	    speed = new PluginPPort(this, "sp");
	    steering = new PluginPPort(this, "st");

	    for (int j = 0; j < 1; j++) {
		try {
		    Thread.sleep(2000);
		    speed.write(0);
		    steering.write(0);
		    Thread.sleep(2000);

		    speed.write(20);
		    steering.write(0);
		    Thread.sleep(2000);
		} catch (InterruptedException e) {
		    VM.println("Interrupted.");
		}
	    }


	    while (true) {
		try {
		    Thread.sleep(500);
		} catch (InterruptedException e) {
		    VM.println("Interrupted.");
		}

		try {
		    //Object obj = ab.receive();
		    Object obj = getval(ab);
		    if(obj != null) {
			VM.println("ab returned an object");
			VM.println("ab returned an object: " + obj);

			String s = (String) obj;
			int x = Integer.parseInt(s);
			if (x < 150) {
			    speed.write(0);
			    steering.write(0);
			}
			if (x > 150) {
			    speed.write(20);
			    steering.write(0);
			}

		    } else {
			speed.write(30);
			steering.write(0);
			VM.println("ab returned null");
		    }
			
		} catch (Exception e) {
		    e.printStackTrace();
		    VM.println("Run: exception");
		}
	    }
	}
}
