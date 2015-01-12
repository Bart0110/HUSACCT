package domain.direct.allowed;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

import domain.direct.Base;

public class AccessInstanceWithinAnonymousClass extends Base{
	
	private JButton browseButton;
	
	public AccessInstanceWithinAnonymousClass(){
		
		browseButton = new JButton("BrowseButton");
	}
	
	private void setListeners(){
		ActionListener ac = new ActionListener() { // Declaration of new anonymous class 
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(profileDao.name);				
			}
		};
		browseButton.addActionListener(ac);
	}

}