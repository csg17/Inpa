package edu.handong.csee.plt.F1WAE;

public class Sub extends AST{
	AST lhs = new AST();
	AST rhs = new AST();
	
	//constructor
	public Sub(AST lhs, AST rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public AST getLhs() {
		return lhs;
	}
	public AST getRhs() {
		return rhs;
	}
	public void changeValue(AST lhs, AST rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	public String getASTCode() {
		return "(sub " + lhs.getASTCode() + " " + rhs.getASTCode() + ")";
	}
}
