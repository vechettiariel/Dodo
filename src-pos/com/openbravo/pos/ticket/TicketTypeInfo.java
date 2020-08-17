/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.ticket;

import com.openbravo.data.loader.IKeyed;
import java.io.Serializable;

/**
 *
 * @author ariel
 */
public class TicketTypeInfo implements IKeyed, Serializable {

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
    1000    RECIBO DE COBRO
     */
 /*
    Desde 1 al 999 de uso para comprontes de Afip
     */
    private int m_id;
    private String m_nanme;

    public TicketTypeInfo(int m_id) {
        this.m_id = m_id;
    }

    public TicketTypeInfo(int m_id, String m_nanme) {
        this.m_id = m_id;
        this.m_nanme = m_nanme;
    }

    public TicketTypeInfo() {
    }

    public int getId() {
        return m_id;
    }

    public void setId(int m_id) {
        this.m_id = m_id;
    }

    public String getNanme() {
        return m_nanme;
    }

    public void setNanme(String m_nanme) {
        this.m_nanme = m_nanme;
    }

    @Override
    public Object getKey() {
        return m_id;
    }

    @Override
    public String toString() {
        return m_nanme;
    }

}
