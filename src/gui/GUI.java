package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.FordFulkerson;
import model.Graph;
import model.Vertex;

public class GUI {
	
	private JFrame frame;
	private JPanel inputPanel;
	private JPanel outputPanel;
	private JLabel input;
	private JLabel output;
	
	private JPanel in1;
	private JPanel in2;
	private JPanel in3;
	private JPanel in4;
	private JPanel in5;
	private JTextField inM1;
	private JTextField inM2;
	private JTextField inM3;
	private JTextField inM4;
	private JTextField inM5;
	private JTextArea inP1;
	private JTextArea inP2;
	private JTextArea inP3;
	private JTextArea inP4;
	private JTextArea inP5;
	
	private JPanel out1;
	private JPanel out2;
	private JPanel out3;
	private JPanel out4;
	private JPanel out5;
	private JTextField outM1;
	private JTextField outM2;
	private JTextField outM3;
	private JTextField outM4;
	private JTextField outM5;
	private JTextArea outP1;
	private JTextArea outP2;
	private JTextArea outP3;
	private JTextArea outP4;
	private JTextArea outP5;
	
	private JButton button;
	
//	private int numberOfMeetings;
	
	/*private void initEntry(JPanel panel, JTextField meeting, JTextArea attendees) {
		panel = new JPanel(new BorderLayout());
		meeting = new JTextField(10);
		attendees = new JTextArea();
		panel.add(meeting, BorderLayout.WEST);
		panel.add(attendees, BorderLayout.CENTER);
	}*/
	
