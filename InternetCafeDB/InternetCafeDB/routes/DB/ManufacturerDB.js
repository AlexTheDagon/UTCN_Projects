const express = require('express');
const router = express.Router();
const db = require('../../db');


// function AddManufacturer(name, callback) {
//     db.query('INSERT INTO manufacturer (name) VALUES (?);', [name], (err, result) => {
//         if(err){
//             callback(err);
//         }
//         callback(false, result);
//     });
// };

// module.exports.AddManufacturer = AddManufacturer;


router.put('/AddManufacturer', (req, res) =>{
    db.query('INSERT INTO manufacturer (name) VALUES (?);',[req.body.name],(err, result) => {
        if(err){
            return res.status(500).send(err.sqlMessage);
        }
        res.status(200).send(result);
    });
});


router.get('/:name', (req, res) => {
    db.query("SELECT id FROM manufacturer WHERE name = ?;", [req.params.name],(err, result) =>{
      if(err){
        return res.status(500).send(err.sqlMessage);
      }
      res.status(200).send(result);
    });
});

module.exports = router;