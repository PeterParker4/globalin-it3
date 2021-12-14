package chart;

import java.io.IOException;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.ChartUtils;

//@WebServlet("/PieChartServlet")
public class PieChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 데이터를 생성함
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("Son-OGong", 63.2);
		dataset.setValue("Jer-PalGae", 37.9);
		dataset.setValue("Sa-OJung", 29.5);
		
		// 2. 차트를 만듬
		JFreeChart chart = ChartFactory.createPieChart(
				"Energy Power", dataset, true, true, false);
		
		// 3. 차트 출력
		ServletOutputStream out = response.getOutputStream();
		ChartUtils.writeChartAsPNG(out, chart, 400, 400);
		
	}
}
