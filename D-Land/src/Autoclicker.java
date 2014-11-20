import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;



public class Autoclicker {
	public static void main(String args[]) throws AWTException, InterruptedException {
		Robot robo = new Robot();
		while(true) {
		robo.mousePress(InputEvent.BUTTON1_MASK);
		Thread.sleep(10);
		robo.mouseRelease(InputEvent.BUTTON1_MASK);
		//Thread.sleep(10);
		}
	}

}
