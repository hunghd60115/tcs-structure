package fsoft.fsu11.bu11.tcs.webservice2.apis;

import fsoft.fsu11.bu11.tcs.commons.dtos.HealthCheckResponseBase;
import fsoft.fsu11.bu11.tcs.commons.dtos.HealthCheckResponseService1;
import fsoft.fsu11.bu11.tcs.commons.dtos.Log;
import fsoft.fsu11.bu11.tcs.commons.dtos.Logs;
import fsoft.fsu11.bu11.tcs.commons.exceptions.BadRequestException;
import fsoft.fsu11.bu11.tcs.commons.exceptions.EntityNotFoundException;
import fsoft.fsu11.bu11.tcs.commons.exceptions.InternalErrorException;
import fsoft.fsu11.bu11.tcs.commons.services.AbstractTcsBackendService;
import fsoft.fsu11.bu11.tcs.commons.services.HealthService;
import fsoft.fsu11.bu11.tcs.webservice2.services.ILogServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/18/14
 * Time: 2:13 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional("transactionManager")
@RequestMapping(value = "/webservice2")
public class LogApis extends AbstractTcsBackendService {

    private Logger LOG = LoggerFactory.getLogger(LogApis.class);

    @Resource(name = "logServices2")
    private ILogServices logServices;

    @Resource(name = "healthService")
    private HealthService healthService;

    @Override
    protected Logger getLog() {
        return LOG;
    }

    @ResponseBody
    @RequestMapping(value = HEALTH_CHECK_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HealthCheckResponseBase healthCheck() {
        return healthService.checkService2();
    }

    @ResponseBody
    @RequestMapping(value = VIEW_LOG_PATH, method = RequestMethod.GET)
    public Log viewLog(
            @PathVariable(value = ID) Integer id) throws EntityNotFoundException {

        Log log = logServices.getLogById(id);
        return log;
    }

    @ResponseBody
    @RequestMapping(value = ADD_LOG_PATH, method = RequestMethod.POST)
    public void addLog(
            @RequestBody Logs logs) throws BadRequestException, InternalErrorException {
        logServices.addListLogs(logs);
    }

    @ResponseBody
    @RequestMapping(value = UPDATE_LOG_PATH, method = RequestMethod.PUT)
    public void updateLog(
            @RequestBody Log log) throws BadRequestException {

        logServices.updateLog(log);
    }

    @ResponseBody
    @RequestMapping(value = DELETE_LOG_PATH, method = RequestMethod.DELETE)
    public void deleteLog(
            @PathVariable(value = ID) Integer id) throws BadRequestException, EntityNotFoundException {

        logServices.deleteLogById(id);
    }

}
