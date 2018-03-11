package org.intel.dcg.leslie.utils;

import static org.intel.dcg.leslie.utils.MyEdmProviderCustomer.ENTITY_SET_NAME_CUSTOMERS;

import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmLiteralKind;
import org.apache.olingo.odata2.api.edm.EdmProperty;
import org.apache.olingo.odata2.api.edm.EdmSimpleType;
import org.apache.olingo.odata2.api.ep.EntityProvider;
import org.apache.olingo.odata2.api.ep.EntityProviderWriteProperties;
import org.apache.olingo.odata2.api.exception.ODataException;
import org.apache.olingo.odata2.api.exception.ODataNotFoundException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;
import org.apache.olingo.odata2.api.processor.ODataResponse;
import org.apache.olingo.odata2.api.processor.ODataSingleProcessor;
import org.apache.olingo.odata2.api.uri.KeyPredicate;
import org.apache.olingo.odata2.api.uri.info.GetEntitySetUriInfo;
import org.apache.olingo.odata2.api.uri.info.GetEntityUriInfo;
import org.intel.dcg.leslie.dao.CustomerRepository;
import org.intel.dcg.leslie.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyODataSingleProcessorCustomerJPA extends ODataSingleProcessor {

    @Autowired
    private CustomerRepository dataStore;

//    public MyODataSingleProcessorCustomerJPA(){
//        dataStore = (CustomerRepository)SpringContextsUtil.getBean("CustomerRepository");
//    }

    @Override
    public ODataResponse readEntitySet(GetEntitySetUriInfo uriInfo, String contentType) throws ODataException {

        EdmEntitySet entitySet;
        if (uriInfo.getNavigationSegments().size() == 0) {
            entitySet = uriInfo.getStartEntitySet();
            System.out.println("hhhhh:");
            if (ENTITY_SET_NAME_CUSTOMERS.equals(entitySet.getName())) {
                List<Map<String, Object>> customers = new ArrayList<Map<String, Object>>();
                for (Customer customer : dataStore.findAll()) {
                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("id", customer.getId());
                    data.put("name", customer.getName());
                    customers.add(data);
                }
                System.out.println("My customer data is:");
                System.out.println(customers);
                return EntityProvider.writeFeed(contentType, entitySet, customers, EntityProviderWriteProperties.serviceRoot(getContext().getPathInfo().getServiceRoot()).build());
            }
            throw new ODataNotFoundException(ODataNotFoundException.ENTITY);

        } else if (uriInfo.getNavigationSegments().size() == 1) {
            System.out.println("uriInfo.getNavigationSegments().size() == 1");
            throw new ODataNotFoundException(ODataNotFoundException.ENTITY);
        }

        throw new ODataNotImplementedException();

    }

    @Override
    public ODataResponse readEntity(GetEntityUriInfo uriInfo, String contentType) throws ODataException {

        if (uriInfo.getNavigationSegments().size() == 0) {
            EdmEntitySet entitySet = uriInfo.getStartEntitySet();

            if (ENTITY_SET_NAME_CUSTOMERS.equals(entitySet.getName())) {
                int id = getKeyValue(uriInfo.getKeyPredicates().get(0));
                Map<String, Object> myData = new HashMap<String, Object>();
                Customer customer = dataStore.findOne(new Long(id));
                myData.put("id", customer.getId());
                myData.put("name", customer.getName());
                Map<String, Object> data = myData;

                if (data != null) {
                    URI serviceRoot = getContext().getPathInfo().getServiceRoot();
                    EntityProviderWriteProperties.ODataEntityProviderPropertiesBuilder propertiesBuilder = EntityProviderWriteProperties.serviceRoot(serviceRoot);

                    return EntityProvider.writeEntry(contentType, entitySet, data, propertiesBuilder.build());
                }
            }
            throw new ODataNotFoundException(ODataNotFoundException.ENTITY);

        } else if (uriInfo.getNavigationSegments().size() == 1) {
            System.out.println("uriInfo.getNavigationSegments().size() == 1");
            throw new ODataNotFoundException(ODataNotFoundException.ENTITY);
        }

        throw new ODataNotImplementedException();
    }


    private int getKeyValue(KeyPredicate key) throws ODataException {
        EdmProperty property = key.getProperty();
        EdmSimpleType type = (EdmSimpleType) property.getType();
        return type.valueOfString(key.getLiteral(), EdmLiteralKind.DEFAULT, property.getFacets(), Integer.class);
    }

}
