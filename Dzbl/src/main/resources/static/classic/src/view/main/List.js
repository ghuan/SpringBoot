/**
 * This view is an example list of people.
 */
Ext.define('MyApp.view.main.List', {
    extend: 'Ext.grid.Panel',
    xtype: 'mainlist',

    requires: [
        'MyApp.store.Personnel'
    ],

    title :'<div class="head_bg"><div id="emr_header"><div class="TopMessage"><div class="fleft" style="width: 1009px;"><div class="ehrviewClear"><h2 class="fleft">'+document.getElementById("patientName").innerHTML+'</h2><div class="fleft" style="margin:0px 80px 0px 0px">&nbsp;</div><div id="recordIcoDiv_ext-gen336" class="ehrviewInfo_ico"></div><div style="margin:4px 0px 0px 0px"><label class="fleft"></label></div></div><ul class="mdetail"><li><p><label>性&nbsp;&nbsp;别：</label>'+document.getElementById("brxb").innerHTML+'&nbsp;&nbsp;</p><p><label>出生日期：</label>'+document.getElementById("csny").innerHTML+'</p></li><li><p><label><font color="red">年&nbsp;&nbsp;龄</font>：</label>'+document.getElementById("age").innerHTML+'</p><p><label>身份证号：</label>'+document.getElementById("sfzh").innerHTML+'</p></li><li><p><label>电&nbsp;&nbsp;&nbsp;&nbsp;话：</label>'+document.getElementById("dhhm").innerHTML+'</p><p><label>住&nbsp;&nbsp;&nbsp;&nbsp;址：</label><span title="'+document.getElementById("xzz").innerHTML+'">'+document.getElementById("xzz").innerHTML+'</span></p></li></ul></div></div></div></div>',

    store: {
        type: 'personnel'
    },

    columns: [
        { text: '序号',  dataIndex: 'xh' },
        { text: '类别', dataIndex: 'lb', flex: 1 },
        { text: '归属医院', dataIndex: 'gsyy', flex: 1 },
		{ text: '诊断', dataIndex: 'zd', flex: 1 },
		{ text: '日期', dataIndex: 'rq', flex: 1 }
    ],

    listeners: {
        rowclick: 'onItemSelected'
    }
});
