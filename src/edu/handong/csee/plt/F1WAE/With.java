package edu.handong.csee.plt.F1WAE;

public class With extends AST{
	// 각각의 구성요소들이 AST안의 여러 종류의 abstract 형태중 하나가
	//될 수 있기 때문에 이렇게 with 안의 객체로 만들어주기.
	String name;
	AST named_expr = new AST();
	AST body = new AST();
	
	//constructor
	public With(String name, AST named_expr, AST body) {
		this.name = name;
		this.named_expr = named_expr;
		this.body = body;
	}
	public String getName() {
		return name;
	}
	public AST getNamedExpr() {
		return named_expr;
	}
	public AST getBody() {
		return body;
	}
	// 파싱한 with 구문 출력.  
	public String getASTCode() {
		return "(with " + getName() + " " + named_expr.getASTCode() + " " + body.getASTCode() + ")";
	}
}
