package agents;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import jade.core.Agent;


public class GUIAgent extends Agent {
	public JFrame frame;
	public JPanel gui;
	public JTextField minSellPrice = new JTextField();
	public JTextField maxSellPrice = new JTextField();
	public JTextField minBuyPrice = new JTextField();
	public JTextField maxBuyPrice = new JTextField();
    public JComboBox negotiationStrategy = new JComboBox();
	
	
	public void SetupGui(){
		
		 gui = new JPanel(new java.awt.GridBagLayout());
	       gui.setBorder(new EmptyBorder(10, 10, 10, 10));
	       GridBagConstraints constraint = new GridBagConstraints();
	       Dimension dimension = new Dimension(140,20);

	       Dimension negotiationDimension = new Dimension(240,20);
	    
		JButton button = new JButton("Click here!");
		//gui.add(button);
		
		JLabel sellPriceLabel = new JLabel("Sell Price:");
		JLabel buyPriceLabel = new JLabel("Buy Price:");
		
		JLabel minLabel = new JLabel("Min:");
		JLabel minBuyLabel = new JLabel("Min:");
       
        minSellPrice.setPreferredSize(dimension);
		JLabel maxLabel = new JLabel("Max:");
		JLabel maxBuyLabel = new JLabel("Max:");
        maxSellPrice.setPreferredSize(dimension);

		minBuyPrice.setPreferredSize(dimension);
		maxBuyPrice.setPreferredSize(dimension);
        negotiationStrategy.setPreferredSize(negotiationDimension);
        
        JSeparator hr = new JSeparator(JSeparator.HORIZONTAL);
        JSeparator vr = new JSeparator(JSeparator.VERTICAL);
        
        JLabel negotiationLabel = new JLabel("Negotiation strategy:");
        
        //SellPrice Section
        constraint.gridy = 0;
        constraint.gridx = 0;
		gui.add(sellPriceLabel, constraint);
        constraint.gridx = 0;
		constraint.gridy = 1;
		gui.add(minLabel,constraint);
        constraint.gridx = 1;
		constraint.gridy = 1;
		gui.add(minSellPrice,constraint);
        constraint.gridx = 0;
		constraint.gridy = 2;
		gui.add(maxLabel, constraint);
        constraint.gridx = 1;
		constraint.gridy = 2;
		gui.add(maxSellPrice, constraint);
		
		
		constraint.gridx = 0;
		constraint.gridy = 3;
		//gui.add(hr,BorderLayout.LINE_START);
		
		constraint.gridx = 0;
		constraint.gridy = 4;
		gui.add(buyPriceLabel, constraint);
		
		constraint.gridx = 0;
		constraint.gridy = 5;
		gui.add(minBuyLabel, constraint);
		
		constraint.gridx = 1;
		constraint.gridy = 5;
		gui.add(minBuyPrice, constraint);
		
		constraint.gridx = 0;
		constraint.gridy = 6;
		gui.add(maxBuyLabel, constraint);

		constraint.gridx = 1;
		constraint.gridy = 6;
		gui.add(maxBuyPrice, constraint);
		
		constraint.gridx = 3;
		constraint.gridy = 0;
		gui.add(vr, constraint);
		
		
		constraint.gridx = 4;
		constraint.gridy = 0;
		gui.add(negotiationLabel, constraint);
		
		
		constraint.gridx = 4;
		constraint.gridy = 1;
		gui.add(negotiationStrategy,constraint);
		
		frame.add(gui);
		frame.pack();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void Initialise() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SetupGui();
	}
	
	public GUIAgent() {
		Initialise();
	}
	
	
	public void setup(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
}
