package rjavaapp;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.RList;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

public class JavaRLab2 {
	
        public static void getDataFrame() throws RserveException, REXPMismatchException {
		
		RConnection rc = new RConnection();
		rc.eval("library(KoNLP)");
	    rc.eval("word <- readLines('C:/haerim/Rstudy/book/hotel.txt')");
		rc.eval("word2 <- extractNoun(word)");
		rc.eval("word3 <- unlist(word2)");
		rc.eval("word4 <- gsub('[[:punct:]]','',word3)");
		rc.eval("word4 <- unlist(strsplit(word4, split = '//s+'))");
		rc.eval("table_word <- table(word4)");
		rc.eval("final_word <- sort(table_word, decreasing = T)");
		rc.eval("final_word2 <- as.data.frame(final_word)");
		REXP x = rc.eval("head(final_word2, 10)");
		RList list = x.asList();
		int v_size = list.size();
		int d_length = list.at(0).length();
		
		int arrayRows = v_size;
		int arrayCols = d_length;
		
		String[][] s = new String[arrayRows][]; // 데이터프레임의 변수 갯수로 행의 크기를 정한다. 2차원 배열에 담는 것.

		for (int i = 0; i < arrayRows; i++) {
			s[i] = list.at(i).asStrings();
		}
		for (int j = 0; j < arrayCols; j++) {
		for (int i = 0; i < arrayRows; i++) {
		// j와 i의 위치를 바꾸면 j(열)을 i만큼 다 뽑고 내려가는 효과가 발생
	    // i -> j 로 뽑으면 1열 다 뽑고 다시 2열 뽑는 효과가 생김
				System.out.print(s[i][j] + "\t");
			}
		System.out.println();
		}
		rc.close();
	}

        public static void main(String[] args) throws REXPMismatchException, REngineException {
    		System.out.println("R 이 보내온 최빈 명사들 :");
    		JavaRLab2.getDataFrame();
    	}


}
