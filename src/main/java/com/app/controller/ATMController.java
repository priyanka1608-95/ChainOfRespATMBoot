package com.app.controller;
import com.app.model.DispenserResponse;
import com.app.service.ATMDispenseChain;
import com.app.service.Rupee2000Dispenser;
import com.app.service.Rupee50Dispenser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping("/atm")
public class ATMController
{

    @Autowired
    private Rupee2000Dispenser rupee2000Dispenser;

    @GetMapping("/getAmountFromAtm/{amt}")
    public ResponseEntity<?> getAmount(@PathVariable int amt)
    {


        DispenserResponse dispenserResponse = new DispenserResponse();
        if(amt %10 != 0) {

            dispenserResponse.setErrorCode("ERROR-001");
            dispenserResponse.setMessage("Entered amount is not in the multiples of 10.");
            dispenserResponse.setStatus("FAILED");

            return new ResponseEntity<>(dispenserResponse, HttpStatus.BAD_REQUEST);
        }
        else
        {

            ATMDispenseChain dispenser = rupee2000Dispenser;
            dispenserResponse=dispenser.dispense(amt,dispenserResponse);
            dispenserResponse.setStatus("SUCCESS");
            return new ResponseEntity<>(dispenserResponse,HttpStatus.OK);
        }

    }
}
