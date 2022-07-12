package com.exercise.sequence.controller;

import com.exercise.sequence.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/alticci")
public class SequenceController {
    @Autowired
    SequenceService sequenceService;

    @GetMapping("/{n}")
    public ResponseEntity<Integer> getNumberOfSequenceByIndex(@PathVariable("n") Integer index){
        if(index < 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(sequenceService.getNumberOfSequenceByIndex(index), HttpStatus.OK);
        }
    }
    @GetMapping("complete/{n}")
    public ResponseEntity<List<Integer>> getSequenceByIndex(@PathVariable("n") Integer index){
        if(index < 0){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(sequenceService.geSequenceByIndex(index), HttpStatus.OK);
        }
    }
}
