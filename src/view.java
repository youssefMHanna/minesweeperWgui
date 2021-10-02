package src;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class view extends JFrame {  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean loose=false,firstclick=true;;
	matrix mat=new matrix(this);
	JFrame f; 
	public JButton[][] buttons=new JButton[mat.getRow()][mat.getCol()];
	public Label[][] labels =new Label[mat.getRow()][mat.getCol()];
    public view()
    {
		super();
		f=new JFrame();  
		for (int i = 0;i<mat.getRow();i++ )
			for(int j=0;j<mat.getCol();j++)
			{
				buttons[i][j]=new JButton();
				buttons[i][j].addMouseListener(new buttonclicked(i,j));
				f.add(buttons[i][j],i*(int)mat.getCol()+j);
			}
		GridLayout layout =	new GridLayout(mat.getRow(),mat.getCol(),1,1);
        f.setLayout(layout);  
        f.setSize(50*mat.getRow(),50*mat.getCol());
        f.setVisible(true); 
    }    
private class buttonclicked implements MouseListener
{
	
	private int i,j;
	public buttonclicked(int i, int j)
	{
		super();
		this.i=i;
	    this.j=j;
	}	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(SwingUtilities.isRightMouseButton(arg0))
		{
			mat.cells[i][j].toggleflag();
			if(mat.cells[i][j].isIsflagged())
				buttons[i][j].setText("F");
			else if(mat.cells[i][j].isIsquestionmarked())
					buttons[i][j].setText("?");
			else 	buttons[i][j].setText("");			
		}		
		else 
			if(!mat.cells[i][j].isIsflagged()&&! mat.cells[i][j].isIsquestionmarked())
			{
				if(firstclick)
				{
					mat.pickfirst((short)i,(short)j);
					firstclick=false;
				}
				else
				{	
					loose=mat.open((short)i,(short)j);
					if(loose)
					{
						JOptionPane.showMessageDialog(null, "u loose");
						f.dispose();
					}
				}
			}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
	
}