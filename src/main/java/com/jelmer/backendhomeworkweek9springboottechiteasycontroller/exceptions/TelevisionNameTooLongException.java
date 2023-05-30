package com.jelmer.backendhomeworkweek9springboottechiteasycontroller.exceptions;

public class TelevisionNameTooLongException extends RuntimeException {

    public TelevisionNameTooLongException(){
        super();
//        met super spreekt ie de runtimeexeption aan.

    }
    public TelevisionNameTooLongException(String message){
        super(message);

    }



}
