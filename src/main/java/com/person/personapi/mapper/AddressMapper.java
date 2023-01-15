package com.person.personapi.mapper;

import com.person.personapi.dto.AddressCreateDTO;
import com.person.personapi.dto.AddressDTO;
import com.person.personapi.model.Address;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public Address addressDtoToAddress(AddressDTO addressDTO) {
        return MODEL_MAPPER.map(addressDTO, Address.class);
    }

    public List<Address> addressListDtoToAddressList(List<AddressDTO> addressDTOList) {
        return addressDTOList.stream().map(this::addressDtoToAddress).collect(Collectors.toList());
    }

    public List<AddressDTO> addressListToAddressListDto(List<Address> addressList) {
        return addressList.stream().map(this::addressToAddressDto).collect(Collectors.toList());
    }

    public AddressDTO addressToAddressDto(Address address) {
        return MODEL_MAPPER.map(address, AddressDTO.class);
    }

    public AddressDTO addressCreateDtoToAddressDto(AddressCreateDTO addressCreateDTO) {
        return MODEL_MAPPER.map(addressCreateDTO, AddressDTO.class);
    }

}
