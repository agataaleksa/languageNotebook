package com.aleksa.langunotebook.controller.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.aleksa.langunotebook.model.MeaningEntity;

public class GamesResponseDTOFactory {

	private GamesResponseDTOFactory() {
    }

    public static GamesResponseDTO create(MeaningEntity meaningEntity) {
        return new GamesResponseDTO(meaningEntity.getMeaning(), meaningEntity.getWord().getWord());
    }

    public static List<GamesResponseDTO> create(List<MeaningEntity> meanings){
        List<GamesResponseDTO> dtos = new ArrayList<>();
        for (MeaningEntity meaning : meanings) {
        	dtos.add(create(meaning));
        }
        return dtos;
    }

}
