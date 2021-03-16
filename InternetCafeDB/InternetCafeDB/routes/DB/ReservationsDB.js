const express = require('express');
const router = express.Router();
const db = require('../../db');


// Insert into reservation based on ID of the reservationInfo and the station id
router.put('/AddReservation', (req, res) => {
    db.query('SELECT * FROM reservations WHERE reservation_id = ? AND station_id = ?;', [req.body.reservation_id, req.body.station_id], (err, resultCheckReservation) => {
        if(err){
            return res.status(400).send(err.sqlMessage);
        }
        if(typeof resultCheckReservation[0] !== 'undefined'){
            return res.status(400).send("Reservation already exist!");
        }
        
        db.query('INSERT INTO reservations (reservation_id, station_id) VALUES (?, ?);', [req.body.reservation_id, req.body.station_id], (err, resultAddReservation) =>{
            if(err){
                return res.status(400).send(err.sqlMessage);
            }
            return res.status(200).send(resultAddReservation);
        });
    });
});











module.exports = router;