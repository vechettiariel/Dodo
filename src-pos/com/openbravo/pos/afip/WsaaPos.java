/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.afip;

import com.openbravo.pos.forms.AppConfig;
import com.openbravo.pos.util.AltEncrypter;
import coop.guenoa.afip.util.Configuracion;
import coop.guenoa.afip.wsaa.TicketLogin;
import coop.guenoa.afip.wsaa.WsaaException;

/**
 *
 * @author ariel
 */
public class WsaaPos {
    
    private final AppConfig config;
    private final String service;

    public WsaaPos(AppConfig config, String service) {
        this.config = config;
        this.service = service;
    }
    
    public TicketLogin getTicketLogin() throws WsaaException{
        
         Configuracion.CUIT = config.getProperty("wsafip.CUIT");
                    Configuracion.DEBUG = true;
                    Configuracion.HOMOLOCION = config.getProperty("wsafip.salidafiscal").equals("WSFEv1-HOMO");
                    Configuracion.KEYSTORE = config.getProperty("wsafip.KEYSTORE");

                    String sUser = config.getProperty("wsafip.KEYSTORE_USER");
                    String sPassword = config.getProperty("wsafip.KEYSTORE_PASS");
                    if (sUser != null && sPassword != null && sPassword.startsWith("crypt:")) {
                        // La clave esta encriptada.
                        AltEncrypter cypher = new AltEncrypter("cypherkey" + sUser);
                        sPassword = cypher.decrypt(sPassword.substring(6));
                    }

                    Configuracion.KEYSTORE_USER = sUser;
                    Configuracion.KEYSTORE_PASS = sPassword;
                    
                    coop.guenoa.afip.wsaa.Wsaa wsaa = new coop.guenoa.afip.wsaa.Wsaa(service);

                  return wsaa.getTicketLogin();
        
    }
    
    
    
}
