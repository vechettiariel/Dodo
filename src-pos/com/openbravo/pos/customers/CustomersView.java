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
package com.openbravo.pos.customers;

import com.openbravo.basic.BasicException;
import com.openbravo.data.gui.ComboBoxValModel;
import com.openbravo.data.gui.MessageInf;
import com.openbravo.data.user.DirtyManager;
import com.openbravo.data.user.EditorRecord;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;

import com.openbravo.pos.forms.AppView;
import com.openbravo.pos.ws.IPadron;
import com.openbravo.pos.ws.Padron;
import com.openbravo.pos.types.GeneralTypes;
import com.openbravo.pos.util.StringUtils;
import java.awt.Component;
import java.util.List;
import java.util.UUID;
import javax.swing.JOptionPane;

/**
 *
 * @author adrianromero
 */
public final class CustomersView extends javax.swing.JPanel implements EditorRecord {

    private Object m_oId;

    //private final SentenceList m_sentcat;
    private ComboBoxValModel m_CategoryModel;

    private final DirtyManager m_Dirty;
    private ComboBoxValModel m_DocTypeModel;
    private final AppView app;

    /**
     * Creates new form CustomersView
     *
     * @param app
     * @param dirty
     */
    public CustomersView(AppView app, DirtyManager dirty) {

        this.app = app;

//        DataLogicSales dlSales = (DataLogicSales) app.getBean("com.openbravo.pos.sales.DataLogicSales");
        initComponents();

        m_CategoryModel = new ComboBoxValModel();

        m_Dirty = dirty;
        m_jTaxID.getDocument().addDocumentListener(dirty);
        m_jSearchkey.getDocument().addDocumentListener(dirty);
        m_jName.getDocument().addDocumentListener(dirty);
        m_jCategory.addActionListener(dirty);
        m_jNotes.getDocument().addDocumentListener(dirty);
        txtMaxdebt.getDocument().addDocumentListener(dirty);
        m_jVisible.addActionListener(dirty);

        txtFirstName.getDocument().addDocumentListener(dirty);
        txtLastName.getDocument().addDocumentListener(dirty);
        txtEmail.getDocument().addDocumentListener(dirty);
        txtPhone.getDocument().addDocumentListener(dirty);
        txtPhone2.getDocument().addDocumentListener(dirty);
        txtFax.getDocument().addDocumentListener(dirty);

        txtAddress.getDocument().addDocumentListener(dirty);
        txtAddress2.getDocument().addDocumentListener(dirty);
        txtPostal.getDocument().addDocumentListener(dirty);
        txtCity.getDocument().addDocumentListener(dirty);
        txtRegion.getDocument().addDocumentListener(dirty);
        txtCountry.getDocument().addDocumentListener(dirty);

        writeValueEOF();
    }

    public void activate() throws BasicException {

        List a = GeneralTypes.getTypesResponsible();
        m_CategoryModel = new ComboBoxValModel(a);
        m_jCategory.setModel(m_CategoryModel);

        List docTypeList = GeneralTypes.getListDocType();
        m_DocTypeModel = new ComboBoxValModel(docTypeList);
        m_DocType.setModel(m_DocTypeModel);
    }

    @Override
    public void refresh() {
    }

    @Override
    public void writeValueEOF() {
        m_oId = null;
        m_jTaxID.setText(null);
        m_jSearchkey.setText(null);
        m_jName.setText(null);
        m_CategoryModel.setSelectedKey(null);
        m_jNotes.setText(null);
        txtMaxdebt.setText(null);
        txtCurdebt.setText(null);
        txtCurdate.setText(null);
        m_jVisible.setSelected(false);
        jcard.setText(null);

        txtFirstName.setText(null);
        txtLastName.setText(null);
        txtEmail.setText(null);
        txtPhone.setText(null);
        txtPhone2.setText(null);
        txtFax.setText(null);

        txtAddress.setText(null);
        txtAddress2.setText(null);
        txtPostal.setText(null);
        txtCity.setText(null);
        txtRegion.setText(null);
        txtCountry.setText(null);

        m_jTaxID.setEnabled(false);
        m_jSearchkey.setEnabled(false);
        m_jName.setEnabled(false);
        m_jCategory.setEnabled(false);
        m_jNotes.setEnabled(false);
        txtMaxdebt.setEnabled(false);
        txtCurdebt.setEnabled(false);
        txtCurdate.setEnabled(false);
        m_jVisible.setEnabled(false);
        jcard.setEnabled(false);

        txtFirstName.setEnabled(false);
        txtLastName.setEnabled(false);
        txtEmail.setEnabled(false);
        txtPhone.setEnabled(false);
        txtPhone2.setEnabled(false);
        txtFax.setEnabled(false);

        txtAddress.setEnabled(false);
        txtAddress2.setEnabled(false);
        txtPostal.setEnabled(false);
        txtCity.setEnabled(false);
        txtRegion.setEnabled(false);
        txtCountry.setEnabled(false);

        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
    }

