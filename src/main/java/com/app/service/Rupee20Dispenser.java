package com.app.service;

import com.app.model.DispenserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Rupee20Dispenser implements ATMDispenseChain
{
    private ATMDispenseChain dispenser;

    @Autowired
    Rupee10Dispenser rupee10Dispenser;

    public void setNextChain(ATMDispenseChain nextChain)
    {
        this.dispenser=nextChain;
    }

    public DispenserResponse dispense(int amt, DispenserResponse dispenserResponse)
    {
        if(amt>= 20)
        {
            int num = amt/20;
            int remainder = amt % 20;
            log.info("Dispensing {} 20 note", num);

            dispenserResponse
                    .getNoteMap()
                    .put("20 Rupees", num);
            if(remainder !=0) {

                this.setNextChain(rupee10Dispenser);

                this.dispenser.dispense(remainder, dispenserResponse);
            }
        }else
        {
            this.setNextChain(new Rupee10Dispenser());
            this.dispenser.dispense(amt, dispenserResponse);

        }
        return  dispenserResponse;

    }


}
