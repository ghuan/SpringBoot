package com.bsoft.schedule;

import com.bsoft.wsClient.QybrDownloadUtil;
import com.bsoft.wsClient.QysjwsUtil;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Administrator on 2018/9/11.
 */
@Component
public class QybrDownloadSchedule {
    private static Logger logger = Logger.getLogger("QybrDownload");

    /**
     * 凌晨下载前一天签约数据
     */
    //@Scheduled(cron = "0 05 0 * * ?")
    public void timer(){
        QybrDownloadUtil.qydrDownload();
    }
}
