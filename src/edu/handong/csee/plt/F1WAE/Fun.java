package edu.handong.csee.plt.F1WAE;

public class Fun extends AST{
	String param;
	AST body = new AST();
	
	public Fun(String param, AST body) {
		this.param = param;
		this.body = body;
	}
	
	public String getParam() {
		return param;
	}
	
	public AST getFunBody() {
		return body;
	}
	
	public String getASTCode() {
		return "(fun '" + getParam() + " " + body.getASTCode() + ")";
	}
}
