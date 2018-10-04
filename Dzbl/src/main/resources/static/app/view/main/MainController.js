/**
 * This class is the controller for the main view for the application. It is specified as
 * the "controller" of the Main view class.
 *
 * TODO - Replace this content of this view to suite the needs of your application.
 */
Ext.define('MyApp.view.main.MainController', {
    extend: 'Ext.app.ViewController',

    alias: 'controller.main',

    onItemSelected: function (a, record) {
        if(record.data.lb == '门诊'){
            window.open('http://192.168.10.158:8080/EMRView/main.html?mzhm='+record.data.visited+'&uid=system&pwd=c5b11e1b20b7f2562f2359f25a4a0ab9','_blank');
        }else {
            window.open('http://192.168.10.158:8080/EMRView/main.html?zyhm='+record.data.visited+'&uid=system&pwd=c5b11e1b20b7f2562f2359f25a4a0ab9','_blank');
        }

    },

    onConfirm: function (choice) {
        if (choice === 'yes') {
            //
        }
    }
});
