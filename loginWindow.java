import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class loginWindow extends JFrame{

	JButton b1 = new JButton("Login");
	JButton b2 = new JButton("Register");
	JButton b3 = new JButton("Exit");
	JTextField userID = new JTextField();
	JPasswordField pass = new JPasswordField();
	JLabel text = new JLabel("Welcome");
	JLabel useridJLabel = new JLabel("User ID");
	JLabel passwordJLabel = new JLabel("Password");
	JLabel emptyfield = new JLabel("Fill the highlighted field");


	//void display(){JOptionPane.showMessageDialog(this,"hello");}

	public loginWindow() {

		this.add(b1);
		b1.setBounds(100,175,100,25);
		this.add(b2);
		b2.setBounds(200,175,100,25);
		this.add(b3);
		b3.setBounds(300,175,100,25);

		this.add(userID);
		userID.setBounds(200,65,100,25);

		this.add(pass);
		pass.setBounds(200,115,100,25);

		this.add(text);
		text.setBounds(190,30,120,30);
        text.setFont(new Font("calibri",Font.BOLD,30));

		this.add(useridJLabel);
		useridJLabel.setBounds(100,50,100,50);

		this.add(passwordJLabel);
		passwordJLabel.setBounds(100,100,100,50);

		this.add(emptyfield);
		emptyfield.setFont(new Font("Tahoma",Font.PLAIN,10));
		emptyfield.setForeground(Color.BLUE);
		emptyfield.setBounds(197,140,150,50);
		emptyfield.setVisible(false);


		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,300);
		this.setLayout(null);
		this.setVisible(true);


		b1.addActionListener(actionEvent -> {

			dispose();
    		try{
				if(mainclass.checkforauth(Integer.parseInt(userID.getText()), pass.getPassword())){

					String name = mainclass.userName;
					JOptionPane.showMessageDialog(null,"Access Authorized\nWelcome back "+name);
				}
				else {
					JOptionPane.showMessageDialog(null,"Access Denied\nLogin credentials does not match");
					new loginWindow();
				}

			}catch(sqlException ex){
				JOptionPane.showMessageDialog(null,"Something went wrong with your SQL connection");
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null,"Invalid Input\nTry again");
				new loginWindow();
			}
		});

		b2.addActionListener(actionEvent -> {

			dispose();
			new registrationPage();

		});

		b3.addActionListener(actionEvent -> dispose());

		b1.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {

				if(userID.getText().length() == 0 || pass.getPassword().length == 0)emptyfield.setVisible(true);
				if(userID.getText().length() == 0)useridJLabel.setForeground(Color.RED);
				if(pass.getPassword().length == 0)passwordJLabel.setForeground(Color.RED);



			}

			@Override
			public void mouseExited(MouseEvent e) {

				useridJLabel.setForeground(Color.BLACK);
				passwordJLabel.setForeground(Color.BLACK);
				emptyfield.setVisible(false);
			}
		});
	}



}
