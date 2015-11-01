package com.echodrama.pdfgen;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 2/28/14
 * Time: 3:22 PM
 * To change this template use File | Settings | File Templates.
 */
public enum PaperEnum {
    A4(297, 210), Letter(279.4, 215.9), Custom(0, 0);

    private double shortSide;
    private double longSide;

    private PaperEnum(double longSide, double shortSide) {
        this.longSide = longSide;
        this.shortSide = shortSide;
    }

    public double getDefaultWidth(PageOrientationEnum orientation) {
        if (orientation == null)
            return 0;

        switch (orientation) {
            case Portrait:
                return this.shortSide;
            case Landscape:
                return this.longSide;
            default:
                return this.longSide;
        }
    }

    public double getDefaultHeight(PageOrientationEnum orientation) {
        if (orientation == null)
            return 0;

        switch (orientation) {
            case Portrait:
                return this.longSide;
            case Landscape:
                return this.shortSide;
            default:
                return this.shortSide;
        }
    }
}