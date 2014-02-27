package fsoft.fsu11.bu11.tcs.webservice1.apis;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import fsoft.fsu11.bu11.tcs.commons.dtos.HealthCheckResponseBase;
import fsoft.fsu11.bu11.tcs.commons.dtos.Log;
import fsoft.fsu11.bu11.tcs.commons.dtos.Logs;
import fsoft.fsu11.bu11.tcs.commons.exceptions.BadRequestException;
import fsoft.fsu11.bu11.tcs.commons.exceptions.EntityNotFoundException;
import fsoft.fsu11.bu11.tcs.commons.exceptions.InternalErrorException;
import fsoft.fsu11.bu11.tcs.commons.services.AbstractTcsBackendService;
import fsoft.fsu11.bu11.tcs.commons.services.HealthService;
import fsoft.fsu11.bu11.tcs.webservice1.services.ILogServices;
import fsoft.fsu11.bu11.tcs.webservice1.services.LogServicesIml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/15/14
 * Time: 7:25 PM
 * To change this template use File | Settings | File Templates.
 */

@Service
@Transactional("transactionManager")
@RequestMapping(value = "/webservice1")
public class LogApis extends AbstractTcsBackendService {

    @Resource(name = "logServices1")
    private ILogServices logServices;

    @Resource(name = "healthService")
    private HealthService healthService;

    private Logger LOG = LoggerFactory.getLogger(LogApis.class);

    private Cache<String, String> a;

    @Override
    protected Logger getLog() {
        return LOG;
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public String testSession(HttpServletRequest requestHeader){
        if(a == null){
            a = CacheBuilder.newBuilder().expireAfterWrite(20, TimeUnit.SECONDS).build();
        }
        if(a.getIfPresent("s") == null){
            a.put("s", "s");
        }
        HealthCheckResponseBase a = (HealthCheckResponseBase) requestHeader.getSession().getAttribute("s");
        return requestHeader.getSession().isNew() + "";
    }

    @ResponseBody
    @RequestMapping(value = HEALTH_CHECK_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public HealthCheckResponseBase healthCheck() {
        return healthService.checkService1();
    }

    @ResponseBody
    @RequestMapping(value = VIEW_LOG_PATH, method = RequestMethod.GET)
    public Log viewLog(
            @PathVariable(value = ID) Integer id)  throws EntityNotFoundException{

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
