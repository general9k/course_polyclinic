package ru.rodionov.polyclinic.service.facade;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.rodionov.polyclinic.model.Address;
import ru.rodionov.polyclinic.service.AddressService;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressFacade {

    private final AddressService addressService;

    public Address save(Address address) {
        return addressService.save(address);
    }
}
