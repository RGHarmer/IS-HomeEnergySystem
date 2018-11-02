package agents;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Serializable.PriceCapUpdateMessageContent;
import behaviours.GUISubscription;
import behaviours.HomeSubscription;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;


public class GUIAgent extends Agent {
	public JFrame frame;
	public JPanel gui;
	public JTextField minSellPrice = new JTextField();
	public JTextField maxSellPrice = new JTextField();
	public JTextField minBuyPrice = new JTextField();
	public JTextField maxBuyPrice = new JTextField();
    public JComboBox negotiationStrategy = new JComboBox();
	
    
    
	public AID myHomeAgent;
    
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
		
		
		
		
		JButton updateButton = new JButton("Update");
		
		ActionListener buttonPress = new ActionListener()
    	{
    		@Override
	        public void actionPerformed(ActionEvent e)
	        {
	        	UpdatePriceCaps();
	        }
    	};
		
	    updateButton.addActionListener(
	    		buttonPress
    	);
	    
	    constraint.gridx = 0;
	    constraint.gridy = 7;
	    gui.add(updateButton, constraint);
	    
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
		Subscribe("Home");
	}
	
	

	protected void Subscribe(String type) {
		DFAgentDescription template = new DFAgentDescription();
		ServiceDescription templateSD = new ServiceDescription();
		templateSD.setType(type);
		template.addServices(templateSD);
		SearchConstraints sc = new SearchConstraints();
		sc.setMaxResults((long)Double.POSITIVE_INFINITY);
		
		GUISubscription subscriber = new GUISubscription(this, DFService.createSubscriptionMessage(this,  getDefaultDF(),  template,  sc), type);
		addBehaviour(subscriber);
	}
	
	
	protected void UpdatePriceCaps() {
		ACLMessage m = new ACLMessage();
		PriceCapUpdateMessageContent priceMessage = new PriceCapUpdateMessageContent(Float.parseFloat(minBuyPrice.getText()), Float.parseFloat(maxBuyPrice.getText()), Float.parseFloat(minSellPrice.getText()), Float.parseFloat(maxSellPrice.getText()));
		m.setContent(priceMessage.toString());
		m.addReceiver(myHomeAgent);
		
		try {
			send(m);
		}catch(Exception e){
			System.out.println("Error event sending Message, exception: " + e);
		}
	}
}
