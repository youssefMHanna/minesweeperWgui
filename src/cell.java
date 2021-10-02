package src;

public class cell {
	private boolean isflagged,isquestionmarked,isbomb,isopened;
	private short bombcount;
	
	public cell() {
		super();
		this.isflagged = false;
		this.isquestionmarked = false;
		this.isbomb=false;
		this.isopened=false;
		this.bombcount = 0;
	}
	
	public boolean isIsopened() {
		return isopened;
	}
	public void setIsopened() {
		this.isopened = true;
	}
	public boolean isIsbomb() {
		return isbomb;
	}
	public void setIsbomb() {
		this.isbomb = true;
	}
	public boolean isIsflagged() {
		return isflagged;
	}
	
	public void setIsflagged() {
		this.isflagged ^= true;
	}
	
	public boolean isIsquestionmarked() {
		return isquestionmarked;
	}
	
	public void setIsquestionmarked() {
		this.isquestionmarked ^= true;
	}
	
	public short getBombcount() {
		return bombcount;
	}
	public void toggleflag()
	{
		if(this.isflagged)
		{
			this.isflagged^=true;
			this.isquestionmarked^=true;
		}
		else if(this.isquestionmarked)
			this.isquestionmarked^=true;
		else
			this.isflagged^=true;
	}
	/*
	public void setBombcount(short bombcount) {
		this.bombcount = bombcount;
	}
	*/
	public void incBombcount() {
		this.bombcount++;
	}
	
	
}
