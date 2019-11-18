package edu.handong.csee.plt;

import java.util.ArrayList;

import edu.handong.csee.plt.F1WAE.*;

// parsing 관련된 것은 다 여기서 합시
public class Parser {

	AST parse(String exampleCode) {
		ArrayList<String> subExpressions = splitExpressionAsSubExpressions(exampleCode);

		// num
		if(subExpressions.size() == 1 && isNumeric(subExpressions.get(0))) {

			return new Num(subExpressions.get(0));
		}
		else if(subExpressions.size() == 1) {
			// identifier
			return new Idtf(subExpressions.get(0));
		}

		if(subExpressions.get(0).equals("+")) {
			// Add객체 리턴. lhs랑 rhs 각각 파서 또 돌려줘야 함. 
			return new Add(parse(subExpressions.get(1)),parse(subExpressions.get(2)));
		}
		
		if(subExpressions.get(0).equals("-")) {
			return new Sub(parse(subExpressions.get(1)), parse(subExpressions.get(2)));
		}
		
		if(subExpressions.get(0).equals("with")) {
			ArrayList<String> subExWAE = splitExpressionAsSubExpressions(subExpressions.get(1));
			
			return new With(parse(subExWAE.get(0)), parse(subExWAE.get(1)), parse(subExpressions.get(2)));
		}
		// If the type is FunDef, use not parse but parserFD.
		if(subExpressions.get(0).equals("deffun")) {
			ArrayList<String> subExWAE = splitExpressionAsSubExpressions(subExpressions.get(1));
			
			return new fundef(subExWAE.get(0), subExWAE.get(1), parse(subExpressions.get(2)));
		}
		
		return null;
	}

	private ArrayList<String> splitExpressionAsSubExpressions(String exampleCode) {

		// {with {x {+ 1 2}}{+ x x}}
		// deal with brackets first.
		if((exampleCode.startsWith("{") && !exampleCode.endsWith("}"))
				|| (!exampleCode.startsWith("{") && exampleCode.endsWith("}"))) {
			System.out.println("Syntax error");
			System.exit(0);
		}

		if(exampleCode.startsWith("{"))
			exampleCode = exampleCode.substring(1, exampleCode.length()-1);


		return getSubExpressions(exampleCode);
	}



	/**
	 * This method return a list of sub-expression from the given expression.
	 * For example, {+ 3 {+ 3 4}  -> +, 2, {+ 3 4}
	 * TODO JC was sleepy while implementing this method...it has complex logic and might be buggy...
	 * You can do better or find an external library.
	 * @param exampleCode
	 * @return list of sub expressions 
	 */
	private ArrayList<String> getSubExpressions(String exampleCode) {

		ArrayList<String> sexpressions = new ArrayList<String>();
		int openingParenthesisCount = 0;
		String strBuffer = "";
		
		//// {with {x {+ 1 2}} {+ x x}} occured error.
		// {deffun {identity x} x}
		for(int i=0; i < exampleCode.length()  ;i++) {
			
			if(i==0 || (i==0 && exampleCode.charAt(i) == '{')) {
				strBuffer = strBuffer + exampleCode.charAt(i);
				continue;
			//subexpression하나가 만들어질 때까지 버퍼에 계속 채워준다.만들어지면 배열에 추가해준다. 
			} else if(exampleCode.charAt(i)==' ' && openingParenthesisCount==0){
				// buffer is ready to be a subexpression
				if(!strBuffer.isEmpty()) {
					sexpressions.add(strBuffer);
					strBuffer = ""; // Ready to start a new buffer
				}
				continue;
			} else {
				if(exampleCode.charAt(i)=='{' && openingParenthesisCount==0){
					openingParenthesisCount++;
					// Ready to start a new buffer
					strBuffer = "" + exampleCode.charAt(i);
					continue;
				} else if(exampleCode.charAt(i)=='{') {
					openingParenthesisCount++;
					strBuffer = strBuffer + exampleCode.charAt(i);
					continue;
					// 열린괄호가 두개이상인 경우. 
				} else if(exampleCode.charAt(i)=='}' && openingParenthesisCount>0) {
					openingParenthesisCount--;
					strBuffer = strBuffer + exampleCode.charAt(i); // 닫는 괄호까지 같이 저장. 
					
					//if(openingParenthesisCount == 0) { sexpressions.add(strBuffer); }
					continue; 
				} else if(exampleCode.charAt(i)=='}') {
					// buffer is ready to be a subexpression
					sexpressions.add(strBuffer);
					continue;
				}
			}
			// 그냥 문자들이 버퍼에 저장되는 경우  
			strBuffer = strBuffer + exampleCode.charAt(i);
		}
		
		sexpressions.add(strBuffer);

		return sexpressions;
	}

	public static boolean isNumeric(String str)
	{
		return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}
}
