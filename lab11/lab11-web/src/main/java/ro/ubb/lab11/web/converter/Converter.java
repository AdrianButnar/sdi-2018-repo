package ro.ubb.lab11.web.converter;


public interface Converter<Model, Dto> {
    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);
}
