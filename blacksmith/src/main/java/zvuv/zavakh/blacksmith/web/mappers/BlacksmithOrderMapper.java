package zvuv.zavakh.blacksmith.web.mappers;

import org.mapstruct.Mapper;
import zvuv.zavakh.blacksmith.domain.BlacksmithOrder;
import zvuv.zavakh.blacksmith.web.dto.BlacksmithOrderDto;

@Mapper(componentModel = "spring", uses = BlacksmithOrderMapper.class)
public interface BlacksmithOrderMapper {

    BlacksmithOrderDto convertFromEntityToDto(BlacksmithOrder blacksmithOrder);
    BlacksmithOrder convertFromDtoToEntity(BlacksmithOrderDto blacksmithOrderDto);
}
