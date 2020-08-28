/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.validation;

import com.openbravo.basic.BasicException;
import com.openbravo.pos.forms.AppProperties;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author ariel
 */
public class InvoiceValidation {

    public static IInvoiceValidation getValidation(AppProperties m_props) throws BasicException {

        IInvoiceValidation invoiceValidation = null;
        try {

            if (m_props.getProperty("fiscal.validation").equals("NoDefinido")) {

                invoiceValidation = new InvoiceValidationNoDefinido();

            } else {

                String validationClass = null;
                // Intentar instanciar y utilizar dicho proveedor
                if (validationClass == null || validationClass.length() == 0) {
                    throw new BasicException("Class No Define");
                }

                Class<?> clazz = Class.forName(validationClass);
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                invoiceValidation = (IInvoiceValidation) constructor.newInstance();
            }

        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new BasicException(ex);
        }
        return invoiceValidation;
    }
}
