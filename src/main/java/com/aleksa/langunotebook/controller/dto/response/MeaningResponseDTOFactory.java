package com.aleksa.langunotebook.controller.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.aleksa.langunotebook.model.MeaningEntity;

public class MeaningResponseDTOFactory {
	
	private MeaningResponseDTOFactory() {
    }

    public static MeaningResponseDTO create(MeaningEntity meaningEntity) {
        return new MeaningResponseDTO(meaningEntity.getMeaning(), meaningEntity.getDescription());
    }
    
    public static List<MeaningResponseDTO> create(List<MeaningEntity> meanings){
        List<MeaningResponseDTO> dtos = new ArrayList<>();
        for (MeaningEntity meaning : meanings) {
        	dtos.add(create(meaning));
        }
        return dtos;
    }

}
