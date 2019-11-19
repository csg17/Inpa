package edu.handong.csee.plt;

import edu.handong.csee.plt.F1WAE.*;

public class Interpreter {

	public String interp(AST ast) {
		
		//ast의 타입이 num일 경우라는 것을 "상속 = type-case"을 써서 해주기.
		if(ast instanceof Num) {
			return ((Num)ast).getStrNum();
		}
		if(ast instanceof Add) {
			Add add = (Add)ast;
			return "" + (Integer.parseInt(interp(add.getLhs())) + Integer.parseInt(interp(add.getRhs())));
		}
		if(ast instanceof Sub) {
			Sub sub = (Sub)ast;
			return "" + (Integer.parseInt(interp(sub.getLhs())) - Integer.parseInt(interp(sub.getRhs())));
		}
		if(ast instanceof With) {
			With with = (With)ast; // with (id a)(num 7)(add (id a)(num 3))
		    return interp( subst(with.getBody(), with.getName(), interp(with.getNamedExpr())) );
		}
		if(ast instanceof Fun) {
			return ast.getASTCode();
		}
		if(ast instanceof App) {
			App app = (App) ast;
			//{{fun {y} {+ x y}} 4}
			Fun ftn = (Fun)app.getFtn();
			
			return interp(subst(ftn.getFunBody(), ftn.getParam(), interp(app.getArg())));
			// val은 무조건 인터프리터 해서 들어가기 때문에 string임. 
		}
		// 에러인 경우, FREE IDTF인 경우 마지막에 해주기.
		
		return null;
	}
	/*
	 *This method is for 'WAE'
	 */
	public AST subst(AST ast, String idtf, String val) {
		// 일단 ast가 with의 요소들을 가지고 이씀. name, named_expr, body
		// ast는 body가 넘어온 거임. 
		// change the name in the body into named_expr
		// 이거 라켓이랑 똑같은 거. (subst exp idtf val)
		if(ast instanceof Num) {
			return ast;
		}
		if(ast instanceof Add) {
			// after changing the components with value and finally return
			((Add) ast).changeValue(subst(((Add)ast).getLhs(), idtf, val), subst(((Add)ast).getRhs(), idtf, val));
			return ast;
		}
		if(ast instanceof Sub) {
			((Sub) ast).changeValue(subst(((Sub)ast).getLhs(), idtf, val), subst(((Sub)ast).getRhs(), idtf, val));
			return ast;
		}
		if(ast instanceof With) {
			subst(((With) ast).getBody(), idtf, val);
			
			if(idtf.equals(((With) ast).getName())) {
				return ast;
			}else {
				subst(((With) ast).getBody(), idtf, val);
			}
		}
		if(ast instanceof Idtf) {
			//System.out.println(((Idtf) ast).getIdtf() + " " +(((Idtf) idtf).getIdtf()) );
			
			if(((Idtf) ast).getIdtf().equals(idtf)) {
			Num newval = new Num(val);
				return newval;
			}
			else {
				return ast; 
			}
		}
		return null;
	}
}
