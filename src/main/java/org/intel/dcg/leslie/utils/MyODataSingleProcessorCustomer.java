package org.intel.dcg.leslie.utils;

import static org.intel.dcg.leslie.utils.MyEdmProvider.ENTITY_SET_NAME_CARS;
import static org.intel.dcg.leslie.utils.MyEdmProvider.ENTITY_SET_NAME_MANUFACTURERS;
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

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyODataSingleProcessorCustomer extends ODataSingleProcessor {
    private final DataStore2 dataStore;

    public MyODataSingleProcessorCustomer() {
        dataStore = new DataStore2();
    }

    @Override
    public ODataResponse readEntitySet(GetEntitySetUriInfo uriInfo, String contentType) throws ODataException {

        EdmEntitySet entitySet;
        if (uriInfo.getNavigationSegments().size() == 0) {
            entitySet = uriInfo.getStartEntitySet();

            if (ENTITY_SET_NAME_CUSTOMERS.equals(entitySet.getName())) {
                return EntityProvider.writeFeed(contentType, entitySet, dataStore.getCustomers(), EntityProviderWriteProperties.serviceRoot(getContext().getPathInfo().getServiceRoot()).build());
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
                Map<String, Object> data = dataStore.getCustomer(id);

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
