package com.entities;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "paper")
@TypeDefs({
        @TypeDef(
                name = "list-array",
                typeClass = ListArrayType.class
        )
})
public class PaperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "paper_author",
            joinColumns = {@JoinColumn(name = "paper_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "email", referencedColumnName = "email")})
    private List<AuthorEntity> authors;

    @OneToMany(mappedBy = "paper", fetch = FetchType.LAZY)
    private List<EvaluationEntity> reviews = new ArrayList<>();;



    @Type(type = "list-array")
    @Column(name = "topics")
    private List<String> topics;

    @Type(type = "list-array")
    @Column(name = "keywords")
    private List<String> keywords;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "paper_pcmember",
            joinColumns = {@JoinColumn(name = "paper_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "email", referencedColumnName = "email")})
    @MapKeyJoinColumn(name = "bid_id")
    private Map<BidEntity, CommitteeMemberEntity> bidders;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private SectionEntity section;


    public void addAuthor(AuthorEntity newAuthor) {
        if(this.authors == null)
        {
            this.authors = new ArrayList<>();
        }
        this.authors.add(newAuthor);
    }

    public Map<BidEntity, CommitteeMemberEntity> getBids() {
        return bidders;
    }

    public List<EvaluationEntity> getReviews() {
        return reviews;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaperEntity)) return false;
        PaperEntity that = (PaperEntity) o;
        return title.equals(that.title) &&
                fileName.equals(that.fileName) &&
                description.equals(that.description) &&
                topics.equals(that.topics) &&
                keywords.equals(that.keywords);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(title, fileName, description, topics, keywords);
        return result;
    }


}
