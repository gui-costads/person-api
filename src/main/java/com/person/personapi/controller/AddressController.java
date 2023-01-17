package com.person.personapi.controller;

import com.person.personapi.dto.AddressCreateDTO;
import com.person.personapi.dto.AddressDTO;
import com.person.personapi.dto.PersonDTO;
import com.person.personapi.mapper.AddressMapper;
import com.person.personapi.mapper.PersonMapper;
import com.person.personapi.model.Address;
import com.person.personapi.model.Person;
import com.person.personapi.service.AddressService;
import com.person.personapi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class AddressController {
    private final PersonService personService;
    private final PersonMapper personMapper;
    private final AddressService addressService;
    private final AddressMapper addressMapper;

    public AddressController(PersonService personService, PersonMapper personMapper,
                             AddressService addressService, AddressMapper addressMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
        this.addressService = addressService;
        this.addressMapper = addressMapper;
    }

    @GetMapping("person/{id}/address")
    public ResponseEntity<List<AddressDTO>> findAllAddress(@PathVariable(name = "id") Long id) {
        Person person = personService.findById(id);
        List<Address> addressList = addressService.findAllAddress(person.getId());
        List<AddressDTO> addressDTOList = addressMapper.addressListToAddressListDto(addressList);
        return ResponseEntity.ok(addressDTOList);
    }

    @PostMapping("person/{id}/address")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AddressDTO> createAddress(@PathVariable(name = "id") Long id,
                                                    @RequestBody AddressCreateDTO addressCreateDTO) {
        AddressDTO addressDTO = addressMapper.addressCreateDtoToAddressDto(addressCreateDTO);
        Address addressCreated = addressService.createAddress(addressDTO);
        Address address = addressService.addAddressToPerson(id, addressCreated.getId());
        AddressDTO addressDtoCreated = addressMapper.addressToAddressDto(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(addressDtoCreated);
    }

    @PutMapping("person/{id}/address/{address_id}")
    public ResponseEntity<AddressDTO> updatedAddress(@PathVariable(name = "id") Long person_id,
                                                     @PathVariable(name = "address_id") Long address_id,
                                                     @RequestBody AddressCreateDTO addressCreateDTO) {
        AddressDTO addressDTO = addressMapper.addressCreateDtoToAddressDto(addressCreateDTO);
        Address addressUpdated = addressService.updateAddress(address_id, addressDTO);
        AddressDTO addressDtoUpdated = addressMapper.addressToAddressDto(addressUpdated);
        return ResponseEntity.ok(addressDtoUpdated);
    }

    @DeleteMapping("person/{id}/address/{address_id}")
    public ResponseEntity deleteAddress(@PathVariable(name = "id") Long id,
                                        @PathVariable(name = "address_id") Long address_id) {
        Address address = addressService.findByiD(address_id);
        addressService.deleteAddress(address.getId());
        return ResponseEntity.noContent().build();
    }
}
