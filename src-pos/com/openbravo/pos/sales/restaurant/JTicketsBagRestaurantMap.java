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
package com.openbravo.pos.sales.restaurant;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.loader.SentenceList;
import com.openbravo.data.loader.SerializerReadClass;
import com.openbravo.data.loader.StaticSentence;
import com.openbravo.pos.customers.CustomerInfo;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.sales.DataLogicReceipts;
import com.openbravo.pos.sales.DataLogicSales;
import com.openbravo.pos.sales.JTicketsBag;
import com.openbravo.pos.sales.SharedTicketInfo;
import com.openbravo.pos.sales.TicketsEditor;
import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import com.openbravo.pos.ticket.UserInfo;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;



public class JTicketsBagRestaurantMap extends JTicketsBag {

//    private static final Icon ICO_OCU = new ImageIcon(JTicketsBag.class.getResource("/com/openbravo/images/edit_group.png"));
//    private static final Icon ICO_FRE = new NullIcon(22, 22);
    private java.util.List<PlaceInfo> m_aplaces;
    private java.util.List<Floor> m_afloors;
    private JTicketsBagRestaurant m_restaurantmap;
    private JTicketsBagRestaurantRes m_jreservations;
    private PlaceInfo m_PlaceCurrent;
    // State vars
    private PlaceInfo m_PlaceClipboard;
    private CustomerInfo customer;
    private DataLogicReceipts dlReceipts = null;
    private DataLogicSales dlSales;
    private UserInfo m_Mozo;

    /**
     * Creates new form JTicketsBagRestaurant
     */
    public JTicketsBagRestaurantMap(AppView app, TicketsEditor panelticket) throws BasicException {

        super(app, panelticket);
        this.dlSales = null;

        dlReceipts = (DataLogicReceipts) app.getBean("com.openbravo.pos.sales.DataLogicReceipts");
        dlSales = (DataLogicSales) m_App.getBean("com.openbravo.pos.sales.DataLogicSales");

        m_restaurantmap = new JTicketsBagRestaurant(app, this);
        m_PlaceCurrent = null;
        m_PlaceClipboard = null;
        customer = null;

        try {
            SentenceList sent = new StaticSentence(
                    app.getSession(),
                    "SELECT ID, NAME, IMAGE FROM FLOORS ORDER BY NAME",
                    null,
                    new SerializerReadClass(Floor.class));
            m_afloors = sent.list();



        } catch (BasicException eD) {
            m_afloors = new ArrayList<Floor>();
        }
        try {
            SentenceList sent = new StaticSentence(
                    app.getSession(),
                    "SELECT ID, NAME, X, Y, FLOOR FROM PLACES ORDER BY FLOOR",
                    null,
                    new SerializerReadClass(PlaceInfo.class));
            m_aplaces = sent.list();
        } catch (BasicException eD) {
            m_aplaces = new ArrayList<PlaceInfo>();
        }

        initComponents();

        // add the Floors containers
        if (m_afloors.size() > 1) {
            // A tab container for 2 or more floors
            JTabbedPane jTabFloors = new JTabbedPane();
            jTabFloors.applyComponentOrientation(getComponentOrientation());
            jTabFloors.setBorder(new javax.swing.border.EmptyBorder(new Insets(5, 5, 5, 5)));
            jTabFloors.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
            jTabFloors.setFocusable(false);
            jTabFloors.setRequestFocusEnabled(false);
            m_jPanelMap.add(jTabFloors, BorderLayout.CENTER);

            for (Floor f : m_afloors) {
                f.getContainer().applyComponentOrientation(getComponentOrientation());

                JScrollPane jScrCont = new JScrollPane();
                jScrCont.applyComponentOrientation(getComponentOrientation());
                JPanel jPanCont = new JPanel();
                jPanCont.applyComponentOrientation(getComponentOrientation());

                jTabFloors.addTab(f.getName(), f.getIcon(), jScrCont);
                jScrCont.setViewportView(jPanCont);
                jPanCont.add(f.getContainer());
            }
        } else if (m_afloors.size() == 1) {
            // Just a frame for 1 floor
            Floor f = m_afloors.get(0);
            f.getContainer().applyComponentOrientation(getComponentOrientation());

            JPanel jPlaces = new JPanel();
            jPlaces.applyComponentOrientation(getComponentOrientation());
            jPlaces.setLayout(new BorderLayout());
            jPlaces.setBorder(new javax.swing.border.CompoundBorder(
                    new javax.swing.border.EmptyBorder(new Insets(5, 5, 5, 5)),
                    new javax.swing.border.TitledBorder(f.getName())));

            JScrollPane jScrCont = new JScrollPane();
            jScrCont.applyComponentOrientation(getComponentOrientation());
            JPanel jPanCont = new JPanel();
            jPanCont.applyComponentOrientation(getComponentOrientation());

            // jPlaces.setLayout(new FlowLayout());
            m_jPanelMap.add(jPlaces, BorderLayout.CENTER);
            jPlaces.add(jScrCont, BorderLayout.CENTER);
            jScrCont.setViewportView(jPanCont);
            jPanCont.add(f.getContainer());
        }

        // Add all the Table buttons.
        Floor currfloor = null;


        for (PlaceInfo pl : m_aplaces) {
            int iFloor = 0;

            if (currfloor == null || !currfloor.getID().equals(pl.getFloor())) {
                // Look for a new floor
                do {
                    currfloor = m_afloors.get(iFloor++);
                } while (!currfloor.getID().equals(pl.getFloor()));
            }

            currfloor.getContainer().add(pl.getButton());
            pl.setButtonBounds();
            pl.getButton().addActionListener(new MyActionListener(pl));
        }

        // Add the reservations panel
        m_jreservations = new JTicketsBagRestaurantRes(app, this);
        add(m_jreservations, "res");

        m_jPlace.addEditorKeys(jEditorKeys1);

    }

