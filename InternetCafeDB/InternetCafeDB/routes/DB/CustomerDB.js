const express = require('express');
const router = express.Router();
const db = require('../../db');

// Insert new customer
router.put('/CreateCustomer', (req, res) => {
    db.query('INSERT INTO customer (name, email, phone) VALUES(?, ?, ?);', [req.body.name, req.body.email, req.body.phone], (err, resultCreateCustomer) => {
        if(err){
            return res.status(500).send(err.sqlMessage);
        }
        res.status(200).send(resultCreateCustomer);
    });
});

// Get customer by Name phone and email
router.get('/GetCustomer', (req, res) =>{
    
    db.query('SELECT * FROM customer WHERE customer.name = ? AND customer.email = ? AND customer.phone = ?;', [req.query.name, req.query.email, req.query.phone], (err, resultGetCustomer) =>{
        if(err){
            return res.status(500).send(err.sqlMessage);
        }
        res.status(200).send(resultGetCustomer);
    });
});





module.exports = router;