package com.service;

import com.entities.AuthorEntity;
import com.entities.EvaluationEntity;
import com.entities.PaperEntity;
import com.mapper.EvaluationMapper;
import com.mapper.PaperMapper;
import com.model.PaperJson;
import com.repository.AuthorRepository;
import com.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;
    private EvaluationRepository evaluationRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional
    public List<PaperJson> getPapersOfAuthor(String email)
    {

        Optional<AuthorEntity> author = authorRepository.findById(email);
        if(author.isPresent())
        {

            return (author.get().getPapers() != null ? author.get().getPapers().stream()
                 .map(PaperMapper::entityToPaper)
                 .collect(Collectors.toList()) : new ArrayList<>());
        }
        else{
            return new ArrayList<>();
        }

    }

    @Transactional
    public List<PaperJson> getAcceptedPapersOfAuthor(String email)
    {

        Optional<AuthorEntity> author = authorRepository.findById(email);
        if(author.isPresent())
        {

            return (author.get().getPapers() != null ? author.get().getPapers().stream()
                    .filter(paper -> {

                        List<EvaluationEntity> evals = paper.getReviews();

                        if(evals.size()<=2)
                            return false;
                        for(EvaluationEntity evaluation : evals)
                        {
                            if(evaluation.getQualifier().getValue().contains("REJECT"))
                                return false;
                        }
                        return true;
                    })
                    .map(PaperMapper::entityToPaper)
                    .collect(Collectors.toList()) : new ArrayList<>());
        }
        else{
            return new ArrayList<>();
        }

    }
}
