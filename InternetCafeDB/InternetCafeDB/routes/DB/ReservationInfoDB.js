const express = require('express');
const router = express.Router();
const db = require('../../db');

// Create a reservation_info based on [customer_id, date, time_start, time_end]
router.put('/CreateReservationInfo', (req, res) => {
    db.query('SELECT * FROM reservation_info WHERE customer_id = ? AND date = ? AND time_start = ? AND time_end = ?', [req.body.customer_id, req.body.date, req.body.time_start, req.body.time_end], (err, resultCheckReservationInfo) => {
        if(err){
            return res.status(500).send(err.sqlMessage);
        }
        if(typeof resultCheckReservationInfo[0] !== 'undefined'){
            return res.status(500).send("Tried to insert a duplicate");
        }
        db.query('INSERT INTO reservation_info (customer_id, date, time_start, time_end) VALUES (?, ?, ?, ?);', [req.body.customer_id, req.body.date, req.body.time_start, req.body.time_end], (err, resultCreateReservationInfo) => {
            if(err){
                return res.status(500).send(err.sqlMessage);
            };
            res.status(200).send(resultCreateReservationInfo);
        });
    });
});


// Get reservation_info based on [customer_id, date, time_start, time_end]
router.get('/GetReservationInfo', (req, res) => {
    db.query('SELECT * FROM reservation_info WHERE customer_id = ? AND date = ? AND time_start = ? AND time_end = ?', [req.body.customer_id, req.body.date, req.body.time_start, req.body.time_end], (err, resultGetReservationInfo) => {
        if(err){
            return res.status(500).send(err.sqlMessage);
        }
        if(typeof resultGetReservationInfo[0] === 'undefined'){
            return res.status(500).send("No record found!");
        }
        
        res.status(200).send(resultGetReservationInfo);
    });
});







module.exports = router;