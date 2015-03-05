package com.suntossh.pdf;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.io.FileOutputStream;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.itextpdf.awt.DefaultFontMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class PieChartDemo {

	public static JFreeChart generatePieChart() {
		DefaultPieDataset dataSet = new DefaultPieDataset();
		dataSet.setValue("China", 19.64);
		dataSet.setValue("India", 17.3);
		dataSet.setValue("United States", 4.54);
		dataSet.setValue("Indonesia", 3.4);
		dataSet.setValue("Brazil", 2.83);
		dataSet.setValue("Pakistan", 2.48);
		dataSet.setValue("Bangladesh", 2.38);

		JFreeChart chart = ChartFactory.createPieChart(
				"World Population by countries", dataSet, true, true, false);

		return chart;
	}

	public static JFreeChart generateBarChart() {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		dataSet.setValue(791, "Population", "1750 AD");
		dataSet.setValue(978, "Population", "1800 AD");
		dataSet.setValue(1262, "Population", "1850 AD");
		dataSet.setValue(1650, "Population", "1900 AD");
		dataSet.setValue(2519, "Population", "1950 AD");
		dataSet.setValue(6070, "Population", "2000 AD");

		JFreeChart chart = ChartFactory.createBarChart(
				"World Population growth", "Year", "Population in millions",
				dataSet, PlotOrientation.VERTICAL, false, true, false);

		return chart;
	}
	public static JFreeChart generateLineChart() {

	DefaultCategoryDataset line_chart_dataset = new DefaultCategoryDataset();
    line_chart_dataset.addValue( 15 , "src" , "Table1" );
    line_chart_dataset.addValue( 30 , "src" , "Table2" );
    line_chart_dataset.addValue( 60 , "src" , "Table3" );
    line_chart_dataset.addValue( 120 , "src" , "Table4" );
    line_chart_dataset.addValue( 240 , "src" , "Table5" ); 
    line_chart_dataset.addValue( 300 , "src" , "Table6" );
    
    line_chart_dataset.addValue( 20 , "tgt" , "Table1" );
    line_chart_dataset.addValue( 35 , "tgt" , "Table2" );
    line_chart_dataset.addValue( 65 , "tgt" , "Table3" );
    line_chart_dataset.addValue( 220 , "tgt" , "Table4" );
    line_chart_dataset.addValue( 290 , "tgt" , "Table5" ); 
    line_chart_dataset.addValue( 400 , "tgt" , "Table6" );
    
    JFreeChart lineChartObject = ChartFactory.createLineChart(
       "Schools Vs Years", //Header
       "Year",//X Axis Name 
       "Schools Count",//Y Axis Name
       line_chart_dataset,
       PlotOrientation.VERTICAL,
       true,true,false);
    return lineChartObject;
	}
	
	public static void main(String[] args) {
//		writeChartToPDF(generateBarChart(), 500, 400, "barchart.pdf");
//		writeChartToPDF(generatePieChart(), 500, 400, "piechart.pdf");
		writeChartToPDF(generateLineChart(), 500, 400, "linechart"+System.currentTimeMillis()+".pdf");
	}

	public static void writeChartToPDF(JFreeChart chart, int width, int height,
			String fileName) {
		PdfWriter writer = null;

		Document document = new Document();

		try {
			writer = PdfWriter.getInstance(document, new FileOutputStream(
					fileName));
			document.open();
			PdfContentByte contentByte = writer.getDirectContent();
			PdfTemplate template = contentByte.createTemplate(width, height);
			Graphics2D graphics2d = template.createGraphics(width, height,
					new DefaultFontMapper());
			Rectangle2D rectangle2d = new Rectangle2D.Double(0, 0, width,
					height);

			chart.draw(graphics2d, rectangle2d);

			graphics2d.dispose();
			contentByte.addTemplate(template, 0, 0);

		} catch (Exception e) {
			e.printStackTrace();
		}
		document.close();
	}

}