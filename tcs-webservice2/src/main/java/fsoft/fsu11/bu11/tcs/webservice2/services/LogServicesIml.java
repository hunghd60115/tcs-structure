package fsoft.fsu11.bu11.tcs.webservice2.services;

import fsoft.fsu11.bu11.tcs.commons.dtos.Logs;
import fsoft.fsu11.bu11.tcs.commons.exceptions.InternalErrorException;
import fsoft.fsu11.bu11.tcs.commons.services.AbstractTcsBackendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/18/14
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogServicesIml extends AbstractTcsBackendService implements ILogServices{

    private Logger LOG = LoggerFactory.getLogger(LogServicesIml.class);

    @Override
    protected Logger getLog() {
        return LOG;
    }

    @Override
    public void addListLogs(Logs logs) throws InternalErrorException {

    }
}
