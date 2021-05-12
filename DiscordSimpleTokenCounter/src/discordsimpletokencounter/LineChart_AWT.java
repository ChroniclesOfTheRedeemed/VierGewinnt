package discordsimpletokencounter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JDialog;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChart_AWT extends JDialog {

    public LineChart_AWT(ArrayList<data> s) {
        //  super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createBarChart(
                "Ahri Mains",
                "Week", "uwu count",
                createDataset(s),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
        pack();
        RefineryUtilities.centerFrameOnScreen(this);
        setTitle("Breaking News");
        setVisible(true);
    }
    SimpleDateFormat myD = new SimpleDateFormat("MMM-dd", Locale.ENGLISH);
    SimpleDateFormat myDs = new SimpleDateFormat("dd-MMM-yy h:m a", Locale.ENGLISH);
boolean first = true;
    private DefaultCategoryDataset createDataset(ArrayList<data> s) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int count = 0;
        String pref = "";
        for (data d : s) {
            
            String f = myD.format(d.date);
            if (first) {
                f = "Jan-02";
                first = false;
            }
            if(f.equals(pref)){
                System.out.println("reeeee" + d.date.toString());
                
            }
            pref = f;

            dataset.addValue(d.count, "general discussion", f);
            count += d.count;
        }
        System.out.println(count);
        return dataset;
    }

}
