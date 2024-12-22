package irmak.tekin.flight.management.mapper;

import irmak.tekin.flight.management.dto.response.ReservationResponseDTO;
import irmak.tekin.flight.management.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")

public interface ReservationMapper {
    ReservationResponseDTO toConvert(Reservation reservation);

}