    @Override
    public void activate() {

        // precondicion es que no tenemos ticket activado ni ticket en el panel

        m_PlaceClipboard = null;
        customer = null;
        loadTickets();
        printState();

        m_panelticket.setActiveTicket(null, null);
        m_restaurantmap.activate();

        showView("map"); // arrancamos en la vista de las mesas.

        // postcondicion es que tenemos ticket activado aqui y ticket en el panel

        m_jPlace.reset();
        m_jPlace.activate();
    }

    @Override
    public boolean deactivate() {

        // precondicion es que tenemos ticket activado aqui y ticket en el panel

        if (viewTables()) {

            // borramos el clipboard
            m_PlaceClipboard = null;
            customer = null;

            // guardamos el ticket
            if (m_PlaceCurrent != null) {

                try {
                    dlReceipts.updateSharedTicket(m_PlaceCurrent.getId(), m_panelticket.getActiveTicket());
                } catch (BasicException e) {
                    new MessageInf(e).show(this);
                }

                m_PlaceCurrent = null;
            }

            // desactivamos cositas.
            printState();
            m_panelticket.setActiveTicket(null, null);

            return true;
        } else {
            return false;
        }

        // postcondicion es que no tenemos ticket activado
    }

    @Override
    protected JComponent getBagComponent() {
        return m_restaurantmap;
    }

    @Override
    protected JComponent getNullComponent() {
        return this;
    }

    @Override
    public void moveTicket() {

        // guardamos el ticket
        if (m_PlaceCurrent != null) {

            try {
                dlReceipts.updateSharedTicket(m_PlaceCurrent.getId(), m_panelticket.getActiveTicket());
            } catch (BasicException e) {
                new MessageInf(e).show(this);
            }

            // me guardo el ticket que quiero copiar.
            m_PlaceClipboard = m_PlaceCurrent;
            customer = null;
            m_PlaceCurrent = null;
        }

        printState();
        m_panelticket.setActiveTicket(null, null);

        m_jPlace.reset();
        m_jPlace.activate();
    }

    public boolean viewTables(CustomerInfo c) {
        // deberiamos comprobar si estamos en reservations o en tables...
        if (m_jreservations.deactivate()) {
            showView("map"); // arrancamos en la vista de las mesas.

            m_PlaceClipboard = null;
            customer = c;
            printState();

            return true;
        } else {
            return false;
        }
    }

    public boolean viewTables() {
        return viewTables(null);
    }

    @Override
    public void newTicket() {

        // guardamos el ticket
        if (m_PlaceCurrent != null) {

            try {
                dlReceipts.updateSharedTicket(m_PlaceCurrent.getId(), m_panelticket.getActiveTicket());
            } catch (BasicException e) {
                new MessageInf(e).show(this); // maybe other guy deleted it
            }

            m_PlaceCurrent = null;
        }

        printState();
        m_panelticket.setActiveTicket(null, null);

        m_jPlace.reset();
        m_jPlace.activate();
    }

