import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.*;
public class registrationPage extends JFrame {

	JButton b1 = new JButton("Register");
	JLabel l1 = new JLabel("Name");
	JLabel l2 = new JLabel("Mobile Number");
	JLabel l3 = new JLabel("Password");
	JLabel l4 = new JLabel("Confirm Password");
	JLabel l = new JLabel("Registration Page");
	JLabel emptyfield = new JLabel("Fill the highlighted field");
	JTextField name = new JTextField();
	JTextField mobileNo = new JTextField();
	JPasswordField pass = new JPasswordField();
	JPasswordField Cpass = new JPasswordField();
	public registrationPage() {
		
		this.add(b1);
		b1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		b1.setBounds(127, 300, 131, 39);
		
		this.add(l1);
		l1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l1.setBounds(75, 110, 100, 35);
		this.add(name);
		name.setBounds(200, 110, 150, 35);
		
		this.add(l2);
		l2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l2.setBounds(75,150,150,35);
		this.add(mobileNo);
		mobileNo.setBounds(200,150,150,35);
		
		this.add(l3);
		l3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l3.setBounds(75,190,100,35);
		this.add(pass);
		pass.setBounds(200,190,150,35);
		
		this.add(l4);
		l4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		l4.setBounds(75,230,150,35);
		this.add(Cpass);
		Cpass.setBounds(200,230,150,35);
		
		this.add(l);
		l.setFont(new Font("Tahoma", Font.PLAIN, 45));
		l.setBounds(25, 10, 351, 61);


		this.add(emptyfield);
		emptyfield.setFont(new Font("Tahoma",Font.PLAIN,10));
		emptyfield.setBounds(139,270,250,35);
		emptyfield.setForeground(Color.blue);
		emptyfield.setVisible(false);
		
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400,450);
		this.setLayout(null);
		this.setVisible(true);
		
		
		b1.addActionListener(actionEvent -> {

			dispose();

			try {
				if(mainclass.addnewuser(name.getText(), pass.getPassword(),Cpass.getPassword(),mobileNo.getText())){
					JOptionPane.showMessageDialog(null,"Hello "+name.getText()+" your UserID is "+mainclass.rID+"");
					new loginWindow();
				}
				else {
					JOptionPane.showMessageDialog(null,"Enter password correctly");
					new registrationPage();
				}
			}catch(SQLIntegrityConstraintViolationException fee) {

				JOptionPane.showMessageDialog(null,"A user is already associated with given mobile number\nTry with a different number");
				new registrationPage();

			}catch (SQLException ex) {

				JOptionPane.showMessageDialog(null,"Something went wrong with your SQL connection");
				ex.printStackTrace();

			}catch(fieldEntryException fee) {

				JOptionPane.showMessageDialog(null,"Fields can't be empty");
				new registrationPage();

			}catch(mobileNoException fee) {

				JOptionPane.showMessageDialog(null,"Invalid mobile number");
				new registrationPage();

			}catch (Exception exc) {

				JOptionPane.showMessageDialog(null,"Error occured!\nTry again");
				exc.printStackTrace();
				new loginWindow();
			}


		});


		b1.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {

				if(pass.getPassword().length == 0 || Cpass.getPassword().length == 0 || name.getText().length() == 0 || mobileNo.getText().length() == 0) emptyfield.setVisible(true);

				if(name.getText().length() == 0)l1.setForeground(Color.red);

				if(mobileNo.getText().length() == 0)l2.setForeground(Color.RED);

				if(pass.getPassword().length == 0)l3.setForeground(Color.RED);

				if(Cpass.getPassword().length == 0)l4.setForeground(Color.RED);


			}

			@Override
			public void mouseExited(MouseEvent e) {
				emptyfield.setVisible(false);
				l1.setForeground(Color.BLACK);
				l2.setForeground(Color.BLACK);
				l3.setForeground(Color.BLACK);
				l4.setForeground(Color.BLACK);
			}
		});
	}
	
}
