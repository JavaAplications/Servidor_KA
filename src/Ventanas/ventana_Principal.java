package Ventanas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;



import BufferRx.BufferDeDatos;
import BufferRx.TiempoGrabacionBBDD;
import KeepAlive.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;




public class ventana_Principal extends JFrame {

	/**
	 * 
	 */
	int pulsaciones=1;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField editPuertoKA;
	public static JTextArea textAreaConsolaDeKeeps;
	BufferDeDatos HiloBuffer;
	static ClaseServidorKA ServerObjKA;
	public JButton btn_ServerKAOn;
public 	JButton btn_ServerKAOff;
TiempoGrabacionBBDD tempo;
	private JPanel panel_1;
	private JScrollPane scrollPane_1;
	private JTextField textTimer;
	private JLabel lblTimer;
	private JLabel lblSegundos;
	//Thread t ;
	int tiempo;
	private JButton btn_Limpiar;
	public static JLabel lbl_numKeeps;
	/**
	 * Create the frame.
	 */
	public ventana_Principal() {
		setTitle("Servidor de Keeps Alives");
		setResizable(false);
	
		Inicializacion();
		
		btn_ServerKAOff.setEnabled(false);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(207, 11, 128, 39);
		panel_1.add(panel);
		panel.setLayout(null);
		
		lblTimer = new JLabel("Timer");
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTimer.setBounds(6, 11, 41, 20);
		panel.add(lblTimer);
		
		textTimer = new JTextField();
		textTimer.setHorizontalAlignment(SwingConstants.CENTER);
		textTimer.setText("15");
		textTimer.setBounds(48, 12, 41, 20);
		panel.add(textTimer);
		textTimer.setColumns(10);
		
		lblSegundos = new JLabel("Seg.");
		lblSegundos.setBounds(99, 15, 31, 14);
		panel.add(lblSegundos);
		
		btn_Limpiar = new JButton("Limpiar");
		btn_Limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textAreaConsolaDeKeeps.setText("");
				
			}
		});
		btn_Limpiar.setBounds(258, 61, 89, 44);
		panel_1.add(btn_Limpiar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.PINK);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(137, 58, 111, 49);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Keeps Alives");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(16, 10, 81, 16);
		panel_2.add(lblNewLabel);
		
		lbl_numKeeps = new JLabel("-");
		lbl_numKeeps.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_numKeeps.setBounds(30, 32, 46, 12);
		panel_2.add(lbl_numKeeps);
	
	
	}

	private void Inicializacion() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 375, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		//panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBackground(new Color(255, 255, 102));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
		);
		panel_1.setLayout(null);
		
		btn_ServerKAOn = new JButton("Server KA ON");
		btn_ServerKAOn.setBounds(10, 11, 117, 23);
		panel_1.add(btn_ServerKAOn);
		
		btn_ServerKAOff = new JButton("Server KA OFF");
		btn_ServerKAOff.setBounds(10, 45, 117, 23);
		panel_1.add(btn_ServerKAOff);
		
		editPuertoKA = new JTextField();
		editPuertoKA.setHorizontalAlignment(SwingConstants.CENTER);
		editPuertoKA.setBounds(76, 79, 51, 20);
		panel_1.add(editPuertoKA);
		editPuertoKA.setText("9002");
		editPuertoKA.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Puerto");
		lblNewLabel_1.setBounds(10, 82, 37, 14);
		panel_1.add(lblNewLabel_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 126, 337, 224);
		panel_1.add(scrollPane_1);
		
		textAreaConsolaDeKeeps = new JTextArea();
		 DefaultCaret caret = (DefaultCaret)textAreaConsolaDeKeeps.getCaret();
		 caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		textAreaConsolaDeKeeps.setText("Consola de Keeps Alives");
		scrollPane_1.setViewportView(textAreaConsolaDeKeeps);
		
		btn_ServerKAOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				stopServidorKA();
				HiloBuffer.parar();
				tempo.detener();
				textTimer.setEnabled(true);
			}
		});
		btn_ServerKAOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tiempo= Integer.parseInt(textTimer.getText().toString());
				  startServidorKA();
				  HiloBuffer=new BufferDeDatos();
					HiloBuffer.start();	
					tempo= new TiempoGrabacionBBDD(tiempo);
			 		tempo.start();
			 		textTimer.setEnabled(false);
			}
		});
		contentPane.setLayout(gl_contentPane);
		
	}

	
	
	
	
	protected void startServidorKA() {
		btn_ServerKAOn.setEnabled(false);
		  btn_ServerKAOff.setEnabled(true);
		  editPuertoKA.setEnabled(false);
		  int portKA=Integer.parseInt(editPuertoKA.getText().toString());
		System.out.println("portKA: "+portKA);
		  ServerObjKA=new ClaseServidorKA(portKA, ventana_Principal.textAreaConsolaDeKeeps);
		  ServerObjKA.start();
		 
	}

	protected void stopServidorKA() {
		  btn_ServerKAOn.setEnabled(true);
		  btn_ServerKAOff.setEnabled(false);
		  editPuertoKA.setEnabled(true);
		 textAreaConsolaDeKeeps.setText("");
		  ServerObjKA.StopServer();
		
		
	}
}

 
	   


