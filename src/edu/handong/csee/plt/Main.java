package edu.handong.csee.plt;

import edu.handong.csee.plt.F1WAE.AST;

public class Main {
	
	static boolean onlyParser = false; // for -p option
	
	public static void main(String[] args) {
		
		
		// This is just an example code. Use args to get -p option and actuall code from CLI
		 //String exampleCode = "{with {a 3} {+ a 4}}";
		//String exampleCode = "{deffun {identity x} x}";
		//String exampleCode = "{with {x {+ 1 2}}{+ x {- 2 x}}}}";
		//String exampleCode = "{with {x {+ 1 2}} {+ x x}}";
		String exampleCode = "{with {f {fun {y} {+ x y}}} {with {x 5} {f 4}}}";
		// **스페이스로 sub expression구분하기 때문에 띄어쓰기 잘해서 넣어줘야 함.**
		
		// Parser
		Parser parser = new Parser();
		AST ast = parser.parse(exampleCode);
		
		if(ast == null)
			System.out.println("Syntax Error!");
		
		//if(onlyParser)
			System.out.println(ast.getASTCode());
		
		// interpreter
		Interpreter interpreter = new Interpreter();
		
		//인터프리터실행시킬떄 이것만 해주면 됨!  
		String result = interpreter.interp(ast);
		
		System.out.println(result);//
	}
}
