//    Openbravo POS is a point of sales application designed for touch screens.
//    Copyright (C) 2007-2009 Openbravo, S.L.
//    http://www.openbravo.com/product/pos
//
//    This file is part of Openbravo POS.
//
//    Openbravo POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Openbravo POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Openbravo POS.  If not, see <http://www.gnu.org/licenses/>.
package com.openbravo.pos.forms;

import java.util.Locale;
import javax.swing.UIManager;
import com.openbravo.format.Formats;
import com.openbravo.pos.instance.InstanceQuery;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.LookAndFeel;
import javax.swing.UnsupportedLookAndFeelException;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.api.SubstanceSkin;
import coop.guenoa.afip.util.Configuracion;

/**
 *
 * @author adrianromero
 */
public class StartPOS {

    private static final Logger logger = Logger.getLogger("com.openbravo.pos.forms.StartPOS");

    /**
     * Creates a new instance of StartPOS
     */
    private StartPOS() {
    }

    public static boolean registerApp() {

        // vemos si existe alguna instancia        
        InstanceQuery i = null;
        try {
            i = new InstanceQuery();
            i.getAppMessage().restoreWindow();
            return false;
        } catch (NotBoundException | RemoteException e) {
            return true;
        }
    }

    public static void main(final String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                if (!registerApp()) {
                    System.exit(1);
                }

                AppConfig config = new AppConfig(args);
                config.load();

                // set Locale.
                String slang = config.getProperty("user.language");
                String scountry = config.getProperty("user.country");
                String svariant = config.getProperty("user.variant");
                if (slang != null && !slang.equals("") && scountry != null && svariant != null) {
                    Locale.setDefault(new Locale(slang, scountry, svariant));
                }

                /*
                Carga la configuracion de factura electronica
                NoDefinido
                WSFEv1-HOMO
                WSFEv1
                IMPRESORA-FISCAL
                 */
                String keystore = config.getProperty("wsafip.KEYSTORE");

                if (!keystore.equals("IMPRESORA-FISCAL") && !keystore.equals("NoDefinido")) {

                    Configuracion.CUIT = config.getProperty("wsafip.CUIT","");
                    Configuracion.DEBUG = config.getProperty("wsafip.DEBUG","").equalsIgnoreCase("True");
                    Configuracion.HOMOLOCION = config.getProperty("wsafip.HOMOLOCION","").equalsIgnoreCase("True");

                    Configuracion.KEYSTORE = config.getProperty("wsafip.KEYSTORE","");
                    Configuracion.KEYSTORE_PASS = config.getProperty("wsafip.KEYSTORE_PASS","");
                    Configuracion.KEYSTORE_USER = config.getProperty("wsafip.KEYSTORE_USER","");

                } else {

                }

                // Set the format patterns
                Formats.setIntegerPattern(config.getProperty("format.integer"));
                Formats.setDoublePattern(config.getProperty("format.double"));
                Formats.setCurrencyPattern(config.getProperty("format.currency"));
                Formats.setPercentPattern(config.getProperty("format.percent"));
                Formats.setDatePattern(config.getProperty("format.date"));
                Formats.setTimePattern(config.getProperty("format.time"));
                Formats.setDateTimePattern(config.getProperty("format.datetime"));

                // Set the look and feel.
                try {

                    Object laf = Class.forName(config.getProperty("swing.defaultlaf")).newInstance();

                    if (laf instanceof LookAndFeel) {
                        UIManager.setLookAndFeel((LookAndFeel) laf);
                    } else if (laf instanceof SubstanceSkin) {
                        SubstanceLookAndFeel.setSkin((SubstanceSkin) laf);
                    }
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
                    logger.log(Level.WARNING, "Cannot set look and feel", e);
                }

                String screenmode = config.getProperty("machine.screenmode");
                if ("fullscreen".equals(screenmode)) {
                    JRootKiosk rootkiosk = new JRootKiosk();
                    rootkiosk.initFrame(config);
                } else {
                    JRootFrame rootframe = new JRootFrame();
                    rootframe.initFrame(config);
                }
            }
        });
    }
}