    @Override
    public void deleteTicket() {

        if (m_PlaceCurrent != null) {

            String id = m_PlaceCurrent.getId();
            try {
                dlReceipts.deleteSharedTicket(id);
            } catch (BasicException e) {
                new MessageInf(e).show(this);
            }

            m_PlaceCurrent.setPeople(false);

            m_PlaceCurrent = null;
        }

        printState();
        m_panelticket.setActiveTicket(null, null);

        m_jPlace.reset();
        m_jPlace.activate();
    }

    public void loadTickets() {

        Set<String> atickets = new HashSet<String>();

        try {
            java.util.List<SharedTicketInfo> l = dlReceipts.getSharedTicketList();
            for (SharedTicketInfo ticket : l) {
                atickets.add(ticket.getId());
            }
        } catch (BasicException e) {
            new MessageInf(e).show(this);
        }

        for (PlaceInfo table : m_aplaces) {
            table.setPeople(atickets.contains(table.getId()));
        }
    }

    private void printState() {

        if (m_PlaceClipboard == null) {
            if (customer == null) {
                // Select a table
                m_jText.setText(null);
                // Enable all tables
                for (PlaceInfo place : m_aplaces) {
                    place.getButton().setEnabled(true);
                }
                m_jbtnReservations.setEnabled(true);
            } else {
                // receive a customer
                m_jText.setText(AppLocal.getIntString("label.restaurantcustomer", new Object[]{customer.getName()}));
                // Enable all tables
                for (PlaceInfo place : m_aplaces) {
                    place.getButton().setEnabled(!place.hasPeople());
                }
                m_jbtnReservations.setEnabled(false);
            }
        } else {
            // Moving or merging the receipt to another table
            m_jText.setText(AppLocal.getIntString("label.restaurantmove", new Object[]{m_PlaceClipboard.getName()}));
            // Enable all empty tables and origin table.
            for (PlaceInfo place : m_aplaces) {
                place.getButton().setEnabled(true);
            }
            m_jbtnReservations.setEnabled(false);
        }
    }

    private TicketInfo getTicketInfo(PlaceInfo place) {

        try {
            return dlReceipts.getSharedTicket(place.getId());
        } catch (BasicException e) {
            new MessageInf(e).show(JTicketsBagRestaurantMap.this);
            return null;
        }
    }

    private void setActivePlace(PlaceInfo place, TicketInfo ticket) {
        m_PlaceCurrent = place;
        m_panelticket.setActiveTicket(ticket, m_PlaceCurrent.getName());
    }

    private void showView(String view) {
        CardLayout cl = (CardLayout) (getLayout());
        cl.show(this, view);
    }

    private void buscarMesa() {
        m_jPlace.getText();
        PlaceInfo placeSelect = null;

        for (Iterator<PlaceInfo> it = m_aplaces.iterator(); it.hasNext();) {
            PlaceInfo placeInfo = it.next();
            if (placeInfo.getId().equals(m_jPlace.getText())) {
                placeSelect = placeInfo;
            }
        }

        if (placeSelect != null) {
            actionPlace(placeSelect);
        } else {
        }
    }