    @Override
    public void writeValueInsert() {

        txtFirstName.setText(null);
        txtLastName.setText(null);
        txtEmail.setText(null);
        txtPhone.setText(null);
        txtPhone2.setText(null);
        txtFax.setText(null);

        m_DocType.setEnabled(true);
        m_jTaxID.setEnabled(true);
        m_jSearchkey.setEnabled(true);
        m_jName.setEnabled(true);
        m_jCategory.setEnabled(true);
        m_jNotes.setEnabled(true);
        txtMaxdebt.setEnabled(true);
        txtCurdebt.setEnabled(true);
        txtCurdate.setEnabled(true);
        m_jVisible.setEnabled(true);
        jcard.setEnabled(true);

        txtFirstName.setEnabled(true);
        txtLastName.setEnabled(true);
        txtEmail.setEnabled(true);
        txtPhone.setEnabled(true);
        txtPhone2.setEnabled(true);
        txtFax.setEnabled(true);

        txtAddress.setEnabled(true);
        txtAddress2.setEnabled(true);
        txtPostal.setEnabled(true);
        txtCity.setEnabled(true);
        txtRegion.setEnabled(true);
        txtCountry.setEnabled(true);

        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
    }

    @Override
    public void writeValueDelete(Object value) {
        Object[] customer = (Object[]) value;
        m_oId = customer[0];
        m_jTaxID.setText((String) customer[1]);
        m_DocTypeModel.setSelectedKey(customer[2]);

        m_jSearchkey.setText((String) customer[3]);
        m_jName.setText((String) customer[4]);
        m_jNotes.setText((String) customer[5]);
        m_jVisible.setSelected(((Boolean) customer[6]));
        jcard.setText((String) customer[7]);
        txtMaxdebt.setText(Formats.CURRENCY.formatValue(customer[8]));
        txtCurdate.setText(Formats.DATE.formatValue(customer[9]));
        txtCurdebt.setText(Formats.CURRENCY.formatValue(customer[10]));

        txtFirstName.setText(Formats.STRING.formatValue(customer[11]));
        txtLastName.setText(Formats.STRING.formatValue(customer[12]));
        txtEmail.setText(Formats.STRING.formatValue(customer[13]));
        txtPhone.setText(Formats.STRING.formatValue(customer[14]));
        txtPhone2.setText(Formats.STRING.formatValue(customer[15]));
        txtFax.setText(Formats.STRING.formatValue(customer[16]));

        txtAddress.setText(Formats.STRING.formatValue(customer[17]));
        txtAddress2.setText(Formats.STRING.formatValue(customer[18]));
        txtPostal.setText(Formats.STRING.formatValue(customer[19]));
        txtCity.setText(Formats.STRING.formatValue(customer[20]));
        txtRegion.setText(Formats.STRING.formatValue(customer[21]));
        txtCountry.setText(Formats.STRING.formatValue(customer[22]));

        m_CategoryModel.setSelectedKey(customer[23]);

        m_jTaxID.setEnabled(false);
        m_DocType.setEnabled(false);
        m_jSearchkey.setEnabled(false);
        m_jName.setEnabled(false);
        m_jNotes.setEnabled(false);
        txtMaxdebt.setEnabled(false);
        txtCurdebt.setEnabled(false);
        txtCurdate.setEnabled(false);
        m_jVisible.setEnabled(false);
        jcard.setEnabled(false);

        txtFirstName.setEnabled(false);
        txtLastName.setEnabled(false);
        txtEmail.setEnabled(false);
        txtPhone.setEnabled(false);
        txtPhone2.setEnabled(false);
        txtFax.setEnabled(false);

        txtAddress.setEnabled(false);
        txtAddress2.setEnabled(false);
        txtPostal.setEnabled(false);
        txtCity.setEnabled(false);
        txtRegion.setEnabled(false);
        txtCountry.setEnabled(false);

        m_jCategory.setEnabled(false);

        jButton2.setEnabled(false);
        jButton3.setEnabled(false);
    }

