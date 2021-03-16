const express = require('express');
const router = express.Router();
const db = require('../../db');


router.get('/ListAvailableStations', (req, res) => {
    db.query('SELECT s.id, t.name FROM station s JOIN type t ON s.type_id = t.id LEFT JOIN reservations r ON s.id = r.station_id LEFT JOIN reservation_info ri on r.reservation_id = ri.id WHERE (r.station_id IS NULL) OR (ri.date != ?) OR (ri.date = ? AND (ri.time_end <= ? || ri.time_start >= ?));', 
    [req.body.date, req.body.date, req.body.time_start, req.body.time_end], (err, resultListAvailableStations) => {
        if(err){
            res.status(500).send(err.sqlMessage);
        }
        res.status(200).send(resultListAvailableStations);
    });
});

router.get('/:rID', (req, res) => {
    db.query('SELECT s.id, t.name as tname FROM station s JOIN type t ON s.type_id = t.id WHERE s.id NOT IN (SELECT s.id FROM station s JOIN type t ON s.type_id = t.id LEFT JOIN reservations r ON s.id = r.station_id LEFT JOIN reservation_info ri on r.reservation_id = ri.id WHERE (r.station_id IS NOT NULL) AND (ri.date = (SELECT reservation_info.date FROM reservation_info WHERE reservation_info.id = ?) AND NOT ((ri.time_end <= (SELECT reservation_info.time_start FROM reservation_info WHERE reservation_info.id = ?)) OR (ri.time_start >= (SELECT reservation_info.time_end FROM reservation_info WHERE reservation_info.id = ?)))))', 
    [req.params.rID, req.params.rID, req.params.rID, req.params.rID], (err, resultListAvailableStations) => {
        if(err){
            res.status(500).send(err.sqlMessage);
        }
        res.status(200).send(resultListAvailableStations);
    });
});












module.exports = router;