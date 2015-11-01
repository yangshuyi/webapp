package com.echodrama.pdfgen;

import com.echodrama.model.Department;
import com.echodrama.model.Employee;
import com.echodrama.model.JobTitle;
import com.echodrama.pdfgen.model.EmployeeListXMLModel;
import com.echodrama.pdfgen.model.XMLModel;
import org.apache.commons.io.FileUtils;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: yangsh
 * Date: 2/28/14
 * Time: 10:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class PDFGenerator {

    public static void main(String[] args) throws Exception {
        PDFGenerator pdfGenerator = new PDFGenerator();
        File file = new File("X:\\workstation\\java\\workspace\\EasyUI\\src\\main\\webapp\\folder\\pdf\\template\\EmployeeList.xsl");
        EmployeeListXMLModel xmlModel = new EmployeeListXMLModel();
        xmlModel.setTitle("Employee List");

        List<Employee> employeeList = new ArrayList<Employee>();
        for (int i = 0; i < 2; i++) {
            Employee employee = new Employee();
            employee.setName("yang shuyi");
            employee.setCode("1001");
            JobTitle jobTitle = new JobTitle();
            jobTitle.setName("developer");
            employee.setJobTitle(jobTitle);
            Department department = new Department();
            department.setName("dev center");
            employee.setDepartment(department);

            employeeList.add(employee);
        }

        xmlModel.setEmployeeList(employeeList);

        pdfGenerator.generatePDF(file, xmlModel, "d:\\", "test.pdf");
    }

    public void setPrinter(Transformer transformer) throws Exception {
        //Page size and margins
//        transformer.setParameter("wd-page-width", printSetting.getWidth() + ReportSizeUnit);
//        transformer.setParameter("wd-page-height", printSetting.getHeight() + ReportSizeUnit);
//        transformer.setParameter("wd-page-margin-left", printSetting.getMarginLeft() + ReportSizeUnit);
//        transformer.setParameter("wd-page-margin-right", printSetting.getMarginRight() + ReportSizeUnit);
//        transformer.setParameter("wd-page-margin-top", printSetting.getMarginTop() + ReportSizeUnit);
//        transformer.setParameter("wd-page-margin-bottom", printSetting.getMarginBottom() + ReportSizeUnit);
    }

    public File generatePDF(File xslTemplateFile, XMLModel xmlModel, String outputPDFFolderPath, String outputPDFFileName) throws Exception {
        if (xslTemplateFile == null || !xslTemplateFile.exists()) {
            return null;
        }

        String xslTemplateContent = FileUtils.readFileToString(xslTemplateFile);
        return generatePDF(xslTemplateContent, xmlModel, outputPDFFolderPath, outputPDFFileName);
    }

    public File generatePDF(String xslTemplateContent, XMLModel xmlModel, String outputPDFFolderPath, String outputPDFFileName) throws Exception {
        //initialize
        File outputPDFFolder = new File(outputPDFFolderPath);
        if (!outputPDFFolder.exists()) {
            outputPDFFolder.mkdirs();
        }
        File outputPDFFile = new File(outputPDFFolderPath + File.separatorChar + outputPDFFileName);
        outputPDFFile.createNewFile();

        FileOutputStream outputStream = null;

        outputStream = new FileOutputStream(outputPDFFile);

        FopFactory fopFactory = FopFactory.newInstance();
        // Construct fop with desired output format
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, outputStream);
        // Setup JAXP using identity transformer
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new StringReader(xslTemplateContent));
        // identity transformer
        Transformer transformer = factory.newTransformer(xslt);
        // Setup input stream
        StreamSource streamSource = new StreamSource(new StringReader(xmlModel.toXMLString()));
        // Resulting SAX events (the generated FO) must be piped through to FOP
        SAXResult saxResult = new SAXResult(fop.getDefaultHandler());
        // Start XSLT transformation and FOP processing
        transformer.transform(streamSource, saxResult);

        return outputPDFFile;
    }
}
