package edu.iis.powp.ink;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import edu.iis.powp.command.manager.PlotterCommandManager;
import edu.iis.powp.observer.Subscriber;
import edu.iis.powp.window.WindowComponent;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class InkManagerWindow extends JFrame implements WindowComponent {

	private PlotterCommandManager inkManager;

	private JTextArea currentInkField;

	private String observerListString;
	private JTextArea observerListField;

	/**
	 * 
	 */
	private static final long serialVersionUID = 9204679248304669948L;

	public InkManagerWindow(PlotterCommandManager inkManager) {
		this.setTitle("Ink Manager");
		this.setSize(400, 400);
		Container content = this.getContentPane();
		content.setLayout(new GridBagLayout());

		this.inkManager = inkManager;

		GridBagConstraints c = new GridBagConstraints();

		observerListField = new JTextArea("");
		observerListField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		//content.add(observerListField, c);
		updateObserverListField();

		currentInkField = new JTextArea("");
		currentInkField.setEditable(false);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 1;
		//content.add(currentInkField, c);
		updateObserverListField();
                
                JLabel text = new JLabel("capacity", JLabel.CENTER);
                content.add(text, c);
                
                JProgressBar bar = new JProgressBar();
                bar.setStringPainted(true);
                bar.setValue(75);
                c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 5;
                content.add(bar, c);
                
		JButton btnClearCommand = new JButton("New ink");
		//btnClearCommand.addActionListener((ActionEvent e) -> this.clearCommand());
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.gridx = 0;
		c.weighty = 10;
		content.add(btnClearCommand, c);


	}


	private void updateObserverListField() {
		observerListString = "";
		List<Subscriber> commandChangeSubscribers = inkManager.getChangePublisher().getSubscribers();
		for (Subscriber observer : commandChangeSubscribers) {
			observerListString += observer.toString() + System.lineSeparator();
		}
		if (commandChangeSubscribers.isEmpty())
			observerListString = "No observers loaded";

		observerListField.setText(observerListString);
	}
        
        public void updateCurrentInkField() {
		currentInkField.setText(inkManager.getCurrentCommandString());
	}

	@Override
	public void HideIfVisibleAndShowIfHidden() {
		updateObserverListField();
		if (this.isVisible()) {
			this.setVisible(false);
		} else {
			this.setVisible(true);
		}
	}

}
