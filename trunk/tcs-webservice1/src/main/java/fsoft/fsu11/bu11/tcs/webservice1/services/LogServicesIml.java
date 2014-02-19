package fsoft.fsu11.bu11.tcs.webservice1.services;

import fsoft.fsu11.bu11.tcs.commons.dtos.Log;
import fsoft.fsu11.bu11.tcs.commons.dtos.Logs;
import fsoft.fsu11.bu11.tcs.commons.exceptions.BadRequestException;
import fsoft.fsu11.bu11.tcs.commons.exceptions.InternalErrorException;
import fsoft.fsu11.bu11.tcs.commons.services.AbstractTcsBackendService;
import fsoft.fsu11.bu11.tcs.core.entities.LogEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/17/14
 * Time: 2:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogServicesIml extends AbstractTcsBackendService implements ILogServices {

    private Logger LOG = LoggerFactory.getLogger(LogServicesIml.class);

    @Override
    protected Logger getLog() {
        return LOG;
    }

    @Override
    public void addListLogs(Logs logs) throws InternalErrorException {
        if(logs == null || logs.getLogList() == null || logs.getLogList().isEmpty()){
            LOG.warn("List Logs null/empty!");
            return;
        }
        List<Log> logList = logs.getLogList();
        for(Log log : logList){

            LogEntity logEntity = mappingToLogEntity(log);

            // If log is added. Update it. If none, add!
            if(isAdded(logEntity)){
                logDAO.update(logEntity);
            } else {
                logDAO.add(logEntity);
            }
        }
    }

    /**
     * Check log is added to database or not!
     * @param logEntity
     * @return
     * @throws InternalErrorException
     */
    private boolean isAdded(LogEntity logEntity) throws InternalErrorException{
        // Don't add null entity!
        if(logEntity == null){
            LOG.warn("Log Entity is null");
            return true;
        }

        Integer id = logEntity.getId();
        if(id == null){
            throw new InternalErrorException("logEntity.id null!");
        }

        return logDAO.findById(id) != null;
    }
}
