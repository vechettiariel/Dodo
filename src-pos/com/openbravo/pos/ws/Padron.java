/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.ws;

import com.openbravo.basic.BasicException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author ariel
 */
public class Padron {

    public static IPadron getPadron(Long identifier) throws BasicException {
        IPadron padron = null;
        String padronClass = null;
        try {

            // Intentar instanciar y utilizar dicho proveedor
            if (padronClass == null || padronClass.length() == 0) {
                return null;
            }

            Class<?> clazz = Class.forName(padronClass);
            Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{Long.class});
            padron = (IPadron) constructor.newInstance(new Object[]{identifier});

        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            throw new BasicException(ex);
        }
        return padron;
    }

}
