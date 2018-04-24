/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heps.db.param_list.excel2DB;

import heps.db.param_list.db.ejb.AttributeFacade;
import heps.db.param_list.db.ejb.DataFacade;
import heps.db.param_list.db.ejb.DevicetypeFacade;
import heps.db.param_list.db.ejb.ParameterFacade;
import heps.db.param_list.db.ejb.ReferenceFacade;
import heps.db.param_list.db.ejb.SubsystemFacade;
import heps.db.param_list.db.ejb.SystemFacade;
import heps.db.param_list.db.ejb.UnitFacade;
import heps.db.param_list.db.entity.Attribute;
import heps.db.param_list.db.entity.Data;
import heps.db.param_list.db.entity.Devicetype;
import heps.db.param_list.db.entity.Parameter;
import heps.db.param_list.db.entity.Reference;
import heps.db.param_list.db.entity.Subsystem;
import heps.db.param_list.db.entity.Unit;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import heps.db.param_list.comman.constants.DateFormat;

/**
 *
 * @author Lvhuihui
 */
public class Data2DB {

    ArrayList dataList;
    File file;

    public Data2DB(ArrayList dataList, File file) {
        this.dataList = dataList;
        this.file = file;
    }

    public Data2DB(File file) {
        this.dataList = null;
        this.file = file;
    }

