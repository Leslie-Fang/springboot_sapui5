package org.intel.dcg.leslie.utils;

import org.apache.olingo.odata2.api.edm.EdmMultiplicity;
import org.apache.olingo.odata2.api.edm.EdmSimpleTypeKind;
import org.apache.olingo.odata2.api.edm.FullQualifiedName;
import org.apache.olingo.odata2.api.edm.provider.*;
import org.apache.olingo.odata2.api.exception.ODataException;

import java.util.ArrayList;
import java.util.List;

public class MyEdmProviderCustomer extends EdmProvider {
    static final String ENTITY_SET_NAME_CUSTOMERS = "Customers";
    static final String ENTITY_NAME_CUSTOMER = "Customer";
    private static final String NAMESPACE = "org.apache.olingo.odata2.ODataCustomers";
    private static final FullQualifiedName ENTITY_TYPE_1_1 = new FullQualifiedName(NAMESPACE, ENTITY_NAME_CUSTOMER);
    private static final String ENTITY_CONTAINER = "ODataCustomersEntityContainer";
    private static final String FUNCTION_IMPORT = "NumberOfCustomers";

    @Override
    public List<Schema> getSchemas() throws ODataException {
        List<Schema> schemas = new ArrayList<Schema>();

        Schema schema = new Schema();
        schema.setNamespace(NAMESPACE);

        List<EntityType> entityTypes = new ArrayList<EntityType>();
        entityTypes.add(getEntityType(ENTITY_TYPE_1_1));
        schema.setEntityTypes(entityTypes);

        List<EntityContainer> entityContainers = new ArrayList<EntityContainer>();
        EntityContainer entityContainer = new EntityContainer();
        entityContainer.setName(ENTITY_CONTAINER).setDefaultEntityContainer(true);

        List<EntitySet> entitySets = new ArrayList<EntitySet>();
        entitySets.add(getEntitySet(ENTITY_CONTAINER, ENTITY_SET_NAME_CUSTOMERS));
        entityContainer.setEntitySets(entitySets);

        List<FunctionImport> functionImports = new ArrayList<FunctionImport>();
        functionImports.add(getFunctionImport(ENTITY_CONTAINER, FUNCTION_IMPORT));
        entityContainer.setFunctionImports(functionImports);

        entityContainers.add(entityContainer);
        schema.setEntityContainers(entityContainers);

        schemas.add(schema);

        return schemas;
    }

    @Override
    public EntityType getEntityType(FullQualifiedName edmFQName) throws ODataException {
        if (NAMESPACE.equals(edmFQName.getNamespace())) {
            if (ENTITY_TYPE_1_1.getName().equals(edmFQName.getName())){
                //Properties
                List<Property> properties = new ArrayList<Property>();
                properties.add(new SimpleProperty().setName("Id").setType(EdmSimpleTypeKind.Int32).setFacets(new Facets().setNullable(false)));
                properties.add(new SimpleProperty().setName("Name").setType(EdmSimpleTypeKind.String));

                //Key
                List<PropertyRef> keyProperties = new ArrayList<PropertyRef>();
                keyProperties.add(new PropertyRef().setName("Id"));
                Key key = new Key().setKeys(keyProperties);

                return new EntityType().setName(ENTITY_TYPE_1_1.getName())
                        .setProperties(properties)
                        .setKey(key);
            }

        }
        return null;
    }

    @Override
    public EntitySet getEntitySet(String entityContainer, String name) throws ODataException {
        if (ENTITY_CONTAINER.equals(entityContainer)) {
            if (ENTITY_SET_NAME_CUSTOMERS.equals(name)) {
                return new EntitySet().setName(name).setEntityType(ENTITY_TYPE_1_1);
            }
        }
        return null;
    }

    @Override
    public FunctionImport getFunctionImport(String entityContainer, String name) throws ODataException {
        if (ENTITY_CONTAINER.equals(entityContainer)) {
            if (FUNCTION_IMPORT.equals(name)) {
                return new FunctionImport().setName(name)
                        .setReturnType(new ReturnType().setTypeName(ENTITY_TYPE_1_1).setMultiplicity(EdmMultiplicity.MANY))
                        .setHttpMethod("GET");
            }
        }
        return null;
    }

    @Override
    public EntityContainerInfo getEntityContainerInfo(String name) throws ODataException {
        if (name == null || "ODataCustomersEntityContainer".equals(name)) {
            return new EntityContainerInfo().setName("ODataCustomersEntityContainer").setDefaultEntityContainer(true);
        }
        return null;
    }
}
