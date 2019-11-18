package edu.handong.csee.plt.F1WAE;

public class App extends AST{
	AST ftn = new AST();
	AST arg = new AST();
	
	public App(AST ftn, AST arg) {
		this.ftn = ftn;
		this.arg = arg;
	}
	
}