    public Data2DB() {
        this.dataList = null;
        this.file = new File("");
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ArrayList getDataList() {
        return dataList;
    }

    public void setDataList(ArrayList dataList) {
        this.dataList = dataList;
    }

    public void sysData2DB() {
        int col = new ListTool(this.getDataList()).getColNum("system");
        ArrayList systemData = new ArrayList();
        Iterator it = this.getDataList().iterator();
        while (it.hasNext()) {
            ArrayList a = (ArrayList) it.next();
            systemData.add(a.get(col));
        }
        Iterator it1 = systemData.iterator();
        while (it1.hasNext()) {
            String s = (String) it1.next();
            if (s != null && "" != s) {
                if (!(s.toLowerCase().contains("system") || s.equals("系统"))) {
                    SystemFacade sf = new SystemFacade();
                    if (sf.getSystem(s) == null) {
                        sf.setSystem(s);
                    }
                }
            }
        }
    }

    public void subsysData2DB() {
        int col = new ListTool(this.getDataList()).getColNum("subsystem");
        ArrayList subsysData = new ArrayList();
        Iterator it = this.getDataList().iterator();
        while (it.hasNext()) {
            ArrayList a = (ArrayList) it.next();
            subsysData.add(a.get(col));
        }
        // System.out.println("+++++++++"+subsysData);
        Iterator it1 = subsysData.iterator();
        while (it1.hasNext()) {
            String s = (String) it1.next();
            if (s != null && "" != s) {
                if (!(s.toLowerCase().contains("subsystem") || s.equals("子系统"))) {
                    SubsystemFacade sf = new SubsystemFacade();
                    if (sf.getSubsystem(s) == null) {
                        sf.setSubsystem(s);
                    }
                }
            }
        }
    }

    public void devData2DB() {
        int col = new ListTool(this.getDataList()).getColNum("device");
        ArrayList devData = new ArrayList();
        Iterator it = this.getDataList().iterator();
        while (it.hasNext()) {
            ArrayList a = (ArrayList) it.next();
            devData.add(a.get(col));
        }
        // System.out.println("+++++++++"+subsysData);
        Iterator it1 = devData.iterator();
        while (it1.hasNext()) {
            String s = (String) it1.next();
            if (s != null && "" != s) {
                if (!(s.toLowerCase().contains("device") || s.equals("设备类"))) {
                    DevicetypeFacade sf = new DevicetypeFacade();
                    if (sf.getDevicetype(s) == null) {
                        sf.setDevicetype(s);
                    }
                }
            }
        }
    }

    public void attData2DB() {
        int col = new ListTool(this.getDataList()).getColNum("attribute");
        ArrayList attData = new ArrayList();
        Iterator it = this.getDataList().iterator();
        while (it.hasNext()) {
            ArrayList a = (ArrayList) it.next();
            attData.add(a.get(col));
        }
        Iterator it1 = attData.iterator();
        while (it1.hasNext()) {
            String s = (String) it1.next();
            if (s != null && "" != s) {
                if (!(s.toLowerCase().contains("attribute") || s.equals("性质"))) {
                    AttributeFacade sf = new AttributeFacade();
                    if (sf.getAttribute(s) == null) {
                        sf.setAttribute(s);
                    }
                }
            }
        }
    }

    public void unitData2DB() {
        int col = new ListTool(this.getDataList()).getColNum("unit");
        ArrayList unitData = new ArrayList();
        Iterator it = this.getDataList().iterator();
        while (it.hasNext()) {
            ArrayList a = (ArrayList) it.next();
            unitData.add(a.get(col));
        }
        Iterator it1 = unitData.iterator();
        while (it1.hasNext()) {
            String s = (String) it1.next();
            if (s != null && "" != s) {
                if (!(s.toLowerCase().contains("unit") || s.equals("单位"))) {
                    UnitFacade sf = new UnitFacade();
                    if (sf.getUnit(s) == null) {
                        sf.setUnit(s);
                    }
                }
            }
        }
    }

    public void refData2DB() {
        int titCol = new ListTool(this.getDataList()).getColNum("title");
        int autCol = new ListTool(this.getDataList()).getColNum("author");
        int pubCol = new ListTool(this.getDataList()).getColNum("publication");
        int urlCol = new ListTool(this.getDataList()).getColNum("url");
        int keyCol = new ListTool(this.getDataList()).getColNum("keyword");
        ArrayList<Reference> refData = new ArrayList();
        Iterator it = this.getDataList().iterator();
        while (it.hasNext()) {
            ArrayList a = (ArrayList) it.next();
            String title = a.get(titCol).toString();
            if (title != null && !"".equals(title)) {
                if (!title.toLowerCase().contains("title") && (!title.equals("参考文献名"))) {
                    ReferenceFacade rf = new ReferenceFacade();
                    if (rf.getReferenceByTitle(title) == null) {
                        rf.setReference(title, a.get(autCol).toString(), a.get(pubCol).toString(), a.get(urlCol).toString(), a.get(keyCol).toString());
                    }
                }
            }
        }
    }

    public void parmData2DB() {
        int nameCol = new ListTool(this.getDataList()).getColNum("parameter");
        int unitCol = new ListTool(this.getDataList()).getColNum("unit");
        int defCol = new ListTool(this.getDataList()).getColNum("definition");
        int dateCol = new ListTool(this.getDataList()).getColNum("date");
        int refCol = new ListTool(this.getDataList()).getColNum("title");
        ArrayList<Parameter> paraData = new ArrayList();

        ParameterFacade pf = new ParameterFacade();
        Iterator it = this.getDataList().iterator();
        while (it.hasNext()) {
            String name = null;
            String unit = null;
            String def = null;
            Date date_mod = null;
            String title = null;

            ArrayList a = (ArrayList) it.next();
            name = a.get(nameCol).toString();
            if (!name.toLowerCase().contains("parameter") && (!name.equals("参数名称"))) {
                if (!"".equals(a.get(unitCol))) unit = a.get(unitCol).toString();
                if (!"".equals(a.get(defCol)))  def = a.get(defCol).toString();                              
                if (!a.get(dateCol).equals("")) {
                    SimpleDateFormat format1 = new SimpleDateFormat(DateFormat.dateFormat);
                    try {
                        date_mod = format1.parse((String) a.get(dateCol));
                    } catch (ParseException ex) {
                        Logger.getLogger(Data2DB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (!"".equals(a.get(refCol)))  title = a.get(refCol).toString();
                if (pf.getParameterBy(name, unit, date_mod, def, title) == null) {
                    Unit u = new UnitFacade().getUnit(unit);
                    Reference r = new ReferenceFacade().getReferenceByTitle(title);
                    pf.setParameter(name, u, def, date_mod, r);
                }
            }
        }
    }

    public void data2DB() {
        int valueCol = new ListTool(this.getDataList()).getColNum("data");
        int sysCol = new ListTool(this.getDataList()).getColNum("system");
        int subCol = new ListTool(this.getDataList()).getColNum("subsystem");
        int devCol = new ListTool(this.getDataList()).getColNum("device");
        int attCol = new ListTool(this.getDataList()).getColNum("attribute");
        int parmCol = new ListTool(this.getDataList()).getColNum("parameter");
        int unitCol = new ListTool(this.getDataList()).getColNum("unit");
        int dateCol = new ListTool(this.getDataList()).getColNum("date");
        int defCol = new ListTool(this.getDataList()).getColNum("definition");
        int refCol = new ListTool(this.getDataList()).getColNum("title");

        String value = null;
        String system = null;
        String sub = null;
        String deviceType = null;
        Date date_mod = null;
        String att = null;
        String para = null;
        String definition = null;
        String unit = null;
        String title = null;

        ArrayList<Data> dList = new ArrayList();
        DataFacade pf = new DataFacade();

        Iterator it = this.getDataList().iterator();
        while (it.hasNext()) {
            ArrayList a = (ArrayList) it.next();
            value = (String) a.get(valueCol);          
            if (!value.toLowerCase().contains("data") && (!value.equals("参数值"))) {
                system = (String) a.get(sysCol);
                heps.db.param_list.db.entity.System sys = new SystemFacade().getSystem(system);
                sub = (String) a.get(subCol);
                Subsystem subsys = new SubsystemFacade().getSubsystem(sub);
                deviceType = (String) a.get(devCol);
                Devicetype device = new DevicetypeFacade().getDevicetype(deviceType);
                SimpleDateFormat format1 = new SimpleDateFormat(DateFormat.dateFormat);
                if (a.get(dateCol) != null && (!"".equals(a.get(dateCol)))) {
                    try {
                        date_mod = format1.parse((String) a.get(dateCol));
                        // System.out.println("*******"+date_mod);
                    } catch (ParseException ex) {
                        Logger.getLogger(Data2DB.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                att = (String) a.get(attCol);
                Attribute attribute = new AttributeFacade().getAttribute(att);
                para = (String) a.get(parmCol);
                unit = (String) a.get(unitCol);
                title = (String) a.get(refCol);
                definition = (String) a.get(defCol);
                Parameter parameter = new ParameterFacade().getParameterBy(para, unit, date_mod, definition, title);
                // System.out.println("++++++++"+date_mod);
                pf.setData(value, null, sys, subsys, device, date_mod, attribute, parameter, null, null, null);
            }
        }

    }

    public void allData2DB() {
        this.sysData2DB();
        this.subsysData2DB();
        this.devData2DB();
        this.attData2DB();
        this.unitData2DB();
        this.refData2DB();
        this.parmData2DB();
        this.data2DB();
    }

}
