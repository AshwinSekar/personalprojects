
import java.io.*;
import java.net.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Server3 {
	public static void main(String[] args) {
		new Server_ChatBox();
	}
}

@SuppressWarnings("serial")
class Server_ChatBox extends JFrame implements ActionListener {
	
	JTextField usernameField;
	JButton setInfo;
	JTextArea chatArea;
	JTextField messageBox;
	JButton sendButton;
	
	String username = "Server";
	PrintWriter out;
	BufferedReader in;
	
	public Server_ChatBox() {
		// Setup---------------------------------
		setTitle("Chat");
		setSize(450, 580);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(1,1));
		// --------------------------------------
		
		// Main Panel----------------------------
		JPanel main = new JPanel();
		main.setLayout(new GridBagLayout());
		main.setBackground(new Color(245,245,245));
		
		usernameField = new JTextField(20);
		addItem(main, new JLabel("Username:"),0,0,1,1,GridBagConstraints.EAST);
		addItem(main, usernameField, 1,0,2,1,GridBagConstraints.WEST);
				
		setInfo = new JButton("Set Name");
		setInfo.addActionListener(this);
		addItem(main, setInfo,3,0,1,1,GridBagConstraints.EAST);
		
		chatArea = new JTextArea(30,40);
		chatArea.setEditable(false);
		chatArea.setBackground(new Color(240,240,240));
		chatArea.setBorder(new LineBorder(new Color(0,33,133),2));
		addItem(main, chatArea,0,1,4,5,GridBagConstraints.WEST);
		
		messageBox = new JTextField(31);
		addItem(main, messageBox,0,6,3,1,GridBagConstraints.WEST);
		
		sendButton = new JButton("Send");
		sendButton.addActionListener(this);
		addItem(main, sendButton,3,6,1,1,GridBagConstraints.EAST);
		//---------------------------------------
		
		add(main);
		
		Server_Out sOut = new Server_Out();
		Server_In sIn = new Server_In();
		sOut.start();
		sIn.start();
		
		setVisible(true);
		
		in = sIn.getReader();
		out = sOut.getWriter();
		while (in == null || out == null) {
			in = sIn.getReader();
			out = sOut.getWriter();
		}
		
		
	}
	
	private void addItem (JPanel panel, JComponent component, int x, int y, int width, int height, int align) {
		GridBagConstraints g = new GridBagConstraints();
		g.gridx = x;
		g.gridy = y;
		g.gridwidth = width;
		g.gridheight = height;
		g.anchor = align;
		
		panel.add(component,g);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == setInfo) {
			username = usernameField.getText();
			usernameField.setText("");
			chatArea.setText("");
		}
		else if (e.getSource() == sendButton) {
			chatArea.append(username + ": " + messageBox.getText()+"\n");
			out.println(username + ": " + messageBox.getText());
			messageBox.setText("");
		}
	}
	
}

class Server_Out extends Thread {
	
	private PrintWriter out;
	
	public PrintWriter getWriter() {
		return out;
	}
	public void run() {
		
		ServerSocket getPort = null;
		Socket socket = null;
		
		try {
			getPort = new ServerSocket(4445);
			socket = getPort.accept();
		}
		catch (IOException e) {
			System.out.println("Could not establish connection.");
		}
		
		out = null;
		try {
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
		} catch (IOException e) {
			System.out.println("Could not establish PrintWriter");
			System.exit(-1);
		}
		
		while(true);
	}
}

class Server_In extends Thread {
	
	private BufferedReader in;
	private JTextArea chatArea;
	
	public BufferedReader getReader() {
		return in;
	}
	public void setTextArea (JTextArea chatArea) {
		this.chatArea = chatArea;
	}
	public void run() {
		
		ServerSocket getPort = null;
		Socket socket = null;
		
		try {
			getPort = new ServerSocket(4446);
			socket = getPort.accept();
		}
		catch (IOException e) {
			System.out.println("Could not establish connection.");
		}
		
		in = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			System.out.println("Could not establish BufferedReader");
			System.exit(-1);
		}
		
		while(true) {
			String s = null;
			try {
				s = in.readLine();
			} catch (IOException e) {
				System.out.println("Could not read line");
			}
			
			if (s != null) chatArea.append("Client: " + s + "\n");
		}
		
	}
}