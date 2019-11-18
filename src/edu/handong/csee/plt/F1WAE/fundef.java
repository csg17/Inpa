package edu.handong.csee.plt.F1WAE;

public class fundef extends AST{
	String funName;
	String argName;
	AST funcBody = new AST();
	
	public fundef(String funName, String argName, AST funcBody) {
		this.funName = funName;
		this.argName = argName;
		this.funcBody = funcBody;
	}
	
	public String getfunName() {
		return funName;
	}
	public String getargName() {
		return argName;
	}
	public AST getfuncBody() {
		return funcBody;
	}
		
	// we need this method for implementing only parser.
	public String getASTCode() {
		
		//*실수 funcBody 자체는 AST의 객체이기 때문에 이 아이의 abstract code는 funcBody 객체의함수를 사용해서 가져와야 한다.
		return "(fundef " + "'" + getfunName() + " '" + getargName() + " " + funcBody.getASTCode() + ")";
	}
}
