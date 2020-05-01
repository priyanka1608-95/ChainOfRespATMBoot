package com.app.service;

import com.app.model.DispenserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Rupee2000Dispenser implements ATMDispenseChain
{
    private ATMDispenseChain dispenser;

    @Autowired
    private Rupee500Dispenser rupee500Dispenser;

    public void setNextChain(ATMDispenseChain nextChain) {
        this.dispenser = nextChain;
    }

    public DispenserResponse dispense(int amt, DispenserResponse dispenserResponse)
    {

        if (amt >= 2000) {
            int num = amt / 2000;

            int remainder = amt % 2000;

            log.info("Dispensing {} 2000 note", num);

            dispenserResponse
                    .getNoteMap()
                    .put("2000 Rupees", num);

            if (remainder != 0) {

                this.setNextChain(rupee500Dispenser);

                this.dispenser.dispense(remainder, dispenserResponse);

            }

        } else {

            this.setNextChain(rupee500Dispenser);

            this.dispenser.dispense(amt, dispenserResponse);

        }
        return dispenserResponse;
    }
}