    @Override
    public void writeValueEdit(Object value) {
        Object[] customer = (Object[]) value;
        m_oId = customer[0];
        m_jTaxID.setText((String) customer[1]);
        m_DocTypeModel.setSelectedKey(customer[2]);
        m_jSearchkey.setText((String) customer[3]);
        m_jName.setText((String) customer[4]);
        m_jNotes.setText((String) customer[5]);
        m_jVisible.setSelected(((Boolean) customer[6]));
        jcard.setText((String) customer[7]);
        txtMaxdebt.setText(Formats.CURRENCY.formatValue(customer[8]));
        txtCurdate.setText(Formats.DATE.formatValue(customer[9]));
        txtCurdebt.setText(Formats.CURRENCY.formatValue(customer[10]));

        txtFirstName.setText(Formats.STRING.formatValue(customer[11]));
        txtLastName.setText(Formats.STRING.formatValue(customer[12]));
        txtEmail.setText(Formats.STRING.formatValue(customer[13]));
        txtPhone.setText(Formats.STRING.formatValue(customer[14]));
        txtPhone2.setText(Formats.STRING.formatValue(customer[15]));
        txtFax.setText(Formats.STRING.formatValue(customer[16]));

        txtAddress.setText(Formats.STRING.formatValue(customer[17]));
        txtAddress2.setText(Formats.STRING.formatValue(customer[18]));
        txtPostal.setText(Formats.STRING.formatValue(customer[19]));
        txtCity.setText(Formats.STRING.formatValue(customer[20]));
        txtRegion.setText(Formats.STRING.formatValue(customer[21]));
        txtCountry.setText(Formats.STRING.formatValue(customer[22]));

        m_CategoryModel.setSelectedKey(customer[23]);

        m_jTaxID.setEnabled(true);
        m_DocType.setEnabled(true);
        m_jSearchkey.setEnabled(true);
        m_jName.setEnabled(true);
        m_jNotes.setEnabled(true);
        txtMaxdebt.setEnabled(true);
        txtCurdebt.setEnabled(true);
        txtCurdate.setEnabled(true);
        m_jVisible.setEnabled(true);
        jcard.setEnabled(true);

        txtFirstName.setEnabled(true);
        txtLastName.setEnabled(true);
        txtEmail.setEnabled(true);
        txtPhone.setEnabled(true);
        txtPhone2.setEnabled(true);
        txtFax.setEnabled(true);

        txtAddress.setEnabled(true);
        txtAddress2.setEnabled(true);
        txtPostal.setEnabled(true);
        txtCity.setEnabled(true);
        txtRegion.setEnabled(true);
        txtCountry.setEnabled(true);

        m_jCategory.setEnabled(true);

        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
    }

