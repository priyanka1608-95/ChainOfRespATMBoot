package com.app.service;

import com.app.model.DispenserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Rupee10Dispenser implements ATMDispenseChain
{
    @Override
    public void setNextChain(ATMDispenseChain nextChain) {

    }

    @Override
    public DispenserResponse dispense(int amt, DispenserResponse dispenserResponse) {

        int num=amt/10;
        log.info("Dispensing {} 10 note", num);

        dispenserResponse
                .getNoteMap()
                .put("10 Rupees", num);
        return dispenserResponse;
    }


}
