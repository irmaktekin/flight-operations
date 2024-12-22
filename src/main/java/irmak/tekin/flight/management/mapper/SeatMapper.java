package irmak.tekin.flight.management.mapper;

import irmak.tekin.flight.management.dto.request.SeatUpdateRequestDTO;
import irmak.tekin.flight.management.dto.response.SeatResponseDTO;
import irmak.tekin.flight.management.entity.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface SeatMapper {
        SeatResponseDTO toConvert(Seat seat);
        void updateFlight(@MappingTarget Seat seat, SeatUpdateRequestDTO seatUpdateRequestDTO);


}
