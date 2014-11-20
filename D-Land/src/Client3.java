
import java.io.*;
import java.net.*;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

public class Client3 {
	public static void main(String[] args) {
		new Client_ChatBox();
	}
}

@SuppressWarnings("serial")
class Client_ChatBox extends JFrame implements ActionListener {
	
	JTextField usernameField;
	JTextField hostnameField;
	JButton setInfo;
	JButton setHost;
	JTextArea chatArea;
	JTextField messageBox;
	JButton sendButton;
	
	String username = "Client";
	PrintWriter out;
	BufferedReader in;
	
	Client_Out sOut;
	Client_In sIn;
	
	public Client_ChatBox() {
		// Setup---------------------------------
		setTitle("Chat");
		setSize(450, 590);
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
		
		hostnameField = new JTextField(20);
		addItem(main,new JLabel("Server Hostname:"),0,1,1,1,GridBagConstraints.EAST);
		addItem(main, hostnameField,1,1,2,1,GridBagConstraints.WEST);
		
		setHost = new JButton("Set Host");
		setHost.addActionListener(this);
		addItem(main, setHost,3,1,1,1,GridBagConstraints.EAST);
		
		chatArea = new JTextArea(30,40);
		chatArea.setEditable(false);
		chatArea.setBackground(new Color(240,240,240));
		chatArea.setBorder(new LineBorder(new Color(0,33,133),2));
		addItem(main, chatArea,0,2,4,5,GridBagConstraints.WEST);
		
		messageBox = new JTextField(31);
		addItem(main, messageBox,0,7,3,1,GridBagConstraints.WEST);
		
		sendButton = new JButton("Send");
		sendButton.addActionListener(this);
		addItem(main, sendButton,3,7,1,1,GridBagConstraints.EAST);
		//---------------------------------------
		
		add(main);
		
		sOut = new Client_Out();
		out = sOut.getWriter();
		
		sIn = new Client_In();
		sIn.setChatArea(chatArea);
		in = sIn.getReader();
		
		setVisible(true);
		
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
		else if (e.getSource() == setHost) {
			setHost.setVisible(false);
			hostnameField.setEditable(false);
			sOut.setHostname(hostnameField.getText());
			sIn.setHostname(hostnameField.getText());
			sOut.start();
			sIn.start();
		}
		else if (e.getSource() == sendButton) {
			chatArea.append(username + ": " + messageBox.getText()+"\n");
			out.println(username + ": " + messageBox.getText());
			messageBox.setText("");
		}
	}
	
}

class Client_In extends Thread {
	
	private String hostname;
	private BufferedReader in;
	private JTextArea chatArea;
	
	public void setHostname (String hostname) {
		this.hostname = hostname;
	}
	
	public void setChatArea (JTextArea chatArea) {
		this.chatArea = chatArea;
	}
	
	public BufferedReader getReader() {
		return in;
	}
	
	public void run() {
		Socket socket = null;
		
		try {
			socket = new Socket(hostname,4445);
		} catch (IOException e) {
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
			
			if (s != null) chatArea.append(s + "/n");
		}
		
	}
}

class Client_Out extends Thread {

	private String hostname;
	private PrintWriter out;
	
	public void setHostname (String hostname) {
		this.hostname = hostname;
	}
	
	public PrintWriter getWriter() {
		return out;
	}
	
	public void run () {
		
		Socket socket = null;
		
		try {
			socket = new Socket(hostname,4445);
		} catch (IOException e) {
			System.out.println("Could not establish connection.");
		}
		
		out = null;
		try {
			out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
		} catch (IOException e) {
			System.out.println("Could not establish PrintWriter");
		}
		
		while(true);
			
	}
}