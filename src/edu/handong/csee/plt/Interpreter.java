package edu.handong.csee.plt;

import edu.handong.csee.plt.F1WAE.*;

public class Interpreter {

	public String interp(AST ast) {
		
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
		    return interp( subst(with.getBody(), with.getName(), with.getNamedExpr()) );
		}
		
		return null;
	}
	/*
	 *This method is for 'WAE'
	 */
	public AST subst(AST ast, AST idtf, AST val) {
		// 일단 ast가 with의 요소들을 가지고 이씀. name, named_expr, body
		// body가 넘어온 거임. 
		// change the name in the body into named_expr
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
			subst(((With) ast).getName(), idtf, val);
			if(idtf.equals(((With) ast).getName())) {
				return ast;
			}else {
				subst(((With) ast).getBody(), ((With) ast).getName(), ((With) ast).getNamedExpr());
			}
		}
		if(ast instanceof Idtf) {
			//System.out.println(((Idtf) ast).getIdtf() + " " +(((Idtf) idtf).getIdtf()) );
			
			if(((Idtf) ast).getIdtf().equals(((Idtf) idtf).getIdtf())) {
				return val;
			}
			else {
				return ast; 
			}
		}
		return null;
	}
}
