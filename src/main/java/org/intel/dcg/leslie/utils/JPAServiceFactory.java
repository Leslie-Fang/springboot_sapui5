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

//    public static final String DEFAULT_ENTITY_UNIT_NAME = "Model";
//    public static final String ENTITY_MANAGER_FACTORY_ID = "entityManagerFactory";

    @Override
    public ODataService createService(ODataContext ctx) throws ODataException {

        EdmProvider edmProvider = new MyEdmProvider();
        ODataSingleProcessor singleProcessor = new MyODataSingleProcessor();

        return createODataSingleProcessorService(edmProvider, singleProcessor);
    }

//    @Override
//    public ODataJPAContext initializeODataJPAContext() throws ODataJPARuntimeException {
//        ODataJPAContext oDataJPAContext = this.getODataJPAContext();
//        EntityManagerFactory factory = (EntityManagerFactory) SpringContextsUtil.getBean(ENTITY_MANAGER_FACTORY_ID);
//
//        oDataJPAContext.setEntityManagerFactory(factory);
//        oDataJPAContext.setPersistenceUnitName(DEFAULT_ENTITY_UNIT_NAME);
////        oDataJPAContext.setJPAEdmExtension(new JPAEdmExtension());
//        ODataContextUtil.setODataContext(oDataJPAContext.getODataContext());
//
//        return oDataJPAContext;
//    }
}


