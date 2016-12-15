package rent.constants;

/**
 * Constants with logs.
 *
 * @author zagart
 */
public interface LoggerConstants {
    String ALL_ENTITIES_RETRIEVED = "All %s entities pulled from model(%d).";
    String ENTITY_DELETED = "%s entity deleted from model by id = %d.";
    String ENTITY_DELETED_BY_ID = "%s entity with id = %d deleted from model.";
    String ENTITY_LIST_BY_QUERY = "%s entity(s) pulled from model by query(%d).";
    String ENTITY_RETRIEVED_BY_ID = "%s entity pulled from model by id = %d.";
    String ENTITY_SAVED = "%s entity saved with id = %d.";
    String ENTITY_UPDATED = "%s entity with id = %d updated.";
    String PK_SET_BY_QUERY = "%s primary key(s) pulled from model by query(%d).";
    String QUERY_EXECUTED = "%s query executed. %d element(s) affected.";
}
