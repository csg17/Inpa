package edu.handong.csee.plt.F1WAE;

public class Add extends AST{
	AST lhs = new AST();
	AST rhs = new AST();
	
	public Add(AST lhs, AST rhs) {
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
	// 타입이 add일 때 add 8 9꼴로 바꿔준다.
	public String getASTCode() {
		return "(add " + lhs.getASTCode() + " " + rhs.getASTCode() + ")";
	}
}

