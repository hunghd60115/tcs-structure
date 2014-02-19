package fsoft.fsu11.bu11.tcs.core.daos.log;

import fsoft.fsu11.bu11.tcs.core.daos.GenericJpaDAO;
import fsoft.fsu11.bu11.tcs.core.entities.LogEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/17/14
 * Time: 2:15 PM
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class LogDAO extends GenericJpaDAO<LogEntity> {

//    public static final String FROM_LOG_PREFIX =
//            "FROM " + LogEntity.class.getName();
//
//    private static final String QUERY_FIND_BY_ID =
//            FROM_LOG_PREFIX + " WHERE id = :id";
//
//    public LogEntity findById(Integer id){
//        Query query = entityManager.createQuery(QUERY_FIND_BY_ID);
//        query.setParameter("id", id);
//        LogEntity logEntity = executeSingle(query);
//        return logEntity;
//    }

}
