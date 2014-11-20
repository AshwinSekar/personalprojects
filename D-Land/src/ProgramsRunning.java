import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ProgramsRunning {

	public static void main (String[] args) throws IOException {
		
		Process p = Runtime.getRuntime().exec("tasklist");
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		ArrayList<String> processes = new ArrayList<String>(25);
		
		String s = "";
		int count = 0;
		while (s != null) {
			if (!s.isEmpty() && count++ >= 2) processes.add(s.substring(0, s.indexOf(" ")));
			s = in.readLine();
		}
	
		while(true) {
			p = Runtime.getRuntime().exec("tasklist");
			in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			count = 0;
			
			s = in.readLine();
			while(s != null) {
				if (!s.isEmpty() && count++ >= 2) {
					s = s.substring(0, s.indexOf(" "));
					if(!processes.contains(s)) {
						System.out.println(s + "\t opened on " + getTime());
						processes.add(s);
					}
				}
				s = in.readLine();
			}	
		}
		
	}
	
	private static String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("M/dd/yyyy - kk:mm:ss a");
		return df.format(Calendar.getInstance().getTime());
	}
}
