package ro.ubb.lab8.web.converter;


import ro.ubb.lab8.core.model.BaseEntity;
import ro.ubb.lab8.web.dto.BaseDto;


public interface Converter<Model, Dto> {
    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);
}
