package com.openbravo.pos.electronicInvoice;

import com.openbravo.pos.ticket.TicketInfo;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class ElectronicInvoiceProvider {

    /**
     * Preferencia sobre proveedor del servicio de FE
     */
    public static String PREFERENCE_WSFE_PROVIDER = "WSFE_PROVIDER_CLASS";

    /**
     * Preferencia sobre proveedor del servicio de FE de Exportacion
     */
    public static String PREFERENCE_WSFEX_PROVIDER = "WSFEX_PROVIDER_CLASS";

    /**
     * Nombre de la clase que provee el servicio de FE (si es que existe)
     */
    public static String wsfeProviderClass = "";

    /**
     * Nombre de la clase que provee el servicio de FEX (si es que existe)
     */
    public static String wsfexProviderClass = "";

    /**
     * Listado de tipos de documento de exportacion segun definicion de FE de
     * AFIP
     */
    private static final ArrayList<String> exportacionDocTypes;

    static {
        // Nomina de tipos de documento de exportacin
        exportacionDocTypes = new ArrayList<>();
    }

    /**
     * Retorna la implementacion (si es que existe) encargada de gestionar la
     * registracion de la FE
     *
     * @param ticket
     * @return
     * @throws java.lang.Exception
     */
    public static ElectronicInvoiceInterface getImplementation(TicketInfo ticket) throws Exception {
        /*	
            try {
			// Recuperar el docType de la factura para determinar si es de exportacion o no
			MDocType docType = new MDocType(Env.getCtx(), inv.getC_DocTypeTarget_ID(), inv.get_TrxName());	
			if (exportacionDocTypes.contains(docType.getdocsubtypecae())){
				return getProvider(inv, wsfexProviderClass);
			}
			return getProvider(inv, wsfeProviderClass);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
         */
        return getProvider(ticket, wsfeProviderClass);
    }

    /**
     * Retorna el provider si es que existe, ya sea de exportacion o no
     * @param ticket
     * @param providerClass
     * @return
     * @throws java.lang.Exception
     */
    protected static ElectronicInvoiceInterface getProvider(TicketInfo ticket, String providerClass) throws Exception {
        // Si no hay proveedor alguno, retornar null
        if (providerClass == null) {
            return null;
        }
        // Intentar instanciar y utilizar dicho proveedor
        Class<?> clazz = Class.forName(providerClass);
        Constructor<?> constructor = clazz.getDeclaredConstructor(new Class[]{TicketInfo.class});
        return (ElectronicInvoiceInterface) constructor.newInstance(new Object[]{ticket});
    }

}
