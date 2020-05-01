package com.app.service;

import com.app.model.DispenserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Rupee200Dispenser implements ATMDispenseChain
{
    private ATMDispenseChain dispenser;

    @Autowired
    private Rupee100Dispenser rupee100Dispenser;

    public void setNextChain(ATMDispenseChain nextChain) {
        this.dispenser = nextChain;
    }

    public DispenserResponse dispense(int amt, DispenserResponse dispenserResponse)
    {

        if (amt >= 200) {
            int num = amt / 200;

            int remainder = amt % 200;

            log.info("Dispensing {} 200 note", num);

            dispenserResponse
                    .getNoteMap()
                    .put("200 Rupees", num);

            if (remainder != 0) {

                this.setNextChain(rupee100Dispenser);

                this.dispenser.dispense(remainder, dispenserResponse);

            }

        } else {

            this.setNextChain(rupee100Dispenser);

            this.dispenser.dispense(amt, dispenserResponse);

        }
        return dispenserResponse;
    }
}
