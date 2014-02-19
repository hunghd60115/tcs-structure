package fsoft.fsu11.bu11.tcs.commons.services;

import fsoft.fsu11.bu11.tcs.commons.conf.TcsStructureConfig;
import fsoft.fsu11.bu11.tcs.commons.dtos.Log;
import fsoft.fsu11.bu11.tcs.commons.dtos.Logs;
import fsoft.fsu11.bu11.tcs.commons.exceptions.AbstractTcsBackendException;
import fsoft.fsu11.bu11.tcs.commons.exceptions.BadRequestException;
import fsoft.fsu11.bu11.tcs.commons.exceptions.EntityNotFoundException;
import fsoft.fsu11.bu11.tcs.commons.exceptions.InternalErrorException;
import fsoft.fsu11.bu11.tcs.commons.utils.ServiceParameter;
import fsoft.fsu11.bu11.tcs.core.daos.log.LogDAO;
import fsoft.fsu11.bu11.tcs.core.entities.LogEntity;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/17/14
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractTcsBackendService implements ServiceParameter {

    @Resource(name = "tcsStructureConfig")
    protected TcsStructureConfig config;

    @Resource(name = "logDAO")
    protected LogDAO logDAO;

    abstract protected Logger getLog();

    /**
     * If a service raise an exception <strong>other than a service exception</strong>,
     * the HTTP response returned will have a status code 500 (internal server error).
     * <p/>
     * Parameters are injected by Spring as specified in the
     * {@link org.springframework.web.bind.annotation.ExceptionHandler} documentation.
     *
     * @param exceptionRaised exception raised
     * @param response        http servlet response
     * @throws java.io.IOException if fail to write the response body
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public void handleException(Exception exceptionRaised, HttpServletResponse response) throws IOException {
        if (getLog().isErrorEnabled()) {
            getLog().error("exception handled by spring, will return status code error " + HttpURLConnection.HTTP_INTERNAL_ERROR, exceptionRaised);
        }
        response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
        String body = String.format(
                HTTP_RESPONSE_ERROR_BODY_JSON_FORMAT,
                exceptionRaised.getClass().getSimpleName(),
                exceptionRaised.getMessage());
        IOUtils.write(body, response.getOutputStream());
    }

    /**
     * If a service raise a service exception, the HTTP response returned
     * will have a status code given by the exception itself.
     * <p/>
     * Parameters are injected by Spring as specified in the
     * {@link org.springframework.web.bind.annotation.ExceptionHandler} documentation.
     *
     * @param exceptionRaised exception raised
     * @param response        http servlet response
     * @throws IOException if fail to write the response body
     */
    @ExceptionHandler(AbstractTcsBackendException.class)
    @ResponseBody
    public void handleServiceException(AbstractTcsBackendException exceptionRaised, HttpServletResponse response) throws IOException {
        if (getLog().isTraceEnabled()) {
            getLog().trace("exception handled by spring, will return status code error " + exceptionRaised.getStatusCode(), exceptionRaised);
        }
        response.setStatus(exceptionRaised.getStatusCode());
        String body = String.format(
                HTTP_RESPONSE_ERROR_BODY_JSON_FORMAT,
                exceptionRaised.getClass().getSimpleName(),
                exceptionRaised.getMessage());
        IOUtils.write(body, response.getOutputStream());
    }

    /**
     * Get Log data by Id.
     *
     * @param id
     * @return
     */
    public Log getLogById(Integer id) throws EntityNotFoundException {
        LogEntity logEntity = logDAO.findById(id);
        if (logEntity == null) {
            getLog().warn("Not find log with id " + id);
            throw new EntityNotFoundException("Not found log with id " + id);
        }

        return mappingToLog(logEntity);
    }

    /**
     * Add log data. If wrong format or Id null. throw HTTP BAD REQUEST!
     *
     * @param logs
     * @throws fsoft.fsu11.bu11.tcs.commons.exceptions.InternalErrorException
     *
     */
    public void addListLogs(Logs logs) throws InternalErrorException{

    }

    /**
     * Update log data. If wrong format or Id null. throw HTTP BAD REQUEST!
     *
     * @param log
     * @throws BadRequestException
     */
    public void updateLog(Log log) throws BadRequestException {
        LogEntity logEntity = mappingToLogEntity(log);
        if (logEntity == null) {
            getLog().error("LogEntity null! Can not update log!");
            throw new BadRequestException("LogEntity null! Can not update log!");
        }
        if (logEntity.getId() == null) {
            getLog().error("ID must not null!");
            throw new BadRequestException("ID must not null!");
        }

        logDAO.update(logEntity);
    }

    /**
     * Delete log data. If wrong format or Id null. throw HTTP BAD REQUEST!
     *
     * @param id
     * @throws BadRequestException
     */
    public void deleteLogById(Integer id) throws BadRequestException, EntityNotFoundException {
        if (id == null) {
            getLog().error("ID must not null!");
            throw new BadRequestException("ID must not null!");
        }

        LogEntity logEntity = logDAO.findById(id);
        if (logEntity == null) {
            getLog().error("LogEntity null! Can not delete log!");
            throw new EntityNotFoundException("Not found log with id " + id);
        }

        logDAO.remove(logEntity);
    }

    /**
     * Mapping from Log Entity to Log for response to client.
     *
     * @param logEntity
     * @return
     */
    public Log mappingToLog(LogEntity logEntity) {

        if (logEntity == null) {
            return null;
        }
        Log log = new Log();
        log.setId(logEntity.getId());
        log.setChannelCode(logEntity.getChannelCode());
        log.setDetail(logEntity.getDetail());
        log.setType(logEntity.getType());
        log.setStartTime(logEntity.getStartTime());
        log.setEndTime(logEntity.getEndTime());
        log.setScore(logEntity.getScore());

        return log;
    }

    /**
     * Mapping from Log to Log Entity to processing with Database.
     *
     * @param log
     * @return
     */
    public LogEntity mappingToLogEntity(Log log) {
        if (log == null) {
            return null;
        }

        LogEntity logEntity = new LogEntity();
        logEntity.setId(log.getId());
        logEntity.setChannelCode(log.getChannelCode());
        logEntity.setType(log.getType());
        logEntity.setDetail(log.getDetail());
        logEntity.setStartTime(log.getStartTime());
        logEntity.setEndTime(log.getEndTime());
        logEntity.setScore(log.getScore());

        return logEntity;
    }
}
