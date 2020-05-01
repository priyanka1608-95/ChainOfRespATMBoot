package com.app.service;

import com.app.model.DispenserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class Rupee50Dispenser implements ATMDispenseChain {

    private ATMDispenseChain dispenser;

    @Autowired
    private Rupee20Dispenser rupee20Dispenser;

    public void setNextChain(ATMDispenseChain nextChain) {
        this.dispenser = nextChain;
    }

    public DispenserResponse dispense(int amt, DispenserResponse dispenserResponse)
    {

        if (amt >= 50) {
            int num = amt / 50;

            int remainder = amt % 50;

            log.info("Dispensing {} 50 note", num);

            dispenserResponse
                    .getNoteMap()
                    .put("50 Rupees", num);

            if (remainder != 0) {

                this.setNextChain(rupee20Dispenser);

                this.dispenser.dispense(remainder, dispenserResponse);

            }

        } else {

            this.setNextChain(rupee20Dispenser);

            this.dispenser.dispense(amt, dispenserResponse);

        }
        return dispenserResponse;
    }
}
