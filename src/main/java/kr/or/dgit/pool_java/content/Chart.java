package kr.or.dgit.pool_java.content;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

import kr.or.dgit.pool_java.dao.SalesDao;
import kr.or.dgit.pool_java.dto.Sales;
import kr.or.dgit.pool_java.service.SalesService;

public class Chart extends JPanel {
	private SalesDao sDao;
	/**
	 * Create the panel.
	 */
	public Chart() {
		this.sDao = SalesService.getInstance();
		final CategoryDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
		chart.getTitle().setFont(new Font("돋움", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("돋움", Font.PLAIN, 12));
		final ChartPanel chartPanel = new ChartPanel(chart);
		add(chartPanel);
	}

	public CategoryDataset createDataset() {

	

		
		// create the dataset...
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Date date = new Date();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy");
		String year= sd.format(date);
		String[] arr = {year , String.valueOf(Integer.parseInt(year)-1)};
		String[] arr1 = {"올해매출","작년매출"};
		String day="";
		
		for(int j=0;j<2;j++) {
			for(int i =1;i<13;i++){
				
				if(i<10) {
					day = "0"+i;
				}else {
					day = i+"";
				}
				List<Sales> lists = null;
				
				lists = sDao.selectDate(arr[j]+"-"+day); 
				if(lists.size()==0) {
					dataset.addValue(0, arr1[j], i+"월");
				}else {
					dataset.addValue(sDao.selectSum(arr[j]+"-"+day)/10000, arr1[j], i+"월");
				}
				
			}
		
		}



		return dataset;

	}

	private JFreeChart createChart(final CategoryDataset dataset) {

		// create the chart...
		final JFreeChart chart = ChartFactory.createLineChart("", // chart title
				"", // domain axis label
				"", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips
				false // urls
		);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		// final StandardLegend legend = (StandardLegend) chart.getLegend();
		// legend.setDisplaySeriesShapes(true);
		// legend.setShapeScaleX(1.5);
		// legend.setShapeScaleY(1.5);
		// legend.setDisplaySeriesLines(true);

		chart.setBackgroundPaint(Color.white);

		final CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);
		 Font font = plot.getDomainAxis().getLabelFont();
		  // X축 라벨
		  plot.getDomainAxis().setLabelFont(new Font("돋움", font.getStyle(), font.getSize()));
		  // X축 도메인
		  plot.getDomainAxis().setTickLabelFont(new Font("돋움", font.getStyle(), 12));
		  
		  font = plot.getRangeAxis().getLabelFont();
		  // Y축 라벨
		  plot.getRangeAxis().setLabelFont(new Font("돋움", font.getStyle(), font.getSize()));
		  // Y축 범위
		  plot.getRangeAxis().setTickLabelFont(new Font("돋움", font.getStyle(), 12));

		// customise the range axis...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);


		Font f = new Font("Gulim", Font.BOLD, 12);

        Font axisF = new Font("Gulim", Font.PLAIN, 14);

        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();


        final ItemLabelPosition p_below = new ItemLabelPosition(

                ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT

                );

        	

		final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
	
		renderer.setBaseItemLabelGenerator(generator);

		renderer.setBaseItemLabelsVisible(true);

		renderer.setBaseShapesVisible(true);
        renderer.setDrawOutlines(true);

        renderer.setUseFillPaint(true);

        renderer.setBaseFillPaint(Color.WHITE);

        renderer.setBaseItemLabelFont(f);

        renderer.setBasePositiveItemLabelPosition(p_below);

        renderer.setSeriesPaint(0,new Color(22,121,220));

        renderer.setSeriesStroke(0,new BasicStroke(

                                               2.0f,

                                               BasicStroke.CAP_ROUND,

                                               BasicStroke.JOIN_ROUND,

                                               3.0f));


		return chart;
	}



}
