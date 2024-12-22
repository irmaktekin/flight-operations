package irmak.tekin.flight.management.mapper;

import irmak.tekin.flight.management.dto.request.FlightUpdateRequestDTO;
import irmak.tekin.flight.management.dto.response.FlightResponseDTO;
import irmak.tekin.flight.management.entity.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    FlightResponseDTO toConvert(Flight flight);
    void updateFlight(@MappingTarget Flight flight, FlightUpdateRequestDTO flightUpdateRequestDto);

}
