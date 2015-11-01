package com.echodrama.pdfgen;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 2/28/14
 * Time: 3:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrinterSetting {
    private PageOrientationEnum orientation;
    private PaperEnum paper;
    private double width;
    private double height;
    private double marginTop;
    private double marginBottom;
    private double marginLeft;
    private double marginRight;

    public PageOrientationEnum getOrientation() {
        return orientation;
    }

    public void setOrientation(PageOrientationEnum orientation) {
        this.orientation = orientation;
    }

    public PaperEnum getPaper() {
        return paper;
    }

    public void setPaper(PaperEnum paper) {
        this.paper = paper;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(double marginTop) {
        this.marginTop = marginTop;
    }

    public double getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(double marginBottom) {
        this.marginBottom = marginBottom;
    }

    public double getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(double marginLeft) {
        this.marginLeft = marginLeft;
    }

    public double getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(double marginRight) {
        this.marginRight = marginRight;
    }
}
