package sasha.forlearning;

import java.io.File;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
public class ReadExample
{
    public static void main(String[] args)
    {
        XYSeries series = new XYSeries("Magnitude(time)");
        double buffer[];
        try
        {
            // Open the wav file specified as the first argument
            //String path = ReadExample.class.getClassLoader().getResource("Chopin.wav").getPath();//path from src
            //WavFile wavFile = WavFile.openWavFile(new File(path));
            WavFile wavFile = WavFile.openWavFile(new File("C:\\Users\\1\\IdeaProjects\\myaudio\\src\\Chopin.wav"));
            //WavFile wavFile = WavFile.openWavFile(new File(args[0]));

            // Display information about the wav file
            wavFile.display();

            // Get the number of audio channels in the wav file
            int numChannels = wavFile.getNumChannels();

            // Create a buffer of 100 frames
            buffer = new double[100 * numChannels];

            int framesRead;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            int i=0;
            do
            {
                // Read frames into buffer
                framesRead = wavFile.readFrames(buffer, 100);

                // Loop through frames and look for minimum and maximum value
                for (int s=0 ; s<framesRead * numChannels ; s++)
                {
                    if (buffer[s] > max) max = buffer[s];
                    if (buffer[s] < min) min = buffer[s];
                    if (i<1000000){//2000 is OK, 10000 looks awesome, 10000000 is too much but still shows graph
                        series.add(i,buffer[s]);
                        i++;
                    }
                }
            }
            while (framesRead != 0);



            // Close the wavFile
            wavFile.close();

            // Output the minimum and maximum value
            System.out.printf("Min: %f, Max: %f\n", min, max);

            XYDataset xyDataset = new XYSeriesCollection(series);
            JFreeChart chart = ChartFactory.createXYLineChart("y=magnitude(time)","time","magnitude",
                    xyDataset,PlotOrientation.VERTICAL,true,true,true);
            JFrame frame = new JFrame("just a try");
            System.out.println("I am here!");
            frame.getContentPane().add(new ChartPanel(chart));
            frame.setSize(800,600);
            frame.setVisible(true);
        }
        catch (Exception e)
        {
            System.err.println(e);
        }





    }
}
