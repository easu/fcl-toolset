package wit.fcl.filehash;

import javax.swing.SwingUtilities;
import javax.swing.plaf.FontUIResource;

import com.alee.laf.WebLookAndFeel;

public class App 
{
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater ( new Runnable ()
        {
            public void run ()
            {
            	WebLookAndFeel.globalControlFont  = new FontUIResource("ËÎÌו",0,12);
//            	WebLookAndFeel.globalTooltipFont
//            	WebLookAndFeel.globalAlertFont
//            	WebLookAndFeel.globalMenuFont
//            	WebLookAndFeel.globalAcceleratorFont
//            	WebLookAndFeel.globalTitleFont
            	WebLookAndFeel.globalTextFont = new FontUIResource("ËÎÌו",0,12);
                WebLookAndFeel.install ();

                MainFrame frame = new MainFrame();
                frame.show();

            }
        });
    }
}
