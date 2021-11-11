package zvuv.zavakh.blacksmith.web.mappers;

import org.mapstruct.Mapper;
import zvuv.zavakh.blacksmith.domain.BlacksmithOrderProduct;
import zvuv.zavakh.blacksmith.web.dto.BlacksmithOrderProductDto;

@Mapper(componentModel = "spring")
public interface BlacksmithOrderProductMapper {

    BlacksmithOrderProductDto convertFromEntityToDto(BlacksmithOrderProduct blacksmithOrderProduct);
    BlacksmithOrderProduct convertFromDtoToEntity(BlacksmithOrderProductDto blacksmithOrderProductDto);
}
