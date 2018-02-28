import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Component;
import java.awt.GridLayout;
import java.util.Stack;
import java.util.StringTokenizer;
public class Calculator extends JFrame implements ActionListener
{
    JButton b1 = new JButton("1");
    JButton b2 = new JButton("2");
    JButton b3 = new JButton("3");
    JButton b4 = new JButton("4");
    JButton b5 = new JButton("5");
    JButton b6 = new JButton("6");
    JButton b7 = new JButton("7");
    JButton b8 = new JButton("8");
    JButton b9 = new JButton("9");
    JButton b0 = new JButton("0");
    JButton bplus = new JButton("+");
    JButton bminus = new JButton("-");
    JButton bdivide = new JButton("/");
    JButton bmultiply = new JButton("*");
    JButton bequal = new JButton("=");
    JButton bdot = new JButton(".");
    JTextField tinput = new JTextField();
    Calculator()
    {
        super("Calculator");
        JComponent cmp[] = {b7,b8,b9,bplus,b4,b5,b6,bminus,b1,b2,b3,bmultiply,bdot,b0,bequal,bdivide};
	int x=10,y=120,w=80,h=50,g=5;
	setVisible(true);
	this.setLayout(null);
	setSize((w+g)*4+30,(h+g)*4+160);
	add(tinput);
        for(int i=0;i<cmp.length;i++)
         add(cmp[i]);
        tinput.setBounds(10,10,335,100);
        for (int i=0;i<cmp.length;i+=4) 
	{
            cmp[i].setBounds(x,y,w,h);
            cmp[i+1].setBounds(x+(w+g),y,w,h);
            cmp[i+2].setBounds(x+(w+g)*2,y,w,h);
            cmp[i+3].setBounds(x+(w+g)*3,y,w,h);
            x=10;
            y+=55;
	}
        tinput.setEnabled(false);
        JButton bn[] = {this.b0,this.b1,this.b2,this.b3,this.b4,this.b5,this.b6,this.b7,this.b8,this.b9,this.bdot,this.bdivide,this.bmultiply,this.bminus,this.bplus,this.bequal};
        for(JButton bnl:bn)
        {
            bnl.addActionListener(this);
            //bnl.addKeyListener(new keyListener(this));
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static double evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();

         // Stack for numbers: 'values'
        Stack<Double> values = new Stack<>();

        // Stack for Operators: 'ops'
        Stack<Character> ops = new Stack<Character>();

        for(int i = 0; i < tokens.length; i++)
        {
             // Current token is a whitespace, skip it
            if (tokens[i] == ' ')
                continue;

            // Current token is a number, push it to stack for numbers
            if ((tokens[i] >= '0' && tokens[i] <= '9')||(tokens[i]=='.'))
            {
                StringBuffer sbuf = new StringBuffer();
                // There may be more than one digits in number
                while (i < tokens.length && ((tokens[i] >= '0' && tokens[i] <= '9')||(tokens[i]=='.')))
                    sbuf.append(tokens[i++]);
                values.push(Double.parseDouble(sbuf.toString()));
            }

            // Current token is an opening brace, push it to 'ops'
            else if (tokens[i] == '(')
                ops.push(tokens[i]);

            // Closing brace encountered, solve entire brace
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
                ops.pop();
            }

            // Current token is an operator.
            else if (tokens[i] == '+' || tokens[i] == '-' ||
                     tokens[i] == '*' || tokens[i] == '/')
            {
                // While top of 'ops' has same or greater precedence to current
                // token, which is an operator. Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));

                // Push current token to 'ops'.
                ops.push(tokens[i]);
            }
        }

        // Entire expression has been parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty())
            values.push(applyOp(ops.pop(), values.pop(), values.pop()));

        // Top of 'values' contains result, return it
        return values.pop();
    }

    // Returns true if 'op2' has higher or same precedence as 'op1',
    // otherwise returns false.
    public static boolean hasPrecedence(char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    // A utility method to apply an operator 'op' on operands 'a' 
    // and 'b'. Return the result.
    public static double applyOp(char op, double b, double a)
    {
        switch (op)
        {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        case '/':
            if (b == 0)
                throw new
                UnsupportedOperationException("Cannot divide by zero");
            return a / b;
        }
        return 0;
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        JButton b = (JButton)ae.getSource();
        JButton bn[] = {this.b0,this.b1,this.b2,this.b3,this.b4,this.b5,this.b6,this.b7,this.b8,this.b9,this.bdot,this.bdivide,this.bmultiply,this.bminus,this.bplus};
        for (JButton bn1 : bn) {
            if (b == bn1) {
                if(b!=this.bdivide&&b!=this.bmultiply&&b!=this.bplus&&b!=this.bminus)
                    this.tinput.setText(this.tinput.getText() + bn1.getText());
                else
                    this.tinput.setText(this.tinput.getText() +" "+ bn1.getText()+" ");
            }
        }
        if(b==this.bequal)
        {
            String equation = this.tinput.getText();
            System.out.println(equation);
            double result = evaluate(equation);
            if(result-(int)result!=0)
                this.tinput.setText(result+"");
            else
                this.tinput.setText((int)result+"");
        }
        
    }      
    public static void main(String[] args) {
        new Calculator();
    }
}