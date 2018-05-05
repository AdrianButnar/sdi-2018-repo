package ro.ubb.lab8.web.converter;


import ro.ubb.lab8.core.model.BaseEntity;
import ro.ubb.lab8.web.dto.BaseDto;


public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

