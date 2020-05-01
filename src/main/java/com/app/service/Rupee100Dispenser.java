package com.app.service;

import com.app.model.DispenserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Rupee100Dispenser implements ATMDispenseChain {

    private ATMDispenseChain dispenser;

    @Autowired
    private Rupee50Dispenser rupee50Dispenser;

    public void setNextChain(ATMDispenseChain nextChain) {
        this.dispenser = nextChain;
    }

    public DispenserResponse dispense(int amt, DispenserResponse dispenserResponse)
    {

        if (amt >= 100) {
            int num = amt / 100;

            int remainder = amt % 100;

            log.info("Dispensing {} 100 note", num);

            dispenserResponse
                    .getNoteMap()
                    .put("100 Rupees", num);

            if (remainder != 0) {

                this.setNextChain(rupee50Dispenser);

                this.dispenser.dispense(remainder, dispenserResponse);

            }

        } else {

            this.setNextChain(rupee50Dispenser);

            this.dispenser.dispense(amt, dispenserResponse);

        }
        return dispenserResponse;
    }
}