    @Override
    public Object createValue() throws BasicException {
        Object[] customer = new Object[24];
        customer[0] = m_oId == null ? UUID.randomUUID().toString() : m_oId;
        customer[1] = m_jTaxID.getText();
        customer[2] = m_DocTypeModel.getSelectedKey();
        customer[3] = m_jSearchkey.getText();
        customer[4] = m_jName.getText();
        customer[5] = m_jNotes.getText();
        customer[6] = m_jVisible.isSelected();
        customer[7] = Formats.STRING.parseValue(jcard.getText()); // Format to manage NULL values
        customer[8] = Formats.CURRENCY.parseValue(txtMaxdebt.getText(), 0.0);
        customer[9] = Formats.TIMESTAMP.parseValue(txtCurdate.getText()); // not saved
        customer[10] = Formats.CURRENCY.parseValue(txtCurdebt.getText()); // not saved
        customer[11] = Formats.STRING.parseValue(txtFirstName.getText());
        customer[12] = Formats.STRING.parseValue(txtLastName.getText());
        customer[13] = Formats.STRING.parseValue(txtEmail.getText());
        customer[14] = Formats.STRING.parseValue(txtPhone.getText());
        customer[15] = Formats.STRING.parseValue(txtPhone2.getText());
        customer[16] = Formats.STRING.parseValue(txtFax.getText());
        customer[17] = Formats.STRING.parseValue(txtAddress.getText());
        customer[18] = Formats.STRING.parseValue(txtAddress2.getText());
        customer[19] = Formats.STRING.parseValue(txtPostal.getText());
        customer[20] = Formats.STRING.parseValue(txtCity.getText());
        customer[21] = Formats.STRING.parseValue(txtRegion.getText());
        customer[22] = Formats.STRING.parseValue(txtCountry.getText());

        customer[23] = m_CategoryModel.getSelectedKey();

        return customer;
    }

