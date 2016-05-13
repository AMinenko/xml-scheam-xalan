package com.marks.pos.backoffice.core;

import com.marks.pos.backoffice.core.css.dto.CssTransactionDto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@SuppressWarnings("squid:S00112")
public class SchemaGen {

    public static final String DEFAULT_SCHEMA_NAME = "/schema.xsd";
    public static final String EXCEPTION_DURING_SCHEMA_GENERATION = "Exception during schema generation '%s'";
    public static final String HTTP_XML_APACHE_ORG_XSLT_INDENT_AMOUNT = "{http://xml.apache.org/xslt}indent-amount";

    private SchemaGen() {
    }

    public static void main(String[] args) throws IOException {
        String targetPath = args.length == 0 ? DEFAULT_SCHEMA_NAME : (args[0] + DEFAULT_SCHEMA_NAME);
        generateXsd(targetPath);
    }

    public static void generateXsd(String targetPath) throws IOException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(CssTransactionDto.class);
            List<DOMResult> results = new ArrayList<>();
            SchemaOutputResolver schemaOutputResolver = new CssSchemaOutputResolver(results);
            jaxbContext.generateSchema(schemaOutputResolver);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            Result output = new StreamResult(new File(targetPath));
            Source input = new DOMSource(results.get(0).getNode());
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(HTTP_XML_APACHE_ORG_XSLT_INDENT_AMOUNT, "2");
            transformer.transform(input, output);

        } catch (JAXBException | TransformerException ex) {
            throw new RuntimeException(format(EXCEPTION_DURING_SCHEMA_GENERATION, ex));
        }
    }

    private static class CssSchemaOutputResolver extends SchemaOutputResolver {

        private List<DOMResult> results;

        CssSchemaOutputResolver(List<DOMResult> results) {
            this.results = results;
        }

        @Override
        public DOMResult createOutput(String namespaceUri, String suggestedFileName) {
            DOMResult result = new DOMResult();
            result.setSystemId("");
            results.add(result);
            return result;
        }
    }

}

