import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import javax.swing.text.DefaultEditorKit;

public class EncryptionUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextArea messageInput = new JTextArea();
	private JTextField privateKeyInput = new JTextField("");
	private JTextField publicKeyInput = new JTextField("");
	private JFrame frame = new JFrame("Dual Key Encryptor");
	private Font myFont = new Font("My Font",Font.PLAIN,16);
	
	public EncryptionUI(){//Builds UI and adds action listeners to buttons
		new UIManager();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 460);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
				
		JPanel panel = new JPanel();
		panel.setSize(new Dimension(800,450));
		panel.setBackground(new Color(235,235,235));
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				
		messageInput.setFont(myFont);
		messageInput.setLineWrap(true);
		JScrollPane scroller = new JScrollPane(messageInput);
		scroller.setPreferredSize(new Dimension(780, 300));
		
		JButton encode = new JButton("Encode");
		encode.setFont(myFont);
		encode.setBackground(new Color(120,120,120));
		JButton decode = new JButton("Decode");
		decode.setFont(myFont);
		decode.setBackground(new Color(120,120,120));
		JButton newKeys = new JButton("Generate new pair of keys");
		newKeys.setFont(myFont);
		newKeys.setBackground(new Color(120,120,120));
		
		privateKeyInput.setFont(myFont);
		privateKeyInput.setPreferredSize(new Dimension(100,30));
		publicKeyInput.setFont(myFont);
		publicKeyInput.setPreferredSize(new Dimension(100,30));
		
		JLabel privateKeyLabel = new JLabel("Enter your private key:");
		privateKeyLabel.setFont(myFont);
		JLabel publicKeyLabel = new JLabel("\nEnter their public key:");
		publicKeyLabel.setFont(myFont);
		
		JLabel space1 = new JLabel("       ");
		JLabel space2 = new JLabel("       ");
		JLabel space3 = new JLabel();
		space3.setPreferredSize(new Dimension(260,30));
		JLabel directions = new JLabel("Enter text into the box");
		directions.setFont(myFont);
		
		EncodeListener encodeListener = new EncodeListener();
		encode.addActionListener(encodeListener);
		
		DecodeListener decodeListener = new DecodeListener();
		decode.addActionListener(decodeListener);
		
		GenerateListener generateListener = new GenerateListener();
		newKeys.addActionListener(generateListener);

		AreaListener areaListener = new AreaListener();
		messageInput.addMouseListener(areaListener);
		
		panel.add(directions);
		panel.add(scroller);
		panel.add(privateKeyLabel);
		panel.add(privateKeyInput);
		panel.add(space1);
		panel.add(publicKeyLabel);
		panel.add(publicKeyInput);
		panel.add(space2);
		panel.add(encode);
		panel.add(decode);
		panel.add(space3);
		panel.add(newKeys);
		
		frame.add(panel);
		frame.setVisible(true);
	}

	private class EncodeListener implements ActionListener{//Action Listener to encode message
		public void actionPerformed(ActionEvent e) {			
			try{
				Encryption encryption = new Encryption(privateKeyInput.getText().toUpperCase(), publicKeyInput.getText().toUpperCase(), messageInput.getText());
				messageInput.setText(encryption.getEncryption());
			}catch(StringIndexOutOfBoundsException ex){
				JOptionPane.showMessageDialog(frame, "Error: invalid input");
			}
		}	
	}
	
	private class DecodeListener implements ActionListener{//Action listener to decode message
		public void actionPerformed(ActionEvent e) {		
			try{
				Decryption d = new Decryption(privateKeyInput.getText().toUpperCase(), publicKeyInput.getText().toUpperCase(), messageInput.getText());
				messageInput.setText(d.getMessage());
			}catch(StringIndexOutOfBoundsException ex){
				JOptionPane.showMessageDialog(frame, "Error: invalid input");
			}
		}	
	}
	
	private class GenerateListener implements ActionListener{//Action listener to generate new set of keys
		public void actionPerformed(ActionEvent e) {
		
			String s = "Private Key: ";
			Key k = new Key();
			s = s.concat(k.getPrivateKey());
			s = s.concat("    Public Key: ");
			s = s.concat(k.getPublicKey());
			
			JTextArea area = new JTextArea();
			area.setPreferredSize(new Dimension(100,50));
            area.setText(s);
            area.setWrapStyleWord(true);
            area.setLineWrap(true);
            area.setCaretPosition(0);
            area.setEditable(false);
            area.setFont(myFont);
            area.setBackground(new Color(240,240,240));
			
            AreaListener areaListener = new AreaListener();
    		area.addMouseListener(areaListener);
            
			JOptionPane.showMessageDialog(frame, area);
			
		}
	}
	
	private class AreaListener implements MouseListener{//mouse listener for adding CCP functionality to text area

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getButton() == MouseEvent.BUTTON3){
				// Create the submenu items
				
				JMenuItem copyItem = new JMenuItem(new DefaultEditorKit.CopyAction());
				copyItem.setText("Copy");

				JMenuItem pasteItem = new JMenuItem(new DefaultEditorKit.PasteAction());
				pasteItem.setText("Paste");

				JMenuItem cutItem = new JMenuItem(new DefaultEditorKit.CutAction());
				cutItem.setText("Cut");
				
				JMenuItem selectAllItem = new JMenuItem(messageInput.getActionMap().get(DefaultEditorKit.selectAllAction));
				selectAllItem.setText("Select All");
				
				// Constructor the pop-up menu
				JPopupMenu popupMenu = new JPopupMenu();
				popupMenu.add(cutItem);
				popupMenu.add(copyItem);
				popupMenu.add(pasteItem);
				popupMenu.addSeparator();
				popupMenu.add(selectAllItem);
				
				popupMenu.show(e.getComponent(),e.getX(), e.getY() );
				
			}
			
		}
		
	}
	
}
