/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.openbravo.pos.types;

import com.openbravo.data.loader.IKeyed;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ariel
 */
public class GeneralTypes implements IKeyed {

    /*
    tipo de documentos
     */
    public static int CUIT = 80;
    public static int CUIL = 86;
    public static int CDI = 87;
    public static int LE = 89;
    public static int LC = 90;
    public static int Pasaporte = 94;
    public static int DNI = 96;
    public static int SinIidentificar = 99;

    /*
    Tipo de Responsable
     */
    public static int IVA_RESPONSABLE_INSCRIPTO = 1;
    public static int IVA_RESPONSABLE_NO_INSCRIPTO = 2;
    public static int IVA_NO_RESPONSABLE = 3;
    public static int IVA_SUJETO_EXENTO = 4;
    public static int CONSUMIDOR_FINAL = 5;
    public static int RESPONSABLE_MONOTRIBUTO = 6;
    public static int SUJETO_NO_CATEGORIZADO = 7;
    public static int PROVEEDOR_DEL_EXTERIOR = 8;
    public static int CLIENTE_DEL_EXTERIOR = 9;
    public static int IVA_LIBERADO_LEY_19640 = 10;
    public static int IVA_RESPONSABLE_INSCRIPTO_AGENTE_DE_PERCEPCION = 11;
    public static int PEQUEÑO_CONTRIBUYENTE_EVENTUAL = 12;
    public static int MONOTRIBUTISTA_SOCIAL = 13;
    public static int PEQUENO_CONTRIBUYENTE_EVENTUAL_SOCIAL = 14;

    private int id;
    private String name;

    public GeneralTypes(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object getKey() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    public static List getTypesResponsible() {
        ArrayList<TypeResposibleInfo> type_list = new ArrayList<>();

        type_list.add(new TypeResposibleInfo(1, "IVA Responsable Inscripto"));
        type_list.add(new TypeResposibleInfo(2, "IVA Responsable no Inscripto"));
        type_list.add(new TypeResposibleInfo(3, "IVA no Responsable"));
        type_list.add(new TypeResposibleInfo(4, "IVA Sujeto Exento"));
        type_list.add(new TypeResposibleInfo(5, "Consumidor Final"));
        type_list.add(new TypeResposibleInfo(6, "Responsable Monotributo"));
        type_list.add(new TypeResposibleInfo(7, "Sujeto no Categorizado"));
        type_list.add(new TypeResposibleInfo(8, "Proveedor del Exterior"));
        type_list.add(new TypeResposibleInfo(9, "Cliente del Exterior"));
        type_list.add(new TypeResposibleInfo(10, "IVA Liberado – Ley Nº 19.640"));
        type_list.add(new TypeResposibleInfo(11, "IVA Responsable Inscripto – Agente de Percepción"));
        type_list.add(new TypeResposibleInfo(12, "Pequeño Contribuyente Eventual"));
        type_list.add(new TypeResposibleInfo(13, "Monotributista Social"));
        type_list.add(new TypeResposibleInfo(14, "Pequeño Contribuyente Eventual Social"));

        return type_list;

    }

    public static List getListDocType() {
        ArrayList<GeneralTypes> type_list = new ArrayList<>();

        type_list.add(new GeneralTypes(CUIT, "CUIT"));
        type_list.add(new GeneralTypes(CUIL, "CUIL"));
        type_list.add(new GeneralTypes(CDI, "CDI"));
        type_list.add(new GeneralTypes(LE, "LE"));
        type_list.add(new GeneralTypes(LC, "LC"));
        type_list.add(new GeneralTypes(Pasaporte, "Pasaporte"));
        type_list.add(new GeneralTypes(DNI, "DNI"));
        type_list.add(new GeneralTypes(SinIidentificar, "Sin identificar/venta global diaria"));

        return type_list;
    }

}