	public void createGUI() {
//		numberOfMeetings = 5;
		
		frame = new JFrame("Meeting Scheduler");
		frame.setLayout(new GridLayout(1, 2));
		
		in1 = new JPanel(new BorderLayout());
		inM1 = new JTextField(15);
		inP1 = new JTextArea();
		in1.add(inM1, BorderLayout.WEST);
		in1.add(inP1, BorderLayout.CENTER);
		
		in2 = new JPanel(new BorderLayout());
		inM2 = new JTextField(15);
		inP2 = new JTextArea();
		in2.add(inM2, BorderLayout.WEST);
		in2.add(inP2, BorderLayout.CENTER);
		
		in3 = new JPanel(new BorderLayout());
		inM3 = new JTextField(15);
		inP3 = new JTextArea();
		in3.add(inM3, BorderLayout.WEST);
		in3.add(inP3, BorderLayout.CENTER);
		
		in4 = new JPanel(new BorderLayout());
		inM4 = new JTextField(15);
		inP4 = new JTextArea();
		in4.add(inM4, BorderLayout.WEST);
		in4.add(inP4, BorderLayout.CENTER);
		
		in5 = new JPanel(new BorderLayout());
		inM5 = new JTextField(15);
		inP5 = new JTextArea();
		in5.add(inM5, BorderLayout.WEST);
		in5.add(inP5, BorderLayout.CENTER);
		
		inputPanel = new JPanel(new GridLayout(7, 1));
		input = new JLabel("Please enter meetings, followed by attendees:");
		inputPanel.add(input);
		inputPanel.add(in1);
		inputPanel.add(in2);
		inputPanel.add(in3);
		inputPanel.add(in4);
		inputPanel.add(in5);
		
		button = new JButton("Schedule");
		button.addActionListener(new myListener());
		inputPanel.add(button);
		
		out1 = new JPanel(new BorderLayout());
		outM1 = new JTextField(15);
		outP1 = new JTextArea();
		out1.add(outM1, BorderLayout.WEST);
		out1.add(outP1, BorderLayout.CENTER);
		
		out2 = new JPanel(new BorderLayout());
		outM2 = new JTextField(15);
		outP2 = new JTextArea();
		out2.add(outM2, BorderLayout.WEST);
		out2.add(outP2, BorderLayout.CENTER);
		
		out3 = new JPanel(new BorderLayout());
		outM3 = new JTextField(15);
		outP3 = new JTextArea();
		out3.add(outM3, BorderLayout.WEST);
		out3.add(outP3, BorderLayout.CENTER);
		
		out4 = new JPanel(new BorderLayout());
		outM4 = new JTextField(15);
		outP4 = new JTextArea();
		out4.add(outM4, BorderLayout.WEST);
		out4.add(outP4, BorderLayout.CENTER);
		
		out5 = new JPanel(new BorderLayout());
		outM5 = new JTextField(15);
		outP5 = new JTextArea();
		out5.add(outM5, BorderLayout.WEST);
		out5.add(outP5, BorderLayout.CENTER);
		
		
		outputPanel = new JPanel(new GridLayout(7, 1));
		output = new JLabel("This is the optimal schedule to handle conflicts:");
		outputPanel.add(output);
		outputPanel.add(out1);
		outputPanel.add(out2);
		outputPanel.add(out3);
		outputPanel.add(out4);
		outputPanel.add(out5);
		
		frame.add(inputPanel);
		frame.add(outputPanel);
		
		frame.setSize(1500, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	/**
	 * Check if an array of vertices contains a specific vertex.
	 * @param vertices array of vertices
	 * @param v the specific vertex
	 * @return true or false
	 */
	private boolean checkContain(Vertex[] vertices, Vertex v) {
		int length = vertices.length;
		boolean flag = false;
		int counter = 0;
		while (!flag && counter < length) {
			flag = flag || v.equals(vertices[counter]);
			counter++;
		}
		return flag;
	}
	
	/**
	 * Create attendee vertices from the raw input string.
	 * @param s raw string
	 * @return an array of vertices, where each vertex corresponds to an attendee.
	 */
	private Vertex[] parseAttendees(String s) {
		String[] names = s.split(",");
		int length = names.length;
		Vertex[] verticesArray = new Vertex[length];
		for (int i = 0; i < names.length; i++) {
//			names[i] = names[i].trim();
			verticesArray[i] = new Vertex(names[i].trim());
		}
		return verticesArray;
	}
	
	private Graph findResidueGraph() {
		Vertex s = new Vertex("s");
		Vertex t = new Vertex("t");
		Vertex m1 = new Vertex(inM1.getText());
		Vertex m2 = new Vertex(inM2.getText());
		Vertex m3 = new Vertex(inM3.getText());
		Vertex m4 = new Vertex(inM4.getText());
		Vertex m5 = new Vertex(inM5.getText());
		HashSet<Vertex> allAttendees = new HashSet<>();
		Vertex[] m1attendees = parseAttendees(inP1.getText());
		Vertex[] m2attendees = parseAttendees(inP2.getText());
		Vertex[] m3attendees = parseAttendees(inP3.getText());
		Vertex[] m4attendees = parseAttendees(inP4.getText());
		Vertex[] m5attendees = parseAttendees(inP5.getText());
		for (Vertex v : m1attendees) {
			allAttendees.add(v);
		}
		for (Vertex v : m2attendees) {
			allAttendees.add(v);
		}
		for (Vertex v : m3attendees) {
			allAttendees.add(v);
		}
		for (Vertex v : m4attendees) {
			allAttendees.add(v);
		}
		for (Vertex v : m5attendees) {
			allAttendees.add(v);
		}
		ArrayList<Vertex[]> meetingAttendees = new ArrayList<>();
		meetingAttendees.add(m1attendees);
		meetingAttendees.add(m2attendees);
		meetingAttendees.add(m3attendees);
		meetingAttendees.add(m4attendees);
		meetingAttendees.add(m5attendees);
		
		int matrixSize = allAttendees.size() + 7;
		Vertex[] vertices = new Vertex[matrixSize]; // each attendee and each meeting is a vertex, plus s and t
		vertices[0] = s;
		vertices[1] = m1;
		vertices[2] = m2;
		vertices[3] = m3;
		vertices[4] = m4;
		vertices[5] = m5;
		vertices[matrixSize - 1] = t;
		Object[] temp = allAttendees.toArray();
		for (int i = 0; i < temp.length; i++) {
			vertices[i + 6] = (Vertex) temp[i];
		}
		int[][] edgeCapacities = new int[matrixSize][matrixSize];
		for (int i = 1; i < 6; i++) {
			edgeCapacities[0][i] = Integer.MAX_VALUE;
		}
		for (int i = 6; i < matrixSize - 1; i++) {
			edgeCapacities[i][matrixSize - 1] = 1;
		}
		for (int i = 1; i < 6; i++) {
			for (int j = 6; j < matrixSize - 1; j++) {
				if (checkContain(meetingAttendees.get(i - 1), vertices[j])) {
					edgeCapacities[i][j] = 1;
				}
			}
		}
		Graph g = new Graph(vertices, edgeCapacities);
		return FordFulkerson.finalResidue(g, s, t);
//		System.out.println(Arrays.toString(parseAttendees(inP1.getText())));
		
	}
	
	class myListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Graph residue = findResidueGraph();
			int dimension = residue.vertices.length;
			
			/*for (int i = 0; i < dimension; i++) {
				for (int j = 0; j < dimension; j++) {
					System.out.print(" " + residue.edgeCapacities[i][j]);
				}
				System.out.println();
			}*/
			
			for (int i = 1; i < 6; i++) {
				String s = "";
				for (int j = 6; j < dimension - 1; j++) {
					if (residue.edgeCapacities[j][i] == 1) {
						s = s + residue.vertices[j].name + ". ";
					}
				}
				switch (i) {
				case 1:
//					System.out.println("In case 1" + s);
					outM1.setText(inM1.getText());
					outP1.setText(s);
					break;
				case 2:
//					System.out.println("In case 2" + s);
					outM2.setText(inM2.getText());
					outP2.setText(s);
					break;
				case 3:
//					System.out.println("In case 3" + s);
					outM3.setText(inM3.getText());
					outP3.setText(s);
					break;
				case 4:
//					System.out.println("In case 4" + s);
					outM4.setText(inM4.getText());
					outP4.setText(s);
					break;
				case 5:
//					System.out.println("In case 5" + s);
					outM5.setText(inM5.getText());
					outP5.setText(s);
					break;
				}
			}
		}
	}
}