package com.mapper;//package com.mapper;
//
//import com.entities.EvaluationEntity;
//import com.model.Evaluation;
//
//public class EvaluationMapper {
//
//    public static EvaluationEntity evaluationToEntity(Evaluation evaluation) {
//        return EvaluationEntity.builder()
//                .qualifier(evaluation.getQualifier())
//                .build();
//    }
//
//    public static Evaluation entityToEvaluation(EvaluationEntity entity) {
//        return Evaluation.builder()
////                .id(entity.getId())
//                .qualifier(entity.getQualifier())
//                .recommendation(entity.getRecommendation().getText())
//                .reviewer(entity.getReviewer().getEmail())
//                .paperId(entity.getPaper().getId())
//                .build();
//    }
//}