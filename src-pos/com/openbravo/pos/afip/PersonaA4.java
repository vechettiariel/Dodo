/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.afip;

import com.openbravo.pos.forms.AppConfig;
import coop.guenoa.afip.padron.a4.Persona;
import coop.guenoa.afip.padron.a4.PersonaReturn;
import coop.guenoa.afip.padron.a4.PersonaServiceA4;
import coop.guenoa.afip.util.Configuracion;
import coop.guenoa.afip.wsaa.TicketLogin;
import coop.guenoa.afip.wsaa.Wsaa;
import coop.guenoa.afip.wsaa.WsaaException;
import java.rmi.RemoteException;

/**
 *
 * @author ariel
 */
public class PersonaA4 {

    private AppConfig config;

    public PersonaA4(AppConfig config) {
        this.config = config;
    }

    public Persona getPersona(Long nrodoc) throws RemoteException, WsaaException {

        WsaaPos wsaa = new WsaaPos((AppConfig) config, Wsaa.ws_sr_padron_a4);

        TicketLogin tl = wsaa.getTicketLogin();

        PersonaServiceA4 personaService = new PersonaServiceA4();

        PersonaReturn personaReturn = personaService.getPersona(tl.getToken(), tl.getSign(), Long.valueOf(Configuracion.CUIT),
                nrodoc);
        return personaReturn.getPersona();
    }

}