    private void actionPlace(PlaceInfo place) {


        if (m_PlaceClipboard == null) {

            if (customer == null) {
                // tables

                // check if the sharedticket is the same
                TicketInfo ticket = getTicketInfo(place);

                // check
                if (ticket == null && !place.hasPeople()) {
                    // Empty table and checked

                    // table occupied
                    ticket = new TicketInfo();
                    try {
                        dlReceipts.insertSharedTicket(place.getId(), ticket);
                    } catch (BasicException e) {
                        new MessageInf(e).show(JTicketsBagRestaurantMap.this); // Glup. But It was empty.
                    }
                    place.setPeople(true);
                    setActivePlace(place, ticket);

                } else if (ticket == null && place.hasPeople()) {
                    // The table is now empty
                    new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.tableempty")).show(JTicketsBagRestaurantMap.this);
                    place.setPeople(false); // fixed

                } else if (ticket != null && !place.hasPeople()) {
                    // The table is now full
                    new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.tablefull")).show(JTicketsBagRestaurantMap.this);
                    place.setPeople(true);

                } else { // both != null
                    // Full table
                    // m_place.setPeople(true); // already true
                    setActivePlace(place, ticket);
                }
            } else {
                // receiving customer.

                // check if the sharedticket is the same
                TicketInfo ticket = getTicketInfo(place);
                if (ticket == null) {
                    // receive the customer
                    // table occupied
                    ticket = new TicketInfo();

                    try {
                        ticket.setCustomer(customer.getId() == null
                                ? null
                                : dlSales.loadCustomerExt(customer.getId()));
                    } catch (BasicException e) {
                        MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfindcustomer"), e);
                        msg.show(JTicketsBagRestaurantMap.this);
                    }

                    try {
                        dlReceipts.insertSharedTicket(place.getId(), ticket);
                    } catch (BasicException e) {
                        new MessageInf(e).show(JTicketsBagRestaurantMap.this); // Glup. But It was empty.
                    }
                    place.setPeople(true);

                    m_PlaceClipboard = null;
                    customer = null;

                    setActivePlace(place, ticket);
                } else {
                    // TODO: msg: The table is now full
                    new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.tablefull")).show(JTicketsBagRestaurantMap.this);
                    place.setPeople(true);
                    place.getButton().setEnabled(false);
                }
            }
        } else {
            // check if the sharedticket is the same
            TicketInfo ticketclip = getTicketInfo(m_PlaceClipboard);

            if (ticketclip == null) {
                new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.tableempty")).show(JTicketsBagRestaurantMap.this);
                m_PlaceClipboard.setPeople(false);
                m_PlaceClipboard = null;
                customer = null;
                printState();
            } else {
                // tenemos que copiar el ticket del clipboard
                if (m_PlaceClipboard == place) {
                    // the same button. Canceling.
                    PlaceInfo placeclip = m_PlaceClipboard;
                    m_PlaceClipboard = null;
                    customer = null;
                    printState();
                    setActivePlace(placeclip, ticketclip);
                } else if (!place.hasPeople()) {
                    // Moving the receipt to an empty table
                    TicketInfo ticket = getTicketInfo(place);

                    if (ticket == null) {
                        try {
                            dlReceipts.insertSharedTicket(place.getId(), ticketclip);
                            place.setPeople(true);
                            dlReceipts.deleteSharedTicket(m_PlaceClipboard.getId());
                            m_PlaceClipboard.setPeople(false);
                        } catch (BasicException e) {
                            new MessageInf(e).show(JTicketsBagRestaurantMap.this); // Glup. But It was empty.
                        }

                        m_PlaceClipboard = null;
                        customer = null;
                        printState();

                        // No hace falta preguntar si estaba bloqueado porque ya lo estaba antes
                        // activamos el ticket seleccionado
                        setActivePlace(place, ticketclip);
                    } else {
                        // Full table
                        new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.tablefull")).show(JTicketsBagRestaurantMap.this);
                        m_PlaceClipboard.setPeople(true);
                        printState();
                    }
                } else {
                    // Merge the lines with the receipt of the table
                    TicketInfo ticket = getTicketInfo(place);

                    if (ticket == null) {
                        // The table is now empty
                        new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.tableempty")).show(JTicketsBagRestaurantMap.this);
                        place.setPeople(false); // fixed
                    } else {
                        //asks if you want to merge tables
                        if (JOptionPane.showConfirmDialog(JTicketsBagRestaurantMap.this, AppLocal.getIntString("message.mergetablequestion"), AppLocal.getIntString("message.mergetable"), JOptionPane.YES_NO_OPTION)
                                == JOptionPane.YES_OPTION) {
                            // merge lines ticket

                            try {
                                dlReceipts.deleteSharedTicket(m_PlaceClipboard.getId());
                                m_PlaceClipboard.setPeople(false);
                                if (ticket.getCustomer() == null) {
                                    ticket.setCustomer(ticketclip.getCustomer());
                                }
                                for (TicketLineInfo line : ticketclip.getLines()) {
                                    ticket.addLine(line);
                                }
                                dlReceipts.updateSharedTicket(place.getId(), ticket);
                            } catch (BasicException e) {
                                new MessageInf(e).show(JTicketsBagRestaurantMap.this); // Glup. But It was empty.
                            }

                            m_PlaceClipboard = null;
                            customer = null;
                            printState();

                            setActivePlace(place, ticket);
                        } else {
                            // Cancel merge operations
                            PlaceInfo placeclip = m_PlaceClipboard;
                            m_PlaceClipboard = null;
                            customer = null;
                            printState();
                            setActivePlace(placeclip, ticketclip);
                        }
                    }
                }
            }
        }
    }

