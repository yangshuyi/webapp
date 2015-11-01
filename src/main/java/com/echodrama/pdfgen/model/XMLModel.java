package com.echodrama.pdfgen.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.ByteArrayOutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 2/28/14
 * Time: 3:30 PM
 * To change this template use File | Settings | File Templates.
 */

@XmlRootElement(name = "XMLModel")
public class XMLModel {

    private String title = null;

    @XmlElement(name = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String toXMLString() {
        JAXBContext context;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        try {
            context = JAXBContext.newInstance(this.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(this, outStream);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return outStream.toString();
    }

}
