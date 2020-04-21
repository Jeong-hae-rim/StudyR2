package rjavaapp;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class JavaRLab1 {
	
	public static void getString() throws RserveException, REXPMismatchException {
		
		RConnection rc = new RConnection(); 
		rc.eval("library(KoNLP)");
	    rc.eval("word <- readLines('C:/haerim/Rstudy/book/hotel.txt')");
		rc.eval("word2 <- extractNoun(word)");
		rc.eval("word3 <- unlist(word2)");
		rc.eval("word4 <- gsub('[[:punct:]]','',word3)");
		rc.eval("word4 <- unlist(strsplit(word4, split = '//s+'))");
		rc.eval("table_word <- table(word4)");
		rc.eval("final_word <- sort(table_word, decreasing = T)");
		rc.eval("final_word2 <- names(final_word)");
		REXP x = rc.eval("head(final_word2, 10)");
		String[] d = x.asStrings();
		System.out.print(d[0]);
		for (int i = 0; i < d.length; i++) {
			System.out.print(", " + d[i]);
		}
		rc.close();
	}

	public static void main(String[] args) throws REXPMismatchException, REngineException {
		System.out.print("R 이 보내온 최빈 명사들 :");
		JavaRLab1.getString();
	}

}
