package edu.handong.csee.plt.F1WAE;

public class Idtf extends AST {
	String idtf = "";
	
	public Idtf(String idtf) {
		this.idtf = idtf;
	}
	public String getIdtf() {
		return idtf;
	}
	public String getASTCode() {
		return "(id '" + idtf + ")";
	}
}
