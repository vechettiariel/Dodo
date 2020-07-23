package com.openbravo.pos.electronicInvoice;

/**
 * Interface para la gestion de Facturas Electronicas mediante WS de AFIP. La
 * interfaz se definió respetando dentro de las posibilidades la tradicional
 * implementacion Wsfe que se apoyaba en pyafipws, a fin de evitar generar mayor
 * impacto en la logica de la clase MInvoice
 */
import java.sql.Timestamp;

public interface ElectronicInvoiceInterface {

    /**
     * Registra una factura electronica en el site de AFIP mediante WSFEV1
     *
     * @return
     * @throws java.lang.Exception
     */
    public String generateCAE() throws Exception;

    /**
     * Retorna el CAE obtenido
     *
     * @return
     */
    public String getCAE();

    /**
     * Retorna el Vencimiento del CAE
     *
     * @return
     */
    public Timestamp getDateCae();

    /**
     * Retorna el Numero de comprobante obtenido
     *
     * @return
     */
    public String getNroCbte();

    /**
     *
     * @param nrodoc
     */
    public void setNroCbte(String nrodoc);

    /**
     * Retorna Error(es) al obtener el CAE
     *
     * @return
     */
    public String getErrorMsg();

}
