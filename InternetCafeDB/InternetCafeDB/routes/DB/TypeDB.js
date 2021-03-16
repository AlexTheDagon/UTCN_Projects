const express = require('express');
const router = express.Router();
const db = require('../../db');


//Get the ID of the given type: PC, PS5, XBOX
router.get('/:name', (req, res) => {
    db.query("SELECT id FROM type WHERE name = ?", [req.params.name], (err, result) =>{
      if(err){
        res.status(500).send(err.sqlMessage);
        throw err;
      }
      
      res.status(200).send(result);
    });
});









module.exports = router;