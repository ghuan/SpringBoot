Ext.define('MyApp.store.Personnel', {
    extend: 'Ext.data.Store',

    alias: 'store.personnel',

    fields: [
        'xh', 'lb', 'gsyy', 'zd', 'rq'
    ],



    proxy: {
        type: 'ajax',
        url: '/getMedicalRecords?patientIndex='+document.getElementById("patientIndex").innerHTML,
        reader: {
            type: 'json',
            rootProperty: 'records'
        }
    },
    autoLoad: true
});
