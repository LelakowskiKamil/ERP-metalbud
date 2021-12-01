package com.lelakowski.ERPMetalbud.pim.validation;

import com.lelakowski.ERPMetalbud.pim.domain.repository.AccountRepository;
import com.lelakowski.ERPMetalbud.pim.domain.repository.AddressRepository;
import com.lelakowski.ERPMetalbud.pim.web.command.CreateCustomerCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateCustomerValidator {

    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;

    public void validate(CreateCustomerCommand createCustomerCommand) {
        if (createCustomerCommand == null) throw new IllegalArgumentException(); //TODO notification

        validateAccountExisting(createCustomerCommand.getAccountId());
        validateAddressExisting(createCustomerCommand.getAddressId());
    }

    private void validateAccountExisting(Long accountId) {
        if (!accountRepository.existsById(accountId)) {
            throw new IllegalArgumentException("Nie ma takiego konta"); //TODO notification
        }
    }

    private void validateAddressExisting(Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            throw new IllegalArgumentException("Nie ma takiego adresu"); //TODO notification
        }
    }
}
