package com.Infendro.ProducerConsumerProblem;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;


public class Consumer implements Runnable {
    private final String name;
    private final Storage storage;
    private final int sleepTime;
    
    private final List<Integer> received;
    private boolean running;

    public Consumer(String name, Storage storage, int sleepTime) {
        this.name = name;
        this.storage = storage;
        this.sleepTime = sleepTime;
        received = new ArrayList<>();
    }

    public List<Integer> getReceived() {
        return received;
    }

    @Override
    public void run() {
        while(!(storage.isProductionComplete()&&storage.getStoredCounter()==storage.getFetchedCounter())){
            Integer get = storage.get();
            if(get!=null){
                received.add(get);
            }
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

