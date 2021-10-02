package src;

import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JOptionPane;

public class matrix {
	view view;
	int opened=0;
	private short row =20,col =20 ,numbombs =40;
	public short getRow() {
		return row;
	}

	public void setRow(short row) {
		this.row = row;
	}

	public short getCol() {
		return col;
	}

	public void setCol(short col) {
		this.col = col;
	}

	public short getNumbombs() {
		return numbombs;
	}

	public void setNumbombs(short numbombs) {
		this.numbombs = numbombs;
	}
	cell[][] cells=new cell[row][col];

	public matrix(view view) {
		super();
		this.view=view;
		for (int i = 0;i<row;i++ )
			for(int j=0;j<col;j++)
				cells[i][j]=new cell();
	}
	
	private boolean isvalid(short x,short y)
	{
		if(x >= 0 && y >= 0 && x < row && y < col)
			return true;
		else
			return false;
	}
	
	public boolean open(short x,short y)
	{
		if(cells[x][y].isIsbomb())
			return true;
		else
		{
			if(isvalid(x,y) && !cells[x][y].isIsflagged() && !cells[x][y].isIsquestionmarked() )
			{
				if ( cells[x][y].isIsopened() )
					return false;
				else 
					{
					cells[x][y].setIsopened();
					view.f.remove(view.buttons[x][y]);
					view.f.add(view.labels[x][y],x*row+y);
					view.f.setVisible(true); 
					opened++;
					if( cells[x][y].getBombcount() == 0 )
						{
						for(short j= (short) (x-1) ; j<x+2 ; j++ )
							 for(short k= (short) (y-1) ; k<y+2 ; k++ )
								 if( isvalid( j , k) )
									 open(j,k);
						}				
					if(opened+numbombs==row*col)
					{
						JOptionPane.showMessageDialog(null, "veni vidi vici");
						view.f.dispose();
					}
					}
		}
		return false;
		}
	}
		
	public void pickfirst(short x,short y)	
	{
		 for (int i=0 ;i < numbombs;i++)
		 {
			 Random rand =new Random();
			 short x1,y1; 
			 x1= (short) Math.abs((rand.nextInt())%row);
			 y1= (short) Math.abs((rand.nextInt())%col);
			 
			 if( x1 ==x ||  y1 == y || cells[x1][y1].isIsbomb() == true ) 
			 {
				 i--;
			 }
			 else
			 {
				 cells[x1][y1].setIsbomb();
				 for(short j= (short) (x1-1) ; j < x1+2 ; j++ )
					 for(short k= (short) (y1-1) ; k < y1+2 ; k++ )
						 if( isvalid(j,k) )
							 cells[j][k].incBombcount();
			 }	  
			 
		 }
		 
		 for (int i = 0;i<row;i++ )
				for(int j=0;j<col;j++)
				{
					 view.labels[i][j] = new Label(Short.toString(cells[i][j].getBombcount()));
					 view.labels[i][j].addMouseListener(new labelclicked(i,j));
					 view.labels[i][j].setAlignment(1);
				}
		 open(x,y);
		 
	}
	public void adminshow() 
	{
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				if(cells[i][j].isIsbomb())
					System.out.print("* ");
				else
				System.out.print(cells[i][j].getBombcount()+" ");
			}
			System.out.println("\n");
		}
	}
	public void usershow() 
	{
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				if(cells[i][j].isIsopened())
				System.out.print(cells[i][j].getBombcount()+" ");
				else if(cells[i][j].isIsflagged())
					System.out.print("F ");
				else if(cells[i][j].isIsquestionmarked())
					System.out.print("? ");
				else
					System.out.print("X ");
			}
			System.out.println("\n");
		}
	}
	

private class labelclicked implements MouseListener
{
	private int i,j;
	
	public labelclicked(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		for(short k= (short) (i-1) ; k<i+2 ; k++ )
			 for(short l=  (short) (j-1) ; l<j+2 ; l++ )
				 if( isvalid( k , l) )
					 if(!cells[k][l].isIsflagged() && !cells[k][l].isIsquestionmarked())
						 view.loose|=open(k,l);
		if(view.loose)
		{
			JOptionPane.showMessageDialog(null, "u loose");
			view.f.dispose();
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

