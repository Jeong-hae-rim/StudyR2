package rtest;

import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Service;

@Service
public class OnePersonService {

	public String returnOneperson1(String path, String gu)  {
		RConnection r = null;
		String retStr = "";
		try {
			r = new RConnection(); 
			r.eval("setwd('c:/haerim/Rstudy')");
			r.eval("library(dplyr)");
			r.eval("library(leaflet)");
			r.eval("library(Kormaps)");
			r.eval("library(htmlwidgets)");
			r.eval("rm(list=ls())");
			r.eval("DONG<-read.csv('data/one.csv')");
			r.eval("Encoding(names(korpopmap2))<-'UTF-8'");
			r.eval("Encoding(korpopmap2@data$name)<-'UTF-8'");
			r.eval("Encoding(korpopmap2@data$행정구역별_읍면동)<-'UTF-8'");
			r.eval("Encoding(names(korpopmap3))<-'UTF-8'");
			r.eval("Encoding(korpopmap3@data$name)<-'UTF-8'");
			r.eval("Encoding(korpopmap3@data$행정구역별_읍면동)<-'UTF-8'");
			r.eval("k3 <- korpopmap3");
			r.eval("guname <- '"+gu+"'");
			//System.out.println(r.eval("guname").asString());
			r.eval("gu <- iconv(guname, from='CP949', to='UTF-8')");
			r.eval("gucodename <- korpopmap2@data[,c('name', 'code.data')]");
			r.eval("gucode <- gucodename[gucodename$name == guname, 'code.data']");
			r.eval("pattern <- paste0('^', gucode)");
			r.eval("k3@data <- korpopmap3@data[grep(pattern, korpopmap3@data$code.data),]");
			r.eval("k3@polygons <- korpopmap3@polygons[grep(pattern, korpopmap3@data$code.data)]");
			r.eval("k3@data$name<-gsub('·','',k3@data$name)");
			r.eval("colnames(DONG)<-c('구별','name','일인가구')");
			r.eval("dong <- DONG %>%filter(구별==guname)");
			r.eval("k3@data<-merge(k3@data,dong,by.x='name',sort=FALSE)");
			r.eval("mypalette <- colorNumeric(palette ='Set3' , domain = k3@data$'일인가구')");
			r.eval("mypopup <- paste0(k3@data$name,'<br> 1인가구: ',k3@data$일인가구)");
			r.eval("onemap <- NULL");
			String baseMap = "onemap<-leaflet(k3) %>% " + "addTiles()%>%"
					+ "setView(lat=37.52711, lng=126.987517, zoom=11) %>%"
					+ "addPolygons(stroke =FALSE,"
					+ "fillOpacity = .7,"
					+ "popup = mypopup,"
					+ "color = ~mypalette(k3@data$일인가구)) %>%"
					+ "addPolygons(stroke =FALSE,"
		            + "fillOpacity = .7,"
		            + "popup = mypopup,"
		            + "color = ~mypalette(k3@data$일인가구)) %>%" 
					+ "addLegend( values = ~k3@data$일인가구,"
					+ "pal = mypalette ,"
					+ " title = '인구수',"
					+ "opacity = 1)";
			r.eval(baseMap);
			
             String fileName = path + "/index.html";
			
			r.eval("saveWidget(onemap,'"+fileName+"',  selfcontained = F)");	        
			retStr = r.eval("'index.html'").asString();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			r.close(); 
		}
		return retStr;
	}	
}
