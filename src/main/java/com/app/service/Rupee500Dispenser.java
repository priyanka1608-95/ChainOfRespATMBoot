package com.app.service;

import com.app.model.DispenserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Rupee500Dispenser implements ATMDispenseChain
{
    private ATMDispenseChain dispenser;

    @Autowired
    private Rupee200Dispenser rupee200Dispenser;

    public void setNextChain(ATMDispenseChain nextChain) {
        this.dispenser = nextChain;
    }

    public DispenserResponse dispense(int amt, DispenserResponse dispenserResponse)
    {

        if (amt >= 500) {
            int num = amt / 500;

            int remainder = amt % 500;

            log.info("Dispensing {} 500 note", num);

            dispenserResponse
                    .getNoteMap()
                    .put("500 Rupees", num);

            if (remainder != 0) {

                this.setNextChain(rupee200Dispenser);

                this.dispenser.dispense(remainder, dispenserResponse);

            }

        } else {

            this.setNextChain(rupee200Dispenser);

            this.dispenser.dispense(amt, dispenserResponse);

        }
        return dispenserResponse;
    }
}
