/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.taxes;

import com.openbravo.data.loader.Datas;
import com.openbravo.data.loader.Session;
import com.openbravo.data.loader.TableDefinition;
import com.openbravo.format.Formats;
import com.openbravo.pos.forms.AppLocal;
import com.openbravo.pos.forms.BeanFactoryDataSingle;

/**
 *
 * @author ariel
 */
public class DataLogicTaxes  extends BeanFactoryDataSingle {

    private Session s;

    @Override
    public void init(Session s) {
         this.s = s;
    }
    
        public final TableDefinition getTableTaxes() {
        return new TableDefinition(s,
                "TAXES", new String[]{"ID", "NAME", "CATEGORY", "VALIDFROM", "CUSTCATEGORY", "PARENTID", "RATE", "RATECASCADE", "RATEORDER", "CODTAX"}, new String[]{"ID", AppLocal.getIntString("Label.Name"), AppLocal.getIntString("label.taxcategory"), AppLocal.getIntString("Label.ValidFrom"), AppLocal.getIntString("label.custtaxcategory"), AppLocal.getIntString("label.taxparent"), AppLocal.getIntString("label.dutyrate"), AppLocal.getIntString("label.cascade"), AppLocal.getIntString("label.order"), AppLocal.getIntString("label.order")}, new Datas[]{Datas.STRING, Datas.STRING, Datas.STRING, Datas.TIMESTAMP, Datas.STRING, Datas.STRING, Datas.DOUBLE, Datas.BOOLEAN, Datas.INT, Datas.INT}, new Formats[]{Formats.STRING, Formats.STRING, Formats.STRING, Formats.TIMESTAMP, Formats.STRING, Formats.STRING, Formats.PERCENT, Formats.BOOLEAN, Formats.INT, Formats.STRING}, new int[]{0});
    }

    public final TableDefinition getTableTaxCustCategories() {
        return new TableDefinition(s,
                "TAXCUSTCATEGORIES", new String[]{"ID", "NAME"}, new String[]{"ID", AppLocal.getIntString("Label.Name")}, new Datas[]{Datas.STRING, Datas.STRING}, new Formats[]{Formats.STRING, Formats.STRING}, new int[]{0});
    }

    public final TableDefinition getTableTaxCategories() {
        return new TableDefinition(s,
                "TAXCATEGORIES", new String[]{"ID", "NAME"}, new String[]{"ID", AppLocal.getIntString("Label.Name")}, new Datas[]{Datas.STRING, Datas.STRING}, new Formats[]{Formats.STRING, Formats.STRING}, new int[]{0});
    }

 
    
}
