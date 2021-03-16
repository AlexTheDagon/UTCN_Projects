var mysql = require('mysql')

var connection = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  port: '3306',
  password: 'BogdanTudorAlex',
  database: 'internetcafe',
});

connection.connect(function (err){
    if(err) throw err;
});

module.exports = connection;