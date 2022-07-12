package com.exercise.sequence.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SequenceService {
    public Integer getNumberOfSequenceByIndex(Integer index) {
        return Stream.iterate(new int[]{0, 1, 1}, a -> new int[]{a[1],a[2], a[0] + a[1]})
                .limit(index+1)
                .reduce((a, b) -> b)
                .get()[0];
    }
    public List<Integer> geSequenceByIndex(Integer index) {
        return Stream.iterate(new int[]{0, 1, 1}, a -> new int[]{a[1],a[2], a[0] + a[1]})
                .limit(index+1)
                .map(n -> n[0])
                .collect(Collectors.toList());
    }
}
