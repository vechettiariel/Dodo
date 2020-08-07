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

package com.openbravo.data.loader;

import java.sql.*;
import java.util.*; 
import com.openbravo.basic.BasicException;
import com.openbravo.data.loader.JDBCSentence.JDBCDataResultSet;

public class MetaSentence extends JDBCSentence {
    
    private String m_sSentence;
    protected SerializerRead m_SerRead = null;
    protected SerializerWrite m_SerWrite = null;

    /** Creates a new instance of MetaDataSentence
     * @param s
     * @param sSentence
     * @param serwrite
     * @param serread */
    public MetaSentence(Session s, String sSentence, SerializerWrite serwrite, SerializerRead serread) {
        super(s);
        m_sSentence = sSentence;
        m_SerWrite = serwrite;
        m_SerRead = serread;
    }
    public MetaSentence(Session s, String sSentence, SerializerRead serread) {
        this(s, sSentence, null, serread);
    }
    
    private static class MetaParameter implements DataWrite {

        private final ArrayList m_aParams;

        /** Creates a new instance of MetaParameter */
        public MetaParameter() {
            m_aParams = new ArrayList();
        }
        
        @Override
        public void setDouble(int paramIndex, Double dValue) throws BasicException {
            throw new BasicException(LocalRes.getIntString("exception.noparamtype"));
        }
        @Override
        public void setBoolean(int paramIndex, Boolean bValue) throws BasicException {
            throw new BasicException(LocalRes.getIntString("exception.noparamtype"));
        }
        @Override
        public void setInt(int paramIndex, Integer iValue) throws BasicException {
            throw new BasicException(LocalRes.getIntString("exception.noparamtype"));
        }   
        @Override
        @SuppressWarnings("unchecked")
        public void setString(int paramIndex, String sValue) throws BasicException {
            ensurePlace(paramIndex - 1);
            m_aParams.set(paramIndex - 1, sValue);
        }
        @Override
        public void setTimestamp(int paramIndex, java.util.Date dValue) throws BasicException {
            throw new BasicException(LocalRes.getIntString("exception.noparamtype"));
        }
//        public void setBinaryStream(int paramIndex, java.io.InputStream in, int length) throws DataException {
//             throw new DataException("Param type not allowed");
//       }
        @Override
        public void setBytes(int paramIndex, byte[] value) throws BasicException {
             throw new BasicException(LocalRes.getIntString("exception.noparamtype"));
       }
        @Override
        public void setObject(int paramIndex, Object value) throws BasicException {
            setString(paramIndex, (value == null) ? null : value.toString());
        }

        public String getString(int index) {
            return (String) m_aParams.get(index);
        }    
        
        @SuppressWarnings("unchecked")
        private void ensurePlace(int i) {
            m_aParams.ensureCapacity(i);
            while (i >= m_aParams.size()){
                m_aParams.add(null);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public DataResultSet openExec(Object params) throws BasicException {
        
        closeExec();
        
        try {
            DatabaseMetaData db = m_s.getConnection().getMetaData();

            MetaParameter mp = new MetaParameter();               
            if (params != null) {
                // si m_SerWrite fuera null deberiamos cascar
                m_SerWrite.writeValues(mp, params);  
            }

            if (null == m_sSentence) {
                return null;
            } else             // Catalogs Has Schemas Has Objects
            // Lo generico de la base de datos
            return switch (m_sSentence) {
                case "getCatalogs" -> new JDBCDataResultSet(db.getCatalogs(), m_SerRead);
                case "getSchemas" -> new JDBCDataResultSet(db.getSchemas(), m_SerRead);
                case "getTableTypes" -> new JDBCDataResultSet(db.getTableTypes(), m_SerRead);
                case "getTypeInfo" -> new JDBCDataResultSet(db.getTypeInfo(), m_SerRead);
                case "getUDTs" -> new JDBCDataResultSet(db.getUDTs(mp.getString(0), mp.getString(1), null, null), m_SerRead);
                case "getSuperTypes" -> new JDBCDataResultSet(db.getSuperTypes(mp.getString(0), mp.getString(1), mp.getString(2)), m_SerRead);
                case "getAttributes" -> new JDBCDataResultSet(db.getAttributes(mp.getString(0), mp.getString(1), null, null), m_SerRead);
                case "getTables" -> new JDBCDataResultSet(db.getTables(mp.getString(0), mp.getString(1), null, null), m_SerRead);
                case "getSuperTables" -> new JDBCDataResultSet(db.getSuperTables(mp.getString(0), mp.getString(1), mp.getString(2)), m_SerRead);
                case "getTablePrivileges" -> new JDBCDataResultSet(db.getTablePrivileges(mp.getString(0), mp.getString(1), mp.getString(2)), m_SerRead);
                case "getBestRowIdentifier" -> new JDBCDataResultSet(db.getBestRowIdentifier(mp.getString(0), mp.getString(1), mp.getString(2), 0, true), m_SerRead);
                case "getPrimaryKeys" -> new JDBCDataResultSet(db.getPrimaryKeys(mp.getString(0), mp.getString(1), mp.getString(2)), m_SerRead);
                case "getColumnPrivileges" -> new JDBCDataResultSet(db.getColumnPrivileges(mp.getString(0), mp.getString(1), mp.getString(2), null), m_SerRead);
                case "getColumns" -> new JDBCDataResultSet(db.getColumns(mp.getString(0), mp.getString(1), mp.getString(2), null), m_SerRead);
                case "getVersionColumns" -> new JDBCDataResultSet(db.getVersionColumns(mp.getString(0), mp.getString(1), mp.getString(2)), m_SerRead);
                case "getIndexInfo" -> new JDBCDataResultSet(db.getIndexInfo(mp.getString(0), mp.getString(1), mp.getString(2), false, false), m_SerRead);
                case "getExportedKeys" -> new JDBCDataResultSet(db.getExportedKeys(mp.getString(0), mp.getString(1), mp.getString(2)), m_SerRead);
                case "getImportedKeys" -> new JDBCDataResultSet(db.getImportedKeys(mp.getString(0), mp.getString(1), mp.getString(2)), m_SerRead);
                case "getCrossReference" -> new JDBCDataResultSet(db.getCrossReference(mp.getString(0), mp.getString(1), mp.getString(2), null, null, null), m_SerRead);
                case "getProcedures" -> new JDBCDataResultSet(db.getProcedures(mp.getString(0), mp.getString(1), null), m_SerRead);
                case "getProcedureColumns" -> new JDBCDataResultSet(db.getProcedureColumns(mp.getString(0), mp.getString(1), mp.getString(2), null), m_SerRead);
                default -> null;
            }; // Los objetos por catalogo, esquema
            // Los tipos definidos por usuario
            // Los atributos
            // Las Tablas y sus objetos relacionados
            // Los procedimientos y sus objetos relacionados
            
        } catch (SQLException eSQL) {
            throw new BasicException(eSQL);
        }
    }  
    
    @Override
    public void closeExec() throws BasicException {
    }
    
    /**
     *
     * @return
     * @throws BasicException
     */
    @Override
    public DataResultSet moreResults() throws BasicException {
        return null;
    }
}
