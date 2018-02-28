import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
class StudentFormSwing extends JFrame implements ActionListener
{
	String ag[]={"18","19","20","21","22","23","24","25"};
	String ac[]={"B.Tech","BBA","M.Tech","MBA","BCA","MCA"};

	String msg;
	JLabel heading = new JLabel("Student detail form",JLabel.CENTER);
	JLabel lname = new JLabel("Name");
	JTextField tname = new JTextField(20);
	JLabel lage = new JLabel("Age");
	JComboBox<String> cage = new JComboBox<>(ag);
	JLabel lgender = new JLabel("Gender");
	ButtonGroup bggender = new ButtonGroup();
	JCheckBox cbmale = new JCheckBox("Male",false);
	JCheckBox cbfemale = new JCheckBox("Female",false); 
	JLabel laddress = new JLabel("Address");
	JTextArea taaddress = new JTextArea("");
	JLabel lcourse = new JLabel("Course");
	JComboBox<String> ccourse = new JComboBox<>(ac);
	JLabel lsemester = new JLabel("Semester");
	JTextField tsemester = new JTextField(20);
	JButton save = new JButton("save");
	Font myFont = new Font("Serif",Font.BOLD,20);
	StudentFormSwing(String title)
	{
		super(title);
		setSize(500,600);
		setVisible(true);
		setLayout(null);
		add(heading);
		add(lname);
		add(tname);
		add(lage);
		add(cage);
		add(lgender);
		add(cbmale);
		add(cbfemale);
		add(lcourse);
		add(ccourse);
		add(lsemester);
		add(tsemester);
		add(laddress);
		add(taaddress);
		add(save);
		bggender.add(cbmale);
		bggender.add(cbfemale);
		save.addActionListener(this);
		heading.setFont(myFont);
		heading.setBounds(150,50,170,40);
		JComponent c[] = {lname,tname,lage,cage,lgender,cbmale,cbfemale,laddress,taaddress,lcourse,ccourse,lsemester,tsemester};
		int x=50,y=100,w=150,h=30;
		for(int i=0;i<c.length;i+=2)
		{
			if(i==4)
			{
				c[i].setBounds(x,y,w,h);
				c[i+1].setBounds(x+160,y,w,h);	
				c[i+2].setBounds(x+320,y,w,h);	
				i++;
			}
			else
			{	
				c[i].setBounds(x,y,w,h);
				c[i+1].setBounds(x+160,y,w,h);
			}
			y+=40;
			x=50;
		}
		laddress.setBounds(x,y,w,h);
		taaddress.setBounds(x+160,y,250,100);
		save.setBounds(220,y+130,100,30);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae){
		StudentFormSwing sf=this;
	    if(ae.getActionCommand().equals("save")){
	      	String name = sf.tname.getText();
			int age = Integer.parseInt(sf.cage.getSelectedItem()+"");
			String gender;
			if(sf.cbmale.isSelected())
				gender="Male";
			else
				gender="Female";
			String course = sf.ccourse.getSelectedItem().toString();
			String semester = sf.tsemester.getText();
			String adds = sf.taaddress.getText();
			try {
			FileOutputStream fout = new FileOutputStream("student.txt",true);
			DataOutputStream dout = new DataOutputStream(fout);
			dout.writeBytes(name+"\t"+age+"\t"+gender+"\t"+course+"\t"+semester+"\t"+adds+"\n");
			fout.close();
			}
			catch(Exception e){}
		}
	}
	public static void main(String s[])
	{
		new StudentFormSwing("Student Form");
	}
}