package edu.handong.csee.plt.F1WAE;

public class AST {
	
	public String getASTCode() {
		String astCode="";
		if(this instanceof Add)
			astCode = ((Add)this).getASTCode();
		
		if(this instanceof Num)
			astCode = ((Num)this).getASTCode();
		
		if(this instanceof Sub)
			astCode = ((Sub)this).getASTCode();

		return astCode;
	}
}

