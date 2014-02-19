package fsoft.fsu11.bu11.tcs.webservice2.services;

import fsoft.fsu11.bu11.tcs.commons.dtos.Log;
import fsoft.fsu11.bu11.tcs.commons.dtos.Logs;
import fsoft.fsu11.bu11.tcs.commons.exceptions.BadRequestException;
import fsoft.fsu11.bu11.tcs.commons.exceptions.EntityNotFoundException;
import fsoft.fsu11.bu11.tcs.commons.exceptions.InternalErrorException;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: HungHD1
 * Date: 2/18/14
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ILogServices {

    public Log getLogById(Integer id) throws EntityNotFoundException;

    public void addListLogs(Logs logs) throws InternalErrorException;

    public void updateLog(Log log) throws BadRequestException;

    public void deleteLogById(Integer id) throws BadRequestException, EntityNotFoundException;
}
