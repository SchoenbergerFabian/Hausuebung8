package com.Infendro.ProducerConsumerProblem;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;

public class Producer implements Runnable {
    private final String name;
    private final Storage storage;
    private final int sleepTime;
    
    private final List<Integer> sent;
    private final int numberOfItems;

    public Producer(String name, Storage storage, int sleepTime, int numberOfItems) {
        this.name = name;
        this.storage = storage;
        this.sleepTime = sleepTime;
        this.numberOfItems = numberOfItems;
        sent = new ArrayList<>(numberOfItems);
    }

    public List<Integer> getSent() {
        return sent;
    }

    @Override
    public void run() {
        int number = 0;
        while(number < numberOfItems){
            try {
                if(storage.put(number)){
                    sent.add(number);
                    number++;
                }else{
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        storage.setProductionComplete();
    }
}
