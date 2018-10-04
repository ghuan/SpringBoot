package com.bsoft.wsClient;

import org.dom4j.DocumentException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Test {
	public static void main(String args[]) throws HdyyElectronicServiceException_Exception, IOException, ParseException, ClassNotFoundException{

		Map<String, Object> paramsMap = new LinkedHashMap<String, Object>();
		Date today = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String datestr = sf.format(today);
		paramsMap.put("PlatformID","ca36ba7989ec3875fc5069443b45abcb");
		paramsMap.put("MedicalOrgCode", "");
		paramsMap.put("RegisteredDoctorCode", "");
		paramsMap.put("RegisteredDoctorCardID", "");
		paramsMap.put("RegisteredYear", 2019);
		paramsMap.put("StartDate", "");
		paramsMap.put("EndDate", "");
		paramsMap.put("RegisteredState", "");
		paramsMap.put("PageIndex", 0);
		paramsMap.put("PageSize", 1000);

		SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		PreparedStatement preparedStatement2 = null;
		try {
			Map<String, Object> QYSJ1 = QysjwsUtil.doRequestWithXml(paramsMap);
			long num11 = Long.parseLong(QYSJ1.get("qysj")+"");
			int pageSize = (int)num11/1000 + (num11%1000 > 0 ? 1 : 0);
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@192.46.112.10:1521/orcl", "phis_show", "phis_show");
			preparedStatement = connection.prepareStatement("insert into hzyb_spba_test1 (jlxh, mzzy, jbbm, bazsl, sybasl, registerid, sfzh, brxm, PATIENTAGE, PATIENTSEX, PATIENTPHONE, PATIENTTELEPHONE, GRBH, PERSONALCARDNO, PERSONALCARDINNO, YBTYPE, BALB, BABH, REGISTEREDYEAR, KSRQ, ZZRQ, JGID, MEDICALORGNAME, YSGH, YSXM, YSSFZH, JBR, SPSSQ, REGISTEREDSTATE, ISRENEW, PREMEDICALORGCODE, PREMEDICALORGNAME, PREREGISTEREDDOCTORCODE, PREREGISTEREDDOCTORNAME, PERSONSCLASS, UPDATETIME, CANCELOPERATIONNAME, cxbz, CXSJ, BZ)  values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement1 = connection.prepareStatement("select max(jlxh) from hzyb_spba_test1");
			preparedStatement2 = connection.prepareStatement("select count(1) from hzyb_spba_test1 where REGISTERID = ? and REGISTEREDYEAR = ?");
			ResultSet rs = preparedStatement1.executeQuery();
			long jzxh = 0;
			while(rs.next()){//next方法返回的是布尔所以如果没有数据就不进行循环了
				jzxh = rs.getLong(1);
			}
			long begin = System.currentTimeMillis();
			 long end = 0;
			for(int num = 1;num<=pageSize;num++){

				try{
					paramsMap.put("PageIndex", num);
					Map<String, Object> QYSJ = QysjwsUtil.doRequestWithXml(paramsMap);

					if(QYSJ==null){
						preparedStatement.executeBatch();
						preparedStatement.clearBatch();

						end = System.currentTimeMillis();

						System.out.println("Time: " + (end - begin)); //569
						connection.commit();
					}
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
						int num2 = 0;
						while(rs1.next()){//next方法返回的是布尔所以如果没有数据就不进行循环了
							num2 = rs1.getInt(1);
						}
						if(num2 >= 1){
							System.out.println("重复");
							continue;
						}else {
							jzxh ++;
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

						preparedStatement.setLong(1, jzxh);
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
						preparedStatement.setTimestamp(36, "".equals(UpdateTime) ? null : new java.sql.Timestamp((sf1.parse(UpdateTime)).getTime()));
						preparedStatement.setString(37, CancelOperationName);
						preparedStatement.setInt(38, 0);
						preparedStatement.setTimestamp(39, "".equals(CancelOperationDate) ? null :  new java.sql.Timestamp((sf1.parse(CancelOperationDate)).getTime()));
						preparedStatement.setString(40, Remark);

						//"积攒" SQL
						preparedStatement.addBatch();

						//当 "积攒" 到一定程度, 就统一的执行一次. 并且清空先前 "积攒" 的 SQL
						if(((1000*(num-1))+(i+1)) % 300 == 0){
							preparedStatement.executeBatch();
							preparedStatement.clearBatch();
						}
						System.out.println(((1000*(num-1))+(i+1)));
					}
				}catch (Exception ex){
					num  = num - 1;
					continue;
				}

		}
			
			
		} catch (SQLException e) {
            e.printStackTrace();
            try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        } finally{
        	try {
				preparedStatement.close();
				preparedStatement1.close();
				preparedStatement2.close();
				connection.close();  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
        	
        }
	}
	
	private static String dealDate(String date){
		if("".equals(date) || date == null){
			return "";
		}
		String dateArr[] = date.split(" ");
		String flag = dateArr[0].indexOf("/") == -1 ? "-" : "/";
		String dayArr[] = dateArr[0].split(flag);
		dayArr[1] = dayArr[1].length() == 1 ? "0" + dayArr[1] : dayArr[1];
		dayArr[2] = dayArr[2].length() == 1 ? "0" + dayArr[2] : dayArr[2];
		if(dateArr.length == 1){
			
			return dayArr[0] + flag + dayArr[1] + flag + dayArr[2];
		}else {
			String hourArr[] = dateArr[1].split(":");
			hourArr[0] = hourArr[0].length() == 1 ? "0" + hourArr[0] : hourArr[0];
			hourArr[1] = hourArr[1].length() == 1 ? "0" + hourArr[1] : hourArr[1];
			hourArr[2] = hourArr[2].length() == 1 ? "0" + hourArr[2] : hourArr[2];
			return dayArr[0] + flag + dayArr[1] + flag + dayArr[2] + " " + hourArr[0] + ":" + hourArr[1] + ":" + hourArr[2];
		}
	}
	
	private static void jdbcDo() throws SQLException{
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.46.112.30:1521/orcl", "phis_show", "phis_show");  
		PreparedStatement preparedStatement = connection.prepareStatement("insert into addbatch_test(id,name) values(?,?)");  
        
        try {
            
            
            long begin = System.currentTimeMillis();
            for(int i = 0; i < 100000; i++){
                preparedStatement.setInt(1, i + 1);
                preparedStatement.setString(2, "name_" + i);
                
                //"积攒" SQL
                preparedStatement.addBatch();
                
                //当 "积攒" 到一定程度, 就统一的执行一次. 并且清空先前 "积攒" 的 SQL
                if((i + 1) % 300 == 0){
                    preparedStatement.executeBatch();
                    preparedStatement.clearBatch();
                }
            }
            
            //若总条数不是批量数值的整数倍, 则还需要再额外的执行一次.
            if(100000 % 300 != 0){
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
            
            long end = System.currentTimeMillis();
            
            System.out.println("Time: " + (end - begin)); //569
            
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        } finally{
        	preparedStatement.close();  
        	connection.close();  
        }
	}
}


//insert into hzyb_spba
//(jlxh,
// mzzy,
// jbbm,
// bazsl,
// sybasl,
// registerid,
// sfzh,
// brxm,
// PATIENTAGE,
// PATIENTSEX,
// PATIENTPHONE,
// PATIENTTELEPHONE,
// GRBH,
// PERSONALCARDNO,
// PERSONALCARDINNO,
// YBTYPE,
// BALB,
// BABH,
// REGISTEREDYEAR,
// KSRQ,
// ZZRQ,
// JGID,
// MEDICALORGNAME,
// YSGH,
// YSXM,
// YSSFZH,
// JBR,
// SPSSQ,
// REGISTEREDSTATE,
// ISRENEW,
// PREMEDICALORGCODE,
// PREMEDICALORGNAME,
// PREREGISTEREDDOCTORCODE,
// PREREGISTEREDDOCTORNAME,
// PERSONSCLASS,
// UPDATETIME,
// CANCELOPERATIONNAME,
// cxbz,
// CXSJ,
// BZ)  select 1408451 + JLXH  
//,1,' ',0,0,REGISTERID  
//,PATIENTCARDID
//,PATIENTNAME
//,PATIENTAGE
//,PATIENTSEX	
//,PATIENTPHONE	
//,PATIENTTELEPHONE	
//,PERSONALCODE	
//,PERSONALCARDNO	
//,PERSONALCARDINNO
//,PERSONALYBTYPE	
//,REGISTEREDTYPE	
//,REGISTEREDCODE		
//,REGISTEREDYEAR			
//,to_date(STARTDATE,'yyyy-MM-dd')
//,to_date(ENDDATE,'yyyy-MM-dd')
//,MEDICALORGCODE
//,MEDICALORGNAME		
//,REGISTEREDDOCTORCODE		
//,REGISTEREDDOCTORNAME			
//,REGISTEREDDOCTORCARDID			
//,OPERATIONNAME	
//,to_date(OPERATIONDATE,'yyyy-MM-dd hh24:mi:ss')		
//,REGISTEREDSTATE	
//,ISRENEW	
//,PREMEDICALORGCODE	
//,PREMEDICALORGNAME	
//,PREREGISTEREDDOCTORCODE		
//,PREREGISTEREDDOCTORNAME		
//,PERSONSCLASS		
//,to_date(UPDATETIME,'yyyy-MM-dd hh24:mi:ss')		
//,CANCELOPERATIONNAME	
//,0
//,CANCELOPERATIONDATE			
//,REMARK		from hzyb_spba_test
//
