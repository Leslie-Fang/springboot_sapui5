package org.intel.dcg.leslie.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.olingo.odata2.jpa.processor.api.ODataJPAContext;
import org.apache.olingo.odata2.jpa.processor.api.ODataJPAServiceFactory;
import org.apache.olingo.odata2.jpa.processor.api.exception.ODataJPARuntimeException;
import org.apache.olingo.odata2.api.ODataService;
import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.apache.olingo.odata2.api.edm.provider.EdmProvider;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.processor.ODataContext;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;

public class JPAServiceFactory extends ODataServiceFactory {

    @Override
    public ODataService createService(ODataContext ctx) throws ODataException {

//        EdmProvider edmProvider = new MyEdmProvider();
//        ODataSingleProcessor singleProcessor = new MyODataSingleProcessor();
        EdmProvider edmProvider = new MyEdmProviderCustomer();
        ODataSingleProcessor singleProcessor = new MyODataSingleProcessorCustomer();
        return createODataSingleProcessorService(edmProvider, singleProcessor);
    }

}


