/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.validation;

import com.openbravo.basic.BasicException;
import com.openbravo.pos.ticket.TicketInfo;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author ariel
 */
public interface IInvoiceValidation {

    public void checkConnexion() throws BasicException;

    /**
     * Registra una factura electronica en el site de AFIP mediante WSFEV1
     *
     * @param ticket
     * @throws com.openbravo.basic.BasicException
     */
    public void validate(TicketInfo ticket) throws BasicException;

    /**
     * Retorna Error(es) al obtener el CAE
     *
     * @return
     */
    public String getErrorMsg();

    public int getNumber();

    public String getCaeNumber();

    public Date getCaeExpiration();

    public String getTokenRequest();

    public String getTokenResponde();

}
