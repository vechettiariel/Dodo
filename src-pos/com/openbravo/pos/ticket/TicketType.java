/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.ticket;

import com.openbravo.pos.customers.CustomerInfoExt;

/**
 *
 * @author ariel
 */
public class TicketType {

    /*
    001	FACTURAS A
    002	NOTAS DE DEBITO A
    003	NOTAS DE CREDITO A
    004	RECIBOS A
    005	NOTAS DE VENTA AL CONTADO A
    006	FACTURAS B
    007	NOTAS DE DEBITO B
    008	NOTAS DE CREDITO B
    009	RECIBOS B
    010	NOTAS DE VENTA AL CONTADO B
    011	FACTURAS C
    012	NOTAS DE DEBITO C
    013	NOTAS DE CREDITO C
    015	RECIBOS C
    016	NOTAS DE VENTA AL CONTADO C
     */
    /*
    Desde 1 al 999 de uso para comprontes de Afip
    */
    private static int FACTURAS_A = 1;
    private static int NOTAS_DE_DEBITO_A = 2;
    private static int NOTAS_DE_CREDITO_A = 3;
    private static int RECIBOS_A = 4;
    private static int NOTAS_DE_VENTA_AL_CONTADO_A = 5;
    private static int FACTURAS_B = 6;
    private static int NOTAS_DE_DEBITO_B = 7;
    private static int NOTAS_DE_CREDITO_B = 8;
    private static int RECIBOS_B = 9;
    private static int NOTAS_DE_VENTA_AL_CONTADO_B = 10;
    private static int FACTURAS_C = 11;
    private static int NOTAS_DE_DEBITO_C = 12;
    private static int NOTAS_DE_CREDITO_C = 13;
    private static int RECIBOS_C = 15;
    private static int NOTAS_DE_VENTA_AL_CONTADO_C = 16;
    
    /*
    De uso interno
    */
    private static int RECEIPT_PAYMENT = 1000;
 

    private int m_id;

    public TicketType(int m_id) {
        this.m_id = m_id;
    }

    public TicketType() {
    }

    public int getId() {
        return m_id;
    }

    public void setId(int m_id) {
        this.m_id = m_id;
    }

    public boolean isNormal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean isRefund() {
        return false;
    }

    public static TicketType getNomalTypeTicket(CustomerInfoExt customer) {
        TicketType ticketType = new TicketType();

        //customer.getTaxCustCategoryID().
        return ticketType;
    }

    public static TicketType getRefundTypeTicket(CustomerInfoExt customer) {

        TicketType ticketType = new TicketType();

        //customer.getTaxCustCategoryID().
        return ticketType;

    }
    
    public static TicketType getPaymentTypeTicket() {
        TicketType ticketType = new TicketType(RECEIPT_PAYMENT);
        return ticketType;
    }

}
