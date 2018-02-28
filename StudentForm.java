import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class StudentForm extends Frame implements ActionListener
{
	String msg;
	Label heading = new Label("Student detail form",Label.CENTER);
	Label lname = new Label("Name");
	TextField tname = new TextField(20);
	Label lage = new Label("Age");
	Choice cage = new Choice();
	Label lgender = new Label("Gender");
	CheckboxGroup cbg = new CheckboxGroup();
	Checkbox cbmale = new Checkbox("Male",false,cbg);
	Checkbox cbfemale = new Checkbox("Female",false,cbg); 
	Label laddress = new Label("Address");
	TextArea taaddress = new TextArea("",100,100,TextArea.SCROLLBARS_VERTICAL_ONLY);
	Label lcourse = new Label("Course");
	Choice ccourse = new Choice();
	Label lsemester = new Label("Semester");
	TextField tsemester = new TextField(20);
	Button save = new Button("save");
	Font myFont = new Font("Serif",Font.BOLD,20);
	StudentForm(String t)
	{
		super(t);
		setSize(500,600);
		setVisible(true);
		//setLayout(new FlowLayout());
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
		save.addActionListener(this);
		for (int i=18;i<=25;i++)
			cage.add(i+"");
		ccourse.add("B.Tech");
		ccourse.add("M.Tech");
		ccourse.add("BBA");
		ccourse.add("MBA");
		ccourse.add("BCA");
		ccourse.add("MCA");
		heading.setFont(myFont);
		heading.setBounds(150,50,170,40);
		Component c[] = {lname,tname,lage,cage,lgender,cbmale,cbfemale,laddress,taaddress,lcourse,ccourse,lsemester,tsemester};
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
		save.setBounds(170,y+130,w,h);
	}

	public void actionPerformed(ActionEvent ae){
		StudentForm sf=this;
    if(ae.getActionCommand().equals("save")){
      	String name = sf.tname.getText();
		int age = Integer.parseInt(sf.cage.getSelectedItem()+"");
		String gender;
		if(sf.cbmale.getState())
			gender="Male";
		else
			gender="Female";
		String course = sf.ccourse.getSelectedItem();
		String semester = sf.tsemester.getText();
		String adds = sf.taaddress.getText();
		try {
		FileOutputStream fout = new FileOutputStream("E:\\study material\\6th Semester\\Advanced Java Technology\\Lab\\student.txt",true);
		DataOutputStream dout = new DataOutputStream(fout);
		dout.writeBytes(name+"\t"+age+"\t"+gender+"\t"+course+"\t"+semester+"\t"+adds+"\n");
		fout.close();
		}
		catch(Exception e){}
	}
  }
	public static void main(String args[])
	{
		StudentForm sf = new StudentForm("Student Details");
	}
}