    @Override
    public Component getComponent() {
        return this;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        txtCurdebt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        m_jTaxID = new javax.swing.JTextField();
        txtCurdate = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        m_jCategory = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jcard = new javax.swing.JTextField();
        m_jVisible = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        txtMaxdebt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        m_jName = new javax.swing.JTextField();
        m_jSearchkey = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        m_DocType = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        txtFax = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtPhone2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtCountry = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtAddress2 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtPostal = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtCity = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtRegion = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        m_jNotes = new javax.swing.JTextArea();

        setLayout(new java.awt.BorderLayout());

        txtCurdebt.setEditable(false);
        txtCurdebt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel4.setText(AppLocal.getIntString("label.visible")); // NOI18N

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/fileclose.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtCurdate.setEditable(false);
        txtCurdate.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setText(AppLocal.getIntString("label.custtaxcategory")); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/openbravo/images/color_line16.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText(AppLocal.getIntString("label.curdate")); // NOI18N

        jLabel2.setText(AppLocal.getIntString("label.curdebt")); // NOI18N

        jLabel5.setText(AppLocal.getIntString("label.card")); // NOI18N

        jLabel1.setText(AppLocal.getIntString("label.maxdebt")); // NOI18N

        jcard.setEditable(false);

        jLabel3.setText(AppLocal.getIntString("label.name")); // NOI18N

        txtMaxdebt.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel7.setText(AppLocal.getIntString("label.doctype")); // NOI18N

        jLabel8.setText(AppLocal.getIntString("label.searchkey")); // NOI18N

        jLabel10.setText(AppLocal.getIntString("label.doc")); // NOI18N

        jButton1.setText("Buscar en Padron Afip");
        jButton1.setToolTipText("Buscar en Padron Afip");
        jButton1.setName(""); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(m_jVisible, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(363, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m_jName, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(m_jSearchkey, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jcard, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(m_jCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(m_jTaxID, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(m_DocType, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtMaxdebt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCurdebt, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCurdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton3)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(m_DocType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jButton1))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel10))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jTaxID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(m_jSearchkey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(m_jName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jcard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(m_jCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(m_jVisible, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMaxdebt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCurdebt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCurdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel4, java.awt.BorderLayout.CENTER);

        jLabel14.setText(AppLocal.getIntString("label.fax")); // NOI18N

        jLabel15.setText(AppLocal.getIntString("label.lastname")); // NOI18N

        jLabel16.setText(AppLocal.getIntString("label.email")); // NOI18N

        jLabel17.setText(AppLocal.getIntString("label.phone")); // NOI18N

        jLabel18.setText(AppLocal.getIntString("label.phone2")); // NOI18N

        jLabel19.setText(AppLocal.getIntString("label.firstname")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhone2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(278, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtPhone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtFax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(AppLocal.getIntString("label.contact"), jPanel1); // NOI18N

        jLabel13.setText(AppLocal.getIntString("label.address")); // NOI18N

        jLabel20.setText(AppLocal.getIntString("label.country")); // NOI18N

        jLabel21.setText(AppLocal.getIntString("label.address2")); // NOI18N

        jLabel22.setText(AppLocal.getIntString("label.postal")); // NOI18N

        jLabel23.setText(AppLocal.getIntString("label.city")); // NOI18N

        jLabel24.setText(AppLocal.getIntString("label.region")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAddress2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtRegion, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(278, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtAddress2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(AppLocal.getIntString("label.location"), jPanel2); // NOI18N

        jScrollPane1.setViewportView(m_jNotes);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab(AppLocal.getIntString("label.notes"), jPanel3); // NOI18N

        add(jTabbedPane1, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.cardnew"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            jcard.setText("c" + StringUtils.getCardNumber());
            m_Dirty.setDirty(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (JOptionPane.showConfirmDialog(this, AppLocal.getIntString("message.cardremove"), AppLocal.getIntString("title.editor"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            jcard.setText(null);
            m_Dirty.setDirty(true);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        GeneralTypes typeDoc = (GeneralTypes) m_DocTypeModel.getSelectedItem();

        if (typeDoc != null) {

            if (typeDoc.getId() == GeneralTypes.CUIT) {

                if (m_jTaxID.getText().trim().length() > 0) {

                    try {
                        IPadron padron = Padron.getPadron(Long.valueOf(m_jTaxID.getText()));

                        m_jSearchkey.setText(null);
                        m_jName.setText(null);
                        m_CategoryModel.setSelectedKey(null);
                        m_jNotes.setText(null);
                        txtMaxdebt.setText(null);
                        txtCurdebt.setText(null);
                        txtCurdate.setText(null);
                        m_jVisible.setSelected(true);
                        jcard.setText(null);

                        txtFirstName.setText(null);
                        txtLastName.setText(null);
                        txtEmail.setText(null);
                        txtPhone.setText(null);
                        txtPhone2.setText(null);
                        txtFax.setText(null);

                        txtAddress.setText(null);
                        txtAddress2.setText(null);
                        txtPostal.setText(null);
                        txtCity.setText(null);
                        txtRegion.setText(null);
                        txtCountry.setText(null);

                        if (padron.getTipoPersona().equalsIgnoreCase("FISICA")) {
                            m_jName.setText(padron.getApellido() + " " + padron.getNombre());
                            m_jSearchkey.setText(padron.getApellido() + " " + padron.getNombre());
                        } else {
                            m_jName.setText(padron.getRazonSocial());
                            m_jSearchkey.setText(padron.getApellido() + " " + padron.getNombre());
                        }

                        txtFirstName.setText(padron.getApellido());
                        txtLastName.setText(padron.getNombre());
                        txtEmail.setText(padron.getEmail());

                        txtPhone.setText(padron.getTelefono());
                        txtPhone2.setText(padron.getTelefono2());
                        txtAddress.setText(padron.getDireccion());
                        txtAddress2.setText(padron.getDatoAdicional());
                        txtPostal.setText(padron.getCodPostal());
                        txtCity.setText(padron.getLocalidad());
                        txtRegion.setText(padron.getProvincia());

                    } catch (BasicException ex) {
                        new MessageInf(MessageInf.SGN_WARNING, ex.getLocalizedMessage()).show(this);
                    }
                } else {
                    new MessageInf(MessageInf.SGN_WARNING, "Debe introducir el nro de documento").show(this);
                }

            } else {
                new MessageInf(MessageInf.SGN_WARNING, "No hay tipo de Documento Selecionado").show(this);
            }
    }//GEN-LAST:event_jButton1ActionPerformed
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jcard;
    private javax.swing.JComboBox<String> m_DocType;
    private javax.swing.JComboBox m_jCategory;
    private javax.swing.JTextField m_jName;
    private javax.swing.JTextArea m_jNotes;
    private javax.swing.JTextField m_jSearchkey;
    private javax.swing.JTextField m_jTaxID;
    private javax.swing.JCheckBox m_jVisible;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAddress2;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtCountry;
    private javax.swing.JTextField txtCurdate;
    private javax.swing.JTextField txtCurdebt;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFax;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMaxdebt;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPhone2;
    private javax.swing.JTextField txtPostal;
    private javax.swing.JTextField txtRegion;
    // End of variables declaration//GEN-END:variables

}
