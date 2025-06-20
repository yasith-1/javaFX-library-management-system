package util;

import org.modelmapper.ModelMapper;

public class Mapper {

    private static Mapper instance;
    private ModelMapper modelMapper;
    private Mapper() {
        modelMapper=new ModelMapper();
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public static Mapper getInstance() {
        return instance == null ? instance = new Mapper() : instance;
    }
}
