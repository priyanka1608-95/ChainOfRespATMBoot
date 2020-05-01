package com.app.service;

import com.app.model.DispenserResponse;

public interface ATMDispenseChain {

    void setNextChain(ATMDispenseChain nextChain);

    DispenserResponse dispense(int amt, DispenserResponse dispenserResponse);
}

