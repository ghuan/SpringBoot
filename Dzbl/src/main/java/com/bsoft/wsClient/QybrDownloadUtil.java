package com.bsoft.wsClient;

import org.apache.log4j.Logger;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by Administrator on 2018/9/13.
 */
public class QybrDownloadUtil {
    private static Logger logger = Logger.getLogger("QybrDownload");
    public static boolean qydrDownload(){
        logger.info("Qybr downloading...");
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> paramsMap = new LinkedHashMap<String, Object>();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day-1);//前一天
        String datestr = sf.format(calendar.getTime());


        //datestr = "2018-09-17";
        paramsMap.put("PlatformID","ca36ba7989ec3875fc5069443b45abcb");
        paramsMap.put("MedicalOrgCode", "");
        paramsMap.put("RegisteredDoctorCode", "");
        paramsMap.put("RegisteredDoctorCardID", "");
        paramsMap.put("RegisteredYear", Integer.parseInt(datestr.split("-")[0]) + 1);
        paramsMap.put("StartDate", datestr);
        paramsMap.put("EndDate", datestr);
        paramsMap.put("RegisteredState", "");
        paramsMap.put("PageIndex", 0);
        paramsMap.put("PageSize", 1000);

        Map<String, Object> QYSJ1 = null;
        for(int jj = 0;jj < 1000;jj++){
            try{
                //垃圾接口，有时候请求会超时
                QYSJ1 = QysjwsUtil.doRequestWithXml(paramsMap);
                break;
            }catch (Exception ex){
                continue;
            }
        }
        long num11 = Long.parseLong(QYSJ1.get("qysj")+"");
        int pageSize = (int)num11/1000 + (num11%1000 > 0 ? 1 : 0);
        logger.info("查询到记录条数："+num11);
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        boolean flag = true;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection("jdbc:oracle:thin:@192.46.112.10:1521/orcl", "phis_show", "phis_show");
            preparedStatement = connection.prepareStatement("insert into hzyb_spba (jlxh, mzzy, jbbm, bazsl, sybasl, registerid, sfzh, brxm, PATIENTAGE, PATIENTSEX, PATIENTPHONE, PATIENTTELEPHONE, GRBH, PERSONALCARDNO, PERSONALCARDINNO, YBTYPE, BALB, BABH, REGISTEREDYEAR, KSRQ, ZZRQ, JGID, MEDICALORGNAME, YSGH, YSXM, YSSFZH, JBR, SPSSQ, REGISTEREDSTATE, ISRENEW, PREMEDICALORGCODE, PREMEDICALORGNAME, PREREGISTEREDDOCTORCODE, PREREGISTEREDDOCTORNAME, PERSONSCLASS, UPDATETIME, CANCELOPERATIONNAME, cxbz, CXSJ, BZ)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement1 = connection.prepareStatement("select max(jlxh) from hzyb_spba");
            preparedStatement2 = connection.prepareStatement("select count(1) from hzyb_spba where REGISTERID = ? and REGISTEREDYEAR = ?");
            ResultSet rs = preparedStatement1.executeQuery();
            int jzxh = 0;
            while(rs.next()){//next方法返回的是布尔所以如果没有数据就不进行循环了
                jzxh = rs.getInt(1);
            }
            //			Statement statement = connection.createStatement();
//			ResultSet resultSet = null;
            long begin = System.currentTimeMillis();
            int failNum = 0;
            for(int numn = 1;numn<=pageSize;numn++){
                paramsMap.put("PageIndex", numn);
                Map<String, Object> QYSJ = null;
                for(int jj = 0;jj < 1000;jj++){
                    try{
                        //垃圾接口，有时候请求会超时
                        QYSJ = QysjwsUtil.doRequestWithXml(paramsMap);
                        break;
                    }catch (Exception ex){
                        continue;
                    }
                }

                if(QYSJ!=null){
                    List<Map<String, Object>> RegisterInfoList = (List<Map<String, Object>>) QYSJ.get("RegisterInfoList");

                    for (int i = 0; i < RegisterInfoList.size(); i++) {
                        List<Map<String, Object>> MedicalRegisterInfoDetail = (List<Map<String, Object>>) RegisterInfoList.get(i).get("MedicalRegisterInfoDetail");
                        Map<String, Object> par = new HashMap<String, Object>();
                        for (Map<String, Object> map : MedicalRegisterInfoDetail) {

                            par.putAll(map);
                        }
                        //查重
                        preparedStatement2.setString(1,par.get("RegisterId").toString());
                        preparedStatement2.setString(2,par.get("RegisteredYear").toString());
                        ResultSet rs1 = preparedStatement2.executeQuery();
                        int num = 0;
                        while(rs1.next()){//next方法返回的是布尔所以如果没有数据就不进行循环了
                            num = rs1.getInt(1);
                        }
                        if(num >= 1){
                            failNum ++;
                            continue;
                        }
                        String RegisterId = par.get("RegisterId").toString();
                        String PatientCardId = par.get("PatientCardId").toString();
                        String PatientName = par.get("PatientName").toString();
                        String PatientAge = par.get("PatientAge").toString();
                        String PatientSex = par.get("PatientSex").toString();
                        String PatientPhone = par.get("PatientPhone").toString();
                        String PatientTelePhone = par.get("PatientTelePhone").toString();
                        String PersonalCode = par.get("PersonalCode").toString();
                        String PersonalCardNo = par.get("PersonalCardNo").toString();
                        String PersonalCardInNo = par.get("PersonalCardInNo").toString();
                        String PersonalYBType = par.get("PersonalYBType").toString();
                        String RegisteredType = par.get("RegisteredType").toString();
                        String RegisteredCode = par.get("RegisteredCode").toString();
                        String RegisteredYear = par.get("RegisteredYear").toString();
                        String StartDate = par.get("StartDate").toString();
                        String EndDate = par.get("EndDate").toString();
                        String MedicalOrgCode = par.get("MedicalOrgCode").toString();
                        String MedicalOrgName = par.get("MedicalOrgName").toString();
//					resultSet = statement.executeQuery("select ygdm from gy_ygdm where sfzh = '"+par.get("RegisteredDoctorCardID").toString()+"' or ygxm = '"+par.get("RegisteredDoctorName").toString()+"'");
//					String a = "";
//					while (resultSet.next()) {
//						a = resultSet.getString(1);
//						break;
//					}

                        String RegisteredDoctorCode = par.get("RegisteredDoctorCode").toString();
                        String RegisteredDoctorName = par.get("RegisteredDoctorName").toString();
                        String RegisteredDoctorCardID = par.get("RegisteredDoctorCardID").toString();
                        String OperationName = par.get("OperationName").toString();
                        String OperationDate = par.get("OperationDate").toString();
                        String RegisteredState = par.get("RegisteredState").toString();
                        String IsRenew = par.get("IsRenew").toString();
                        String PreMedicalOrgCode = par.get("PreMedicalOrgCode").toString();
                        String PreMedicalOrgName = par.get("PreMedicalOrgName").toString();
                        String PreRegisteredDoctorCode = par.get("PreRegisteredDoctorCode").toString();
                        String PreRegisteredDoctorName = par.get("PreRegisteredDoctorName").toString();
                        String PersonsClass = par.get("PersonsClass").toString();
                        String UpdateTime = par.get("UpdateTime").toString();
                        String CancelOperationName = par.get("CancelOperationName").toString();
                        String CancelOperationDate = par.get("CancelOperationDate").toString();
                        String Remark = par.get("Remark").toString();

                        preparedStatement.setInt(1, jzxh + i + 1);
                        preparedStatement.setInt(2, 1);
                        preparedStatement.setString(3, " ");
                        preparedStatement.setInt(4, 0);
                        preparedStatement.setInt(5, 0);
                        preparedStatement.setString(6, RegisterId);
                        preparedStatement.setString(7, PatientCardId);
                        preparedStatement.setString(8, PatientName);
                        preparedStatement.setString(9, PatientAge);
                        preparedStatement.setInt(10, Integer.parseInt(PatientSex));
                        preparedStatement.setString(11, PatientPhone);
                        preparedStatement.setString(12, PatientTelePhone);
                        preparedStatement.setString(13, PersonalCode);
                        preparedStatement.setString(14, PersonalCardNo);
                        preparedStatement.setString(15, PersonalCardInNo);
                        preparedStatement.setInt(16, Integer.parseInt(PersonalYBType));
                        preparedStatement.setInt(17, Integer.parseInt(RegisteredType));
                        preparedStatement.setString(18, RegisteredCode);
                        preparedStatement.setString(19, RegisteredYear);
                        preparedStatement.setDate(20, new java.sql.Date(sf.parse(StartDate).getTime()));
                        preparedStatement.setDate(21, new java.sql.Date(sf.parse(EndDate).getTime()));
                        preparedStatement.setString(22, MedicalOrgCode);
                        preparedStatement.setString(23, MedicalOrgName);
                        preparedStatement.setString(24, RegisteredDoctorCode);
                        preparedStatement.setString(25, RegisteredDoctorName);
                        preparedStatement.setString(26, RegisteredDoctorCardID);
                        preparedStatement.setString(27, OperationName);
                        preparedStatement.setDate(28,  new java.sql.Date(sf.parse(OperationDate).getTime()));
                        preparedStatement.setInt(29, Integer.parseInt(RegisteredState));
                        preparedStatement.setInt(30, Integer.parseInt(IsRenew));
                        preparedStatement.setString(31, PreMedicalOrgCode);
                        preparedStatement.setString(32, PreMedicalOrgName);
                        preparedStatement.setString(33, PreRegisteredDoctorCode);
                        preparedStatement.setString(34, PreRegisteredDoctorName);
                        preparedStatement.setString(35, PersonsClass);
                        preparedStatement.setTimestamp(36,"".equals(UpdateTime) ? null :  new java.sql.Timestamp((sf1.parse(UpdateTime)).getTime()));
                        preparedStatement.setString(37, CancelOperationName);
                        preparedStatement.setInt(38, 0);
                        preparedStatement.setTimestamp(39, "".equals(CancelOperationDate) ? null :  new java.sql.Timestamp((sf1.parse(CancelOperationDate)).getTime()));
                        preparedStatement.setString(40, Remark);

                        //"积攒" SQL
                        preparedStatement.addBatch();

                    }
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();

                }
            }
            connection.commit();
            long end = System.currentTimeMillis();
            logger.info("成功下载"+(num11-failNum) + "条记录;过滤"+failNum+"条重复数据;花时" + (end - begin));

        } catch (SQLException e) {

            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                logger.error(e1);
                flag = false;
            }
            logger.error(e);
            flag = false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.error(e);
            flag = false;
        } catch (ParseException e) {
            e.printStackTrace();
            logger.error(e);
            flag = false;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            flag = false;
        } finally{
            try {
                preparedStatement.close();
                preparedStatement1.close();
                preparedStatement2.close();
                connection.close();
                return flag;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                logger.error(e);
                return false;
            }

        }



    }
}
