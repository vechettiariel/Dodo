/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.validation;

import com.openbravo.basic.BasicException;
import com.openbravo.pos.ticket.TicketInfo;
import java.util.Date;

/**
 *
 * @author ariel
 */
public class InvoiceValidationNoDefinido implements IInvoiceValidation {

    public InvoiceValidationNoDefinido() {
    }

    @Override
    public void checkConnexion() throws BasicException {

    }

    @Override
    public void validate(TicketInfo ticket) throws BasicException {

    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public String getErrorMsg() {
        return null;
    }

    @Override
    public String getCaeNumber() {
        return null;
    }

    @Override
    public Date getCaeExpiration() {
        return null;
    }

    @Override
    public String getTokenRequest() {
        return null;
    }

    @Override
    public String getTokenResponde() {
        return null;
    }

}