    public void setMozo(UserInfo u) {
        m_Mozo = u;
    }

    @Override
    public UserInfo getVendedor() {
        return m_Mozo;
    }

    private class MyActionListener implements ActionListener {

        private PlaceInfo m_place;

        public MyActionListener(PlaceInfo place) {
            m_place = place;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            actionPlace(m_place);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        m_jPanelMap = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        m_jbtnReservations = new javax.swing.JButton();
        m_jbtnRefresh = new javax.swing.JButton();
        m_jText = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jEditorKeys1 = new com.openbravo.editor.JEditorKeys();
        jPanel4 = new javax.swing.JPanel();
        m_jPlace = new com.openbravo.editor.JEditorIntegerPositive();
        jButton1 = new javax.swing.JButton();

        setLayout(new java.awt.CardLayout());

        m_jPanelMap.setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        m_jbtnReservations.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/date.png"))); // NOI18N
        m_jbtnReservations.setText(AppLocal.getIntString("button.reservations")); // NOI18N
        m_jbtnReservations.setFocusPainted(false);
        m_jbtnReservations.setFocusable(false);
        m_jbtnReservations.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jbtnReservations.setRequestFocusEnabled(false);
        m_jbtnReservations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jbtnReservationsActionPerformed(evt);
            }
        });
        jPanel2.add(m_jbtnReservations);

        m_jbtnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/reload.png"))); // NOI18N
        m_jbtnRefresh.setText(AppLocal.getIntString("button.reloadticket")); // NOI18N
        m_jbtnRefresh.setFocusPainted(false);
        m_jbtnRefresh.setFocusable(false);
        m_jbtnRefresh.setMargin(new java.awt.Insets(8, 14, 8, 14));
        m_jbtnRefresh.setRequestFocusEnabled(false);
        m_jbtnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jbtnRefreshActionPerformed(evt);
            }
        });
        jPanel2.add(m_jbtnRefresh);
        jPanel2.add(m_jText);

        jPanel1.add(jPanel2, java.awt.BorderLayout.LINE_START);

        m_jPanelMap.add(jPanel1, java.awt.BorderLayout.NORTH);

        jPanel3.setPreferredSize(new java.awt.Dimension(200, 293));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jEditorKeys1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditorKeys1ActionPerformed(evt);
            }
        });
        jPanel3.add(jEditorKeys1, java.awt.BorderLayout.NORTH);

        jPanel4.add(m_jPlace);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/button_ok.png"))); // NOI18N
        jButton1.setFocusPainted(false);
        jButton1.setFocusable(false);
        jButton1.setMargin(new java.awt.Insets(8, 14, 8, 14));
        jButton1.setRequestFocusEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jPanel3.add(jPanel4, java.awt.BorderLayout.LINE_END);

        m_jPanelMap.add(jPanel3, java.awt.BorderLayout.EAST);

        add(m_jPanelMap, "map");
    }// </editor-fold>//GEN-END:initComponents

    private void m_jbtnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jbtnRefreshActionPerformed

        m_PlaceClipboard = null;
        customer = null;
        loadTickets();
        printState();

    }//GEN-LAST:event_m_jbtnRefreshActionPerformed

    private void m_jbtnReservationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jbtnReservationsActionPerformed
        showView("res");
        m_jreservations.activate();
    }//GEN-LAST:event_m_jbtnReservationsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscarMesa();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jEditorKeys1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditorKeys1ActionPerformed
        buscarMesa();
    }//GEN-LAST:event_jEditorKeys1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private com.openbravo.editor.JEditorKeys jEditorKeys1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel m_jPanelMap;
    private com.openbravo.editor.JEditorIntegerPositive m_jPlace;
    private javax.swing.JLabel m_jText;
    private javax.swing.JButton m_jbtnRefresh;
    private javax.swing.JButton m_jbtnReservations;
    // End of variables declaration//GEN-END:variables
}
