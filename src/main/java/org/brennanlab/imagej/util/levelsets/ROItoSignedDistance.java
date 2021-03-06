package org.brennanlab.imagej.util.levelsets;

import ij.ImagePlus;
import ij.gui.Overlay;
import ij.gui.Roi;
import ij.plugin.filter.PlugInFilter;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;

public class ROItoSignedDistance implements PlugInFilter {
    String arg;
    ImagePlus imp;
    ImplicitShape2D rp;

    /**
     * Describe <code>run</code> method here.
     *
     * @param ip an <code>ImageProcessor</code> value
     */
    public void run(ImageProcessor ip) {

        // Get the current ROI from the Image and output a signed distance
        // function map
        Roi r = imp.getRoi();
        imp.unlock();
        ImplicitShape2D rp;
        rp = new ImplicitShape2D(r, ip.getWidth(),ip.getHeight());

        ImagePlus impresult = rp.getLSImagePlus();
        FloatProcessor ip3 = rp.getLSFloatProcessor();
        if (r != null) {
            Overlay ov = new Overlay(r);
            impresult.setOverlay(ov);

        }
        impresult.updateAndDraw();
        impresult.show();
    }

    public int setup(String arg, ImagePlus imp) {
        this.imp = imp;
        this.arg = arg;
        return DOES_ALL + NO_CHANGES;
    }

}